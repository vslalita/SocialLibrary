package com.sociallibrary.service.operations;

//This interface and the classes implementing this class are observalbles of the observer pattern
public interface BookOperation extends IObservable {
	
  // the variable bo is the receiver	
  BookCRUDOperations bo=new BookCRUDOperations();
	public void execute();
}
