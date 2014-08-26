package com.sociallibrary;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public final Connection conn;
    static DatabaseConnection  databaseInstance=new DatabaseConnection();
	private DatabaseConnection(){
		this.conn=connectionRequest();
	}
	
	public static  Connection connectionRequest(){
		
			String url = "jdbc:mysql://localhost:3306/"; 
			String dbName = "sociallibrary_development";
			String driver = "com.mysql.jdbc.Driver"; 
			String userName = "root"; 
			String password = "sridatta";

			try { 
				Class.forName(driver).newInstance(); 
				Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
			    return conn;
			}catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
}
