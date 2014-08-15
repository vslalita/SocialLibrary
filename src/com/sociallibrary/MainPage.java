package com.sociallibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		

		response.setContentType("HTML");
		PrintWriter out=response.getWriter();

		try { 
			MemberOperations mo=new MemberOperations();
			if(mo.register(new Member("Siva","Lalita","SivaLalita","slt1","1050Benton","l@gmail.com"))){
				out.println("User has been created");
			}
			else{
				out.println("User Already Exists");
			}
			
			if(mo.login("SivaLalita", "slt1")){
				out.println("Logged in");
				out.println(CurrentMember.cm.current_member.firstName);
			}
			
			BookOperations bo=new BookOperations();
			Book b=new Book("helloBook","fiction",2,"1SD4");
			bo.addBook(b);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberOperations mo=new MemberOperations();
		response.setContentType("HTML");
		//PrintWriter out=response.getWriter();

		if(mo.login(request.getParameter("username"),request.getParameter("password"))){
			request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
			request.setAttribute("address",CurrentMember.cm.current_member.address);
			request.setAttribute("email",CurrentMember.cm.current_member.Email);
			request.setAttribute("member", CurrentMember.cm.current_member);
			BookOperations bo=new BookOperations();
			ResultSet myBooks=bo.getMyBooks();
			ResultSet myGroups=mo.getgroups();
			ResultSet myBorrowedBooks=bo.getMyBorrowedBooks();
			
            request.setAttribute("ownedbooks", myBooks);
            request.setAttribute("groups", myGroups);
            request.setAttribute("borrowedbooks", myBorrowedBooks);
            
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
		else{
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}
		
	}

}
