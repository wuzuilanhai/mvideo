package com.mvideo.user.dto;

import com.mvideo.user.dal.po.User;

/**
 * Created by haibiao.zhang on 16/12/31.
 */
public class UserReturnDto {

    private User user;

    private String token;

    private String message;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
