package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.Member;
import com.sociallibrary.service.BookServiceController;

//This class acts as a Concrete Command of the Command Pattern and Observable of Observer Pattern
public class RequestOperation implements BookOperation {
	private int id;
	private ArrayList<Member> members=new ArrayList<Member>();
	
	public RequestOperation(int id){
		this.id=id;
	}
	@Override
	public void execute() {
		//Macro- these 3 lines of code give the sequence of actions to be performed. Hence command pattern was used.
		bo.requestBook(id);
		addSubscribers();
		notifyAllSubscribers();
	}
	
    // This method computes list of members to be notified of the addition of a book and add them to the observers list (variable members here) as a part of the Observer Pattern.
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
			int id=member.getInt("borrower_id");
			m.setId(member.getInt("borrower_id"));
			members.add(m);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//This method calls notify method on all the observers as a part of the Observer Pattern
	@Override
	public void notifyAllSubscribers() {
		for(int i=0;i<members.size();i++){
			ResultSet book=BookServiceController.getInstance().getBookbyId(id);
			try {
				book.first();
				members.get(i).notify(book.getString("bookname"),"request");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
