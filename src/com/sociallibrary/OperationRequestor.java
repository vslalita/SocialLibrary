package com.sociallibrary;

import java.util.ArrayList;

public class OperationRequestor {
  private ArrayList<BookOperationRequest> operationsList=new ArrayList<BookOperationRequest>();
  public static ArrayList<Book> addBooks=new ArrayList<Book>();
  public static ArrayList<Integer> deleteBooks=new ArrayList<Integer>();
  public static ArrayList<Integer> requestBooks=new ArrayList<Integer>();
  
  public void addBookOperations(){
	  if(addBooks.size()>0){
		  for(int i=0;i<addBooks.size();i++){
			  operationsList.add(new AddOperation(addBooks.get(i)));
			  addBooks.remove(i);
		  }
	  }
  }
  
  public void deleteBookOperations(){
	  if(deleteBooks.size()>0){
		  for(int i=0;i<deleteBooks.size();i++){
			  operationsList.add(new DeleteOperation(deleteBooks.get(i)));
			  deleteBooks.remove(i);
		  }
	  }
  }
  
  public void requestBookOperations(){
	  if(requestBooks.size()>0){
		  for(int i=0;i<requestBooks.size();i++){
			  operationsList.add(new RequestOperation(requestBooks.get(i)));
			  requestBooks.remove(i);
		  }
	  }
  }
  
  public void takeRequest(){
	  this.addBookOperations();
	  this.deleteBookOperations();
	  this.requestBookOperations();
  }
  
  public void runRequests(){
	  for(int i=0;i<operationsList.size();i++){
		  operationsList.get(i).execute();
	  }
  }
  
  
}
