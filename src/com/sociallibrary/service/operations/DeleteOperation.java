package com.sociallibrary.service.operations;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.domain.Member;
import com.sociallibrary.service.BookServiceController;

//This class acts as a Concrete Command of the Command Pattern and Observable of Observer Pattern
public class DeleteOperation implements BookOperation{
    private int id;
	private ArrayList<Member> members=new ArrayList<Member>();

    
    public DeleteOperation(int id){
    	this.id=id;
    }
    
	@Override
	public void execute() {
		//Macro- these 4 lines of code give the sequence of actions to be performed. Hence command pattern was used.
		addSubscribers();
		notifyAllSubscribers();
		deleteBookRequests();
        bo.deleteBook(id);
	}
	
	//This method is one of the actions to be performed after the addition of a book happens
	public void deleteBookRequests(){
		String sql ="Delete from bookrequest where member_book_id="+id;
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			st.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

    // This method computes list of members to be notified of the addition of a book and add them to the observers list (variable members here) as a part of the Observer Pattern.
	public void addSubscribers(){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Select * from groups g, membergroups mg, members m "
					+ "where g.id=mg.group_id "
					+ "and mg.member_id=m.id "
					+ "and mg.member_id="+CurrentSession.getMember().getId();
			ResultSet member=st.executeQuery(sql);
			if(DBHelper.getCount(member)>0){
				member.beforeFirst();
				while(member.next()){
					Member m=new Member(member.getString("firstname"),member.getString("lastname"),member.getString("username"),member.getString("password"),member.getString("address"),member.getString("email"));
					m.setId(member.getInt("member_id"));
					members.add(m);
				}
			}
			
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
				members.get(i).notify(book.getString("bookname"),"delete");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
