package com.sociallibrary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.domain.Book;
import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.BookServiceController;
import com.sociallibrary.service.operations.OperationsFacade;

/**
 * Servlet implementation class AddDeleteOperationServlet
 */
@WebServlet("/AddDeleteOperationServlet")
public class AddDeleteOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeleteOperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		OperationsFacade of=new OperationsFacade();
		
		if(request.getParameter("operation")!=null && request.getParameter("operation").equals("CancelAll")){
			of.removeOperations();
			System.out.println("removed");
		}
		if(request.getParameter("operation")!=null && request.getParameter("operation").equals("ExecuteAll")){
			of.executeRequests();
			System.out.println("Executed");
		}
		
		String[] deletedBooks=new String[of.displayDeletedBooks().size()];
		deletedBooks=of.displayDeletedBooks().toArray(deletedBooks);
		
		String[] requestedBooks=new String[of.displayRequestedBooks().size()];
		requestedBooks=of.displayRequestedBooks().toArray(requestedBooks);
		
		request.setAttribute("deletedBooks", deletedBooks);
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		request.setAttribute("requestedBooks", requestedBooks);
		
		getServletContext().getRequestDispatcher("/add_delete.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name=(String) request.getParameter("bookname");
		String isbn=(String) request.getParameter("isbn");
		String category=(String) request.getParameter("category");
		int rating=Integer.valueOf(request.getParameter("rating"));
		
		Book newBook=new Book(name,category,rating,isbn);
		BookServiceController.getInstance().addBook(newBook);
		
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		getServletContext().getRequestDispatcher("/add_delete.jsp").forward(request, response);
	}

}
