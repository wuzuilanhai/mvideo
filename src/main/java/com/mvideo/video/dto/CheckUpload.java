package com.mvideo.video.dto;

/**
 * Created by admin on 16/12/8.
 */
public class CheckUpload {

    private String filename;

    private Long chunk_size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getChunk_size() {
        return chunk_size;
    }

    public void setChunk_size(Long chunk_size) {
        this.chunk_size = chunk_size;
    }

}
