package com.project.serch;

import java.util.ArrayList;

public class NullSearch implements SearchOperation {

	@Override
	public ArrayList<String> doSearch(String name, String type) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList();
		String message = "You dint select any type of search !!!";
		list.add(message);
		return list;
	}

}
