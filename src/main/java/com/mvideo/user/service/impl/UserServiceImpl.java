package com.mvideo.user.service.impl;

import com.mvideo.common.util.MD5Util;
import com.mvideo.common.util.RedisUtil;
import com.mvideo.common.util.TokenUtil;
import com.mvideo.user.dal.dao.UserMapper;
import com.mvideo.user.dal.po.User;
import com.mvideo.user.dto.UserReturnDto;
import com.mvideo.user.dto.UserTokenQueryDto;
import com.mvideo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haibiao.zhang on 16/12/31.
 */
@RestController
@RequestMapping("/user")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${token.expire.time}")
    private Long expireTime;

    @Override
    @RequestMapping("/register")
    public Object register(User user) {
        Map<String, String> message = new HashMap<>();
        user.setLoginPassword(MD5Util.md5(user.getLoginPassword()));
        //判断是否已经被注册过
        if (userMapper.getUserByEmailOrPhone(user) != null) {
            message.put("status", "fail,the accout has exist!");
            return message;
        }
        Date date = new Date();
        user.setLastUpdateTime(date);
        user.setLoginTime(date);
        user.setRealName("游客");
        if (userMapper.insert(user) > 0) {
            message.put("status", "success");
        } else {
            message.put("status", "fail");
        }
        return message;
    }

    @Override
    @RequestMapping("/login")
    public UserReturnDto login(User user) {
        UserReturnDto userReturn = new UserReturnDto();
        user.setLoginPassword(MD5Util.md5(user.getLoginPassword()));
        User findUser = userMapper.getUser(user);
        if (findUser != null) {
            //生成token
            userReturn.setUser(findUser);
            userReturn.setMessage("success");
            Map<String, Object> map = new HashMap<>();
            map.put("email", findUser.getLoginEmail());
            map.put("phone", findUser.getLoginPhone());
            try {
                String token = TokenUtil.authentication(map);
                redisUtil.set(findUser.getLoginEmail() + findUser.getLoginPhone() + findUser.getId(), token, expireTime);
                userReturn.setToken(token);
            } catch (Exception e) {
                userReturn.setMessage("fail");
                throw new RuntimeException("token生成失败!");
            }
        } else {
            userReturn.setMessage("fail");
        }
        return userReturn;
    }

    /**
     * 判断token是否正确和有效
     *
     * @param token
     */
    @RequestMapping("/judgeToken")
    public Object judgeToken(UserTokenQueryDto token) {
        Map<String, String> message = new HashMap<>();
        String accessToken = (String) redisUtil.get(token.getLoginEmail() + token.getLoginPhone() + token.getUserId());
        if (accessToken != null) {
            if (accessToken.equals(token.getToken())) {
                message.put("status", "success");
            } else {
                message.put("status", "fail");
            }
        } else {
            message.put("status", "fail");
        }
        return message;
    }

    @Override
    @RequestMapping("/logout")
    public Object logout(User user) {
        Map<String, String> message = new HashMap<>();
        try {
            redisUtil.remove(user.getLoginEmail() + user.getLoginPhone() + user.getId());
            message.put("status", "success");
        } catch (Exception e) {
            message.put("status", "fail");
        }
        return message;
    }
}
