package com.seven.dao;

import com.seven.domain.Admin;

import java.util.List;

/**
 * @ClassName AdminDao
 * @Description TODO
 * @Author ying
 * @Date 2021/7/19 20:37
 **/
public interface AdminDao {
    /*查询所有管理员*/
    public List<Admin> getAllAdmin();
}