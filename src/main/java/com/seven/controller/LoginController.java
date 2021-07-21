package com.seven.controller;

import com.seven.domain.User;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/20 22:59
 **/

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    /*访问登录页面*/
    @RequestMapping("/toLogin")
    public String toLogin()
    {
        System.out.println("进入登录页面controller————————————————");
        return "login";
    }

    /*验证登录*/
    @RequestMapping("/login")
    public String login(User user, Model model, HttpServletRequest request)
    {
        System.out.println("进入登录验证controller————————————————");
        System.out.println(user);
        System.out.println(user.getUserName() + "+" + user.getPassword() + "+" + user.getType());
        /*调用验证登录的逻辑类去验证登录*/
        if(userService.checkLogin(user.getUserName(),user.getPassword(),user.getType(),request)==true)
        {
            System.out.println("验证成功，进入主页");
            return "redirect:/index";
        }else {
            System.out.println("验证失败，进入登录页");
            model.addAttribute("msg","用户名或者密码或者用户类型错误！");
            return "login";
        }
    }

    /*访问主页面*/
    @RequestMapping("/index")
    public String toIndex()
    {
        System.out.println("进入主页面controller————————————————");
        return "index";
    }
}
