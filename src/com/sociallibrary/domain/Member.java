package com.sociallibrary.domain;

import java.sql.SQLException;
import java.sql.Statement;

import com.sociallibrary.*;
import com.sociallibrary.db.DatabaseConnection;

//This class acts as a observer of the observer Pattern
public class Member implements IObserver {
	private String  firstName;
	private String lastName;
	private String username;
	private String password;
	private String address;
	private String Email;
	int id;

	public Member(String firstName,String lastName,String username,String password,String address,String Email){
		this.firstName=firstName;
		this.lastName=lastName;
		this.username=username;
		this.password=password;
		this.address=address;
        this.Email=Email;

	}
    
	public void setId(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getEmail(){
		return this.Email;
	}
	
	//This method provides functionality of what shud happen when notified.
	public void notify(String bookname,String action){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			String notification="";
			if(action.equals("add")){
				notification=bookname+"has been added by "+CurrentSession.getMember().firstName;
			}
			else if(action.equals("delete")){
				notification=bookname+"has been deleted by "+CurrentSession.getMember().firstName;
			}
			else if(action.equals("request")){
				notification=bookname+"has been requested by "+CurrentSession.getMember().firstName;
			}
			else if(action.equals("update")){
				notification=bookname+"has been updated by "+CurrentSession.getMember().firstName;
			}
			String sql="Insert into "
					+ "notifications (member_id,notification) "
					+ "values ("+this.id+", '"+notification+"')";
		    st.executeUpdate(sql);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
