package com.etime.ui;

import com.etime.service.impl.LoginServiceImpl;
import com.etime.service.LoginService;
import com.etime.utils.ScannerUtil;

public class AdminUI {
    LoginService loginService = new LoginServiceImpl();
    UserUI userUI = new UserUI();
    BookUI bookUI = new BookUI();
    BorrowUI borrowUI = new BorrowUI();
    BookTypeUI bookTypeUI = new BookTypeUI();
    //主页
    public void indexRun(){
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页\n");
            System.out.println("                 1.用户管理");
            System.out.println("                 2.图书管理");
            System.out.println("                 3.图书类型管理");
            System.out.println("                 4.借阅管理");
            System.out.println("                 5.退出系统");
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
                    userUI.userRun();
                    break;
                case 2:
                    bookUI.bookRun();
                    break;
                case 3:
                    bookTypeUI.bookTypeRun();
                    break;
                case 4:
                    borrowUI.borrowRun();
                    break;
                case 5:
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
