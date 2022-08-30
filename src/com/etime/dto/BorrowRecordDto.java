package com.etime.dto;

import com.etime.bean.Borrow;

public class BorrowRecordDto extends Borrow {
    private int id;
    private String username;
    private int count;

    @Override
    public String toString() {
        return "用户id："+id+", 用户名："+username+", 借书书的种类："+count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BorrowRecordDto() {
    }

    public BorrowRecordDto(int id, String username, int count) {
        this.id = id;
        this.username = username;
        this.count = count;
    }
}
