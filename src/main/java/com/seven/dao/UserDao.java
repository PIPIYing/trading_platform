package com.seven.dao;

import com.seven.domain.User;

public interface UserDao {
    /*验证登录*/
    public User checkUser(String username, String password, int type);
}
