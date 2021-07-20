package com.seven.domain;

/**
 * @ClassName admin
 * @Description TODO
 * @Author ying
 * @Date 2021/7/19 20:21
 **/
public class Admin {

    private int id;

    private String username;

    private String password;

    private int adminType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }
}