package com.seven.dao;

import com.seven.domain.User;

import java.util.List;

public interface UserDao {
    /*验证登录*/
    public User checkUser(String username, String password, int type);

    /*分页查询用户信息*/
    public List<User> getAllUser();
}
