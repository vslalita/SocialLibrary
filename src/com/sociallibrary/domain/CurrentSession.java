package com.sociallibrary.domain;


public class CurrentSession {
     //current_member holds the information of the member who is currently logged in
	 private static  Member current_member;
	 private static CurrentSession cm=null;
	 private CurrentSession(Member member){
		 current_member=member;
	 }
	
	 // returns an instance of this class and the same instance is used through out 
	 public static CurrentSession getMemberInstance(Member member){
		 if(cm==null){
			 cm=new CurrentSession(member);
			 return cm;
		 }
		 else{
			 //the current_member is set 
			 current_member=member;
			 return cm;
		 }
	 }
	 
	 //current_member variable is set once a user logs in. The same current_member info is returned whenever this method is called
	 public static Member getMember(){
		 if(cm!=null && current_member!=null){
			 return current_member;
		 }
		 else return null;
	 }
	 
	 //current_member variable is reset to null so that it can be set to next user who logs in 
	 public static void resetCurrentMember(){
		 current_member=null;
	 }
	
}
