package com.mvideo.video.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.video.dal.po.VideoState;
import com.mvideo.video.dto.CheckUpload;

public interface VideoStateMapper extends BaseMapper<VideoState, Integer> {
    VideoState selectByVideoPath(CheckUpload checkUpload) throws Exception;
}