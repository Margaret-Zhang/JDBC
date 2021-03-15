package com.link.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
测试DBUtil的可用性
实现JDBC的模糊查询
*/

public class JDBCTest04 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 注册驱动并获取连接对象
			conn = DBUtil.getConnection();

			// 获取预编译的数据库操作对象
			String sql = "select ENAME,SAL from emp where ENAME like ?";
			ps = conn.prepareStatement(sql);

			// 给占位符赋值
			ps.setString(1, "%A%");

			// 执行SQL并获取查询结果集
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("ENAME") + " " + rs.getString("SAL"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResource(conn, ps, rs);
		}
	}
}
