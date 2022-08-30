package com.etime.dao;

import com.etime.bean.Book;
import com.etime.bean.State;
import com.etime.utils.DbUtil;
import com.etime.utils.StateEnum;

import java.util.Iterator;
import java.util.List;

public class BookDao {
    //添加图书
    public int insertBook(Book book){
        String sql = "insert into book (bookname,typeid,author,booknum) values (?,?,?,?)";
        int result = DbUtil.executeUpdate(sql, book.getBookName(), book.getTypeId(), book.getAuthor(), book.getBookNum());
        return result;
    }

    //修改图书
    public int updateBook(Book book){
        String sql = "update book set bookname = ?,typeid = ?,author = ?,booknum = ? where bookid =? and state = ?";
        int result = DbUtil.executeUpdate(sql, book.getBookName(), book.getTypeId(), book.getAuthor(), book.getBookNum(), book.getBookId(),StateEnum.STATE_OPEN);
        return result;
    }
    //删除图书
    //将状态码设置为1
    public int deleteBook(int bookId){
        String sql = "update book set state = ? where bookid = ?";
//        String sql = "delete from book where bookid =?";
        int result = DbUtil.executeUpdate(sql, StateEnum.STATE_CLOSE, bookId);
        return result;
    }
    //查询所有图书
    public List<Book> findAllBook(int role){
        if (role==0){
            //用户查询所有图书
            String sql = "select bookid,bookname,typeid,author,booknum,state from book where state = ?";
            List<Book> list = DbUtil.executeQuery(sql, Book.class, StateEnum.STATE_OPEN);
            return list;
        }else {
            //管理员查询所有图书
            String sql = "select bookid,bookname,typeid,author,booknum,state from book";
            List<Book> list = DbUtil.executeQuery(sql, Book.class);
            return list;
        }
    }
    //判断图书是否存在
    public boolean isExists(String bookName){
        String sql="select bookid,bookname,typeid,author,booknum,state from book where bookname = ? and state = ?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, bookName,StateEnum.STATE_OPEN);
        Iterator<Book> iterator = list.iterator();
        Book book = null;
        while (iterator.hasNext()){
            book = iterator.next();
        }
        if (book!=null){
            return true;
        }
        return false;
    }
    //根据id查询图书
    public Book findBookById(int bookId){
        String sql = "select bookid,bookname,typeid,author,booknum,state from book where bookid = ? and state = ?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, bookId,StateEnum.STATE_OPEN);
        Iterator<Book> iterator = list.iterator();
        Book book = null;
        while (iterator.hasNext()){
            book = iterator.next();
        }
        return book;
    }
    //管理员设置图书状态
    public int setBookState(int bookId,int state){
        String sql = "update book set state = ? where bookid = ?";
        int result = DbUtil.executeUpdate(sql, bookId, state);
        return result;
    }
    //获取图书状态
    public int getBookState(int bookId){
        String sql = "select state from book where bookid = ?";
        List<State> list = DbUtil.executeQuery(sql, State.class, bookId);
        Iterator<State> iterator = list.iterator();
        int state = -1;
        while (iterator.hasNext()){
            state = iterator.next().getState();
        }
        return state;
    }
    //通过图书名检索图书
    public List<Book> findBookByName(String bookName){
        String sql= "select bookid,bookname,typeid,author,booknum,state from book where bookname like ?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, "%" + bookName + "%");
        return list;
    }
    //通过图书类型检索图书
    public List<Book> findBookByTypeId(int typeId){
        String sql= "select bookid,bookname,typeid,author,booknum,state from book where typeid like ?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, "%" + typeId + "%");
        return list;
    }
    //通过作者检索图书
    public List<Book> findBookByAuthor(String author){
        String sql= "select bookid,bookname,typeid,author,booknum,state from book where author like ?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, "%" + author + "%");
        return list;
    }
}
