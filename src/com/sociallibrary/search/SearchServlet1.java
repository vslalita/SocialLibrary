package com.sociallibrary.search;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet1 extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {

		SearchContext context;
		ResultSet searchResults;
		String searchname = request.getParameter("searchattribute");
		String searchtype = request.getParameter("searchtype");
		
		if (searchtype.equals("bookname")) {
			context = new SearchContext(new BookNameSearch());
			searchResults = context.executeSearch(searchname);
			request.setAttribute("bookresult", searchResults);
			
		}

		else if (searchtype.equals("isbn")) {
			context = new SearchContext(new IsbnSearch());
			searchResults = context.executeSearch(searchname);
			request.setAttribute("bookresult", searchResults);
		}

		else if (searchtype.equals("membername")) {
			context = new SearchContext(new MemberNameSearch());
			searchResults = context.executeSearch(searchname);
			request.setAttribute("memberresult",searchResults );
			try {
				response.sendRedirect("NameDetails.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			context = new SearchContext(new NullSearch());
			searchResults = context.executeSearch(searchname);
			request.setAttribute("nullresult",searchResults );
		}
		
	}
}
