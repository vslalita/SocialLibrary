package com.sociallibrary;

import java.sql.ResultSet;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.search.SearchOperation;

public class MemberNameSearch implements SearchOperation {

	@Override
	public ResultSet doSearch(String name) {
		String[] nameTokens;
		String firstName;
		String lastName;
		nameTokens = name.split(" ");
		firstName = nameTokens[0];
		lastName = nameTokens[1];
		
		String sql = "SELECT * "
				+ "FROM members where firstname= '" + firstName
				+ "' AND  lastname = '" + lastName + "'";
		return DBHelper.getQueryResult(sql);
	}

}
