package com.sociallibrary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.domain.BookInformation;
import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.BookServiceController;

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
		
        if(request.getParameter("operation")!=null&&(request.getParameter("operation").equals("setAvailibility"))){
        	BookServiceController.getInstance().updateBook("availability", memberbookId);
		}
        if(request.getParameter("operation")!=null&&(request.getParameter("operation").equals("setBorrower"))){
        	BookServiceController.getInstance().updateBook("borrower", memberbookId);
		}
        
        BookInformation bookInfo=new BookInformation(memberbookId);
		request.setAttribute("book", bookInfo.getBook());
		request.setAttribute("owner", bookInfo.getBookOwner());
		request.setAttribute("borrower",bookInfo.getBookBorrower() );
		request.setAttribute("id",memberbookId);
		request.setAttribute("requestors", bookInfo.getBookRequestors());
		
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		request.setAttribute("member", CurrentSession.getMember());
		getServletContext().getRequestDispatcher("/book_info.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
