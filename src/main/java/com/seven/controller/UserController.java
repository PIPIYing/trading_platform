package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.User;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/21 14:28
 **/
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /*访问用户管理页*/
    @RequestMapping("/userIndex")
    public String userIndex()
    {
        return "user/userIndex";
    }

    /*查询所有用户*/
    @RequestMapping("/userAll")
    @ResponseBody
    public Map<String,Object> userAll(int page,int limit)
    {
        System.out.println("进入查询所有用户controller——————————————————");
        Map<String,Object> map = new HashMap<>();

        /*所有管理员*/
        List<User> userList = userService.getAllUser(page,limit);
        /*包含分页信息*/
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        /*layui要求的返回数据格式，名称不能变*/
        map.put("data",pageInfo.getList());
        map.put("count",pageInfo.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

}
