package com.etime.ui;

import com.etime.bean.Book;
import com.etime.bean.BookType;
import com.etime.dao.BookDao;
import com.etime.service.BookService;
import com.etime.service.BookTypeService;
import com.etime.service.impl.BookServiceImpl;
import com.etime.service.impl.BookTypeServiceImpl;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

public class BookUI {
    BookService bookService = new BookServiceImpl();
    BookTypeService bookTypeService = new BookTypeServiceImpl();
    BookDao bookDao = new BookDao();
    BookSearchUI bookSearchUI = new BookSearchUI();
    public void bookRun(){
        boolean isContinue = true;
        Book book = null;
        int index = 0;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>图书管理\n");
            bookService.findAllBook(UserConfig.userRole);
            System.out.println();
            System.out.println("1.检索图书");
            System.out.println("2.添加图书");
            System.out.println("3.修改图书");
            System.out.println("4.删除图书");
            System.out.println("5.设置图书状态码");
            System.out.println("6.返回");
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
                    bookSearchUI.bookSearchRun();
                    break;
                case 2:
                    System.out.println("请输入图书名称：");
                    String bookName = ScannerUtil.getScanner().next();
                    System.out.println("请输入图书类型：");
                    String typeName = ScannerUtil.getScanner().next();
                    int bookType = bookTypeService.findTypeIdByTypeName(typeName);
                    System.out.println("请输入图书作者：");
                    String author = ScannerUtil.getScanner().next();
                    System.out.println("请输入图书数量：");
                    int bookNum = 0;
                    try{
                        bookNum = ScannerUtil.getScanner().nextInt();
                    }catch (Exception e){
                        System.out.println("输入的数量的类型不匹配");
                    }
                    book = new Book(bookName, bookType, author, bookNum);
                    bookService.insertBook(book);
                    break;
                case 3:
                    System.out.println("请输入要修改的图书的编号：");
                    try{
                        index = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的编号类型不匹配");
                    }
                    book = bookDao.findBookById(index);
                    System.out.println("请输入图书名称("+book.getBookName()+")");
                    String bookName1 = ScannerUtil.getScanner().next();
                    BookType bookTypeByTypeId = bookTypeService.findBookTypeByTypeId(book.getTypeId());
                    String type = bookTypeByTypeId.getTypeName();
                    System.out.println("请输入图书类型("+type+")");
                    String typeName1 = ScannerUtil.getScanner().next();
                    int bookType1 = bookTypeService.findTypeIdByTypeName(typeName1);
                    System.out.println("请输入图书作者("+book.getAuthor()+")");
                    String author1 = ScannerUtil.getScanner().next();
                    System.out.println("请输入图书数量("+book.getBookNum()+")");
                    int bookNum1 = ScannerUtil.getScanner().nextInt();
                    book.setBookName(bookName1);
                    book.setTypeId(bookType1);
                    book.setAuthor(author1);
                    book.setBookNum(bookNum1);
                    bookService.updateBook(book);
                    break;
                case 4:
                    System.out.println("请输入要删除的图书的编号：");
                    try{
                        index = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的图书编号类型不匹配");
                    }
                    System.out.println("(*)是否真的删除?(Y/N)");
                    String next = ScannerUtil.getScanner().next();
                    bookService.deleteBook(index,next);
                    break;
                case 5:
                    System.out.println("请输入要修改的图书编号：");
                    try {
                        index = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的用户编号类型不匹配");
                    }
                    System.out.println("请输入状态码：(0：使用中，1：未使用)");
                    int state = 0;
                    try {
                        state = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e1){
                        System.out.println("输入的状态码类型不匹配");
                    }
                    bookService.setBookState(index,state);
                    break;
                case 6:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        }while (isContinue);
    }
}
