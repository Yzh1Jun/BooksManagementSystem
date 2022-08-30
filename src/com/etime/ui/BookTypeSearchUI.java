package com.etime.ui;

import com.etime.service.BookTypeService;
import com.etime.service.impl.BookTypeServiceImpl;
import com.etime.utils.ScannerUtil;

public class BookTypeSearchUI {

    public void bookTypeSearchRun(){
        BookTypeService bookTypeService = new BookTypeServiceImpl();
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>图书类型管理>检索图书类型\n");
            System.out.println();
            System.out.println("1.查询所有图书类型");
            System.out.println("2.通过图书类型名查询图书类型");
            System.out.println("3.返回");
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
                    bookTypeService.findAllBookType();
                    break;
                case 2:
                    System.out.println("请输入图书类型名称：");
                    String typename = ScannerUtil.getScanner().next();
                    bookTypeService.findBookTypeByName(typename);
                    break;
                case 3:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        }while (isContinue);
    }
}
