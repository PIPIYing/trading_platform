package com.seven.service;

import com.seven.dao.UserDao;
import com.seven.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author ying
 * @Date 2021/7/20 22:57
 **/

@Service
public class UserService {
    @Autowired
    UserDao userDao;


    /*根据用户名和密码匹配，验证是否登录*/
    public Boolean checkLogin(String username, String password, int type, HttpServletRequest request)
    {
        System.out.println("进入登录验证service——————————————————");
        System.out.println(username + "+" + password + "+" + type);

        User user = userDao.checkUser(username,password,type);

        /*如果查询到的用户不为null*/
        if(user!=null)
        {
            /*用户名和密码是正确, 可进行登录，用户信息存到session当中*/
            request.getSession().setAttribute("loginUser",user);
            User user1 = (User) request.getSession().getAttribute("loginUser");
            System.out.println("session的用户测试:"+user1);
            return true;
        }
        else {
            return false;
        }
    }
}
