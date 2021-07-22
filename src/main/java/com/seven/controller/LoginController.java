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

    /*访问注册页面*/
    @RequestMapping("/toRegister")
    public String toRegister()
    {
        System.out.println("进入注册页面controller————————————————");
        return "register";
    }

    /*访问主页面 - 管理员*/
    @RequestMapping("/index1")
    public String toIndex1()
    {
        System.out.println("进入主页面1controller————————————————");
        return "user/userIndex";
    }

    /*访问主页面 - 客户*/
    @RequestMapping("/index2")
    public String toIndex2()
    {
        System.out.println("进入主页面2controller————————————————");
        return "toCustomer/goodShow";
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
            int userId = getUserId(user.getUserName());
            String name = user.getUserName();
            model.addAttribute("id",userId);
            model.addAttribute("name",name);
            if(user.getType() == 0) {
                return "redirect:/index1";
            }else {
                return "redirect:/index2";
            }
        }else {
            System.out.println("验证失败，进入登录页");
            model.addAttribute("msg","用户名/密码/用户类型错误！");
            return "login";
        }
    }

    /*获取数据库中用户的id*/
    @RequestMapping("/getUserId")
    public int getUserId(String userName)
    {
        int id = userService.getUserId(userName);
        return id;
    }

    /*注册 - 新增用户（客户）*/
    @RequestMapping("/register")
    public String register(User user, Model model)
    {
        System.out.println("进入注册controller——————————————————");
        boolean addResult = userService.addUser(user);

        if(addResult==true)
        {
            System.out.println("注册成功，进入登录页");
            return "redirect:/toLogin";
        }else {
            System.out.println("注册失败，进入注册页");
            model.addAttribute("msg","用户名已经被使用！");
            return "register";
        }
    }
}
