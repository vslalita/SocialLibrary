package com.sociallibrary.service.operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.db.DatabaseConnection;
import com.sociallibrary.domain.Book;
import com.sociallibrary.domain.CurrentSession;

public class BookCRUDOperations {
 	
    // Method to add create new Books for a group. If a book already exists a record is created only in 'memberbooks' table.
	public void addBook(Book book){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			ResultSet bookExistingQuery=st.executeQuery("Select * "
					+ "from books "
					+ "where bookname='"+book.getBookName()+"'");

			Calendar javaCalendar = null;
			String currentDate = "";
			javaCalendar = Calendar.getInstance();
			currentDate = javaCalendar.get(Calendar.YEAR) + "/" + (javaCalendar.get(Calendar.MONTH) + 1) + "/" + javaCalendar.get(Calendar.DATE);
			
			int bookCount=DBHelper.getCount(bookExistingQuery);
			if(bookCount==1){
				ResultSet newBookId=st.executeQuery("Select * "
						                          + "from books "
						                          + "where ISBN='"+book.getBookISBN()+"'");
				newBookId.next();
				ResultSet checkExistingBook=st.executeQuery("Select * "
						                                 + "from memberbooks "
						                                 + "where book_id="+newBookId.getInt("id")+" "
						                                  + "and owner_id="+CurrentSession.getMember().getId());
				if(DBHelper.getCount(checkExistingBook)==0){
					st.executeUpdate("insert into "
							+ "memberbooks (book_id, owner_id,borrower_id, memberrating, last_updated_at ) "
							+ "values ('"+newBookId.getInt("id")+"',"+CurrentSession.getMember().getId()+",null,"+book.getBookRating()+",'"+currentDate+"')");
				}
			}
			else{
				int categoryId=addCategory(book.getBookCategory());
				if(categoryId>0){
					st.executeUpdate("insert into books (bookname, category_id,ISBN ) "
							+ "values ('"+book.getBookName()+"',"+categoryId+",'"+book.getBookISBN()+"')");
					ResultSet newBookId=st.executeQuery("Select * from books where ISBN='"+book.getBookISBN()+"'");
					newBookId.next();
					st.executeUpdate("insert into memberbooks (book_id, owner_id,borrower_id, memberrating, last_updated_at ) "
							+ "values ('"+newBookId.getInt("id")+"',"+CurrentSession.getMember().getId()+","+CurrentSession.getMember().getId()+","+book.getBookRating()+",'"+currentDate+"')");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	
	// Creates a category if it does not exist already
	public int addCategory(String categoryName){
		try {
			Statement st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Select * "
					+ "from bookcategories "
					+ "where categoryname='"+categoryName+"'";
			ResultSet categoryExistingQuery=st.executeQuery(sql);
			int categoryCount=DBHelper.getCount(categoryExistingQuery);
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
	
	//This method is to delete book based on the id 
	public void deleteBook(int memberBookid){
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Delete from memberbooks "
					 + "where id="+memberBookid;
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//This method is to place a request based on the id
	public boolean requestBook(int memberBookId){
		Statement st;
		try {
			st = DatabaseConnection.connectionRequest().createStatement();
			String sql="Select * from memberbooks where id="+memberBookId;
			
			ResultSet bookInfo=st.executeQuery(sql);
			bookInfo.first();
			int id=CurrentSession.getMember().getId();
			if(((bookInfo.getInt("borrower_id")==id))){
				return false;
			}
			else if((bookInfo.getInt("borrower_id")!=id)){
				String validateQuery="Select * from bookrequest where member_id="+id+" and member_book_id="+memberBookId;
				ResultSet request=st.executeQuery(validateQuery);
				if(DBHelper.getCount(request)==0){
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
	
	
	//This method delegates the update request based on what is to be requested.
	public void updateBook(String updateAction,int id){
		if(updateAction.equals("availability")){
			IUpdateTemplate update=new UpdateBookAvailability();
			update.executeUpdate(id);
		}
		if(updateAction.equals("borrower")){
			IUpdateTemplate update=new UpdateBookBorrower();
			update.executeUpdate(id);
		}
	}
	
}
