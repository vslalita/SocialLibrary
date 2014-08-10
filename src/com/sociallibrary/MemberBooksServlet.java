package com.sociallibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter out=response.getWriter();
		out.println("hello");
		MemberOperations mo1=new MemberOperations();
		BookOperations bo1=new BookOperations();
		ResultSet myBooks=bo1.getMyBooks();
		ResultSet myGroups=mo1.getgroups();

		request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
		request.setAttribute("address",CurrentMember.cm.current_member.address);
		request.setAttribute("email",CurrentMember.cm.current_member.Email);
		request.setAttribute("member", CurrentMember.cm.current_member);
		request.setAttribute("groupbooks", myBooks);
		request.setAttribute("groups", myGroups);

		getServletContext().getRequestDispatcher("/books.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberOperations mo1=new MemberOperations();
		ResultSet myGroups=mo1.getgroups();
		BookOperations bo1=new BookOperations();
		String s=request.getParameter("groupname").toString();
		ResultSet groupBooks=bo1.getBooksbyGroup(s);

		request.setAttribute("groups", myGroups);
		request.setAttribute("groupbooks", groupBooks);
		request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
		request.setAttribute("address",CurrentMember.cm.current_member.address);
		request.setAttribute("email",CurrentMember.cm.current_member.Email);
		request.setAttribute("member", CurrentMember.cm.current_member);

		getServletContext().getRequestDispatcher("/books.jsp").forward(request, response);
	}

}
