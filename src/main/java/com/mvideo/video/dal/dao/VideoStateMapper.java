package com.mvideo.video.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.video.dal.po.VideoState;

public interface VideoStateMapper extends BaseMapper<VideoState, Integer> {
    VideoState selectByVideoPath(String videoPath) throws Exception;
}