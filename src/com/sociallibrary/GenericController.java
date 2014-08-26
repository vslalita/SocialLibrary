package com.sociallibrary;

public class GenericController {
	private static GenericController controller=null;
	private GenericController(){
		BookServiceController.getInstance();
		MemberServiceController.getInstance();
		GroupServiceController.getInstance();
	}
	
	public static GenericController getInstance(){
		if(controller==null){
			controller=new GenericController();
			return controller;
		}
		return controller;
	}
	
}
