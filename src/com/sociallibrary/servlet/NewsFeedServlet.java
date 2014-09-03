package com.sociallibrary.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.domain.CurrentSession;
import com.sociallibrary.service.MemberServiceController;

/**
 * Servlet implementation class NewsFeedServlet
 */
@WebServlet("/NewsFeedServlet")
public class NewsFeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsFeedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.print("Helloooo");
		if((request.getParameter("notificationOperation")!=null)&&(request.getParameter("notificationOperation").equals("delete"))){
			MemberServiceController.getInstance().deleteNotifications();
		}
	
		ResultSet notifications=MemberServiceController.getInstance().getNotifications();
		request.setAttribute("notifications", notifications);
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		
		getServletContext().getRequestDispatcher("/newsfeed.jsp").forward(request, response);
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
