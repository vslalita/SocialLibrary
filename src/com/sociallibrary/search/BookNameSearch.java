package com.sociallibrary.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sociallibrary.JDBCConnectionFactory;
import com.sociallibrary.db.DBHelper;

public class BookNameSearch implements SearchOperation {
	JDBCConnectionFactory factory = JDBCConnectionFactory.getFactory();

	Connection connection = factory.setConnection();

	@Override
	public ResultSet doSearch(String name) {
		
		String sql = "SELECT * "
				+ "FROM books,bookcategories "
				+ "where bookname= '"+ name + "' "
				+ "AND  books.id = bookcategories.id";
		return DBHelper.getQueryResult(sql);
	}
}