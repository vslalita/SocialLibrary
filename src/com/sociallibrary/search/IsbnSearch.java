package com.sociallibrary.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sociallibrary.JDBCConnectionFactory;
import com.sociallibrary.db.DBHelper;

public class IsbnSearch implements SearchOperation {

	JDBCConnectionFactory factory = JDBCConnectionFactory.getFactory();
	Connection connection = factory.setConnection();

	public ResultSet doSearch(String isbn) {
		String sql = "Select * "
				+ "from books "
				+ "where ISBN = '"+ isbn +"'";

		return DBHelper.getQueryResult(sql);
	}
}
