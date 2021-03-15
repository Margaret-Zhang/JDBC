package com.link.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtil {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	private DBUtil() {

	}

	/**
	 * 获取数据库管理系统登录信息以及注册驱动
	 */
	static {
		// 利用资源绑定器绑定属性配置文件，获取连接数据库的用户名信息
		ResourceBundle bundle = ResourceBundle.getBundle("mysql");
		driver = bundle.getString("driver");
		url = bundle.getString("url");
		username = bundle.getString("username");
		password = bundle.getString("password");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取数据连接对象
	 * 
	 * @return 连接对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	/**
	 * 关闭资源
	 * 
	 * @param 数据库连接对象
	 * @param 数据库操作对象或预编译的数据库操作对象
	 */
	public static void closeResource(Connection conn, Statement stmt) {

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

	/**
	 * 关闭资源
	 * 
	 * @param 数据库连接对象
	 * @param 数据库操作对象或预编译的数据库操作对象
	 * @param 查询结果集
	 */
	public static void closeResource(Connection conn, Statement stmt, ResultSet rs) {
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
