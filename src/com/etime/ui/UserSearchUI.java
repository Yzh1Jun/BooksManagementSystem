package com.etime.ui;

import com.etime.service.impl.UserServiceImpl;
import com.etime.service.UserService;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

public class UserSearchUI {

    public void userSearchRun(){
        UserService userService = new UserServiceImpl();
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>用户管理>检索用户\n");
            System.out.println();
            System.out.println("1.查询所有用户");
            System.out.println("2.通过用户名查询用户");
            System.out.println("3.通过性别查询用户");
            System.out.println("4.返回");
            System.out.println("请输入编号:");
            int nextInt = 0;
            try {
                nextInt = ScannerUtil.getScanner().nextInt();
            } catch (Exception e){
                System.out.println("输入的数的类型不匹配");
                continue;
            }
            switch (nextInt){
                case 1:
                    userService.findUser(UserConfig.userId);
                    break;
                case 2:
                    System.out.println("请输入用户名：");
                    String username = ScannerUtil.getScanner().next();
                    userService.findUserByName(username);
                    break;
                case 3:
                    System.out.println("请输入性别：");
                    String gender = ScannerUtil.getScanner().next();
                    userService.findUserByGender(gender);
                    break;
                case 4:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        }while (isContinue);
    }
}
