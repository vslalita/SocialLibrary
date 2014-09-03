package com.sociallibrary.search;

import java.sql.ResultSet;

public interface SearchOperation {
	
	public ResultSet doSearch(String name);

}
