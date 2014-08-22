package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookServiceController {
	static BookServiceController bookServicecontroller=null;
	private GetUserRelatedBooks booksType;
	private BookServiceController(){
	}
	
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

	public static BookServiceController getInstance(){
		if(bookServicecontroller==null){
			bookServicecontroller=new BookServiceController();
			return bookServicecontroller;
		}
		return bookServicecontroller;
	}

	public ResultSet getBooks(String bookType, int id){
		if(bookType.equals("OwnedBooks")){
			this.booksType= new GetUserOwnedBooks();
		}
		else if(bookType.equals("BorrowedBooks")){
			this.booksType= new GetUserBorrowedBooks();
		}
		else if(bookType.equals("RequestedBooks")){
			this.booksType= new GetUserRequestedBooks();
		}
		else if(bookType.equals("GroupBooks")){
			this.booksType=new GetUserGroupBooks();
		}
		return booksType.getBooks(id);
	}

	public ResultSet getBookbyId(int memberBookid){
		String sql="Select * "
				+ "from books b,memberbooks mb "
				+ "where mb.book_id=b.id "
				+ "and mb.id="+memberBookid;
		return SqlOperations.getQueryResult(sql);
	}

	public ResultSet getBookRequestors(int memberBookId){

		String sql="Select * "
				+ "from members m, bookrequest br "
				+ "where br.member_id=m.id "
				+ "and br.member_book_id="+memberBookId;

		return SqlOperations.getQueryResult(sql);
	}


	public boolean updateAvailibility(int memberBookid){
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * from memberbooks where id="+memberBookid;
			ResultSet bookInfo=st.executeQuery(sql);
			bookInfo.first();
			int id=CurrentMember.cm.current_member.id;
			if(((bookInfo.getInt("owner_id")==id)||(bookInfo.getInt("borrower_id")==id))&&bookInfo.getBoolean("availability")==true){
				String updateQuery="update memberbooks set availability=false where id="+memberBookid;
				st.executeUpdate(updateQuery);
				return true;
			}
			else if(((bookInfo.getInt("owner_id")==id)||(bookInfo.getInt("borrower_id")==id))&&bookInfo.getBoolean("availability")==false){
				String updateQuery="update memberbooks set availability=true where id="+memberBookid;
				st.executeUpdate(updateQuery);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ResultSet getBooksbyGroup(String groupName){
		String sql="Select * "
				+ "from memberbooks mb, groups g, membergroups mg, members m, books b "
				+ "where g.groupname='"+groupName+"' "
				+ "and g.id=mg.group_id "
				+ "and mg.member_id=m.id "
				+ "and mb.owner_id=mg.member_id "
				+ "and b.id=mb.book_id "
				+ "and mb.owner_id !="+CurrentMember.cm.current_member.id;
		return SqlOperations.getQueryResult(sql);
	}
	
  public String getCategory(int memberBookid){
		
		try {
			Statement st = DatabaseConnection.databaseInstance.conn.createStatement();
			String sql="Select * "
					+ "from books b, bookcategories bc, memberbooks mb "
					+ "where mb.book_id=b.id "
					+ "and b.category_id=bc.id "
					+ "and mb.id="+memberBookid;
		    ResultSet category=st.executeQuery(sql);
		    category.first();
		    return category.getString("categoryname");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
}
