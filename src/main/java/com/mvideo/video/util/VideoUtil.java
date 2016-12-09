package com.mvideo.video.util;

import com.mvideo.video.constant.VideoConstants;
import com.mvideo.video.dal.dao.VideoStateMapper;
import com.mvideo.video.dal.po.VideoState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

/**
 * Created by admin on 16/12/9.
 */
@Component
public class VideoUtil {

    @Autowired
    private VideoStateMapper videoStateMapper;

    public void process(String videoPath, String targetPath, String imgTargetPath) throws Exception {
        int type = checkContentType(videoPath);
        if (type == 0) {
            generateVideoThumbnailImg(videoPath, imgTargetPath);
            ffmpegTransVideo(videoPath, targetPath);
        }
    }

    private int checkContentType(String videoPath) {
        String type = videoPath.substring(videoPath.lastIndexOf(".") + 1, videoPath.length())
                .toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        return 1;
    }

    /**
     * 使用ffmpeg生成缩略图
     *
     * @param videoPath 源路径 -- 要生成缩略图的视频文件
     * @param imgPath   目标路径 -- 生成缩略图文件(.jpg)
     */
    @Transactional
    private void generateVideoThumbnailImg(String videoPath, String imgPath) throws Exception {
        List<String> command = new java.util.ArrayList<String>();
        command.add("/usr/local/Cellar/ffmpeg/2.8.5/bin/ffmpeg");
        command.add("-i");
        command.add(videoPath);
        command.add("-f");
        command.add("image2");
        command.add("-ss");
        command.add("1");
        command.add("-t");
        command.add("0.001");
        command.add("-s");
        command.add("320x240");
        command.add("-y");
        command.add(imgPath);
        boolean success = startConvert(parseCommand(command));
        if (success) {
            VideoState videoState = videoStateMapper.selectByVideoPath(videoPath);
            videoState.setLevel(3);
            videoState.setName(VideoConstants.Video.getVideoState(3).getName());
            videoStateMapper.update(videoState);
        }
    }

    /**
     * 使用ffmpeg转码
     *
     * @param videoPath  源路径 -- 要转换的视频文件
     * @param targetPath 目标路径 -- 转换后的视频flv
     * @return
     */
    @Transactional
    private void ffmpegTransVideo(String videoPath, String targetPath) throws Exception {
        List<String> command = new java.util.ArrayList<String>();
        command.add("/usr/local/Cellar/ffmpeg/2.8.5/bin/ffmpeg");
        command.add("-i");
        command.add(videoPath);
        command.add("-vcodec");
        command.add("libx264");
        command.add("-b:v");
        command.add("500000");
        command.add("-r");//设置帧频
        command.add("25");
        command.add("-acodec");
        command.add("libmp3lame");
        command.add("-b:a");
        command.add("64000");
        command.add("-ar");
        command.add("22050");
        command.add("-ab");
        command.add("64");
        command.add("-ac");
        command.add("2");
        command.add("-qscale");//清晰度    -qscale 4 为最好可是文件大, -qscale 6就可以了
        command.add("4");
        command.add("-y");// 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        command.add(targetPath);
        boolean success = startConvert(parseCommand(command));
        if (success) {
            VideoState videoState = videoStateMapper.selectByVideoPath(videoPath);
            videoState.setLevel(4);
            videoState.setName(VideoConstants.Video.getVideoState(4).getName());
            videoStateMapper.update(videoState);
        }
    }

    /**
     * 使用mencoder转码
     *
     * @param videoPath  源路径 -- 要转换的视频文件
     * @param targetPath 目标路径 -- 转换后的视频flv
     * @return
     */
    private void mencoderTransVideo(String videoPath, String targetPath) {
        List<String> command = new java.util.ArrayList<String>();
        command.add("/usr/local/bin/mencoder");
        command.add(videoPath);
        command.add("-oac");
        command.add("mp3lame");
        command.add("-lameopts");
        command.add("preset=64");
        command.add("-ovc");
        command.add("xvid");
        command.add("-xvidencopts");
        command.add("bitrate=600");
        command.add("-of");
        command.add("avi");
        command.add("-o");
        command.add(targetPath);
        startConvert(parseCommand(command));
    }

    private static String parseCommand(List<String> command) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < command.size(); i++) {
            sb.append(command.get(i) + " ");
        }
        return sb.toString();
    }

    private boolean startConvert(String command) {
        Process process = null;
        BufferedReader inBr = null;
        BufferedReader errBr = null;
        try {
            process = Runtime.getRuntime().exec(command);
            inBr = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errBr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String lineStr;
            while ((lineStr = inBr.readLine()) != null) {
                System.out.println(lineStr);
            }
            while ((lineStr = errBr.readLine()) != null) {
                System.out.println(lineStr);
            }

            if (process.waitFor() != 0) {
                if (process.exitValue() == 1) {
                    System.err.println("Failed!");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inBr != null) {
                try {
                    inBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (errBr != null) {
                try {
                    errBr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
