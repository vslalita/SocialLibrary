package com.sociallibrary;

import java.sql.*;

import com.sociallibrary.db.DBHelper;

public class GetUserOwnedBooks implements GetUserRelatedBooks {
    
	//This method gives the implementation for retrieving the books of the member bearing the id ; parameter id.
	@Override
	public ResultSet getBooks(int id) {
		String sql="Select mb.id memberbookid, b.id bookid, bookname "
				+ "from memberbooks mb,books b "
				+ "where mb.owner_id="+id
				+ " and mb.book_id=b.id";
		return DBHelper.getQueryResult(sql);
	}

}
