package com.etime;

import com.etime.ui.LoginUI;

/**
 * BooksManagementSystem Version:0.9.0.2 （未使用DBUtils的版本）
 */

/**
 * BooksManagementSystem Version:0.9.0.3 （未使用DBUtils的版本）
 * 1.修复了某些int类型输入不当时会报错的bug
 * 2.优化了线程睡眠1秒
 * 3.添加了一个新的BookType类以及新增了一张booktype表
 * 4.优化了图书管理的类型（受到限制）
 */

/**
 * BooksManagementSystem Version:0.9.0.4 （未使用DBUtils的版本）
 * 1.优化了图书管理增加时的图书存在判断
 */

/**
 * BooksManagementSystem Version:0.9.1.1 （使用DBUtils的版本）
 * 1.将Dao层以DbUtil框架的方式实现，减少了代码冗余
 * 2.优化了实现类的代码的逻辑
 */

/**
 * BooksManagementSystem Version:0.9.1.2 （使用DBUtils的版本）
 * 1.为User类与Book类增加了state属性，使删除语句不再真正删除数据，而是将状态码设置为1，使普通用户无法看见
 * 2.增加了管理员修改状态码的接口
 */

/**
 * BooksManagementSystem Version:0.9.1.3 （使用DBUtils的版本）
 * 1.增加了删除操作时的逻辑
 * 2.优化了所有输入编号操作时的逻辑
 */

/**
 * BooksManagementSystem Version:0.9.1.4 （使用DBUtils的版本）
 * 1.增加了用户角色字段，修改了对角色判断的逻辑
 * 2.修改了图书和图书类型的连接
 * 3.借阅表增加一个id字段
 * 4.还书没还完 ，显示未归还
 */

/**
 * BooksManagementSystem Version:0.9.2.1 （使用DBUtils的版本）
 * 1.对Service层进行优化
 */

/**
 *BooksManagementSystem Version:0.9.2.2 （使用DBUtils的版本）
 *1.新增对图书类型的增改
 *2.解决解决存入数据库的Date数据精度丢失 Timestamp
 *3.优化了方法冗余和方法的命名规范
 *4.Admin和普通用户使用两套UI
*/


/**
 * BooksManagementSystem Version:0.9.0.3 （未使用DBUtils的版本）
 * 1.修复了某些int类型输入不当时会报错的bug
 * 2.优化了线程睡眠1秒
 * 3.添加了一个新的BookType类以及新增了一张booktype表
 * 4.优化了图书管理的类型（受到限制）
 */

/**
 * BooksManagementSystem Version:0.9.0.4 （未使用DBUtils的版本）
 * 1.优化了图书管理增加时的图书存在判断
 */

/**
 * BooksManagementSystem Version:0.9.1.1 （使用DBUtils的版本）
 * 1.将Dao层以DbUtil框架的方式实现，减少了代码冗余
 * 2.优化了实现类的代码的逻辑
 */

/**
 * BooksManagementSystem Version:0.9.1.2 （使用DBUtils的版本）
 * 1.为User类与Book类增加了state属性，使删除语句不再真正删除数据，而是将状态码设置为1，使普通用户无法看见
 * 2.增加了管理员修改状态码的接口
 */

/**
 * BooksManagementSystem Version:0.9.1.3 （使用DBUtils的版本）
 * 1.增加了删除操作时的逻辑
 * 2.优化了所有输入编号操作时的逻辑
 */

/**
 * BooksManagementSystem Version:0.9.1.4 （使用DBUtils的版本）
 * 1.增加了用户角色字段，修改了对角色判断的逻辑
 * 2.修改了图书和图书类型的连接
 * 3.借阅表增加一个id字段
 * 4.还书没还完 ，显示未归还
 */

/**
 * BooksManagementSystem Version:0.9.2.1 （使用DBUtils的版本）
 * 1.对Service层进行优化
 */

/**
 * BooksManagementSystem Version:0.9.2.2 （使用DBUtils的版本）
 * 1.新增对图书类型的增改
 * 2.解决解决存入数据库的Date数据精度丢失 Timestamp
 * 3.优化了方法冗余和方法的命名规范
 * 4.Admin和普通用户使用两套UI
 */

/**
 * BooksManagementSystem Version:0.9.2.3 （使用DBUtils的版本）
 * 1.新增对用户，图书，图书类型的检索功能
 * 2.新增普通用户查看自己未还图书的选项
 * 3.给密码加密，并对用户名和密码进行限制
 * 4.增加验证码
 */

/**
 * BooksManagementSystem Version:0.9.2.4 （使用DBUtils的版本）
 * 1.新增消息功能
 */
public class Application {
    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.run();
    }
}
