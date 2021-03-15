package com.link.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCTest03 {
	@SuppressWarnings("null")
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;

		// 利用资源绑定器绑定属性配置文件，获取连接数据库的用户名信息
		ResourceBundle bundle = ResourceBundle.getBundle("mysql");
		String url = bundle.getString("url");
		String username = bundle.getString("username");
		String password = bundle.getString("password");

		try {
			// 获取连接对象
			conn = DriverManager.getConnection(url, username, password);

			// 关闭自动提交事务
			conn.setAutoCommit(false);
			System.out.println("自动提交事务已关闭！");

			// 获取预编译的数据库操作对象
			String sql = "update t_user set username = ? where id = ?";
			ps = conn.prepareStatement(sql);

			// 给占位符传值
			ps.setString(1, "zoey");
			ps.setInt(2, 3);

			// 执行SQL语句
			System.out.println(ps.executeUpdate() == 1 ? "修改成功！" : "修改失败！");

			// 一定发生异常
			String str = null;
			str.toString();

			// 提交事务
			conn.commit();
			System.out.println("事务提交成功！");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 如果发生异常就回滚数据
			if (conn != null) {
				try {
					conn.rollback();
					System.out.println("事务回滚成功！");
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
		}

	}
}
