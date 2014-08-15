package com.sociallibrary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookInformationServlet
 */
@WebServlet("/BookInformationServlet")
public class BookInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInformationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int memberbookId=Integer.valueOf(request.getParameter("id"));
		BookOperations bo=new BookOperations();
		MemberOperations mo=new MemberOperations();
		ResultSet bookDetails =bo.getBookbyId(memberbookId);
		request.setAttribute("book", bookDetails);
		
		try {
			bookDetails.first();
			ResultSet Owner=mo.getMemberInfo(bookDetails.getInt("owner_id"));
			ResultSet Borrower=mo.getMemberInfo(bookDetails.getInt("borrower_id"));
			request.setAttribute("owner", Owner);
			request.setAttribute("borrower", Borrower);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
		request.setAttribute("address",CurrentMember.cm.current_member.address);
		request.setAttribute("email",CurrentMember.cm.current_member.Email);
		request.setAttribute("member", CurrentMember.cm.current_member);
		getServletContext().getRequestDispatcher("/book_info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
