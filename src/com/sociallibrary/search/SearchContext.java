package com.sociallibrary.search;

import java.sql.ResultSet;

public class SearchContext {
	private SearchOperation soperation;
	
	public SearchContext(SearchOperation soperation){
		this.soperation=soperation;
	}

	public ResultSet executeSearch(String name){
		return soperation.doSearch(name);
	}
	
}
