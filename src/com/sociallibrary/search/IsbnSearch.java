package com.sociallibrary.search;

import java.sql.ResultSet;

import com.sociallibrary.db.DBHelper;

public class IsbnSearch implements SearchOperation {

	public ResultSet doSearch(String isbn) {
		String sql = "Select * "
				+ "from books "
				+ "where ISBN = '"+ isbn +"'";

		return DBHelper.getQueryResult(sql);
	}
}
