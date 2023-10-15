package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user/login")
public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from t_user where loginName =? and loginPwd=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             DBUtil.close(conn,ps,rs);
        }

        if (success){
            // 成功，跳转到部门页面(这里用转发)
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 失败, 跳转到失败页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
