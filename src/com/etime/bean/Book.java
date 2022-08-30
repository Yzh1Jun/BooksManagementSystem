package com.etime.bean;

public class Book {
    private int bookId;
    private String bookName;
    private int typeId;
    private String author;
    private int bookNum;
    private int state;//状态码 默认为0使用中 1为未使用

    @Override
    public String toString() {
        return "图书编号："+bookId+", 图书名称："+bookName+", 图书类型："+ typeId +", 作者："+author+", 数量："+bookNum+", 状态"+state;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Book() {
    }

    public Book(int bookId, String bookName, int typeId, String author, int bookNum , int state) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.typeId = typeId;
        this.author = author;
        this.bookNum = bookNum;
        this.state = state;
    }

    public Book(String bookName, int typeId, String author, int bookNum) {
        this.bookName = bookName;
        this.typeId = typeId;
        this.author = author;
        this.bookNum = bookNum;
    }
}
