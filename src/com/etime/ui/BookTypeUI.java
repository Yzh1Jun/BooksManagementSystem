package com.etime.ui;

import com.etime.bean.BookType;
import com.etime.service.BookTypeService;
import com.etime.service.impl.BookTypeServiceImpl;
import com.etime.utils.ScannerUtil;

public class BookTypeUI {
    BookTypeService bookTypeService = new BookTypeServiceImpl();
    BookTypeSearchUI bookTypeSearchUI = new BookTypeSearchUI();
    public void bookTypeRun(){
        boolean isContinue = true;
        BookType bookType = null;
        int index = 0;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>图书类型管理\n");
            bookTypeService.findAllBookType();
            System.out.println();
            System.out.println("1.检索图书类型");
            System.out.println("2.添加图书类型");
            System.out.println("3.修改图书类型");
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
                    bookTypeSearchUI.bookTypeSearchRun();
                    break;
                case 2:
                    System.out.println("请输入要添加的图书类型：");
                    String typeName = ScannerUtil.getScanner().next();
                    bookType = new BookType(typeName);
                    bookTypeService.insertBookType(bookType);
                    break;
                case 3:
                    System.out.println("请输入要修改的图书类型编号：");
                    try {
                        index = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的图书类型编号不匹配");
                    }
                    bookType = bookTypeService.findBookTypeByTypeId(index);
                    if (bookType==null){
                        System.out.println("输入的图书类型编号不存在");
                        continue;
                    }
                    System.out.println("请输入新的图书类型：("+bookType.getTypeName()+")");
                    String typeName1 = ScannerUtil.getScanner().next();
                    bookType.setTypeName(typeName1);
                    bookTypeService.updateBookType(bookType);
                    break;
                case 4:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        } while (isContinue);
    }
}
