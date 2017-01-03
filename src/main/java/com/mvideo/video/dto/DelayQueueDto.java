package com.mvideo.video.dto;

import java.io.File;

/**
 * Created by haibiao.zhang on 17/1/3.
 */
public class DelayQueueDto {

    private File file;

    private Integer userId;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
