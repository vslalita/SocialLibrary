package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetCurrentUserOwnedBooks implements GetCurrentUserRelatedBooks {

	@Override
	public ResultSet getBooks() {
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			int id=CurrentMember.cm.current_member.id;
			String sql="Select mb.id memberbookid, b.id bookid, bookname "
					+ "from memberbooks mb,books b "
					+ "where mb.owner_id="+CurrentMember.cm.current_member.id
					+ " and mb.book_id=b.id";
			ResultSet myBooks=st.executeQuery(sql);
		
			if(SqlOperations.getCount(myBooks)>0){
				myBooks.beforeFirst();
				return myBooks;
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
