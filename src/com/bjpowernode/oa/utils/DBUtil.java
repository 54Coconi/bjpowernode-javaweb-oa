package com.bjpowernode.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC 工具类
 */
public class DBUtil {

    // 静态变量：
    // 自上而下顺序执行
    // 属性资源绑定
    private static ResourceBundle bundle = ResourceBundle.getBundle("res.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    static {
        // System.out.println("加载 JDBC 驱动");
        // 注册驱动，只执行一次，放在静态代码快中
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return conn 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 获取连接
        Connection conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    // 获取数据库操作对象
    // 执行SQL语句
    // 处理查询结果集
    // 释放资源

    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=====end=====");
    }
}
