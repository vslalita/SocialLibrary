package com.sociallibrary;

import java.sql.*;

public class GetUserBorrowedBooks implements GetUserRelatedBooks{

	@Override
	public ResultSet getBooks(int id) {
		
		String sql="Select * "
				+ "from memberbooks mb,books b "
				+ "where mb.borrower_id="+id+" "
				+ "and mb.book_id=b.id";
		return SqlOperations.getQueryResult(sql);
	}

}
