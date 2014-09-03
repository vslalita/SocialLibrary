package com.sociallibrary.search;

import java.sql.ResultSet;
import java.sql.Connection;

import com.sociallibrary.JDBCConnectionFactory;
import com.sociallibrary.db.DBHelper;


public class NullSearch implements SearchOperation {

	JDBCConnectionFactory factory = JDBCConnectionFactory.getFactory();
	Connection connection = factory.setConnection();
	
	@Override
	public ResultSet doSearch(String name) {
		String sql = "SELECT * "
				+ "FROM nullsearch";
		return DBHelper.getQueryResult(sql);
	}

}
