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
 * Servlet implementation class MemberInfoServlet
 */
@WebServlet("/MemberInfoServlet")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		int id=Integer.valueOf(request.getParameter("id"));
		ResultSet memberBorrowedBooks= BookServiceController.getInstance().getBooks("BorrowedBooks", id);
		ResultSet memberOwnedBooks=BookServiceController.getInstance().getBooks("OwnedBooks", id);
		ResultSet memberRequestedBooks=BookServiceController.getInstance().getBooks("RequestedBooks", id);
		ResultSet memberInfo =MemberServiceController.getInstance().viewMemberDetails(id);
		
		request.setAttribute("ownedbooks", memberOwnedBooks);
        request.setAttribute("borrowedbooks", memberBorrowedBooks);
        request.setAttribute("requestedbooks", memberRequestedBooks);
        request.setAttribute("memberinfo", memberInfo);
        
        request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		request.setAttribute("member", CurrentSession.getMember());
		
		getServletContext().getRequestDispatcher("/member_info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
