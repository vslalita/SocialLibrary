package com.sociallibrary.service.operations;


public interface BookOperation extends IObservable {
  BookCRUDOperations bo=new BookCRUDOperations();
	public void execute();
}
