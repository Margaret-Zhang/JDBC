package com.link.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTest02 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 利用资源绑定器绑定属性配置文件，获取连接数据库的用户名信息
		ResourceBundle bundle = ResourceBundle.getBundle("mysql");
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String username = bundle.getString("username");
		String password = bundle.getString("password");

		try {
			// 注册驱动
			Class.forName(driver);

			// 获取连接
			conn = DriverManager.getConnection(url, username, password);

			// 获取数据库操作对象
			stmt = conn.createStatement();

			// 执行SQL语句
			String sql = "update dept set dname = '销售部', loc = '上海' where deptno = 50";
			System.out.println(stmt.executeUpdate(sql) == 1 ? "修改成功！" : "修改失败！");

			// 处理查询结果集
			sql = "select ENAME,SAL from emp";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString("ENAME") + " " + rs.getDouble("SAL"));
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
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
		}
	}
}
