package com.etime.service.impl;

import com.etime.bean.Message;
import com.etime.bean.User;
import com.etime.dao.MessageDao;
import com.etime.dao.UserDao;
import com.etime.service.MessageService;
import com.etime.utils.RoleEnum;
import com.etime.utils.UserConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    MessageDao messageDao = new MessageDao();
    UserDao userDao = new UserDao();
    @Override
    public void insertMessage(Message message) {
        int result = messageDao.insertMessage(message);
        if (result>0){
            System.out.println("消息添加成功");
        } else {
            System.out.println("消息添加失败");
        }
    }

    @Override
    public void deleteMessage(int messageId) {
        int result = messageDao.deleteMessage(messageId);
        if (result>0){
            System.out.println("消息删除成功");
        } else {
            System.out.println("消息删除失败");
        }
    }

    @Override
    public List<String> findMessage(int id) {
        List<Message> list = messageDao.findMessage(id);
        ArrayList<String> messages = new ArrayList<>();
        Iterator<Message> iterator = list.iterator();
        int role = userDao.getUserRoleByUserId(id);
        if (role == RoleEnum.ROLE_ADMIN){
            while (iterator.hasNext()){
                Message message = iterator.next();
                String string = message.toString();
                messages.add(string);
            }
            return messages;
        } else {
            while (iterator.hasNext()){
                Message message = iterator.next();
                String string = message.toString();
                int index = string.indexOf(", 用户编号");
                String substring = string.substring(0, index+1);
                String replace = string.replace(substring, "");
                int index1 = replace.indexOf(", 消息内容");
                String substring1 = replace.substring(0, index1 + 1);
                User user = userDao.findUserById(UserConfig.userId);
                String replace1 = replace.replace(substring1, "用户名：" + user.getUserName());
                messages.add(replace1);
            }
            return messages;
        }
    }
}
