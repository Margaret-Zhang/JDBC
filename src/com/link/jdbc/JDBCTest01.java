package com.link.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest01 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// 注册驱动
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			// 获取连接
			String url = "jdbc:mysql://192.168.0.162:3306/bjpowernode?serverTimezone=GMT";
			String user = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);

			// 获取数据库操作对象
			stmt = conn.createStatement();

			// 执行SQL
			String sql = "insert into dept(deptno,dname,loc) values(50,'人事部','北京')";
			// 返回值是影响数据库的记录条数
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "保存成功！" : "保存失败！");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 关闭资源
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
