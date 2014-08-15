package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCurrentUserBorrowedBooks implements GetCurrentUserRelatedBooks{

	@Override
	public ResultSet getBooks() {
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * "
					+ "from memberbooks mb,books b "
					+ "where mb.borrower_id="+CurrentMember.cm.current_member.id+" "
					+ "and mb.book_id=b.id";
			ResultSet myBorrowedBooks=st.executeQuery(sql);
			
			if(SqlOperations.getCount(myBorrowedBooks)>0){
				myBorrowedBooks.beforeFirst();
				return myBorrowedBooks;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
