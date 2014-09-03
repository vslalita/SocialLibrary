package com.sociallibrary.service.operations;

import java.sql.*;
import java.util.ArrayList;

import com.sociallibrary.domain.Book;
import com.sociallibrary.service.BookServiceController;

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
        else if((operation.equals("availability")||(operation.equals("borrower")))){
        	//String[] updateParameters={operation,String.valueOf(parameter)};
        	//OperationRequestor.updateBooks.add(updateParameters);
        	new UpdateOperation(operation,parameter).execute();
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
			ResultSet books=BookServiceController.getInstance().getBookbyId(id);
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
