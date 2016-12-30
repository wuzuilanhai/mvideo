package com.mvideo.index.dto;

import com.mvideo.video.dal.po.Video;

import java.util.List;

/**
 * Created by haibiao.zhang on 16/12/25.
 */
public class PrepareIndexDataDto {

    List<CategoryResultDto> categoryResultDtoList;

    List<Video> recentlyVideos;

    public List<CategoryResultDto> getCategoryResultDtoList() {
        return categoryResultDtoList;
    }

    public void setCategoryResultDtoList(List<CategoryResultDto> categoryResultDtoList) {
        this.categoryResultDtoList = categoryResultDtoList;
    }

    public List<Video> getRecentlyVideos() {
        return recentlyVideos;
    }

    public void setRecentlyVideos(List<Video> recentlyVideos) {
        this.recentlyVideos = recentlyVideos;
    }
}
