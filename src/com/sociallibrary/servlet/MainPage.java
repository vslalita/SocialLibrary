package com.sociallibrary.servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.BookServiceController;
import com.sociallibrary.service.MemberServiceController;

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
		if(request.getParameter("operation").equals("signout")){
			MemberServiceController.getInstance().signout();
			System.out.println("Signedout");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage;
		if(MemberServiceController.getInstance().login(request.getParameter("username"),request.getParameter("password"))){
			request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
			request.setAttribute("address",CurrentSession.getMember().getAddress());
			request.setAttribute("email",CurrentSession.getMember().getEmail());
			request.setAttribute("member", CurrentSession.getMember());

			int userId=CurrentSession.getMember().getId();
			ResultSet myBooks=BookServiceController.getInstance().getBooks("OwnedBooks",userId);
			ResultSet myBorrowedBooks=BookServiceController.getInstance().getBooks("BorrowedBooks",userId);
			ResultSet myRequestedBooks=BookServiceController.getInstance().getBooks("RequestedBooks",userId);
			ResultSet myGroups=MemberServiceController.getInstance().getgroups(CurrentSession.getMember().getId());

			request.setAttribute("ownedbooks", myBooks);
			request.setAttribute("groups", myGroups);
			request.setAttribute("borrowedbooks", myBorrowedBooks);
			request.setAttribute("requestedbooks", myRequestedBooks);
			nextPage="/home.jsp";
		}
		else{
			nextPage="/register.jsp";

		}

		getServletContext().getRequestDispatcher(nextPage).forward(request, response);

	}



}
