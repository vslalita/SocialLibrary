package com.sociallibrary.search;

import java.io.IOException;
import java.sql.ResultSet;

public class SearchController {
		
	public ResultSet getData(String searchType,String searchParameter){
		if(searchType.equals("bookname")){
			SearchContext context = new SearchContext(new BookNameSearch());
			return context.executeSearch(searchParameter);
		}
		else if (searchType.equals("isbn")) {
			SearchContext context = new SearchContext(new IsbnSearch());
			return context.executeSearch(searchParameter);	
		}
		else if (searchType.equals("membername")) {
			SearchContext context = new SearchContext(new MemberNameSearch());
			return context.executeSearch(searchParameter);
		} else {
			SearchContext context = new SearchContext(new NullSearch());
			return context.executeSearch(searchParameter);
		}
	}
}
