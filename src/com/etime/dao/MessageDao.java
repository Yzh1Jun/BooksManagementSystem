package com.etime.dao;

import com.etime.bean.Message;
import com.etime.utils.DbUtil;
import com.etime.utils.RoleEnum;

import java.util.List;

public class MessageDao {
    UserDao userDao = new UserDao();
    //添加消息
    public Integer insertMessage(Message message){
        String sql = "insert into message(id,message) values (?,?)";
        int result = DbUtil.executeUpdate(sql, message.getId(),message.getMessage());
        return result;
    }
    //删除消息
    public Integer deleteMessage(int messageId){
        String sql = "delete from message where messageid = ?";
        int result = DbUtil.executeUpdate(sql, messageId);
        return result;
    }
    //查询消息
    public List<Message> findMessage(int id){
        int role = userDao.getUserRoleByUserId(id);
        if (role== RoleEnum.ROLE_ADMIN){
            String sql = "select messageid,id,message from message";
            List<Message> list = DbUtil.executeQuery(sql, Message.class);
            return list;
        } else {
            String sql = "select messageid,id,message from message where id = ?";
            List<Message> list = DbUtil.executeQuery(sql, Message.class , id);
            return list;
        }
    }
}
