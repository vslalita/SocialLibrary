package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.CurrentSession;

//This class provides an implementation for the abstract methods of IUpdateTemplate
public class UpdateBookAvailability extends IUpdateTemplate{
  
	//This method validates if the user updating the book is a borrower of the book
	public boolean validate(){
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Select * from memberbooks where id="+getId();
			ResultSet bookInfo=st.executeQuery(sql);
			bookInfo.first();
			int id=CurrentSession.getMember().getId();
			if(((bookInfo.getInt("borrower_id")==id))){
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
				String sql="Select * from memberbooks where id="+getId();
				ResultSet bookInfo=st.executeQuery(sql);
				bookInfo.first();
				if(bookInfo.getBoolean("availability")==true){
					String updateQuery="update memberbooks set availability=false where id="+getId();
					st.executeUpdate(updateQuery);
				}
				if(bookInfo.getBoolean("availability")==false){
					String updateQuery="update memberbooks set availability=true where id="+getId();
					st.executeUpdate(updateQuery);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return false;
	}


}
