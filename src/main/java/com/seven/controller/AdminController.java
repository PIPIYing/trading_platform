package com.seven.controller;

import com.seven.domain.Admin;
import com.seven.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author ying
 * @Date 2021/7/19 20:23
 **/
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/totest")
    public String totest(Model model)
    {
        List<Admin> admins = adminService.getAllAdmin();
        System.out.println(admins);
        model.addAttribute("admins",admins);
        return "test";
    }
}
