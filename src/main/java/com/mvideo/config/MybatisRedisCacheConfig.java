package com.mvideo.config;

import com.mvideo.common.util.RedisUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by admin on 16/12/12.
 */
public class MybatisRedisCacheConfig implements Cache {

    @Autowired
    private RedisUtil redisUtil;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private String id;

    public MybatisRedisCacheConfig(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances requires an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        if (value != null) {
            redisUtil.set(key, value);
        }
    }

    @Override
    public Object getObject(Object key) {
        if (key != null) {
            return redisUtil.get(key);
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        if (key != null) {
            redisUtil.remove(key);
        }
        return null;
    }

    @Override
    public void clear() {
        //jedis nonsupport
    }

    @Override
    public int getSize() {
        return redisUtil.getSize();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
