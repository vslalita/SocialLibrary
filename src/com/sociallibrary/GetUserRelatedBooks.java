package com.sociallibrary;

import java.sql.ResultSet;

public interface GetUserRelatedBooks {
     
	//This method is implemented by the subclasses. It retrieves the required type of books based on the parameter id.
	 public ResultSet getBooks(int id);
	 
}
