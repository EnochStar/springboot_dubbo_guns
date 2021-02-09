package com.stylefeng.guns.api.user.vo;

import java.io.Serializable;

/**
 * @author EnochStar
 * @title: UserModel
 * @projectName guns-parent
 * @description: 注册时使用，由于是多个模块的调用，故一定涉及序列化
 * @date 2021/2/5 20:55
 */



public class UserModel implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
