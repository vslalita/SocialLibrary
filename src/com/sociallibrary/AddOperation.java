package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddOperation implements BookOperationRequest {
	Book book;
	private ArrayList<Member> members=new ArrayList<Member>();

	public AddOperation(Book book){
		this.book=book;
	}

	@Override
	public void execute() {
		bo.addBook(book);
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * from groups g, membergroups mg, members m "
					+ "where g.id=mg.group_id "
					+ "and mg.member_id=m.id "
					+ "and mg.member_id="+CurrentMember.cm.current_member.id;
			ResultSet member=st.executeQuery(sql);
			if(SqlOperations.getCount(member)>0){
				member.beforeFirst();
				while(member.next()){
					Member m=new Member(member.getString("firstname"),member.getString("lastname"),member.getString("username"),member.getString("password"),member.getString("address"),member.getString("email"));
					m.setId(member.getInt("id"));
					members.add(m);
				}
			}
			notifyAllMembers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	public void notifyAllMembers(){
		for(int i=0;i<members.size();i++){
			members.get(i).notifyNotification(book.bookName);//(book.bookName);
		}
	}

}
