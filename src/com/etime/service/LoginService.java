package com.etime.service;

import com.etime.bean.User;

public interface LoginService {
    int login(String userName,String password);
    boolean register(User user);
    boolean exit();
}
