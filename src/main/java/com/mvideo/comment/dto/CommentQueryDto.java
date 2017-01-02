package com.mvideo.comment.dto;

import com.mvideo.common.dto.PageQueryDto;

/**
 * Created by haibiao.zhang on 17/1/2.
 */
public class CommentQueryDto extends PageQueryDto {

    private Integer videoId;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}
