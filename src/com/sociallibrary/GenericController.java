package com.sociallibrary;

public class GenericController {
	private static GenericController controller=null;
	private GenericController(){
	}
	
	public static GenericController getInstance(){
		if(controller==null){
			controller=new GenericController();
			return controller;
		}
		return controller;
	}
	
	public void createInstanceType(String requestObjectType){
		if(requestObjectType.equals("BookService")){
			BookServiceController.getInstance();
		}
		else if(requestObjectType.equals("MemberService")){
			MemberServiceController.getInstance();
		}
		else if(requestObjectType.equals("GroupService")){
			GroupServiceController.getInstance();
		}
		
	}
	
}
