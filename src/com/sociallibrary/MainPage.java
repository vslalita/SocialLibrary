package com.sociallibrary;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GenericController gc=GenericController.getInstance();
		gc.createInstanceType("MemberService");
		gc.createInstanceType("BookService");
		gc.createInstanceType("GroupService");
		response.setContentType("HTML");

		
		if(MemberServiceController.memberServicecontroller.login(request.getParameter("username"),request.getParameter("password"))){
			request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
			request.setAttribute("address",CurrentMember.cm.current_member.address);
			request.setAttribute("email",CurrentMember.cm.current_member.Email);
			request.setAttribute("member", CurrentMember.cm.current_member);
			
			int id=CurrentMember.cm.current_member.id;
			ResultSet myBooks=BookServiceController.bookServicecontroller.getBooks("OwnedBooks",id);
			ResultSet myBorrowedBooks=BookServiceController.bookServicecontroller.getBooks("BorrowedBooks",id);
			ResultSet myRequestedBooks=BookServiceController.bookServicecontroller.getBooks("RequestedBooks",id);
			ResultSet myGroups=MemberServiceController.memberServicecontroller.getgroups();
			
			
            request.setAttribute("ownedbooks", myBooks);
            request.setAttribute("groups", myGroups);
            request.setAttribute("borrowedbooks", myBorrowedBooks);
            request.setAttribute("requestedbooks", myRequestedBooks);
            
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else{
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
	}
	
	

}
