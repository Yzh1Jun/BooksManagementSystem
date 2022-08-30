package com.etime.service.impl;


import com.etime.bean.User;
import com.etime.dao.UserDao;
import com.etime.service.LoginService;
import com.etime.utils.ScannerUtil;
import com.etime.utils.StateEnum;

public class LoginServiceImpl implements LoginService {
    UserDao userDao = new UserDao();

    @Override
    public int login(String userName, String password) {
        User user = userDao.login(userName, password);
        int id = 0;
        if (user != null) {
            id = user.getId();
        } else {
            int state = userDao.findStateByUserNameAndPwd(userName, password);
            if (state== StateEnum.STATE_CLOSE){
                System.out.println("该用户已停用");
            }
        }
        return id;
    }

    @Override
    public boolean register(User user) {
        boolean userJudge = userDao.isExists(user.getUserName());

        if (!userJudge) {
            int register = userDao.register(user);
            if (register > 0) {
                System.out.println("注册成功");
                return true;
            } else {
                System.out.println("注册失败");
                return false;
            }
        } else {
            System.out.println("用户名已存在,请直接登录");
        }
        return false;
    }

    @Override
    public boolean exit() {
        System.out.println("确定吗?(Y/N)");
        String next = ScannerUtil.getScanner().next();
        if ("Y".equals(next) || "y".equals(next)) {
            return true;
        }
        return false;
    }
}
