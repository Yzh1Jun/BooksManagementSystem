package com.etime.service.impl;


import com.etime.bean.BookType;
import com.etime.dao.BookTypeDao;
import com.etime.service.BookTypeService;

import java.util.Iterator;
import java.util.List;

public class BookTypeServiceImpl implements BookTypeService {
    BookTypeDao bookTypeDao = new BookTypeDao();
    //查询图书类型是否存在
    @Override
    public BookType findBookTypeByTypeId(int typeId) {
        BookType bookTypeByTypename = bookTypeDao.findBookTypeByTypeId(typeId);
        if (bookTypeByTypename!=null){
            return bookTypeByTypename;
        }
        return null;
    }

    @Override
    public int findTypeIdByTypeName(String typeName) {
        int typeId = bookTypeDao.selectTypeIdByTypeName(typeName);
        return typeId;
    }

    @Override
    public void insertBookType(BookType bookType) {
        //判断图书类型是否存在
        int typeId = bookTypeDao.selectTypeIdByTypeName(bookType.getTypeName());
        if (typeId!=0){
            System.out.println("图书类型已存在");
            return;
        }
        int insert = bookTypeDao.insert(bookType);
        if (insert>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }

    @Override
    public void updateBookType(BookType bookType) {
        int update = bookTypeDao.update(bookType);
        if (update>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    @Override
    public void findBookTypeByName(String typeName) {
        List<BookType> list = bookTypeDao.findBookTypeByName(typeName);
        if (list==null){
            System.out.println("没有查到图书类型");
            return;
        }
        Iterator<BookType> iterator = list.iterator();
        while (iterator.hasNext()){
            BookType bookType = iterator.next();
            System.out.println(bookType);
        }
    }

    @Override
    public List<BookType> findAllBookType() {
        List<BookType> allBookType = bookTypeDao.getAllBookType();
        Iterator<BookType> iterator = allBookType.iterator();
        while (iterator.hasNext()){
            BookType bookType = iterator.next();
            System.out.println(bookType);
        }
        return allBookType;
    }
}
