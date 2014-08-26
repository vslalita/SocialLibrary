package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlOperations {

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
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet result=st.executeQuery(sql);
			if(SqlOperations.getCount(result)>0){
				result.beforeFirst();
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
