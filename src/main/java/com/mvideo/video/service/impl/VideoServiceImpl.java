package com.mvideo.video.service.impl;

import com.mvideo.video.dto.Plupload;
import com.mvideo.video.util.PluploadUtil;
import com.mvideo.video.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by admin on 16/12/7.
 */
@RestController
@RequestMapping("/video")
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private PluploadUtil pluploadUtil;

    @RequestMapping("/upload")
    public void upload(Plupload plupload, HttpServletRequest request) throws Exception{
        String fileDir="upload";
        plupload.setRequest(request);
        //文件存储绝对路径 request.getSession().getServletContext().getRealPath("/")
        File dir=new File(request.getContextPath()+fileDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        pluploadUtil.upload(plupload,dir);
    }

}
