package com.sociallibrary;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationsFacade {

	public void operations(String operation,Integer parameter,Book book){
        if(operation.equals("Add")){
        	OperationRequestor.addBooks.add(book);
        }
        else if(operation.equals("Delete")){
            OperationRequestor.deleteBooks.add(parameter);
            System.out.println("Size:"+OperationRequestor.deleteBooks.size());
        }
        else if(operation.equals("Request")){
        	OperationRequestor.requestBooks.add(parameter);
        }
	}
	
	public void executeRequests(){
		OperationRequestor or=new OperationRequestor();
		or.takeRequest();
		or.runRequests();
	}
	
	public ArrayList<String> displayDeletedBooks(){
		return getListofBooks(OperationRequestor.deleteBooks);
	}
	
	public ArrayList<String> displayRequestedBooks(){
		return getListofBooks(OperationRequestor.requestBooks);
	}
	
	private ArrayList<String> getListofBooks(ArrayList<Integer> book){
		ArrayList<String> returnBooks=new ArrayList<String>();
		for(int i=0;i<book.size();i++){
			int id=book.get(i);
			ResultSet books=BookServiceController.bookServicecontroller.getBookbyId(id);
			//String category=bo.getCategory(id);
			try {
				books.first();
				String book1=books.getString("bookname");
				returnBooks.add(book1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnBooks;
	}
}
