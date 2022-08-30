package com.etime.service;

import com.etime.bean.User;

public interface UserService {
    User findUser(int userId);
    void updateUser(User user);
    void insertUser(User user);
    void deleteUser(int userId, String inScan);
    void setUserState(int userId, int state);
    int findUserRoleById(int userId);
    void setUserRole(int role, int userId);
    void findUserByName(String username);
    void findUserByGender(String gender);
    //找回用户通过username查询一个用户（state=0）启用中
    User findIsExists(String username);
}
