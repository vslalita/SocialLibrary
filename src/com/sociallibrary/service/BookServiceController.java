package com.sociallibrary.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sociallibrary.GetUserRelatedBooks;
import com.sociallibrary.db.*;
import com.sociallibrary.domain.Book;
import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.factory.GetBooksFactory;
import com.sociallibrary.service.operations.OperationsFacade;
import com.sociallibrary.service.operations.UpdateBookAvailability;
import com.sociallibrary.service.operations.UpdateBookBorrower;
import com.sociallibrary.service.operations.UpdateOperation;

public class BookServiceController {
	private static BookServiceController bookServicecontroller=null;
	private BookServiceController(){
	}
	
	//This method returns an instance for BookServiceController and the same instance is used through out the application 
	public static BookServiceController getInstance(){
		if(bookServicecontroller==null){
			bookServicecontroller=new BookServiceController();
			return bookServicecontroller;
		}
		return bookServicecontroller;
	}
    
	//
	public void addBook(Book book){
		OperationsFacade of=new OperationsFacade();
		of.operations("Add", null, book);
		of.executeRequests();
	}

	public void deleteBook(int id){
		OperationsFacade of=new OperationsFacade();
		of.operations("Delete",id,null);
	}

	public void requestBook(int id){
		OperationsFacade of=new OperationsFacade();
		of.operations("Request",id,null);
	}

	public void execute(){
		OperationsFacade of=new OperationsFacade();
		of.executeRequests();
	}
	
	public void updateBook(String updateAction,int id){
		OperationsFacade of=new OperationsFacade();
		of.operations(updateAction,id,null);
	}

	public ArrayList<String> displayBooks(String bookType){
		OperationsFacade of=new OperationsFacade();
		if(bookType.equals("DeletedBooks")){
			return of.displayDeletedBooks();
		}
		else if(bookType.equals("RequestedBooks")){
			return of.displayRequestedBooks();
		}
		return null;
	}

	

	public ResultSet getBooks(String bookType, int id){
		GetBooksFactory bookFactory=new GetBooksFactory();
		GetUserRelatedBooks books=bookFactory.createBookList(bookType);
		return books.getBooks(id);
	}

	public ResultSet getBookbyId(int memberBookid){
		String sql="Select * "
				+ "from books b,memberbooks mb "
				+ "where mb.book_id=b.id "
				+ "and mb.id="+memberBookid;
		return DBHelper.getQueryResult(sql);
	}

//	public void updateBook(String updateField, int id){
//		UpdateBookOperation update;
//		if(updateField.equals("availability")){
//			update=new UpdateBookAvailability();
//			update.executeUpdate(id);
//		}
//		else if(updateField.equals("borrower")){
//			update=new UpdateBookBorrower();
//			update.executeUpdate(id);
//		}
//	}

//	public boolean updateAvailibility(int memberBookid){
//		try {
//			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
//			String sql="Select * from memberbooks where id="+memberBookid;
//			ResultSet bookInfo=st.executeQuery(sql);
//			bookInfo.first();
//			int id=CurrentMember.cm.current_member.id;
//			if(((bookInfo.getInt("owner_id")==id)||(bookInfo.getInt("borrower_id")==id))&&bookInfo.getBoolean("availability")==true){
//				String updateQuery="update memberbooks set availability=false where id="+memberBookid;
//				st.executeUpdate(updateQuery);
//				return true;
//			}
//			else if(((bookInfo.getInt("owner_id")==id)||(bookInfo.getInt("borrower_id")==id))&&bookInfo.getBoolean("availability")==false){
//				String updateQuery="update memberbooks set availability=true where id="+memberBookid;
//				st.executeUpdate(updateQuery);
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}


	public ResultSet getBooksbyGroup(String groupName){
		String sql="Select * "
				+ "from memberbooks mb, groups g, membergroups mg, members m, books b "
				+ "where g.groupname='"+groupName+"' "
				+ "and g.id=mg.group_id "
				+ "and mg.member_id=m.id "
				+ "and mb.owner_id=mg.member_id "
				+ "and b.id=mb.book_id "
				+ "and mb.owner_id !="+CurrentSession.getMember().getId();
		return DBHelper.getQueryResult(sql);
	}

	//  public String getCategory(int memberBookid){
	//		
	//		try {
	//			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
	//			String sql="Select * "
	//					+ "from books b, bookcategories bc, memberbooks mb "
	//					+ "where mb.book_id=b.id "
	//					+ "and b.category_id=bc.id "
	//					+ "and mb.id="+memberBookid;
	//		    ResultSet category=st.executeQuery(sql);
	//		    category.first();
	//		    return category.getString("categoryname");
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}

}
