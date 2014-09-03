package com.sociallibrary.search;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.MemberNameSearch;

public class SearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {

		SearchContext context;
		ResultSet searchresults;
		String searchname = request.getParameter("search");
		String searchtype = request.getParameter("select");
		
		if (searchtype.equals("bookname")) {
			context = new SearchContext(new BookNameSearch());
			searchresults = context.executeSearch(searchname);
			
		}

		else if (searchtype.equals("isbn")) {
			context = new SearchContext(new IsbnSearch());
			searchresults = context.executeSearch(searchname);
			
		}

		else if (searchtype.equals("membername")) {
			context = new SearchContext(new MemberNameSearch());
			searchresults = context.executeSearch(searchname);
			request.setAttribute("resultset",searchresults );
			try {
				response.sendRedirect("NameDetails.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			context = new SearchContext(new NullSearch());
			searchresults = context.executeSearch(searchname);
		}
	}
}
