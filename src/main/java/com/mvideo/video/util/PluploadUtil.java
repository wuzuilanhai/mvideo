package com.mvideo.video.util;

import com.mvideo.common.util.MD5Util;
import com.mvideo.configuration.dal.po.Configuration;
import com.mvideo.configuration.service.IConfigurationService;
import com.mvideo.video.constant.VideoConstants;
import com.mvideo.video.dal.dao.VideoCheckMapper;
import com.mvideo.video.dal.dao.VideoMapper;
import com.mvideo.video.dal.dao.VideoStateMapper;
import com.mvideo.video.dal.po.Video;
import com.mvideo.video.dal.po.VideoCheck;
import com.mvideo.video.dal.po.VideoState;
import com.mvideo.video.dto.CheckUpload;
import com.mvideo.video.dto.Plupload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Plupload 模块
 * <p>
 * Created by admin on 16/12/7.
 */
@Component
public class PluploadUtil implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoCheckMapper videoCheckMapper;

    @Autowired
    private VideoStateMapper videoStateMapper;

    @Autowired
    private IConfigurationService configurationService;

    @Value("${ffmpeg.video.suffix}")
    private String convertVideoSuffix;

    @Value("${ffmpeg.video.thumbnail.img.suffix}")
    private String thumbnailImgSuffix;

    @Autowired
    private VideoUtil videoUtil;

    private String projectUrl;

    public void upload(Plupload plupload, File pluploadDir, HttpServletRequest request) {
        projectUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String fileName = plupload.getName();//在服务器内生成唯一文件名,TODO
        upload(plupload, pluploadDir, fileName);
    }

    @Transactional
    private void upload(Plupload plupload, File pluploadDir, String fileName) {

        int chunks = plupload.getChunks();//用户上传文件被分隔的总块数
        int nowChunk = plupload.getChunk();//当前块，从0开始

        //这里Request请求类型的强制转换可能出错，配置文件中向SpringIOC容器引入multipartResolver对象即可。
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) plupload.getRequest();
        //调试发现map中只有一个键值对
        MultiValueMap<String, MultipartFile> map = multipartHttpServletRequest.getMultiFileMap();

        if (map != null) {
            try {
                Iterator<String> iterator = map.keySet().iterator();
                while (iterator.hasNext()) {

                    String key = iterator.next();
                    List<MultipartFile> multipartFileList = map.get(key);

                    for (MultipartFile multipartFile : multipartFileList) {//循环只进行一次

                        plupload.setMultipartFile(multipartFile);//手动向Plupload对象传入MultipartFile属性值
                        File targetFile = new File(pluploadDir + "/" + fileName);//新建目标文件，只有被流写入时才会真正存在
                        if (plupload.getChunk() == 0) {
                            //保存视频状态
                            VideoState videoState = new VideoState();
                            videoState.setName(VideoConstants.Video.STATE_01.getName());
                            videoState.setLevel(VideoConstants.Video.STATE_01.getLevel());
                            videoState.setVideoPath(targetFile.getPath());
                            videoStateMapper.insert(videoState);
                        }
                        if (chunks > 1) {//用户上传资料总块数大于1，要进行合并
                            String prefixName = pluploadDir.getPath() + "/" + plupload.getName() + "_tmp_";
                            String tmpFileName = prefixName + plupload.getChunk();
                            File tempFile = new File(tmpFileName);
                            //保存每一块的内容在临时文件中
                            savePluploadFile(multipartFile.getInputStream(), tempFile, false);
                            //更新数据库表video_check的数据
                            VideoCheck videoCheck = new VideoCheck();
                            videoCheck.setCurrentChunk(plupload.getChunk());
                            videoCheck.setUserId(Integer.parseInt(multipartHttpServletRequest.getParameter("userId")));

                            CheckUpload checkUpload=new CheckUpload();
                            checkUpload.setUserId(Integer.parseInt(multipartHttpServletRequest.getParameter("userId")));
                            checkUpload.setFilename(prefixName);
                            List<VideoCheck> findVideoChecks = videoCheckMapper.selectByTmpFileNameLimitOne(checkUpload);
                            if (findVideoChecks.size() == 0) {
                                videoCheck.setTmpFileName(tmpFileName);
                                videoCheckMapper.insert(videoCheck);
                            } else {
                                videoCheck.setId(findVideoChecks.get(0).getId());
                                videoCheckMapper.update(videoCheck);
                            }

                            if (chunks - nowChunk == 1) {//全部块已经上传完毕，合并所有块文件
                                for (int i = 0; i < chunks; i++) {
                                    File srcFile = new File(prefixName + i);
                                    FileInputStream fi = new FileInputStream(srcFile);
                                    savePluploadFile(fi, targetFile, i == 0 ? false : true);
                                    srcFile.delete();
                                }
                                //每当文件上传完毕，将上传信息插入数据库
                                updateVideoState(multipartHttpServletRequest, targetFile);

                                //添加数据到延迟队列，开始转码
                                convertQueue.add(new DelayQueueNode(targetFile, System.currentTimeMillis() + 1000));
                            }
                        } else {
                            //只有一块，就直接拷贝文件内容
                            multipartFile.transferTo(targetFile);

                            //每当文件上传完毕，将上传信息插入数据库
                            updateVideoState(multipartHttpServletRequest, targetFile);

                            //添加数据到延迟队列，开始转码
                            convertQueue.add(new DelayQueueNode(targetFile, System.currentTimeMillis() + VideoConstants.CONVERT_TIME_DELAY));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateVideoState(MultipartHttpServletRequest multipartHttpServletRequest, File targetFile) throws Exception {
        VideoState videoState = videoStateMapper.selectByVideoPath(targetFile.getPath());
        videoState.setLevel(VideoConstants.Video.STATE_02.getLevel());
        videoState.setName(VideoConstants.Video.STATE_02.getName());
        videoStateMapper.update(videoState);

        Video video = new Video();
        video.setUserId(Integer.parseInt(multipartHttpServletRequest.getParameter("userId")));
        video.setName(multipartHttpServletRequest.getParameter("videoRemark"));
        video.setRemark(multipartHttpServletRequest.getParameter("videoRemark"));
        video.setCategoryId(Integer.parseInt(multipartHttpServletRequest.getParameter("categoryId")));
        video.setIntro(multipartHttpServletRequest.getParameter("videoIntro"));
        video.setIslive(VideoConstants.IS_NOT_LIVE);
        video.setLastUpdateTime(new Date());
        video.setState(VideoConstants.VideoAudit.AUDIT_01.getAuditState());
        video.setOriurl(targetFile.getPath());
        videoMapper.insert(video);
    }

    private void savePluploadFile(InputStream inputStream, File tempFile, boolean flag) {
        OutputStream outputStream = null;
        try {
            if (flag == false) {
                //从头写入
                outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            } else {
                //从末端写入
                outputStream = new BufferedOutputStream(new FileOutputStream(tempFile, true));
            }
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = (inputStream.read(bytes))) > 0) {
                outputStream.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BlockingQueue<DelayQueueNode<File>> convertQueue = new DelayQueue();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Map<String, Configuration> configurationMap = new HashMap<>();
            try {
                Configuration transcoder_vcodec = configurationService.getConfigurationByName("transcoder_vcodec");
                Configuration transcoder_bv = configurationService.getConfigurationByName("transcoder_bv");
                Configuration transcoder_framerate = configurationService.getConfigurationByName("transcoder_framerate");
                Configuration transcoder_acodec = configurationService.getConfigurationByName("transcoder_acodec");
                Configuration transcoder_ba = configurationService.getConfigurationByName("transcoder_ba");
                Configuration transcoder_ar = configurationService.getConfigurationByName("transcoder_ar");
                Configuration transcoder_ab = configurationService.getConfigurationByName("transcoder_ab");
                Configuration transcoder_ac = configurationService.getConfigurationByName("transcoder_ac");
                Configuration transcoder_qscale = configurationService.getConfigurationByName("transcoder_qscale");
                configurationMap.put("vcodec", transcoder_vcodec);
                configurationMap.put("bv", transcoder_bv);
                configurationMap.put("framerate", transcoder_framerate);
                configurationMap.put("acodec", transcoder_acodec);
                configurationMap.put("ba", transcoder_ba);
                configurationMap.put("ar", transcoder_ar);
                configurationMap.put("ab", transcoder_ab);
                configurationMap.put("ac", transcoder_ac);
                configurationMap.put("qscale", transcoder_qscale);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    //开始转码
                    File file = convertQueue.take().getValue();
                    File convertDir = new File("converts");
                    if (!convertDir.exists()) {
                        convertDir.mkdirs();
                    }
                    File thumbnails = new File("thumbnails");
                    if (!thumbnails.exists()) {
                        thumbnails.mkdirs();
                    }
                    String name = MD5Util.md5(file.getName().substring(0, file.getName().lastIndexOf(".")) + new Date().getTime());
                    videoUtil.process(file.getPath(), convertDir + "/" + name + convertVideoSuffix, thumbnails + "/" + name + thumbnailImgSuffix, configurationMap, projectUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //springboot容器启动后才执行这个方法
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(runnable).start();
    }

    private static class DelayQueueNode<T> implements Delayed {

        private T v;
        private long timeout;

        public DelayQueueNode(T v, long time) {
            this.v = v;
            this.timeout = time;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(timeout - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed other) {
            if (other == this)
                return 0;
            if (other instanceof DelayQueueNode) {
                DelayQueueNode x = (DelayQueueNode) other;
                long diff = timeout - x.timeout;
                if (diff < 0)
                    return -1;
                else
                    return 1;
            }

            long d = getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MICROSECONDS);
            return (d == 0) ? 0 : (d < 1) ? -1 : 1;
        }

        public T getValue() {
            return v;
        }
    }

}
