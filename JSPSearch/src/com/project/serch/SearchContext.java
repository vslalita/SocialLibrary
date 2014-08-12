package com.project.serch;

import java.util.ArrayList;

public class SearchContext {
	
	

	private SearchOperation soperation;
	
	public SearchContext(SearchOperation soperation){
		
		this.soperation=soperation;
		
	}

	public ArrayList<String> executeSearch(String name, String type){
		return soperation.doSearch(name, type);
	}
	
}
