package com.sociallibrary.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

	public static int getCount(ResultSet queryResult){
		int count=0;
		try {
			while(queryResult.next()){
				count=count+1;
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static ResultSet getQueryResult(String sql){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			ResultSet result=st.executeQuery(sql);
			if(DBHelper.getCount(result)>0){
				result.beforeFirst();
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int executeDMLQuery(String sql){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			return st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
