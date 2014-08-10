package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
