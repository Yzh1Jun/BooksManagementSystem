package com.etime.service;

import com.etime.bean.Message;

import java.util.List;

public interface MessageService {
    void insertMessage(Message message);
    void deleteMessage(int messageId);
    List<String> findMessage(int id);
}
