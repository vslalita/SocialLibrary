package com.sociallibrary.domain;

public class Book{
   private String bookName;
   private String bookCategory;
   private int bookRating;
   private String bookISBN;
   
   public Book(String bookName,String bookCategory, int bookRating, String bookISBN){
	   this.bookName=bookName;
	   this.bookCategory=bookCategory;
	   this.bookRating=bookRating;
	   this.bookISBN=bookISBN;
   }
   
   public String getBookName(){
	   return this.bookName;
   }
   
   public String getBookCategory(){
	   return this.bookCategory;
   }
   
   public int getBookRating(){
	   return this.bookRating;
   }
   
   public String getBookISBN(){
	   return this.bookISBN;
   }
   
   
}
