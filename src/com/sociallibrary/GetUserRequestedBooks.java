package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserRequestedBooks implements GetUserRelatedBooks {

	@Override
	public ResultSet getBooks(int id) {
		
		String sql="Select * "
				+ "from bookrequest br, memberbooks mb, books b "
				+ "where br.member_book_id=mb.id "
				+ "and mb.book_id=b.id "
				+ "and br.member_id="+id;
		return DBHelper.getQueryResult(sql);
	}

}
