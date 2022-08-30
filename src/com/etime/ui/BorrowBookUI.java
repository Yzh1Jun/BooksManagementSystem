package com.etime.ui;

import com.etime.service.BookService;
import com.etime.service.BorrowService;
import com.etime.service.impl.BookServiceImpl;
import com.etime.service.impl.BorrowServiceImpl;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

public class BorrowBookUI {
    BorrowService borrowService = new BorrowServiceImpl();
    BookService bookService = new BookServiceImpl();
    BookSearchUI bookSearchUI = new BookSearchUI();
    public void borrowBookRun(){
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>借书还书>借书\n");
            System.out.println("              1.查询所有图书");
            System.out.println("              2.检索图书");
            System.out.println("              3.借书");
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
                    bookService.findAllBook(UserConfig.userRole);
                    break;
                case 2:
                    bookSearchUI.bookSearchRun();
                    break;
                case 3:
                    System.out.println("请输入要借的书的编号：");
                    int bookId = 0;
                    try {
                        bookId = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的书的编号类型不匹配");
                    }
                    System.out.println("请输入要借的数量：");
                    int number = 0;
                    try {
                        number = ScannerUtil.getScanner().nextInt();
                        if (number<=0){
                            System.out.println("借书的数量不能小于1");
                            return;
                        }
                    } catch (Exception e){
                        System.out.println("输入的书的数量类型不匹配");
                    }
                    borrowService.borrowBook(UserConfig.userId,bookId,number);
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
