package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserBorrowedBooks implements GetUserRelatedBooks{
   
	//This method has an implementation to retrieve the books that have been borrowed by the member bearing the id; parameter id.
	@Override
	public ResultSet getBooks(int id) {
		
		String sql="Select * "
				+ "from memberbooks mb,books b "
				+ "where mb.borrower_id="+id+" "
				+ "and mb.book_id=b.id";
		return DBHelper.getQueryResult(sql);
	}

}
