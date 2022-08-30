package com.etime.bean;

import java.util.Date;

public class BorrowRecord {
    private int id;
    private String username;
    private int bookId;
    private String bookName;
    private int count;
    private Date startTime;
    private Date endTime;
    private int state;

    @Override
    public String toString() {
        return "用户id："+id+", 用户名："+username+", 图书编号："+bookId+", 图书名："+bookName
                +", 数量"+count+", 借书时间："+startTime+", 还书时间："+endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public BorrowRecord() {
    }

    public BorrowRecord(int id, String username, int bookId, String bookName, int count, Date startTime, Date endTime, int state) {
        this.id = id;
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.count = count;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
