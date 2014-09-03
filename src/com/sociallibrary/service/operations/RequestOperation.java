package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.Member;
import com.sociallibrary.service.BookServiceController;


public class RequestOperation implements BookOperation {
	private int id;
	private ArrayList<Member> members=new ArrayList<Member>();
	
	public RequestOperation(int id){
		this.id=id;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		bo.requestBook(id);
	}
	
	public void addSubscribers(){
		String sql ="Select * "
				+ "from memberbooks mb, members m "
				+ "where m.id=mb.borrower_id"
				+ " and mb.id="+id;
		try {
			Statement st=DatabaseConnection.connectionRequest().createStatement();
			ResultSet member=st.executeQuery(sql);
			member.first();
			Member m=new Member(member.getString("firstname"),member.getString("lastname"),member.getString("username"),member.getString("password"),member.getString("address"),member.getString("email"));
			int id=member.getInt("member_id");
			m.setId(member.getInt("member_id"));
			members.add(m);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void notifyAllSubscribers() {
		for(int i=0;i<members.size();i++){
			ResultSet book=BookServiceController.getInstance().getBookbyId(id);
			try {
				book.first();
				members.get(i).notify(book.getString("bookname"),"delete");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
