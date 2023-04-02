package com.bjpowernode.oa.web.action;

import com.bjpowernode.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取应用的根路径
        String contextPath = request.getContextPath();

        // 设置响应的内容类型以及字符集。防止中文乱码
        response.setContentType("text/html;character=UTF-8");
        // 打印到页面上
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>部门列表页面</title>");
        out.print("    <style>");
        out.print("                table{");
        out.print("            background: #d5b770;");
        out.print("        }");
        out.print("    </style>");

        out.print("<script type='text/javascript'>");
        out.print("    function del(dno){");
        out.print("        if (window.confirm('亲，删了不可回复哦！')){");
        out.print("            document.location.href='/oa/dept/delete?deptno='+dno");
        out.print("        }");
        out.print("    }");
        out.print("</script>");

        out.print("</head>");
        out.print("<body>");
        out.print("    <h1 align='center'>部门列表</h1>");
        out.print("    <hr width='80%'>");
        out.print("    <table border='1' align='center' width=''>");
        out.print("        <tr>");
        out.print("            <th>序号</th>");
        out.print("            <th>部门编号</th>");
        out.print("            <th>部门名称</th>");
        out.print("            <th>操作</th>");
        out.print("        </tr>");

        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select deptno, dname, loc from dept";
            ps = conn.prepareStatement(sql);
            // 执行SQL语句
            rs = ps.executeQuery();
            // 处理结果集
            int i = 0;
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("        <tr>");
                out.print("            <td>" + (++i) + "</td>");  // 序号
                out.print("            <td>" + deptno + "</td>"); // 部门编号
                out.print("            <td>" + dname + "</td>");  // 部门名字
                out.print("            <td>");
                out.print("                <a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.print("                <a href='/oa/dept/edit?deptno="+deptno+"'>修改</a>");
                out.print("                <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.print("            </td>");
                out.print("        </tr>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }

        /* 下面是固定的 */
        out.print("    </table>");
        out.print("    <hr width='80%'>");
        out.print("    <a href='"+contextPath+"/add.html'>新增部门</a>");
        out.print("</body>");
        out.print("</html>");

    }
}