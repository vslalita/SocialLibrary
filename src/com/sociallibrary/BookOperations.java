package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class BookOperations {
    
	// Method to add create new Books for a group. If a book already exists a record is created only in 'memberbooks' table.
	public void addBook(Book book){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			ResultSet bookExistingQuery=st.executeQuery("Select * "
					+ "from books "
					+ "where bookname='"+book.bookName+"'");

			Calendar javaCalendar = null;
			String currentDate = "";
			javaCalendar = Calendar.getInstance();
			currentDate = javaCalendar.get(Calendar.YEAR) + "/" + (javaCalendar.get(Calendar.MONTH) + 1) + "/" + javaCalendar.get(Calendar.DATE);
			
			int bookCount=SqlOperations.getCount(bookExistingQuery);
			if(bookCount==1){
				ResultSet newBookId=st.executeQuery("Select * "
						                          + "from books "
						                          + "where ISBN='"+book.bookISBN+"'");
				newBookId.next();
				ResultSet checkExistingBook=st.executeQuery("Select * "
						                                 + "from memberbooks "
						                                 + "where book_id="+newBookId.getInt("id")+" "
						                                  + "and owner_id="+CurrentMember.cm.current_member.id);
				if(SqlOperations.getCount(checkExistingBook)==0){
					st.executeUpdate("insert into "
							+ "memberbooks (book_id, owner_id,borrower_id, memberrating, last_updated_at ) "
							+ "values ('"+newBookId.getInt("id")+"',"+CurrentMember.cm.current_member.id+",null,"+book.bookRating+",'"+currentDate+"')");
				}
			}
			else{
				int categoryId=addCategory(book.bookCategory);
				if(categoryId>0){
					st.executeUpdate("insert into books (bookname, category_id,ISBN, availability ) "
							+ "values ('"+book.bookName+"',"+categoryId+",'"+book.bookISBN+"',true)");
					ResultSet newBookId=st.executeQuery("Select * from books where ISBN='"+book.bookISBN+"'");
					newBookId.next();
					st.executeUpdate("insert into memberbooks (book_id, owner_id,borrower_id, memberrating, last_updated_at ) "
							+ "values ('"+newBookId.getInt("id")+"',"+CurrentMember.cm.current_member.id+",null,"+book.bookRating+",'"+currentDate+"')");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
	// Creates a category if it does not exist already
	public int addCategory(String categoryName){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * "
					+ "from bookcategories "
					+ "where categoryname='"+categoryName+"'";
			ResultSet categoryExistingQuery=st.executeQuery(sql);
			int categoryCount=SqlOperations.getCount(categoryExistingQuery);
			if(categoryCount==1){
				while (categoryExistingQuery.last()){
					return categoryExistingQuery.getInt("id");
				}
			}
			else{
				st.executeUpdate("insert into bookcategories (categoryname) values ('"+categoryName+"')");
				ResultSet newCategory=st.executeQuery(sql);
				while (newCategory.last()){
					return newCategory.getInt("id");
				}
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	
	//gets the list of books the user owns
	public ResultSet getMyBooks(){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			int id=CurrentMember.cm.current_member.id;
			String sql="Select * "
					+ "from memberbooks mb,books b "
					+ "where mb.owner_id="+CurrentMember.cm.current_member.id
					+ " and mb.book_id=b.id";
			ResultSet myBooks=st.executeQuery(sql);
		
			if(SqlOperations.getCount(myBooks)>0){
				myBooks.beforeFirst();
				return myBooks;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	//gets the list of books the user borrowed.
	public ResultSet getMyBorrowedBooks(){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * "
					+ "from memberbooks mb,books b "
					+ "where mb.borrower_id="+CurrentMember.cm.current_member.id
					+ "and mb.book_id=b.id";
			ResultSet myBorrowedBooks=st.executeQuery(sql);
			if(SqlOperations.getCount(myBorrowedBooks)>0){
				return myBorrowedBooks;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResultSet getBooksbyGroup(String groupName){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * "
					+ "from memberbooks mb, groups g, membergroups mg, members m, books b "
					+ "where g.groupname='"+groupName+"' "
					+ "and g.id=mg.group_id "
					+ "and mg.member_id=m.id "
					+ "and mb.owner_id=mg.member_id "
					+ "and b.id=mb.book_id";
			ResultSet getGroupBooks=st.executeQuery(sql);
			if(SqlOperations.getCount(getGroupBooks)>0){
				getGroupBooks.beforeFirst();
				return getGroupBooks;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteBook(Book book){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Delete from memberbooks "
					 + "where book_id in (Select id "
					                  + "from books "
					                  + "where id="+book.bookISBN+")";
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet getBooks(){
        
		return null;
	}

}
