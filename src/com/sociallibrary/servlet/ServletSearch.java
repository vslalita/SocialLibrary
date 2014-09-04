package com.sociallibrary.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.search.BookNameSearch;
import com.sociallibrary.search.IsbnSearch;
import com.sociallibrary.search.MemberNameSearch;
import com.sociallibrary.search.NullSearch;
import com.sociallibrary.search.SearchContext;

/**
 * Servlet implementation class SearchServlet2
 */
@WebServlet("/SearchServlet2")
public class ServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SearchContext context;
		ResultSet searchResults;
		
		if((request.getParameter("searchtype")!=null)&&(request.getParameter("searchattribute")!=null)){
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
				
			} else {
				context = new SearchContext(new NullSearch());
				searchResults = context.executeSearch(searchname);
				request.setAttribute("nullresult",searchResults );
			}
		}
		
		getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
