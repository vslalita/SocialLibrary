package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.domain.Member;
import com.sociallibrary.service.BookServiceController;

public class UpdateOperation implements BookOperation, IObservable {
	private ArrayList<Member> members=new ArrayList<Member>();
    private String updateAction;
    private int memberBookId;
    
	public UpdateOperation(String updateAction,int memberBookId){
		this.updateAction=updateAction;
		this.memberBookId=memberBookId;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		bo.updateBook(updateAction, memberBookId);
		addSubscribers();
		notifyAllSubscribers();
	}
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
   
	public void notifyAllSubscribers(){
		for(int i=0;i<members.size();i++){
			ResultSet book=BookServiceController.getInstance().getBookbyId(memberBookId);
			try {
				book.first();
				members.get(i).notify(book.getString("bookname"),"update");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
