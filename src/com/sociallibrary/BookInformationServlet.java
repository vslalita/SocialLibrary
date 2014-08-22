package com.sociallibrary;

import java.io.IOException;
import java.io.PrintWriter;
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
		PrintWriter out=response.getWriter();
		int memberbookId=Integer.valueOf(request.getParameter("id"));
		
        if(request.getParameter("operation")!=null&&(request.getParameter("operation").equals("setAvailibility"))){
			if(BookServiceController.bookServicecontroller.updateAvailibility(memberbookId)){
				
			}
			else{
				out.println("You cannot update availability because you are not an owner or a borrower for this book");
			}
		}
		ResultSet bookDetails =BookServiceController.bookServicecontroller.getBookbyId(memberbookId);
		request.setAttribute("book", bookDetails);
		
		try {
			bookDetails.first();
			ResultSet Owner=MemberServiceController.memberServicecontroller.getMemberInfo(bookDetails.getInt("owner_id"));
			ResultSet Borrower=MemberServiceController.memberServicecontroller.getMemberInfo(bookDetails.getInt("borrower_id"));
			request.setAttribute("owner", Owner);
			request.setAttribute("borrower", Borrower);
			request.setAttribute("id",memberbookId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet requestors=BookServiceController.bookServicecontroller.getBookRequestors(memberbookId);
		request.setAttribute("requestors", requestors);
		
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
