package com.sociallibrary.service.operations;

//This class provides a template method as a part of Template Pattern
public abstract class IUpdateTemplate{
   private int memberBookId;
	
   //This is a template method which gives a skeleton of what is to be done in a sequence.When an update happens a validation needs to be done to make sure the user is authorized to make changes and then update.
	public void executeUpdate(int memberBookId){
		this.memberBookId=memberBookId;
		if(validate()){
			update();
		}	
	}
	
	public int getId(){
		return memberBookId;
	}
	
	 abstract boolean validate();
	 abstract boolean update();
	
}
