package com.etime.bean;

public class User {
    private int id;
    private String userName;
    private String password;
    private String gender;
    private int age;
    private int state;//状态码 默认为1使用中，0为未使用
    private int role;//用户角色 默认为0普通用户，1为管理员用户

    public User() {
    }

    public User(int id, String userName, String password, String gender, int age, int state, int role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.state = state;
        this.role = role;
    }

    public User(String userName, String password, String gender, int age) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toString() {
        return "用户id："+id+", 用户名："+userName+", 性别："+gender+", 年龄："+age+", 状态："+state;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
