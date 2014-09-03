package com.sociallibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCConnectionFactory {

	private static JDBCConnectionFactory instance = new JDBCConnectionFactory();
	static final String USER_NAME ="harsha";
	static final String PASSWORD="password";
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3307/sociallibrary";
	private static Connection connection = null;
	
	private JDBCConnectionFactory(){}
	
	public Connection setConnection(){
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to Conect to DB");
			e.printStackTrace();
		}
		return connection	;
	}
	
	public static JDBCConnectionFactory getFactory(){
		return instance;
	}
}
