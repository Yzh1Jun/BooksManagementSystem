package com.etime.service.impl;


import com.etime.bean.Book;
import com.etime.bean.Borrow;
import com.etime.bean.BorrowRecord;
import com.etime.bean.User;
import com.etime.dao.BookDao;
import com.etime.dao.BorrowDao;
import com.etime.dao.UserDao;
import com.etime.dto.BorrowRecordDto;
import com.etime.service.BorrowService;
import com.etime.utils.StateEnum;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    BorrowDao borrowDao = new BorrowDao();
    BookDao bookDao = new BookDao();
    UserDao userDao = new UserDao();
    Book book = null;
    @Override
    public List<BorrowRecordDto> findAllUserAndBookBorrow() {
        List<BorrowRecordDto> list = borrowDao.findAllBookBorrow();
        if (list.size()==0){
            System.out.println("所有书均已还清");
        }
        Iterator<BorrowRecordDto> iterator = list.iterator();
        while (iterator.hasNext()){
            BorrowRecordDto borrowRecordDto = iterator.next();
            System.out.println(borrowRecordDto);
        }
        return list;
    }

    @Override
    public void findBookBorrowByUserId(int userId) {
        //判断用户是否存在
        User user = userDao.findUserById(userId);
        if (user == null){
            System.out.println("此用户不存在");
            return;
        }
        //判断用户是否借书
        List<BorrowRecord> list = borrowDao.findBookBorrowByUserId(userId);
        if (list.size()==0){
            System.out.println("用户没有借过书");
            return;
        }
        Iterator<BorrowRecord> iterator = list.iterator();
        while (iterator.hasNext()){
            BorrowRecord borrowRecord = iterator.next();
            String string = borrowRecord.toString();
            String replace = string.replace("null", "");
            System.out.println(replace);
        }
    }
    //用户借书
    @Override
    public void borrowBook(int userId, int bookId, int number) {
        //判断该书存不存在
        book = bookDao.findBookById(bookId);
        if (book == null){
            System.out.println("输入的编号的书不存在");
            return;
        }
        int state = StateEnum.STATE_OPEN;
        Borrow borrow = new Borrow(userId, bookId, number, null, null, state);
        int num = borrowDao.findBookNumberByBookId(userId, bookId);
        if (num==0){
            borrowDao.borrowBook(borrow);
        }else {
            System.out.println("该书您已经接过了还没归还");
        }
    }

    @Override
    public List<BorrowRecord> userFindBookBorrowByUserId(int userId,int ...state) {
        List<BorrowRecord> list = new LinkedList<>();
        if (state.length==1){
            list = borrowDao.findBookBorrowByUserId(userId, state);
        }else{
            list = borrowDao.findBookBorrowByUserId(userId);
        }
        if (list.size()==0){
            System.out.println("您没有需要还的书");
            return null;
        }
        Iterator<BorrowRecord> iterator = list.iterator();
        while (iterator.hasNext()){
            BorrowRecord borrowRecord = iterator.next();
            Date endTime = borrowRecord.getEndTime();
            String string = borrowRecord.toString();
            int index = 0;
            String str = "";
            index = string.indexOf("用户名");
            String substring = string.substring(0, index);
            String replace = string.replace(substring, "");
            if (endTime==null){
                replace = replace.replace("null", "\t\t");
                str = ", 未归还";
            }else {
                str = ", 已归还";
            }
            System.out.println(replace+str);
        }
        return list;
    }
    //用户还书
    @Override
    public void returnBook(int userId, int bookId, int number) {
        //判断书是否存在
        book = bookDao.findBookById(bookId);
        if (book == null){
            System.out.println("输入的编号的书不存在");
            return;
        }
        //判断用户是否借过这本书
        List<BorrowRecord> list = borrowDao.findBookBorrowByUserId(userId);
        int bookId1 = 0;
        for (BorrowRecord b: list
             ) {
            bookId1 = b.getBookId();
            if (bookId1==bookId){
                break;
            }
            bookId1 = 0;
        }
        if (bookId1==0){
            System.out.println("您没有借这本书");
            return;
        }
        int state= StateEnum.STATE_OPEN;
        //还书
        Borrow borrow = new Borrow(userId, bookId, number, null, null, state);
        borrowDao.returnBook(borrow);
        int num = borrowDao.findBookNumberByBookId(userId, bookId);
        int borrowId = borrowDao.getBorrowIdByUserIdAndBookId(userId, bookId);
        if (num==0){
            state = StateEnum.STATE_CLOSE;
            borrowDao.setEndTimeByState(borrowId);
            borrowDao.setState(userId, bookId, state);
        }
    }
}
