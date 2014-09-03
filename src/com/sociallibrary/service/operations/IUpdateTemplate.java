package com.sociallibrary.service.operations;

public abstract class IUpdateTemplate{
   private int memberBookId;
	
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
