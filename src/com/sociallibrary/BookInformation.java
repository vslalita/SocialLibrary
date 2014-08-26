package com.sociallibrary;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInformation {
  private ResultSet book;
  private ResultSet bookOwner;
  private ResultSet bookRequestor;
  private ResultSet bookBorrower;
  
  public BookInformation(int id){
	  setBookbyId(id);
	  setBookOwner();
	  setBookBorrower();
	  setBookRequestors(id);
  }
  
  public ResultSet getBookOwner(){
	  return this.bookOwner;
  }
  
  public ResultSet getBook(){
	  return this.book;
  }
  
  public ResultSet getBookBorrower(){
	  return this.bookBorrower;
  }
  
  public ResultSet getBookRequestors(){
	  return this.bookRequestor;
  }
  
  public void setBookbyId(int memberBookid){
		String sql="Select * "
				+ "from books b,memberbooks mb "
				+ "where mb.book_id=b.id "
				+ "and mb.id="+memberBookid;
		this.book= SqlOperations.getQueryResult(sql);
	}
  
  public void setBookOwner(){
	  try {
		book.first();
		ResultSet Owner=MemberServiceController.memberServicecontroller.getMemberInfo(book.getInt("owner_id"));
		this.bookOwner=Owner;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void setBookBorrower(){
	  try {
		book.first();
		ResultSet borrower=MemberServiceController.memberServicecontroller.getMemberInfo(book.getInt("borrower_id"));
        this.bookBorrower=borrower;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  public void setBookRequestors(int memberBookId){
		String sql="Select * "
				+ "from members m, bookrequest br "
				+ "where br.member_id=m.id "
				+ "and br.member_book_id="+memberBookId;
		this.bookRequestor=SqlOperations.getQueryResult(sql);
	}
  
}
