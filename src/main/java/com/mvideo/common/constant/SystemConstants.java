package com.mvideo.common.constant;

/**
 * Created by admin on 16/12/6.
 */
public interface SystemConstants {
    enum RedisConfig {
        EXPIRATION("DEFAULT", 10),
        EXPIRATION_OUT("UNKNOWN", -1)
        ;

        public static RedisConfig getByKey(String key) {
            for (RedisConfig redisConfig : RedisConfig.values()) {
                if (redisConfig.getExpirekey().equals(key)) {
                    return redisConfig;
                }
            }
            return EXPIRATION_OUT;
        }

        private RedisConfig(String expirekey, Integer expireValue) {
            this.expirekey = expirekey;
            this.expireValue = expireValue;
        }

        private String expirekey;
        private Integer expireValue;

        public String getExpirekey() {
            return expirekey;
        }

        public Integer getExpireValue() {
            return expireValue;
        }
    }
}
