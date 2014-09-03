package com.sociallibrary.domain;


public class CurrentSession {

	 private static  Member current_member;
	 private static CurrentSession cm=null;
	 private CurrentSession(Member member){
		 CurrentSession.current_member=member;
	 }
	
	 public static CurrentSession getMemberInstance(Member member){
		 if(cm==null){
			 cm=new CurrentSession(member);
			 return cm;
		 }
		 else{
			 return cm;
		 }
	 }
	 
	 public static Member getMember(){
		 if(cm!=null){
			 return current_member;
		 }
		 else return null;
		
	 }
	
}
