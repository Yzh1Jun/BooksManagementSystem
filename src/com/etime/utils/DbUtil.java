package com.etime.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DbUtil {

    //创建数据库连接池
    private static DataSource dataSource = new ComboPooledDataSource("mysql");

    //获取数据库连接池
    public static DataSource getDataSource(){
        return dataSource;
    }
    //创建连接
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException("获取数据库连接失败");
        }
    }

    /**
     * 执行更新操作:增加、修改、删除
     * @param sql SQL语句
     * @param parameters SQL参数
     * @return 0失败、1成功
     */
    public static Integer executeUpdate(String sql,Object ...parameters){
        Connection connection = getConnection();
        QueryRunner queryRunner = new QueryRunner();
        Integer result = 0;
        try {
            if(parameters == null){
                result = queryRunner.update(connection, sql);
            }else{
                result = queryRunner.update(connection, sql, parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 执行更新操作:增加、修改、删除
     */
    public static Integer executeUpdate(String sql){
        Object [] parameters=null;
        return executeUpdate(sql, parameters);
    }

    /**
     * 执行查询操作
     * @param <T> 实体泛型
     * @param sql SQL语句
     * @param clazz 实体的Class
     * @param parameters SQL参数
     * @return 存放Bean的list
     */
    public static <T> List<T> executeQuery(String sql, Class<T> clazz, Object ...parameters){
        Connection connection = getConnection();
        List<T> list = null;
        try{
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<T> beanListHandler = new BeanListHandler<T>(clazz);
            if(parameters == null){
                list = queryRunner.query(connection, sql,beanListHandler);
            }else{
                list = queryRunner.query(connection, sql,beanListHandler,parameters);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 执行查询操作
     */
    public static <T> List<T> executeQuery(String sql, Class<T> clazz){
        Object [] parameters=null;
        return executeQuery(sql,clazz,parameters);
    }

    //释放连接
    public static void releaseConnection(Connection connection, Statement statement, ResultSet resultSet){
        DbUtils.closeQuietly(connection, statement, resultSet);
    }
}
