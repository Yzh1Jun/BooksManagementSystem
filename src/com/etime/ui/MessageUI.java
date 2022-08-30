package com.etime.ui;

import com.etime.bean.Message;
import com.etime.bean.User;
import com.etime.dao.UserDao;
import com.etime.service.MessageService;
import com.etime.service.impl.MessageServiceImpl;
import com.etime.utils.ScannerUtil;
import com.etime.utils.UserConfig;

import java.util.Iterator;
import java.util.List;

public class MessageUI {
    public void messageRun(){
        MessageService messageService = new MessageServiceImpl();
        UserDao userDao = new UserDao();
        boolean isContinue = true;
        do {
            System.out.println("--------------欢迎使用图书管理系统--------------");
            System.out.println("目前位置    主页>借阅管理>消息管理\n");
            System.out.println("              1.查看所有消息");
            System.out.println("              2.删除消息");
            System.out.println("              3.给用户发送消息");
            System.out.println("              4.返回");
            System.out.println("请输入编号:");
            int nextInt = 0;
            try {
                nextInt = ScannerUtil.getScanner().nextInt();
            } catch (Exception e){
                System.out.println("输入的数的类型不匹配");
                continue;
            }
            switch (nextInt){
                case 1:
                    List<String> list = messageService.findMessage(UserConfig.userId);
                    Iterator<String> iterator = list.iterator();
                    while (iterator.hasNext()){
                        String next = iterator.next();
                        System.out.println(next);
                    }
                    break;
                case 2:
                    System.out.println("请输入要删除的信息的编号：");
                    int messageId = 0;
                    try{
                        messageId = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的信息编号类型不匹配");
                    }
                    messageService.deleteMessage(messageId);
                    break;
                case 3:
                    System.out.println("请输入要发送的用户编号");
                    int userId = 0;
                    try{
                        userId = ScannerUtil.getScanner().nextInt();
                    } catch (Exception e){
                        System.out.println("输入的用户编号类型不匹配");
                    }
                    User user = userDao.findUserById(userId);
                    if (user==null){
                        System.out.println("该用户编号不存在");
                        continue;
                    }
                    System.out.println("请输入要发送的消息");
                    String messageStr = ScannerUtil.getScanner().next();
                    Message message = new Message(userId, messageStr);
                    messageService.insertMessage(message);
                    break;
                case 4:
                    isContinue = false;
                    break;
                default:
                    System.out.println("输入的数不符合规范请重新输入");
                    break;
            }
        } while (isContinue);
    }
}
