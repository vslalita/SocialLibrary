package com.sociallibrary.search;


import java.sql.ResultSet;
import com.sociallibrary.db.DBHelper;

public class BookNameSearch implements SearchOperation {
   
	// 
	@Override
	public ResultSet doSearch(String name) {
		
		String sql = "SELECT * "
				+ "FROM books "
				+ "where bookname= '"+ name + "' ";
		return DBHelper.getQueryResult(sql);
	}
}