package com.mvideo.video.constant;

/**
 * Created by admin on 16/12/9.
 */
public interface VideoConstants {

    Integer CONVERT_TIME_DELAY = 1000;

    enum Video {
        STATE_01(1, " 等待上传"),
        STATE_02(2, " 等待截图"),
        STATE_03(3, " 等待转码"),
        STATE_04(4, " 完成");

        public static Video getVideoState(Integer key) {
            for (Video video : Video.values()) {
                if (video.getLevel() == key) {
                    return video;
                }
            }
            return null;
        }

        private Video(Integer level, String name) {
            this.level = level;
            this.name = name;
        }

        private Integer level;
        private String name;

        public Integer getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }
    }
}
