package com.seven.controller;

import com.github.pagehelper.PageInfo;
import com.seven.domain.User;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    /*访问添加管理员页*/
    @RequestMapping("/userAdd")
    public String userAdd()
    {
        return "user/userAdd";
    }

    /*访问修改密码页*/
    @RequestMapping("/userPwdUpdate")
    public String userPwdUpdate()
    {
        return "user/userPwdUpdate";
    }

    /*查询所有用户*/
    @RequestMapping("/userAll")
    @ResponseBody
    public Map<String,Object> userAll(int page,int limit)
    {
        System.out.println("进入查询所有用户controller——————————————————");
        Map<String,Object> map = new HashMap<>();

        /*所有管理员*/
        List<User> userList1 = userService.getAllUser(page,limit);
        /*包含分页信息*/
        PageInfo<User> pageInfo1 = new PageInfo<>(userList1);
        /*layui要求的返回数据格式，名称不能变*/
        map.put("data",pageInfo1.getList());
        map.put("count",pageInfo1.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    /*搜索用户（根据用户名和类型）*/
    @RequestMapping("/userSearch")
    @ResponseBody
    public Map<String,Object> userSearch(String userName, int type, int page, int limit)
    {
        System.out.println("进入搜索用户controller——————————————————");
        System.out.println("username: " + userName + "+" + "type: " + type);
        Map<String,Object> map = new HashMap<>();
        if(userName==null||userName.trim().length()==0)
        {
            userName=null;
        }
        if(type==2)
        {
            type= 2;
        }

        List<User> userList2 = userService.searchUser(userName, type, page, limit);
        PageInfo<User> pageInfo2 = new PageInfo<>(userList2);
        map.put("data",pageInfo2.getList());
        map.put("count",pageInfo2.getTotal());
        map.put("code",0);
        map.put("msg","");
        return map;
    }


    /*添加管理员操作*/
    @RequestMapping("/userAddSumbit")
    @ResponseBody
    public Map<String,Object> userAddSumbit(User user)
    {
        System.out.println("进入添加管理员controller——————————————————");
        HashMap<String,Object> map = new HashMap<>();
        boolean addResult = userService.addUser(user);

        if(addResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    /*修改密码*/
    @RequestMapping("/userUpdatePwdSumbit")
    @ResponseBody
    public Map<String,Object> userUpdatePwdSumbit(String id,String oldPwd,String newPwd)
    {
        System.out.println("进入修改密码controller——————————————————");
        System.out.println(id+ '+' + oldPwd+ '+' + newPwd);
        boolean updateResult = userService.updatePwd(Integer.parseInt(id),oldPwd,newPwd);

        HashMap<String,Object> map = new HashMap<>();
        if(updateResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }

    /*根据id删除用户*/
    @RequestMapping("/userDelete")
    @ResponseBody
    public Map<String,Object> userDelete(String ids)
    {
        System.out.println(ids);
        System.out.println("进入删除管理员controller————————————————");
        HashMap<String,Object> map = new HashMap<>();

        /*需要把ids转化为集合*/
        List<String> idlist = Arrays.asList(ids.split(","));
        List<Integer> idList = new ArrayList<>();

        /*需要把String集合转化为整数集合*/
        for (String id:idlist)
        {
            idList.add(Integer.parseInt(id));
        }

        boolean deleteResult = userService.deleteUser(idList);

        if(deleteResult==true)
        {
            map.put("code",0);
        }
        else {
            map.put("code",-1);
        }
        return map;
    }
}
