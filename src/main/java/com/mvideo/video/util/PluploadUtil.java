package com.mvideo.video.util;

import com.mvideo.video.dal.dao.VideoCheckMapper;
import com.mvideo.video.dal.po.VideoCheck;
import com.mvideo.video.dto.Plupload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Plupload Service模块
 * <p>
 * Created by admin on 16/12/7.
 */
@Component
public class PluploadUtil {

    @Autowired
    private VideoCheckMapper videoCheckMapper;

    public void upload(Plupload plupload, File pluploadDir) {
        String fileName = "" + System.currentTimeMillis() + plupload.getName();//在服务器内生成唯一文件名
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
                        if (chunks > 1) {//用户上传资料总块数大于1，要进行合并
                            String prefixName = pluploadDir.getPath() + "/" + plupload.getName() + "_tmp_";
                            String tmpFileName = prefixName + plupload.getChunk();
                            File tempFile = new File(tmpFileName);
                            //保存每一块的内容在临时文件中
                            savePluploadFile(multipartFile.getInputStream(), tempFile, false);
                            //更新数据库表video_check的数据
                            VideoCheck videoCheck = new VideoCheck();
                            videoCheck.setCurrentChunk(plupload.getChunk());
                            List<VideoCheck> findVideoChecks = videoCheckMapper.selectByTmpFileNameLimitOne(prefixName);
                            if (findVideoChecks.size() == 0) {
                                videoCheck.setTmpFileName(tmpFileName);
                                videoCheckMapper.insert(videoCheck);
                            } else {
                                videoCheck.setId(findVideoChecks.get(0).getId());
                                videoCheckMapper.update(videoCheck);
                            }

                            if (chunks - nowChunk == 1) {//全部块已经上传完毕，合并所有块文件
                                for (int i = 0; i < chunks; i++) {
                                    File srcFile = new File(prefixName +i);
                                    FileInputStream fi = new FileInputStream(srcFile);
                                    savePluploadFile(fi, targetFile, i == 0 ? false : true);
                                    srcFile.delete();
                                }
                                //每当文件上传完毕，将上传信息插入数据库
                                //Timestamp now = new Timestamp(System.currentTimeMillis());
                                //youandmeService.uploadInfo(fileName,((User)(plupload.getRequest().getSession().getAttribute("user"))).getUsername(),now);
                            }
                        } else {
                            //只有一块，就直接拷贝文件内容
                            multipartFile.transferTo(targetFile);

                            //每当文件上传完毕，将上传信息插入数据库
                            //Timestamp now = new Timestamp(System.currentTimeMillis());
                            //youandmeService.uploadInfo(fileName, ((User) (plupload.getRequest().getSession().getAttribute("user"))).getUsername(), now);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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

}
