package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.CurrentSession;

public class UpdateBookBorrower extends IUpdateTemplate{

	public boolean validate(){
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Select * from memberbooks where id="+getId();
			ResultSet bookInfo=st.executeQuery(sql);
			bookInfo.first();
			int id=CurrentSession.getMember().getId();
			if(((bookInfo.getInt("owner_id")==id)||(bookInfo.getInt("borrower_id")==id))){
				bookInfo.first();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean update() {
			try {
				Statement st = DatabaseConnection.connectionRequest().createStatement();
				String sql="Select * "
						+ "from bookrequest "
						+ "where member_book_id="+getId();
				ResultSet bookRequest=st.executeQuery(sql);
				if(DBHelper.getCount(bookRequest)>0){
					bookRequest.first();
				    String updateQuery="Update memberbooks set borrower_id="+bookRequest.getInt("member_id") +" where id="+getId();
				    st.executeUpdate("Delete from bookrequest where member_id="+bookRequest.getInt("member_id"));
				    st.executeUpdate(updateQuery);
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

}
