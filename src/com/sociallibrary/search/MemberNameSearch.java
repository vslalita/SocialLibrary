package com.sociallibrary.search;

import java.sql.ResultSet;

import com.sociallibrary.db.DBHelper;

public class MemberNameSearch implements SearchOperation {

	@Override
	public ResultSet doSearch(String name) {
		String sql = "SELECT * "
				+ "FROM members "
				+ "where concat(firstname, concat(' ',lastname))='"+name+"'";
		return DBHelper.getQueryResult(sql);
	}

}
