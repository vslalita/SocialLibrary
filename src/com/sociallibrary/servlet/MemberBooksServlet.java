package com.sociallibrary.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.BookServiceController;
import com.sociallibrary.service.MemberServiceController;

/**
 * Servlet implementation class MemberBooksServlet
 */
@WebServlet("/MemberBooksServlet")
public class MemberBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberBooksServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("HTML");
		if((request.getParameter("operation")!=null)&&(request.getParameter("operation").equals("Request"))){
			BookServiceController.getInstance().requestBook(Integer.valueOf(request.getParameter("id")));
		}
		
		ResultSet myGroups=MemberServiceController.getInstance().getgroups(CurrentSession.getMember().getId());

		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		request.setAttribute("member", CurrentSession.getMember());
		request.setAttribute("groupbooks", BookServiceController.getInstance().getBooks("GroupBooks",CurrentSession.getMember().getId()));
		request.setAttribute("groups", myGroups);

		getServletContext().getRequestDispatcher("/books.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ResultSet myGroups=MemberServiceController.getInstance().getgroups(CurrentSession.getMember().getId());
		String groupName=request.getParameter("groupname").toString();
		ResultSet groupBooks=BookServiceController.getInstance().getBooksbyGroup(groupName);

		request.setAttribute("groups", myGroups);
		request.setAttribute("groupbooks", groupBooks);
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		request.setAttribute("member", CurrentSession.getMember());

		getServletContext().getRequestDispatcher("/books.jsp").forward(request, response);
	}

}
