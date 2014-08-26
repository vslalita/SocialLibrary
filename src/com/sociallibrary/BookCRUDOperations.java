package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class BookCRUDOperations {
 	
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
					st.executeUpdate("insert into books (bookname, category_id,ISBN ) "
							+ "values ('"+book.bookName+"',"+categoryId+",'"+book.bookISBN+"')");
					ResultSet newBookId=st.executeQuery("Select * from books where ISBN='"+book.bookISBN+"'");
					newBookId.next();
					st.executeUpdate("insert into memberbooks (book_id, owner_id,borrower_id, memberrating, last_updated_at ) "
							+ "values ('"+newBookId.getInt("id")+"',"+CurrentMember.cm.current_member.id+","+CurrentMember.cm.current_member.id+","+book.bookRating+",'"+currentDate+"')");
				}
			}
		} catch (SQLException e) {
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
	
	public boolean deleteBook(Book book){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Delete from memberbooks "
					 + "where book_id in (Select id from books where ISBN="+book.bookISBN+" "
					 		+ "and owner_id="+CurrentMember.cm.current_member.id+")";
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteBook(int memberBookid){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Delete from memberbooks "
					 + "where id="+memberBookid;
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean requestBook(int memberBookId){
		Statement st;
		try {
			st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * from memberbooks where id="+memberBookId;
			
			ResultSet bookInfo=st.executeQuery(sql);
			bookInfo.first();
			int id=CurrentMember.cm.current_member.id;
			if(((bookInfo.getInt("borrower_id")==id))){
				return false;
			}
			else if((bookInfo.getInt("borrower_id")!=id)){
				String validateQuery="Select * from bookrequest where member_id="+id;
				ResultSet request=st.executeQuery(validateQuery);
				if(SqlOperations.getCount(request)==0){
					String insertQuery="insert into bookrequest (member_book_id,member_id) values("+memberBookId+","+id+")";
					st.executeUpdate(insertQuery);
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
