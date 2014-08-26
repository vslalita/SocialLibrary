package com.sociallibrary;


import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOperation implements BookOperationRequest {
    private int id;
    
    public DeleteOperation(int id){
    	this.id=id;
    }
    
	@Override
	public void execute() {
		deleteBookRequests();
        bo.deleteBook(id);
	}
	
	public void deleteBookRequests(){
		String sql ="Delete from bookrequest where member_book_id="+id;
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
