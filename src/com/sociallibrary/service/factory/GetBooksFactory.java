package com.sociallibrary.service.factory;

import com.sociallibrary.GetUserBorrowedBooks;
import com.sociallibrary.GetUserGroupBooks;
import com.sociallibrary.GetUserOwnedBooks;
import com.sociallibrary.GetUserRelatedBooks;
import com.sociallibrary.GetUserRequestedBooks;

public class GetBooksFactory {
	
	public GetUserRelatedBooks createBookList(String bookType){
		if(bookType.equals("OwnedBooks")){
			return new GetUserOwnedBooks();
		}
		else if(bookType.equals("BorrowedBooks")){
			return new GetUserBorrowedBooks();
		}
		else if(bookType.equals("RequestedBooks")){
			return new GetUserRequestedBooks();
		}
		else if(bookType.equals("GroupBooks")){
			return new GetUserGroupBooks();
		}
		else{
			return null;
		}
	}
}
