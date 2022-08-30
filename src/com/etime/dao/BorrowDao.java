package com.etime.dao;

import com.etime.bean.Book;
import com.etime.bean.Borrow;
import com.etime.bean.BorrowRecord;
import com.etime.dto.BorrowRecordDto;
import com.etime.utils.DbUtil;
import com.etime.utils.StateEnum;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class BorrowDao {
    private static final BorrowDao borrowDao = new BorrowDao();
    //管理员查看所有借书情况
    public List<BorrowRecordDto> findAllBookBorrow(){
        String sql = "select u.id,u.username,count(*) as count from borrow as b join user as u on b.userid = u.id where b.state = ? and u.state = ? group by u.id";
        List<BorrowRecordDto> list = DbUtil.executeQuery(sql, BorrowRecordDto.class,StateEnum.STATE_OPEN,StateEnum.STATE_OPEN);
        return list;
    }
    //查询个人借书情况
    public List<BorrowRecord> findBookBorrowByUserId(int userId,int ...state){
        if (state.length==0){
            String sql = "select u.id,u.username,book.bookid,book.bookname,b.number as count,b.starttime,b.endtime,b.state from borrow as b\n" +
                    "left join user as u on b.userid = u.id\n" +
                    "left join book on b.bookid = book.bookid\n" +
                    "where u.id = ?";
            List<BorrowRecord> list = DbUtil.executeQuery(sql, BorrowRecord.class, userId);
            return list;
        } else {
            String sql = "select u.id,u.username,book.bookid,book.bookname,b.number as count,b.starttime,b.endtime,b.state from borrow as b\n" +
                    "left join user as u on b.userid = u.id\n" +
                    "left join book on b.bookid = book.bookid\n" +
                    "where u.id = ? and b.state = ?";
            List<BorrowRecord> list = DbUtil.executeQuery(sql, BorrowRecord.class, userId,state);
            return list;
        }
    }
    //用户借书
    public void borrowBook(Borrow borrow){
        QueryRunner qr1 = new QueryRunner(DbUtil.getDataSource());
        QueryRunner qr2 = new QueryRunner(DbUtil.getDataSource());
        try {
            int num = borrowDao.findBookNumberByBookId(borrow.getBookId());//总共可以借的书
            //图书是否存在
            if (num<=0){
                System.out.println("图书数量为空或者此图书编号不存在");
                return;
            }
            //查看数量
            if (borrow.getNumber()>num){
                System.out.println("数量超过最大值，借书失败");
                return;
            }
            //添加数据
            String inSQL = "insert into borrow (userid,bookid,number,starttime,state) values (?,?,?,?,?)";
            long time = new Date().getTime();
            String p = "yyyy-MM-dd HH:mm:ss";
            Locale locale = Locale.getDefault();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p,locale);
            String format = simpleDateFormat.format(time);
            Date startTime = new Date();
            try {
                startTime = simpleDateFormat.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp timestamp = new Timestamp(startTime.getTime());
            Object[] params = {borrow.getUserId(),borrow.getBookId(),borrow.getNumber(),timestamp,borrow.getState()};
            qr1.update(inSQL, params);
            //图书数量减少
            String outSQL ="update book set booknum = booknum - ? where bookid = ?";
            Object[] params1 = {borrow.getNumber(),borrow.getBookId()};
            qr2.update(outSQL, params1);
            System.out.println("借书成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //用户还书
    public void returnBook(Borrow borrow){
        QueryRunner qr1 = new QueryRunner(DbUtil.getDataSource());
        QueryRunner qr2 = new QueryRunner(DbUtil.getDataSource());
        try {
            //还书判断
            int num = borrowDao.findBookNumberByBookId(borrow.getUserId(), borrow.getBookId());//总共需要还的书
            if (borrow.getNumber()>num){
                System.out.println("还书失败，超过需要还的书的数量");
                return;
            }
            //设置数据
            int borrowId = borrowDao.getBorrowIdByUserIdAndBookId(borrow.getUserId(), borrow.getBookId());
            String inSQL = "update borrow set number = number - ? where borrowid = ?";
            Object[] params = {borrow.getNumber(),borrowId};
            qr1.update(inSQL, params);
            //图书数量增加
            String outSQL ="update book set booknum = booknum + ? where bookid = ?";
            Object[] params1 = {borrow.getNumber(),borrow.getBookId()};
            qr2.update(outSQL, params1);
            System.out.println("还书成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //从borrow中查询某个bookId的书的数量
    public int findBookNumberByBookId(int userId, int bookId){
        int borrowId = borrowDao.getBorrowIdByUserIdAndBookId(userId, bookId);
        String sql ="select borrowid,userid,bookid,number,starttime,endtime,state from borrow where borrowid = ? and state <> 1";
        List<Borrow> list = DbUtil.executeQuery(sql, Borrow.class,borrowId);
        Iterator<Borrow> iterator = list.iterator();
        int number = 0;
        while (iterator.hasNext()){
            number = iterator.next().getNumber();
        }
        return number;
    }
    //从book中查询某个bookId的书的数量
    public int findBookNumberByBookId(int bookId){
        String sql ="select bookid,bookname,typeid,author,booknum,state from book where bookid =?";
        List<Book> list = DbUtil.executeQuery(sql, Book.class, bookId);
        Iterator<Book> iterator = list.iterator();
        int bookNum = 0;
        while (iterator.hasNext()){
            bookNum = iterator.next().getBookNum();
        }
        return bookNum;
    }
    public int setState(int userId,int bookId,int state){
        String sql = "update borrow set state = ? where userid =? and bookid = ?";
        int result = DbUtil.executeUpdate(sql, state, userId, bookId);
        return result;
    }
    //根据userId和bookId获取borrowId
    public int getBorrowIdByUserIdAndBookId(int userId,int bookId){
        String sql = "select borrowid,userid,bookid,number,starttime,endtime,state from borrow where userid = ? and bookid = ? and state = ?";
        List<Borrow> list = DbUtil.executeQuery(sql, Borrow.class, userId, bookId, StateEnum.STATE_OPEN);
        if (list.size()>1){
            return -1;
        }
        Iterator<Borrow> iterator = list.iterator();
        while (iterator.hasNext()){
            Borrow borrow = iterator.next();
            int borrowId = borrow.getBorrowId();
            return borrowId;
        }
        return -1;
    }
    //如果borrowid的state为0，设置endTime
    public int setEndTimeByState(int borrowId){
        String sql = "update borrow set endtime = ? where borrowid = ? and state = ?";
        long time = new Date().getTime();
        String p = "yyyy-MM-dd HH:mm:ss";
        Locale locale = Locale.getDefault();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(p,locale);
        String format = simpleDateFormat.format(time);
        Date endTime = new Date();
        try {
            endTime = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp = new Timestamp(endTime.getTime());//获取还书时间
        int result = DbUtil.executeUpdate(sql, timestamp, borrowId, StateEnum.STATE_OPEN);
        return result;
    }
}
