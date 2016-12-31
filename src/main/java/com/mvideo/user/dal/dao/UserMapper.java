package com.mvideo.user.dal.dao;

import com.mvideo.common.dao.BaseMapper;
import com.mvideo.user.dal.po.User;

/**
 * Created by admin on 16/12/5.
 */
public interface UserMapper extends BaseMapper<User, Integer> {

    User getUser(User user);

}
