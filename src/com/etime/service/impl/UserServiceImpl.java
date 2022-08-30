package com.etime.service.impl;


import com.etime.bean.User;
import com.etime.dao.UserDao;
import com.etime.service.UserService;
import com.etime.utils.RoleEnum;
import com.etime.utils.StateEnum;

import java.util.Iterator;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

    //显示用户信息进行操作
    @Override
    public User findUser(int userId) {
        int state = userDao.getUserState(userId);
        int role = userDao.getUserRoleByUserId(userId);
        String stateStr = "";
        String roleStr = "";
        if (role== RoleEnum.ROLE_ADMIN){
            List<User> list = userDao.findAllUser();
            Iterator<User> iterator = list.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                int role1 = user.getRole();
                if (role1== RoleEnum.ROLE_ADMIN){
                    roleStr = ", 权限：管理员";
                }else {
                    roleStr = ", 权限：普通用户";
                }
                if (state== StateEnum.STATE_OPEN){
                    stateStr = ", 使用中";
                }else {
                    stateStr = ", 未使用";
                }
                System.out.println(user+stateStr+roleStr);
            }
        }else {
            //用户显示个人信息
            User user = userDao.findUserById(userId);
            String str = user.toString();
            int index = str.indexOf("用户名");
            int indexOf = str.indexOf(", 状态");
            String substring = str.substring(0, index);
            String substring1 = str.substring(indexOf);
            str = str.replace(substring, "");
            str = str.replace(substring1, "");
            System.out.println(str);
            return user;
        }
        return null;
    }
    //修改用户信息
    @Override
    public void updateUser(User user) {
        int result = userDao.updateUser(user);
        if (result > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Override
    public void insertUser(User user) {
        //判断user是否存在
        boolean judge = userDao.isExists(user.getUserName());
        if (judge){
            System.out.println("用户已存在，无法再次添加");
            return;
        }
        int result = userDao.insertUser(user);
        if (result > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Override
    public void deleteUser(int userId, String inScan) {
        //判断用户是否已经被删除
        int state = userDao.getUserState(userId);
        if (state==StateEnum.STATE_CLOSE){
            System.out.println("该用户没有使用，无法删除");
        }
        //判断用户是否存在
        User user = userDao.findUserById(userId);
        if (user == null){
            System.out.println("该编号的用户不存在");
            return;
        }
        int result = 0;
        if ("Y".equals(inScan) || "y".equals(inScan)) {
            result = userDao.deleteUser(userId);
        }
        if (result > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    @Override
    public void setUserState(int userId, int state) {
        if (state == StateEnum.STATE_CLOSE){
            int num = userDao.setUserState(StateEnum.STATE_CLOSE, userId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }else {
            int num = userDao.setUserState(StateEnum.STATE_OPEN, userId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }
    }

    @Override
    public int findUserRoleById(int userId) {
        int role = userDao.getUserRoleByUserId(userId);
        if (role==-1){
            System.out.println("获取权限失败");
        }
        return role;
    }

    @Override
    public void setUserRole(int role, int userId) {
        if (role == RoleEnum.ROLE_ADMIN){
            int num = userDao.setUserRole(RoleEnum.ROLE_ADMIN, userId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }else {
            int num = userDao.setUserRole(RoleEnum.ROLE_COMMON, userId);
            if (num>0){
                System.out.println("设置成功");
            }else {
                System.out.println("设置失败");
            }
        }
    }

    @Override
    public void findUserByName(String username) {
        List<User> list = userDao.findUserByName(username);
        if (list==null){
            System.out.println("没有查到用户");
            return;
        }
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            String stateStr = "";
            String roleStr = "";
            User user = iterator.next();
            int role = user.getRole();
            int state = user.getState();
            if (role== RoleEnum.ROLE_ADMIN){
                roleStr = ", 权限：管理员";
            }else {
                roleStr = ", 权限：普通用户";
            }
            if (state== StateEnum.STATE_OPEN){
                stateStr = ", 使用中";
            }else {
                stateStr = ", 未使用";
            }
            System.out.println(user+stateStr+roleStr);
        }
    }

    @Override
    public void findUserByGender(String gender) {
        List<User> list = userDao.findUserByGender(gender);
        if (list==null){
            System.out.println("没有查到用户");
            return;
        }
        Iterator<User> iterator = list.iterator();
        while (iterator.hasNext()){
            String stateStr = "";
            String roleStr = "";
            User user = iterator.next();
            int role = user.getRole();
            int state = user.getState();
            if (role== RoleEnum.ROLE_ADMIN){
                roleStr = ", 权限：管理员";
            }else {
                roleStr = ", 权限：普通用户";
            }
            if (state== StateEnum.STATE_OPEN){
                stateStr = ", 使用中";
            }else {
                stateStr = ", 未使用";
            }
            System.out.println(user+stateStr+roleStr);
        }
    }

    @Override
    public User findIsExists(String username) {
        User user = null;
        user = userDao.findIsExists(username);
        return user;
    }


}
