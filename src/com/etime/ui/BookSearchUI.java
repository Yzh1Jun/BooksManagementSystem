package com.etime.ui;

import com.etime.service.BookService;
import com.etime.service.impl.BookServiceImpl;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

public class BookSearchUI {

    public void bookSearchRun(){
        BookService bookService = new BookServiceImpl();
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>图书管理>检索图书\n");
            System.out.println();
            System.out.println("1.查询所有图书");
            System.out.println("2.通过图书名查询图书");
            System.out.println("3.通过图书类型查询图书");
            System.out.println("4.通过作者查询图书");
            System.out.println("5.返回");
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
                    System.out.println("请输入图书名称：");
                    String bookName = ScannerUtil.getScanner().next();
                    bookService.findBookByName(bookName,UserConfig.userRole);
                    break;
                case 3:
                    System.out.println("请输入图书类型：");
                    String typeName = ScannerUtil.getScanner().next();
                    bookService.findBookByType(typeName,UserConfig.userRole);
                    break;
                case 4:
                    System.out.println("请输入作者：");
                    String author = ScannerUtil.getScanner().next();
                    bookService.findBookByAuthor(author,UserConfig.userRole);
                    break;
                case 5:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        }while (isContinue);
    }
}
