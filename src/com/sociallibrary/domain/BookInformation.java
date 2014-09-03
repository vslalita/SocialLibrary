package com.sociallibrary.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.sociallibrary.db.DBHelper;
import com.sociallibrary.service.MemberServiceController;

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
		this.book= DBHelper.getQueryResult(sql);
	}
  
  public void setBookOwner(){
	  try {
		book.first();
		ResultSet Owner=MemberServiceController.getInstance().getMemberInfo(book.getInt("owner_id"));
		this.bookOwner=Owner;
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
  
  public void setBookBorrower(){
	  try {
		book.first();
		ResultSet borrower=MemberServiceController.getInstance().getMemberInfo(book.getInt("borrower_id"));
        this.bookBorrower=borrower;
	} catch (SQLException e) {
		e.printStackTrace();
	}
  }
  
  public void setBookRequestors(int memberBookId){
		String sql="Select * "
				+ "from members m, bookrequest br "
				+ "where br.member_id=m.id "
				+ "and br.member_book_id="+memberBookId;
		this.bookRequestor=DBHelper.getQueryResult(sql);
	}
  
}
