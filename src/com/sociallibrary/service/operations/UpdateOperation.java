package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.domain.Member;
import com.sociallibrary.service.BookServiceController;

//This class acts as a Concrete Command of the Command Pattern and Observable of Observer Pattern
public class UpdateOperation implements BookOperation{
	private ArrayList<Member> members=new ArrayList<Member>();
    private String updateAction;
    private int memberBookId;
    
	public UpdateOperation(String updateAction,int memberBookId){
		this.updateAction=updateAction;
		this.memberBookId=memberBookId;
	}
	@Override
	public void execute() {
		//Macro- these 4 lines of code give the sequence of actions to be performed. Hence command pattern was used.
		bo.updateBook(updateAction, memberBookId);
		addSubscribers();
		notifyAllSubscribers();
	}
	
	//Macro- these 4 lines of code give the sequence of actions to be performed. Hence command pattern was used.
	public void addSubscribers(){
		String sql="Select * "
				+ "from members m, bookrequest br "
				+ "where br.member_id=m.id "
				+ "and br.member_book_id="+memberBookId;
		ResultSet bookRequestor=DBHelper.getQueryResult(sql);
		if(bookRequestor!=null){
			try {
				while(bookRequestor.next()){
					Member m=new Member(bookRequestor.getString("firstname"), bookRequestor.getString("lastname"), bookRequestor.getString("username"), bookRequestor.getString("password"), bookRequestor.getString("address"), bookRequestor.getString("email"));
					m.setId(bookRequestor.getInt("member_id"));
					members.add(m);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
   
	//This method calls notify method on all the observers as a part of the Observer Pattern
	public void notifyAllSubscribers(){
		for(int i=0;i<members.size();i++){
			ResultSet book=BookServiceController.getInstance().getBookbyId(memberBookId);
			try {
				book.first();
				members.get(i).notify(book.getString("bookname"),"update");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
