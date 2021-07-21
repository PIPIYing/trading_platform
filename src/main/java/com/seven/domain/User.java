package com.seven.domain;

/**
 * @ClassName User
 * @Description TODO
 * @Author ying
 * @Date 2021/7/20 22:46
 **/
public class User {
    private int id;
    private String userName;
    private String password;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
