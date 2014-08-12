package com.project.serch;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		ArrayList<String> searchresults = new ArrayList();
		String searchname = request.getParameter("search");
		System.out.println("Searchname is :" + searchname);
		String searchtype = request.getParameter("select");
		System.out.println("search type is :" + searchtype);

		SearchContext context = new SearchContext(new BookSearch());
		searchresults = context.executeSearch(searchname, searchtype);

	}

}
