package com.yarlnet.BankServer;

import java.sql.*;

public class AccessDbase {

	Statement stmt, tmpStmt, stmt2;
	Connection conn1, conn2;
	ResultSet uprs, tmpuprs;
	PreparedStatement pstmt;

	private static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "VithunP99#";
	private static Connection connection;

	public AccessDbase() {
	}

	public void connectionDb() {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("----Successfully connected to MySQL----");
			stmt = connection.createStatement();
			tmpStmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (SQLException sqle) {
			System.out.println("Error:" + sqle);
		}
	}
}