package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateBookAvailability extends UpdateBookInfo{

	@Override
	boolean update() {
		ResultSet bookInfo=getBookInfo();
		if(bookInfo!=null){
			try {
				Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
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
		}
		return false;
	}

}
