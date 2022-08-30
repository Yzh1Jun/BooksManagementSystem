package com.etime.dao;

import com.etime.bean.BookType;
import com.etime.utils.DbUtil;

import java.util.Iterator;
import java.util.List;

public class BookTypeDao {

    //根据typeId查询图书类型
    public BookType findBookTypeByTypeId(int typeId){
        String sql = "select typeid,typename from booktype where typeid = ?";
        List<BookType> list = DbUtil.executeQuery(sql, BookType.class, typeId);
        Iterator<BookType> iterator = list.iterator();
        BookType bookType = null;
        while (iterator.hasNext()){
            bookType = iterator.next();
        }
        return bookType;
    }
    //根据typeName查图书类型id
    public int selectTypeIdByTypeName(String typeName){
        String sql = "select typeid,typename from booktype where typename = ?";
        List<BookType> list = DbUtil.executeQuery(sql, BookType.class, typeName);
        Iterator<BookType> iterator = list.iterator();
        int id = 0;
        while (iterator.hasNext()){
            BookType bookType = iterator.next();
            id = bookType.getTypeId();
        }
        return id;
    }
    //查询所有图书类型
    public List<BookType> getAllBookType(){
        String sql = "select typeid,typename from booktype";
        List<BookType> list = DbUtil.executeQuery(sql, BookType.class);
        return list;
    }
    //添加图书类型
    public int insert(BookType bookType){
        String sql = "insert into booktype (typename) values (?)";
        int result = DbUtil.executeUpdate(sql, bookType.getTypeName());
        return result;
    }
    //修改图书类型
    public int update(BookType bookType){
        String sql = "update booktype set typename = ? where typeid = ?";
        int result = DbUtil.executeUpdate(sql, bookType.getTypeName(),bookType.getTypeId());
        return result;
    }
    //根据图书类型名称检索图书类型
    public List<BookType> findBookTypeByName(String typeName){
        String sql = "select typeid,typename from booktype where typename like ?";
        List<BookType> list = DbUtil.executeQuery(sql, BookType.class, "%" + typeName + "%");
        return list;
    }
}
