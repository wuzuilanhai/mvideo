package com.mvideo.video.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.video.dal.po.VideoCheck;
import com.mvideo.video.dto.CheckUpload;

import java.util.List;

public interface VideoCheckMapper extends BaseMapper<VideoCheck, Integer> {
    VideoCheck selectByTmpFileName(String tmpFileName) throws Exception;

    List<VideoCheck> selectByTmpFileNameLimitOne(CheckUpload checkUpload) throws Exception;
}