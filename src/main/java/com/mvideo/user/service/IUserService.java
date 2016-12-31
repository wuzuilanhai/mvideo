package com.mvideo.user.service;

import com.mvideo.user.dal.po.User;
import com.mvideo.user.dto.UserReturnDto;

/**
 * Created by haibiao.zhang on 16/12/31.
 */
public interface IUserService {

    Object register(User user);

    UserReturnDto login(User user);

    Object logout(User user);

}
