package com.sociallibrary.service.operations;

import java.sql.*;
import java.util.ArrayList;

import com.sociallibrary.domain.Book;
import com.sociallibrary.service.BookServiceController;

public class OperationsFacade {
	OperationRequestor or=new OperationRequestor();
	
	// This method takes care of what operation is to be called based on the parameters. It hides the complexity of how to execute a command since each of the operations have a different way of being executed.
	public void operations(String operation,Integer parameter,Book book){
		
        if(operation.equals("Add")){
        	//Add Operation needs to be executed immediately. It need not be queued up. Hence execute is called from here itself. This also acts as an invoker of the command pattern.
        	//Add Operation requires a sequence of steps to be executed. Hence Command is used.
        	new AddOperation(book).execute();
        }
        else if(operation.equals("Delete")){
        	  //Delete Operation needs to be just queued up here
              OperationRequestor.deleteBooks.add(parameter);
              or.addOperation(new DeleteOperation(parameter));
        }
        else if(operation.equals("Request")){
        	 //Request Operation needs to be just queued up here
        	 or.addOperation(new RequestOperation(parameter));
        	 OperationRequestor.requestBooks.add(parameter);
        }
        else if((operation.equals("availability")||(operation.equals("borrower")))){
        	//Update Operation needs to be executed immediately. It need not be queued up. Hence execute is called from here itself. This also acts as an invoker of the command pattern.
        	//Update Operation requires a sequence of steps to be executed. Hence Command is used.
        	new UpdateOperation(operation,parameter).execute();
        }
	}
	
	//This method is to execute the queued up operations.
	public void executeRequests(){
		or.runRequests();
	}
	
	//This method cancels all the operations that are queued up.
	public void removeOperations(){
	
		or.removeOperations();
	}
	
	//This method is used for displaying the list of books to be deleted to current user. Does not play much role in Command Pattern
	public ArrayList<String> displayDeletedBooks(){
		return getListofBooks(OperationRequestor.deleteBooks);
	}
	
	//This method is used for displaying the list of books to be deleted to current user. Does not play much role in Command Pattern
	public ArrayList<String> displayRequestedBooks(){
		return getListofBooks(OperationRequestor.requestBooks);
	}
	
	//This method is used for getting the list of books through the Arraylists.
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
