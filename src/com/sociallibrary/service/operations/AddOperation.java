package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.Book;
import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.domain.Member;

public class AddOperation implements BookOperation, IObservable{
	Book book;
	private ArrayList<Member> members=new ArrayList<Member>();

	public AddOperation(Book book){
		this.book=book;
	}

	@Override
	public void execute() {
		bo.addBook(book);
		updateRating();
		addSubscribers();
		notifyAllSubscribers();
	}


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
					int id=member.getInt("member_id");
					m.setId(member.getInt("member_id"));
					System.out.println(id);
					members.add(m);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateRating(){
		String sql= "Select memberrating "
				+ "from memberbooks mb,books b "
				+ "where mb.book_id=b.id "
				+ "and b.ISBN='"+book.getBookISBN()+"'";
		int rating=0;
		int count=0;
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			ResultSet ratingList=st.executeQuery(sql);
			while(ratingList.next()){
				count=count+1;
				rating =rating+ratingList.getInt("memberrating");
			}if(count>0){
				String updateRatingQuery="Update books "
						+ "set rating="+(rating/count)+" "
						+ "where isbn='"+book.getBookISBN()+"'";
				st.executeUpdate(updateRatingQuery);
			}
			else{
				String updateRatingQuery="Update books "
						+ "set rating="+(rating)+" "
						+ "where isbn='"+book.getBookISBN()+"'";
				st.executeUpdate(updateRatingQuery);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public void notifyAllSubscribers(){
		for(int i=0;i<members.size();i++){
			members.get(i).notify(book.getBookName(),"add");
		}
	}

}
