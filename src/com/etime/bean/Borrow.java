package com.etime.bean;

import java.util.Date;

//借阅表
public class Borrow {
    private int borrowId;
    private int userId;
    private int bookId;
    private int number;
    private Date startTime;
    private Date endTime;
    private int state;//借阅状态码 默认为0未归还，1为已归还

    @Override
    public String toString() {
        return "Borrow{" +
                "borrowId=" + borrowId +
                ", userId=" + userId +
                ", bookId=" + bookId +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public Borrow() {
    }

    public Borrow(int userId, int bookId, int number, Date startTime, Date endTime, int state) {
        this.userId = userId;
        this.bookId = bookId;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
