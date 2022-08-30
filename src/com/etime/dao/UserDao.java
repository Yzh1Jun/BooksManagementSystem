package com.etime.dao;

import com.etime.bean.State;
import com.etime.bean.User;
import com.etime.utils.DbUtil;
import com.etime.utils.StateEnum;

import java.util.Iterator;
import java.util.List;

// DBUtils
public class UserDao {
    //添加用户
    public Integer insertUser(User user){
        String sql = "insert into user(username,password,gender,age) values (?,?,?,?)";
        int result = DbUtil.executeUpdate(sql, user.getUserName(), user.getPassword(), user.getGender(), user.getAge());
        return result;
    }
    //修改用户
    public Integer updateUser(User user){
        String sql = "update user set username = ?,password = ?,gender = ?,age = ? where id =? and state = ?";
        int result = DbUtil.executeUpdate(sql, user.getUserName(),user.getPassword(),user.getGender(),user.getAge(),user.getId(), StateEnum.STATE_OPEN);
        return result;
    }
    //删除用户
    //状态码修改为1
    public Integer deleteUser(int userId){
        String sql = "update user set state = ? where id = ?";
//        String sql = "delete from user where id =?";
        int result = DbUtil.executeUpdate(sql, StateEnum.STATE_CLOSE, userId);
        return result;
    }
    //根据id查询用户
    public User findUserById(int userId){
        String sql = "select id,username,password,gender,age,state,role from user where id = ? and state = ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, userId, StateEnum.STATE_OPEN);
        Iterator<User> iterator = list.iterator();
        User user = null;
        while (iterator.hasNext()){
            user = iterator.next();
        }
        return user;
    }
    //获得所有用户
    public List<User> findAllUser(){
        String sql = "select id,username,password,gender,age,state,role from user";
        List<User> list = DbUtil.executeQuery(sql, User.class);
        return list;
    }
    public User login(String inputUserName, String inputPassword){
        String sql = "select id,username,password,gender,age,state,role from user where username=? and password=? and state = ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, inputUserName, inputPassword, StateEnum.STATE_OPEN);
        Iterator<User> iterator = list.iterator();
        User user = null;
        while (iterator.hasNext()){
            user = iterator.next();
        }
        return user;
    }
    public int register(User user){
        String sql = "insert into user (username,password,gender,age) values (?,?,?,?)";
        int result = DbUtil.executeUpdate(sql, user.getUserName(), user.getPassword(), user.getGender(), user.getAge());
        return result;
    }
    //注册判断用户名是否存在
    public boolean isExists(String inputUserName){
        String sql = "select id,username,password,gender,age,state,role from user where username=?";
        List<User> list = DbUtil.executeQuery(sql, User.class, inputUserName);
        Iterator<User> iterator = list.iterator();
        User user = null;
        while (iterator.hasNext()){
            user = iterator.next();
        }
        if (user!=null){
            return true;
        }
        return false;
    }
    //管理员设置用户的状态码（恢复用户）
    public int setUserState(int state,int userId){
        String sql = "update user set state = ? where id = ?";
        int result = DbUtil.executeUpdate(sql, state, userId);
        return result;
    }
    //获取用户状态
    public int getUserState(int userId){
        String sql = "select state from user where id = ?";
        List<State> list = DbUtil.executeQuery(sql, State.class, userId);
        Iterator<State> iterator = list.iterator();
        int state = -1;
        while (iterator.hasNext()){
            state = iterator.next().getState();
        }
        return state;
    }
    //获取用户权限
    public int getUserRoleByUserId(int userId){
        String sql = "select id,username,password,gender,age,state,role from user where id = ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, userId);
        Iterator<User> iterator = list.iterator();
        int role = -1;
        while(iterator.hasNext()){
            User user = iterator.next();
            role = user.getRole();
        }
        return role;
    }
    //管理员设置权限
    public int setUserRole(int role, int userId){
        String sql = "update user set role = ? where id = ?";
        int result = DbUtil.executeUpdate(sql, role, userId);
        return result;
    }
    //根据用户名和密码查询该用户状态码
    public int findStateByUserNameAndPwd(String userName,String password){
        String sql = "select id,username,password,gender,age,state,role from user where username = ? and password = ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, userName, password);
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            int state = iterator.next().getState();
            return state;
        }
        return -1;
    }
    //根据用户名检索用户
    public List<User> findUserByName(String username){
        String sql = "select id,username,password,gender,age,state,role from user where username like ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, "%"+username+"%");
        return list;
    }
    //根据性别检索用户
    public List<User> findUserByGender(String gender){
        String sql = "select id,username,password,gender,age,state,role from user where gender like ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, "%"+gender+"%");
        return list;
    }
    //找回用户通过username查询一个用户（state=0）启用中
    public User findIsExists(String username){
        User user = null;
        String sql = "select id,username,password,gender,age,state,role from user where username = ? and state = ?";
        List<User> list = DbUtil.executeQuery(sql, User.class, username, StateEnum.STATE_OPEN);
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            user = iterator.next();
        }
        return user;
    }
}
