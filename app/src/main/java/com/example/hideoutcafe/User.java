package com.example.hideoutcafe;
public class User {
    private String mobile;
    private String name;

    public User(String mobile, String name) {
        this.mobile = mobile;
        this.name = name;
    }

    public User() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
