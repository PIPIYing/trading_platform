package com.seven.service;

import com.seven.dao.AdminDao;
import com.seven.domain.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author ying
 * @Date 2021/7/19 20:23
 **/

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    /*查询所有管理员*/
    public List<Admin> getAllAdmin()
    {
        return adminDao.getAllAdmin();
    }
}
