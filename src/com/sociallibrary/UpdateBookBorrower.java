package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateBookBorrower extends UpdateBookInfo{

	@Override
	boolean update() {
		ResultSet bookInfo=getBookInfo();
		if(bookInfo!=null){
			try {
				Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
				String sql="Select * "
						+ "from bookrequest "
						+ "where member_book_id="+getId();
				ResultSet bookRequest=st.executeQuery(sql);
				if(SqlOperations.getCount(bookRequest)>0){
					bookRequest.first();
				    String updateQuery="Update memberbooks set borrower_id="+bookRequest.getInt("member_id") +" where id="+getId();
				    st.executeUpdate("Delete from bookrequest where member_id="+bookRequest.getInt("member_id"));
				    st.executeUpdate(updateQuery);
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
