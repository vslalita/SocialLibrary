package com.sociallibrary.service.operations;

//This class acts as a Observable of the Observer Pattern
public interface IObservable {
    // The subjects that are being observed provide the implementation of the method.
	public void notifyAllSubscribers();
	
}
