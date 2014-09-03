package com.sociallibrary.service;

public class GroupServiceController {
	static GroupServiceController groupServicecontroller=null;
	private GroupServiceController(){
	}
	
	public static GroupServiceController getInstance(){
		if(groupServicecontroller==null){
			groupServicecontroller=new GroupServiceController();
			return groupServicecontroller;
		}
		return groupServicecontroller;
	}
}
