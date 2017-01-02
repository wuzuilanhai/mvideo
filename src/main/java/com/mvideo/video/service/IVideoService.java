package com.mvideo.video.service;

import com.mvideo.common.dto.PageQueryDto;
import com.mvideo.video.dal.po.Video;

import java.util.List;

/**
 * Created by admin on 16/12/7.
 */
public interface IVideoService {

    List<Video> getRecentlyVideos(PageQueryDto pageQueryDto);

    List<Video> getOnlineVideos();

    List<Video> getOnUpcomingChannels();

}
