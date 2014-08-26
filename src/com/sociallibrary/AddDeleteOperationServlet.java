package com.sociallibrary;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if(request.getParameter("operation")!=null && request.getParameter("operation").equals("ExecuteAll")){
			of.executeRequests();
			System.out.println("Executed");
		}
		
		String[] deletedBooks=new String[of.displayDeletedBooks().size()];
		deletedBooks=of.displayDeletedBooks().toArray(deletedBooks);
		
		String[] requestedBooks=new String[of.displayRequestedBooks().size()];
		requestedBooks=of.displayRequestedBooks().toArray(requestedBooks);
		
		request.setAttribute("deletedBooks", deletedBooks);
		request.setAttribute("requestedBooks", requestedBooks);
		request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
		request.setAttribute("address",CurrentMember.cm.current_member.address);
		request.setAttribute("email",CurrentMember.cm.current_member.Email);
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
		BookServiceController.bookServicecontroller.addBook(newBook);
		
		request.setAttribute("name",CurrentMember.cm.current_member.firstName+" "+CurrentMember.cm.current_member.lastName);
		request.setAttribute("address",CurrentMember.cm.current_member.address);
		request.setAttribute("email",CurrentMember.cm.current_member.Email);
		getServletContext().getRequestDispatcher("/add_delete.jsp").forward(request, response);
	}

}
