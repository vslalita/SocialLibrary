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

//This class acts as a Concrete Command of the Command Pattern and Observable of Observer Pattern
public class AddOperation implements BookOperation{
	Book book;
	private ArrayList<Member> members=new ArrayList<Member>();

	public AddOperation(Book book){
		this.book=book;
	}

	@Override
	public void execute() {
		//Macro- these 4 lines of code give the sequence of actions to be performed. Hence command pattern was used.
		bo.addBook(book);
		updateRating();
		addSubscribers();
		notifyAllSubscribers();
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
   
	//This method is one of the actions to be performed after the addition of a book happens
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

	//This method calls notify method on all the observers as a part of the Observer Pattern
	public void notifyAllSubscribers(){
		for(int i=0;i<members.size();i++){
			members.get(i).notify(book.getBookName(),"add");
		}
	}

}
