package com.link.jdbc.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
	public static void main(String[] args) {
		Map<String, String> userInfo = init_menu();
		System.out.println(login(userInfo) ? "登录成功！" : "登录失败！");
	}

	/**
	 * 用户输入用户名和密码
	 * 
	 * @return 用户名和密码组合成的键值对，存放在Map集合
	 */
	private static Map<String, String> init_menu() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入用户名：");
		String username = scanner.nextLine();
		System.out.print("请输入密码：");
		String password = scanner.nextLine();
		scanner.close();

		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		return userInfo;
	}

	private static boolean login(Map<String, String> userInfo) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://192.168.0.162:3306/bjpowernode?serverTimezone=GMT";
		String username = "root";
		String password = "123456";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();

			// 拼接的SQL语句极有可能出现SQL注入问题
			String sql = "select username,password from t_user where username = '" + userInfo.get("username")
					+ "' and password = '" + userInfo.get("password") + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
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
		return false;
	}
}
