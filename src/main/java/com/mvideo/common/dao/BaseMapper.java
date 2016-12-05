package com.mvideo.common.dao;

import java.io.Serializable;

/**
 * Created by admin on 16/12/5.
 */
public interface BaseMapper<T, ID extends Serializable> {

    int insert(T t);

    int deleteByPrimaryKey(ID id);

    T selectByPrimaryKey(ID id);

    int update(T t);

}
