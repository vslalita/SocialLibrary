package com.sociallibrary.service.operations;

import java.util.ArrayList;

import com.sociallibrary.domain.Book;

//This class acts as an invoker
public class OperationRequestor {
	
 //OperationsList maintains the list of operations that are to be queued up.	
  private static ArrayList<BookOperation> operationsList=new ArrayList<BookOperation>();
  
  //These static variables are maintained just to store and display the details of the books that are to be deleted and requested to the user.Does not play much role in Command Pattern.
  public static ArrayList<Book> addBooks=new ArrayList<Book>();
  public static ArrayList<Integer> deleteBooks=new ArrayList<Integer>();
  public static ArrayList<Integer> requestBooks=new ArrayList<Integer>();

  //Method to queue delete and request operations through operationsList
  public void addOperation(BookOperation operation){
	  operationsList.add(operation);
  }
  
  //Method to remove all the operations if the user wants to cancel all the deletes or requests.
  public void removeOperations(){
	  for(int i=0;i< OperationRequestor.deleteBooks.size();i++){
			OperationRequestor.deleteBooks.remove(i);
		}
		for(int i=0;i< OperationRequestor.requestBooks.size();i++){
			OperationRequestor.requestBooks.remove(i);
		}
	  for(int i=0;i<operationsList.size();i++){
		  operationsList.remove(i);
	  }
  }
  
  //Calls an execute over the operations which are queued up
  public void runRequests(){
	  for(int i=0;i<operationsList.size();i++){
		  operationsList.get(i).execute();
	  }
	  removeOperations();
  }
  
  
}
