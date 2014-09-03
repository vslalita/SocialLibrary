package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserOwnedBooks implements GetUserRelatedBooks {

	@Override
	public ResultSet getBooks(int id) {
		String sql="Select mb.id memberbookid, b.id bookid, bookname "
				+ "from memberbooks mb,books b "
				+ "where mb.owner_id="+id
				+ " and mb.book_id=b.id";
		return DBHelper.getQueryResult(sql);
	}

}
