package com.etime.ui;

import com.etime.bean.User;
import com.etime.dao.UserDao;
import com.etime.service.impl.UserServiceImpl;
import com.etime.service.UserService;
import com.etime.utils.Md5Util;
import com.etime.utils.RoleEnum;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserUI {
    UserService userService = new UserServiceImpl();
    UserDao userDao = new UserDao();
    UserSearchUI userSearchUI = new UserSearchUI();
    public void userRun(){
        boolean isContinue = true;
        String patternName = "^[a-z0-9A-Z]{1,}$";
        String patternPwd = "^[a-z0-9A-Z]{3,}$";
        Pattern r1 = Pattern.compile(patternName);
        Pattern r2 = Pattern.compile(patternPwd);
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>用户管理\n");
            if (UserConfig.userRole== RoleEnum.ROLE_ADMIN){
                userService.findUser(UserConfig.userId);
                System.out.println();
                System.out.println("1.检索用户");
                System.out.println("2.添加用户");
                System.out.println("3.修改用户");
                System.out.println("4.删除用户");
                System.out.println("5.设置用户状态码");
                System.out.println("6.设置用户权限");
                System.out.println("7.返回");
                System.out.println("请输入编号:");
                int nextInt = 0;
                int index = 0;
                User user = null;
                int state = 0;
                try {
                    nextInt = ScannerUtil.getScanner().nextInt();
                } catch (Exception e){
                    System.out.println("输入的数的类型不匹配");
                    continue;
                }
                switch (nextInt){
                    case 1:
                        userSearchUI.userSearchRun();
                        break;
                    case 2:
                        System.out.println("请输入用户名：（至少一位,为数字和字母）");
                        String inputUsername = ScannerUtil.getScanner().next();
                        Matcher m = r1.matcher(inputUsername);
                        if (!m.matches()){
                            System.out.println("输入的用户名不规范");
                            continue;
                        }
                        System.out.println("请输入密码：（至少三位,为数字和字母）");
                        String inputPassword = ScannerUtil.getScanner().next();
                        m = r2.matcher(inputPassword);
                        if (!m.matches()){
                            System.out.println("输入的密码不规范");
                            continue;
                        }
                        String md5Pwd = Md5Util.md5(inputPassword);
                        System.out.println("请输入性别：");
                        String inputGender = ScannerUtil.getScanner().next();
                        System.out.println("请输入年龄：");
                        int inputAge = 0;
                        try {
                            inputAge = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e) {
                            System.out.println("输入的年龄类型不匹配");
                        }
                        user = new User(inputUsername, md5Pwd, inputGender, inputAge);
                        userService.insertUser(user);
                        break;
                    case 3:
                        System.out.println("请输入要修改的用户编号");
                        try {
                            index = ScannerUtil.getScanner().nextInt();
                        }catch (Exception e){
                            System.out.println("输入的用户编号类型不匹配");
                        }
                        //获取用户信息
                        user = userDao.findUserById(index);
                        if (user==null){
                            System.out.println("此编号的用户不存在");
                            continue;
                        }
                        //进行修改
                        System.out.println("请输入新的用户名(" + user.getUserName() + ")：（至少一位,为数字和字母）");
                        String inputUsername1 = ScannerUtil.getScanner().next();
                        Matcher m1 = r1.matcher(inputUsername1);
                        if (!m1.matches()){
                            System.out.println("输入的用户名不规范");
                            continue;
                        }
                        System.out.println("请输入新的密码：（至少三位,为数字和字母）");
                        String inputPassword1 = ScannerUtil.getScanner().next();
                        m1 = r2.matcher(inputPassword1);
                        if (!m1.matches()){
                            System.out.println("输入的密码不规范");
                            continue;
                        }
                        String md5Pwd1 = Md5Util.md5(inputPassword1);
                        System.out.println("请输入新的性别(" + user.getGender() + ")：");
                        String inputGender1 = ScannerUtil.getScanner().next();
                        System.out.println("请输入新的年龄(" + user.getAge() + ")：");
                        int inputAge1 = 0;
                        try {
                            inputAge1 = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e) {
                            System.out.println("输入的年龄类型不匹配");
                        }
                        user.setUserName(inputUsername1);
                        user.setPassword(md5Pwd1);
                        user.setGender(inputGender1);
                        user.setAge(inputAge1);
                        userService.updateUser(user);
                        break;
                    case 4:
                        System.out.println("请输入要删除的用户编号：");
                        try {
                            index = ScannerUtil.getScanner().nextInt();
                        }catch (Exception e){
                            System.out.println("输入的用户编号类型不匹配");
                        }
                        System.out.println("(*)是否真的删除?(Y/N)");
                        String next = ScannerUtil.getScanner().next();
                        userService.deleteUser(index,next);
                        break;
                    case 5:
                        System.out.println("请输入要修改的用户编号：");
                        try {
                            index = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e){
                            System.out.println("输入的用户编号类型不匹配");
                        }
                        System.out.println("请输入状态码：(0：使用中，1：未使用)");
                        try {
                            state = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e1){
                            System.out.println("输入的状态码类型不匹配");
                        }
                        userService.setUserState(index,state);
                        break;
                    case 6:
                        System.out.println("请输入要修改的用户编号：");
                        try {
                            index = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e){
                            System.out.println("输入的用户编号类型不匹配");
                        }
                        System.out.println("请输入权限：(0：普通用户，1：管理员)");
                        int role = 0;
                        try {
                            role = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e1){
                            System.out.println("输入的状态码类型不匹配");
                        }
                        userService.setUserRole(role,index);
                        break;
                    case 7:
                        isContinue = false;
                        break;
                    default:
                        System.out.println("输入的数不符合规范请重新输入");
                        break;
                }
            }else {
                User user = userService.findUser(UserConfig.userId);
                System.out.println();
                System.out.println("1.修改用户");
                System.out.println("2.返回");
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
                        System.out.println("请输入新的用户名(" + user.getUserName() + ")：（至少一位,为数字和字母）");
                        String inputUsername = ScannerUtil.getScanner().next();
                        Matcher m = r1.matcher(inputUsername);
                        if (!m.matches()){
                            System.out.println("输入的用户名不规范");
                            continue;
                        }
                        System.out.println("请输入新的密码：（至少三位,为数字和字母）");
                        String inputPassword = ScannerUtil.getScanner().next();
                        m = r2.matcher(inputPassword);
                        if (!m.matches()){
                            System.out.println("输入的密码不规范");
                            continue;
                        }
                        String md5Pwd = Md5Util.md5(inputPassword);
                        System.out.println("请输入新的性别(" + user.getGender() + ")：");
                        String inputGender = ScannerUtil.getScanner().next();
                        System.out.println("请输入新的年龄(" + user.getAge() + ")：");
                        int inputAge = 0;
                        try {
                            inputAge = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e) {
                            System.out.println("输入的年龄类型不匹配");
                        }
                        user.setUserName(inputUsername);
                        user.setPassword(md5Pwd);
                        user.setGender(inputGender);
                        user.setAge(inputAge);
                        userService.updateUser(user);
                        break;
                    case 2:
                        isContinue = false;
                        break;
                    default:
                        System.out.println("输入的数不符合规范请重新输入");
                        break;
                }
            }
        }while (isContinue);
    }
}
