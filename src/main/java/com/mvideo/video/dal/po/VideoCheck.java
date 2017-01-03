package com.mvideo.video.dal.po;

public class VideoCheck {
    private Integer id;

    private String tmpFileName;

    private String tmpDir;

    private Integer currentChunk;

    private Long chunkSize;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTmpFileName() {
        return tmpFileName;
    }

    public void setTmpFileName(String tmpFileName) {
        this.tmpFileName = tmpFileName == null ? null : tmpFileName.trim();
    }

    public String getTmpDir() {
        return tmpDir;
    }

    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir == null ? null : tmpDir.trim();
    }

    public Integer getCurrentChunk() {
        return currentChunk;
    }

    public void setCurrentChunk(Integer currentChunk) {
        this.currentChunk = currentChunk;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}