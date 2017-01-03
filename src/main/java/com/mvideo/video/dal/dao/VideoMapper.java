package com.mvideo.video.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.video.dal.po.Video;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by admin on 16/12/5.
 */
public interface VideoMapper extends BaseMapper<Video, Integer> {

    Video selectByOriUrl(String oriUrl) throws Exception;

    List<Video> getRecentlyVideos();

    List<Video> getOnlineVideos();

    List<Video> getOnUpcomingChannels();

    List<Video> getHistoryVideo(Integer userId);
}
