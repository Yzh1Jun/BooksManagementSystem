package com.etime.ui;

import com.etime.service.BorrowService;
import com.etime.service.impl.BorrowServiceImpl;
import com.etime.utils.ScannerUtil;
import com.etime.utils.StateEnum;
import com.etime.utils.UserConfig;

public class ReturnBookUI {
    BorrowService borrowService = new BorrowServiceImpl();
    public void returnBookRun(){
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>借书还书>还书\n");
            System.out.println("              1.查询自己借书情况");
            System.out.println("              2.查询自己未还图书");
            System.out.println("              3.还书");
            System.out.println("              4.返回");
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
                    borrowService.userFindBookBorrowByUserId(UserConfig.userId);
                    break;
                case 2:
                    borrowService.userFindBookBorrowByUserId(UserConfig.userId, StateEnum.STATE_OPEN);
                    break;
                case 3:
                    System.out.println("请输入要还的书的编号：");
                    int bookId = 0;
                    try {
                        bookId = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的书的编号类型不匹配");
                    }
                    System.out.println("请输入要还的数量：");
                    int number = 0;
                    try {
                        number = ScannerUtil.getScanner().nextInt();
                        if (number<=0){
                            System.out.println("还书的数量不能小于1");
                        }
                    } catch (Exception e){
                        System.out.println("输入的书的数量类型不匹配");
                    }
                    borrowService.returnBook(UserConfig.userId,bookId,number);
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
