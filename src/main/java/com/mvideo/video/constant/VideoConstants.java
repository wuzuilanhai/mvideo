package com.mvideo.video.constant;

/**
 * Created by admin on 16/12/9.
 */
public interface VideoConstants {

    Integer CONVERT_TIME_DELAY = 1000;

    Integer IS_LIVE = 1;

    Integer IS_NOT_LIVE = 0;

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

    enum VideoAudit {
        AUDIT_01(1, "待审核"),
        AUDIT_02(2, "审核通过"),
        AUDIT_03(3, "审核失败");

        VideoAudit(Integer auditCode, String auditState) {
            this.auditCode = auditCode;
            this.auditState = auditState;
        }

        private Integer auditCode;
        private String auditState;

        public Integer getAuditCode() {
            return auditCode;
        }

        public String getAuditState() {
            return auditState;
        }
    }

}
