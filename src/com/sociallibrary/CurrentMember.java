package com.sociallibrary;

public class CurrentMember {

	 final  Member current_member;
	 static CurrentMember cm=null;
	 private CurrentMember(Member member){
		 this.current_member=member;
	 }
	
	 public static CurrentMember getMemberInstance(Member member){
		 if(cm==null){
			 cm=new CurrentMember(member);
			 return cm;
		 }
		 else{
			 return cm;
		 }
	 }
	
}
