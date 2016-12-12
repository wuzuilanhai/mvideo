package com.mvideo.video.service.impl;

import com.mvideo.video.dal.dao.VideoCheckMapper;
import com.mvideo.video.dal.po.VideoCheck;
import com.mvideo.video.dto.CheckResult;
import com.mvideo.video.dto.CheckUpload;
import com.mvideo.video.dto.Plupload;
import com.mvideo.video.util.PluploadUtil;
import com.mvideo.video.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by admin on 16/12/7.
 */
@RestController
@RequestMapping("/video")
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private PluploadUtil pluploadUtil;

    @Autowired
    private VideoCheckMapper videoCheckMapper;

    @RequestMapping("/checkUpload")
    public CheckResult checkUpload(CheckUpload checkUpload) throws Exception {
        String tmpFileName = "upload/" + checkUpload.getFilename() + "_tmp_";
        List<VideoCheck> videoChecks = videoCheckMapper.selectByTmpFileNameLimitOne(tmpFileName);
        CheckResult checkResult = new CheckResult();
        if (videoChecks.size() != 0) {
            Integer currentChunk = videoChecks.get(0).getCurrentChunk();
            checkResult.setOffset((currentChunk + 1) * checkUpload.getChunk_size());
            checkResult.setMessage("exist");
        } else {
            checkResult.setMessage("not exist");
        }
        return checkResult;
    }

    @RequestMapping("/upload")
    public void upload(Plupload plupload, HttpServletRequest request) throws Exception {
        String fileDir = "upload";
        plupload.setRequest(request);
        //文件存储绝对路径 request.getSession().getServletContext().getRealPath("/")
        File dir = new File(request.getContextPath() + fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        plupload.setName(plupload.getName().replaceAll(" ", ""));
        pluploadUtil.upload(plupload, dir);
    }

}
