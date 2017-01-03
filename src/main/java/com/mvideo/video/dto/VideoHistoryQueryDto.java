package com.mvideo.video.dto;

import com.mvideo.common.dto.PageQueryDto;

/**
 * Created by haibiao.zhang on 17/1/3.
 */
public class VideoHistoryQueryDto extends PageQueryDto {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
