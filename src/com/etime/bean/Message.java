package com.etime.bean;

public class Message {
    private int messageId;
    private int id;
    private String message;

    @Override
    public String toString() {
        return "消息编号："+messageId+", 用户编号："+id+", 消息内容："+message;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
    }

    public Message(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public Message(int messageId, int id, String message) {
        this.messageId = messageId;
        this.id = id;
        this.message = message;
    }
}
