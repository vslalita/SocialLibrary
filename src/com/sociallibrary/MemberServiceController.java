package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberServiceController {
	static MemberServiceController memberServicecontroller=null;
	private MemberServiceController(){
	}

	public static MemberServiceController getInstance(){
		if(memberServicecontroller==null){
			memberServicecontroller=new MemberServiceController();
			return memberServicecontroller;
		}
		return memberServicecontroller;
	}

	 
	public boolean register(Member member){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			int validationValue=validate(member.username,member.password);
			if(validationValue>0){
				return false;
			}
			else{
				st.executeUpdate("Insert into members (firstname,lastname,username,password,address,email) "
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

	public ResultSet getgroups(int id){
		
		String sql="Select * "
				+ "from groups g, membergroups mg "
				+ "where g.id=mg.group_id "
				+  "and member_id="+id;
		return SqlOperations.getQueryResult(sql);
		
	}

	public void createGroup(String groupname){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet validateGroup=st.executeQuery("Select * from groups where groupname='"+groupname+"'");
			if(SqlOperations.getCount(validateGroup)==0){
				String sql="Insert into groups(groupname) values ('"+groupname+"')";
				st.executeUpdate(sql);
				joinGroup(groupname,CurrentMember.cm.current_member.id);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet viewMemberDetails(int memberId){
		String sql="Select * "
				+ "from members "
				+ "where id="+memberId;
       return SqlOperations.getQueryResult(sql);
	}

	public void joinGroup(String groupname,int id){
		try {
			Statement st=DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="select * "
					+ "from membergroups mg,groups g "
					+ "where g.id=mg.group_id "
					+ "and g.groupname='"+groupname+"' "
					+ "and mg.member_id="+id;
			ResultSet validateExistanceinGroup=st.executeQuery(sql); 
			if(SqlOperations.getCount(validateExistanceinGroup)==0){
				ResultSet groupIdQuery=st.executeQuery("Select id from groups where groupname='"+groupname+"'");
				groupIdQuery.next();
				String insertQuery="Insert into "
						+ "membergroups (group_id,member_id) "
						+ "values ("+groupIdQuery.getInt("id")+", "+id+")";
				st.executeUpdate(insertQuery);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ResultSet getMemberInfo(int id){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet member=st.executeQuery("Select * "
                    + "from members "
                    + "where id="+id);
			if(SqlOperations.getCount(member)>0){
				member.beforeFirst();
				return member;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ResultSet getGroupInformation(int id){
		String sql="Select * "
				+ "from membergroups mg, groups g, members m "
				+ "where mg.group_id=g.id and m.id=mg.member_id "
				+ "and mg.group_id in (Select group_id "
				                     + "from membergroups mg "
				                     + "where mg.member_id="+id+")";
		return SqlOperations.getQueryResult(sql);
	}
	
}
