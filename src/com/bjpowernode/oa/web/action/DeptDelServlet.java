package com.bjpowernode.oa.web.action;


import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeptDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 根据部门编号，删除部门
        // 获取部门编号
        String deptno = request.getParameter("deptno");
        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;

        int count = 0;
        try {
            conn = DBUtil.getConnection();
            // 开启事务，关闭自动提交
            conn.setAutoCommit(false);
            String sql = "delete from dept where deptno=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            // 返回值：表示影响了数据库表中多少条记录
            count = ps.executeUpdate();
            // 事务提交
            conn.commit();

        } catch (SQLException e) {
            // 遇到异常要回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }

        // 判断是否删除成功
        if (count == 1) {
            // 删除成功
            // 跳转到list页面(这里用转发)
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            // 删除失败
            request.getRequestDispatcher("/error.html").forward(request,response);
        }
    }
}
