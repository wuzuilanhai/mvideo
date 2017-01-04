package com.mvideo.video.dto;

import com.mvideo.common.dto.PageQueryDto;

/**
 * Created by haibiao.zhang on 17/1/4.
 */
public class VideoSearchQueryDto extends PageQueryDto {

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
