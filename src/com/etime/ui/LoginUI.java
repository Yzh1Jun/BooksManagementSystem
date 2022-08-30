package com.etime.ui;

import com.etime.bean.User;
import com.etime.service.impl.LoginServiceImpl;
import com.etime.service.impl.UserServiceImpl;
import com.etime.service.LoginService;
import com.etime.service.UserService;
import com.etime.utils.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginUI {
    LoginService loginService = new LoginServiceImpl();
    UserService userService = new UserServiceImpl();
    public void run() {
        boolean isContinue = true;
        String patternName = "^[a-z0-9A-Z]{1,}$";
        String patternPwd = "^[a-z0-9A-Z]{3,}$";
        Pattern r1 = Pattern.compile(patternName);
        Pattern r2 = Pattern.compile(patternPwd);
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("-------------------请登录---------------------");
            System.out.println("                  1.登录");
            System.out.println("                  2.注册");
            System.out.println("                  3.忘记密码？找回密码");
            System.out.println("                  4.退出");
            System.out.println("请输入编号:");
            int nextInt = 0;
            String username = null;
            try {
                nextInt = ScannerUtil.getScanner().nextInt();
            } catch (Exception e){
                System.out.println("输入的数的类型不匹配");
                continue;
            }
            switch (nextInt){
                case 1:
                    System.out.println("用户名:");
                    username = ScannerUtil.getScanner().next();
                    System.out.println("密码:");
                    String inputPassword = ScannerUtil.getScanner().next();
                    String md5Pwd = Md5Util.md5(inputPassword);
                    int userId = loginService.login(username,md5Pwd);
                    if (userId>0){
                        System.out.println("登录成功");
                        UserService userService = new UserServiceImpl();
                        int role = userService.findUserRoleById(userId);
                        //将用户的id和权限存入UserConfig
                        UserConfig.userId = userId;
                        UserConfig.userRole = role;
                        if (role== RoleEnum.ROLE_ADMIN){
                            AdminUI adminUI = new AdminUI();
                            adminUI.indexRun();
                        }else {
                            CommonUI commonUI = new CommonUI();
                            commonUI.indexRun();
                        }
                    }else {
                        System.out.println("登陆失败");
                    }
                    break;
                case 2:
                    System.out.println("请输入用户名:（至少一位,为数字和字母）");
                    username = ScannerUtil.getScanner().next();
                    Matcher m = r1.matcher(username);
                    if (!m.matches()){
                        System.out.println("输入的用户名不规范");
                        continue;
                    }
                    System.out.println("请输入密码:（至少三位,为数字和字母）");
                    String inputPassword1 = ScannerUtil.getScanner().next();
                    m = r2.matcher(inputPassword1);
                    if (!m.matches()){
                        System.out.println("输入的密码不规范");
                        continue;
                    }
                    String md51 = Md5Util.md5(inputPassword1);
                    System.out.println("请再次输入密码:");
                    String inputPassword2 = ScannerUtil.getScanner().next();
                    String md52 = Md5Util.md5(inputPassword2);
                    System.out.println("请输入性别:");
                    String inputGender = ScannerUtil.getScanner().next();
                    System.out.println("请输入年龄:");
                    int inputAge = 0;
                    try {
                        inputAge = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的年龄类型不匹配");
                    }
                    if (!md51.equals(md52)){
                        System.out.println("两次密码不相同");
                        break;
                    }
                    String password = md51;
                    User user = new User(username, password, inputGender, inputAge);
                    loginService.register(user);
                    break;
                case 3:
                    System.out.println("请输入要找回的用户名：");
                    String userName = ScannerUtil.getScanner().next();
                    user = userService.findIsExists(userName);
                    if (user == null){
                        System.out.println("用户不存在，请注册");
                        continue;
                    } else {
                        System.out.println("用户存在");
                        System.out.println("*验证码已发送*");
                        VerificationCodeUtil v = new VerificationCodeUtil();
                        String vc = v.getVC(4);
                        v.sendVC(vc);
                        System.out.println("请输入验证码：");
                        String inVC = ScannerUtil.getScanner().next();
                        int code = v.judgeVerificationCode(vc, inVC);
                        if (code==-1){
                            System.out.println("验证码输入错误");
                            continue;
                        }
                        System.out.println("请输入新的密码：（至少三位,为数字和字母）");
                        String inputPassword3 = ScannerUtil.getScanner().next();
                        m = r2.matcher(inputPassword3);
                        if (!m.matches()){
                            System.out.println("输入的密码不规范");
                            continue;
                        }
                        String md5 = Md5Util.md5(inputPassword3);
                        user.setPassword(md5);
                        userService.updateUser(user);
                    }
                    break;
                case 4:
                    boolean exit = loginService.exit();
                    if (exit){
                        isContinue = false;
                    }
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        }while (isContinue);
    }
}
