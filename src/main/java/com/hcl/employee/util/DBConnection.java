package com.hcl.employee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String user = "root";
	private static final String password = "9793";
	private static final String url = "jdbc:mysql://localhost:3306/employee";

	private DBConnection() {

	}
	private static Connection con = null;
	public static Connection getConnection() {
		
		try {

			if (con == null) {
				con = DriverManager.getConnection(url, user, password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}
}

