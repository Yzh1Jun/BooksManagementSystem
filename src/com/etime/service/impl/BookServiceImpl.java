package com.etime.service.impl;


import com.etime.bean.Book;
import com.etime.bean.BookType;
import com.etime.dao.BookDao;
import com.etime.dao.BookTypeDao;
import com.etime.service.BookService;
import com.etime.service.BookTypeService;
import com.etime.utils.RoleEnum;
import com.etime.utils.StateEnum;

import java.util.Iterator;
import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDao();
    BookTypeDao bookTypeDao = new BookTypeDao();
    BookTypeService bookTypeService = new BookTypeServiceImpl();
    @Override
    public List<Book> findAllBook(int role) {
        List<Book> allBook = bookDao.findAllBook(role);
        int typeId = 0;
        BookType bookType = null;
        //普通用户获得所有图书
        if (role== RoleEnum.ROLE_COMMON){
            Iterator<Book> iterator = allBook.iterator();
            while (iterator.hasNext()){
                Book book = iterator.next();
                String string = book.toString();
                typeId = book.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                String typeName = bookType.getTypeName();
                String str1 = "图书类型："+typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                int index = replace.indexOf(", 状态");
                String substring = replace.substring(index);
                replace= replace.replace(substring, "");
                System.out.println(replace);
            }
            return allBook;
        }else {
            //管理员获得所有图书
            for (Book b: allBook) {
                int state = b.getState();
                String str = "";
                if (state==0){
                    str = ", 使用中";
                }else {
                    str = ", 未使用";
                }
                String string = b.toString();
                typeId = b.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                String typeName = bookType.getTypeName();
                String str1 = "图书类型："+typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                System.out.println(replace+str);
            }
            return allBook;
        }
    }

    @Override
    public void insertBook(Book book) {
        boolean bookByBookName = bookDao.isExists(book.getBookName());
        if (bookByBookName){
            System.out.println("该图书已存在");
            return;
        }
        BookType bookTypeByTypeId = bookTypeService.findBookTypeByTypeId(book.getTypeId());
        if (bookTypeByTypeId == null){
            System.out.println("图书类型不存在");
            return;
        }
        int num = bookDao.insertBook(book);
        if (num>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }

    @Override
    public void deleteBook(int bookId, String inScan) {
        //判断图书是否已经被删除
        int state = bookDao.getBookState(bookId);
        if (state==1){
            System.out.println("该图书未使用，无法删除");
            return;
        }
        //判断图书是否存在
        Book book = bookDao.findBookById(bookId);
        if (book==null){
            System.out.println("该编号的图书不存在");
            return;
        }
        int i = 0;
        if ("Y".equals(inScan)||"y".equals(inScan)){
            i = bookDao.deleteBook(bookId);
        }
        if (i>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败，该编号的图书不存在");
        }
    }

    @Override
    public void updateBook(Book book) {
        try{
            int i = bookDao.updateBook(book);
            if (i>0){
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("输入的图书编号不存在");
        }
    }

    @Override
    public void setBookState(int bookId, int state) {

        if (state == StateEnum.STATE_CLOSE){
            int num = bookDao.setBookState(StateEnum.STATE_CLOSE, bookId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }else {
            int num = bookDao.setBookState(StateEnum.STATE_OPEN, bookId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }
    }

    @Override
    public void findBookByName(String bookName,int role) {
        List<Book> list = bookDao.findBookByName(bookName);
        if (list == null) {
            System.out.println("没有查到图书");
            return;
        }
        BookType bookType = null;
        int typeId = 0;
        String typeName = null;
        Iterator<Book> iterator = list.iterator();
        if (role == RoleEnum.ROLE_COMMON) {
            while (iterator.hasNext()) {
                Book book = iterator.next();
                String string = book.toString();
                typeId = book.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                typeName = bookType.getTypeName();
                String str1 = "图书类型：" + typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                int index = replace.indexOf(", 状态");
                String substring = replace.substring(index);
                replace = replace.replace(substring, "");
                System.out.println(replace);
            }
        } else {
            //管理员获得所有图书
            while (iterator.hasNext()) {
                Book book = iterator.next();
                int state = book.getState();
                String str = "";
                if (state == StateEnum.STATE_OPEN) {
                    str = ", 使用中";
                } else {
                    str = ", 未使用";
                }
                String string = book.toString();
                typeId = book.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                typeName = bookType.getTypeName();
                String str1 = "图书类型：" + typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                System.out.println(replace + str);
            }
        }
    }

    @Override
    public void findBookByType(String typeName,int role) {
        List<BookType> list1 = bookTypeDao.findBookTypeByName(typeName);
        if (list1 == null) {
            System.out.println("没有查到图书");
            return;
        }
        Iterator<BookType> iterator1 = list1.iterator();
        while (iterator1.hasNext()){
            int typeId = iterator1.next().getTypeId();
            List<Book> list = bookDao.findBookByTypeId(typeId);
            BookType bookType = null;
            Iterator<Book> iterator = list.iterator();
            if (role == RoleEnum.ROLE_COMMON) {
                while (iterator.hasNext()) {
                    Book book = iterator.next();
                    String string = book.toString();
                    typeId = book.getTypeId();
                    bookType = bookTypeService.findBookTypeByTypeId(typeId);
                    typeName = bookType.getTypeName();
                    String str1 = "图书类型：" + typeId;
                    String replace = string.replace(str1, "图书类型：" + typeName);
                    int index = replace.indexOf(", 状态");
                    String substring = replace.substring(index);
                    replace = replace.replace(substring, "");
                    System.out.println(replace);
                }
            } else {
                //管理员获得所有图书
                while (iterator.hasNext()) {
                    Book book = iterator.next();
                    int state = book.getState();
                    String str = "";
                    if (state == StateEnum.STATE_OPEN) {
                        str = ", 使用中";
                    } else {
                        str = ", 未使用";
                    }
                    String string = book.toString();
                    typeId = book.getTypeId();
                    bookType = bookTypeService.findBookTypeByTypeId(typeId);
                    typeName = bookType.getTypeName();
                    String str1 = "图书类型：" + typeId;
                    String replace = string.replace(str1, "图书类型：" + typeName);
                    System.out.println(replace + str);
                }
            }
        }
    }

    @Override
    public void findBookByAuthor(String author,int role) {
        List<Book> list = bookDao.findBookByAuthor(author);
        if (list == null) {
            System.out.println("没有查到图书");
            return;
        }
        BookType bookType = null;
        int typeId = 0;
        String typeName = null;
        Iterator<Book> iterator = list.iterator();
        if (role == RoleEnum.ROLE_COMMON) {
            while (iterator.hasNext()) {
                Book book = iterator.next();
                String string = book.toString();
                typeId = book.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                typeName = bookType.getTypeName();
                String str1 = "图书类型：" + typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                int index = replace.indexOf(", 状态");
                String substring = replace.substring(index);
                replace = replace.replace(substring, "");
                System.out.println(replace);
            }
        } else {
            //管理员获得所有图书
            while (iterator.hasNext()) {
                Book book = iterator.next();
                int state = book.getState();
                String str = "";
                if (state == StateEnum.STATE_OPEN) {
                    str = ", 使用中";
                } else {
                    str = ", 未使用";
                }
                String string = book.toString();
                typeId = book.getTypeId();
                bookType = bookTypeService.findBookTypeByTypeId(typeId);
                typeName = bookType.getTypeName();
                String str1 = "图书类型：" + typeId;
                String replace = string.replace(str1, "图书类型：" + typeName);
                System.out.println(replace + str);
            }
        }
    }
}