package com.etime.ui;

import com.etime.service.BorrowService;
import com.etime.service.MessageService;
import com.etime.service.impl.BorrowServiceImpl;
import com.etime.service.impl.MessageServiceImpl;
import com.etime.utils.RoleEnum;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

import java.util.Iterator;
import java.util.List;

public class BorrowUI {
    BorrowService borrowService = new BorrowServiceImpl();
    MessageService messageService = new MessageServiceImpl();
    MessageUI messageUI = new MessageUI();
    public void borrowRun(){
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            if (UserConfig.userRole== RoleEnum.ROLE_ADMIN){
                System.out.println("目前位置    主页>借阅管理\n");
                System.out.println("              1.统计所有借书情况");
                System.out.println("              2.查询某个用户借书情况");
                System.out.println("              3.消息管理");
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
                        borrowService.findAllUserAndBookBorrow();
                        break;
                    case 2:
                        System.out.println("请输入要查询的用户的编号：");
                        int userId = 0;
                        try{
                            userId = ScannerUtil.getScanner().nextInt();
                        } catch (Exception e){
                            System.out.println("输入的用户编号类型不匹配");
                        }
                        borrowService.findBookBorrowByUserId(userId);
                        break;
                    case 3:
                        messageUI.messageRun();
                        break;
                    case 4:
                        isContinue = false;
                        break;
                    default:
                        System.out.println("输入的数不符合规范请重新输入");
                        break;
                }
            }else {
                System.out.println("目前位置    主页>借书还书\n");
                List<String> message = messageService.findMessage(UserConfig.userId);
                Iterator<String> iterator = message.iterator();
                while (iterator.hasNext()){
                    String next = iterator.next();
                    System.out.println("                      "+next);
                }
                System.out.println("              1.借书");
                System.out.println("              2.还书");
                System.out.println("              3.返回");
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
                        BorrowBookUI borrowBookUI = new BorrowBookUI();
                        borrowBookUI.borrowBookRun();
                        break;
                    case 2:
                        ReturnBookUI returnBookUI = new ReturnBookUI();
                        returnBookUI.returnBookRun();
                        break;
                    case 3:
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
