package com.sociallibrary;

public interface IObserver {
    // The actual observer provide an implementation for this method 
	public void notify(String bookname,String action);
}
