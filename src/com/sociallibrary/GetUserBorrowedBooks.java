package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserBorrowedBooks implements GetUserRelatedBooks{

	@Override
	public ResultSet getBooks(int id) {
		
		String sql="Select * "
				+ "from memberbooks mb,books b "
				+ "where mb.borrower_id="+id+" "
				+ "and mb.book_id=b.id";
		return DBHelper.getQueryResult(sql);
	}

}
