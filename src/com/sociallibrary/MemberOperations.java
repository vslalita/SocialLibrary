package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberOperations {

	public boolean register(Member member){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			int validationValue=validate(member.username,member.password);
			if(validationValue>0){
				return false;
			}
			else{
				int newMemberId=st.executeUpdate("Insert into members (firstname,lastname,username,password,address,email) "
						+ "values('"+member.firstName+"',"+"'"+member.lastName+"','"+member.username+"','"+member.password+"','"+member.address+"','"+member.Email+"')");
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public int validate(String username,String password){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet validateUserQry=st.executeQuery("select *"
					+ " from members"
					+ " where username='"+username+"'"
					+ "and password='"+password+"'");
			int userCount=SqlOperations.getCount(validateUserQry);
			if(userCount==1){
				validateUserQry.last();
				return validateUserQry.getInt("id");
			}
			else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public boolean login(String username,String password){
		Statement st;
		int validationValue=validate(username,password);
		if(validationValue>0){
			try {
				st = DatabaseConnection.databaseInstance.conn.createStatement();
				ResultSet validateUserQry=st.executeQuery("select *"
						+ " from members"
						+ " where id="+validationValue);
				Member current_member;
				while(validateUserQry.next()){
					current_member=new Member(validateUserQry.getString("firstname"),
							validateUserQry.getString("lastname"),
							validateUserQry.getString("username"),
							validateUserQry.getString("password"),
							validateUserQry.getString("address"),
							validateUserQry.getString("email"));
					current_member.id=validateUserQry.getInt("id");
					CurrentMember.getMemberInstance(current_member);
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


	public ResultSet getgroups(){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet myGroups=st.executeQuery("Select * "
                    + "from groups g, membergroups mg "
                    + "where g.id=mg.group_id "
                    +  "and member_id="+CurrentMember.cm.current_member.id);
			if(SqlOperations.getCount(myGroups)>0){
				myGroups.beforeFirst();
				return myGroups;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
