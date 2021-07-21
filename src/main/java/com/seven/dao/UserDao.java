package com.seven.dao;

import com.seven.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /*验证登录*/
    public User checkUser(String username, String password, int type);

    /*分页查询用户信息*/
    public List<User> getAllUser();

    /*根据用户名和类型查询用户*/
    public List<User> searchUser(@Param("userName") String userName, @Param("type") int type);

    /*添加管理员*/
    public int addUser(String userName,String password,int type);

    /*修改密码*/
    public int updatePwd(int id,String oldPwd,String newPwd);

    /*删除用户*/
    public int deleteUser(List<Integer> ids);

    /*获取用户id*/
    public int getUserId(String userName);
}
