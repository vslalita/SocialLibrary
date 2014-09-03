package com.sociallibrary.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sociallibrary.ActualPrice;
import com.sociallibrary.BitCoinTotal;
import com.sociallibrary.CreditCardTotal;
import com.sociallibrary.OrderPriceCalculator;
import com.sociallibrary.TaxTotal;
import com.sociallibrary.db.DBHelper;
import com.sociallibrary.domain.CurrentSession;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql="Select * from books where id="+request.getParameter("id");
		ResultSet purchasingBook=DBHelper.getQueryResult(sql);
		
		request.setAttribute("name",CurrentSession.getMember().getFirstName()+" "+CurrentSession.getMember().getLastName());
		request.setAttribute("address",CurrentSession.getMember().getAddress());
		request.setAttribute("email",CurrentSession.getMember().getEmail());
		try {
			purchasingBook.first();
			String paymentOption=request.getParameter("payment_option");
			int price=purchasingBook.getInt("price");
			String deliveryType=request.getParameter("delivery_type");
			
			System.out.println("the parameters are" +paymentOption +deliveryType);
			OrderPriceCalculator calctotal1 = new ActualPrice();
			request.setAttribute("ActualPrice", calctotal1.calculate(price));
			if((paymentOption.equals("creditcard") || paymentOption.equals("bitcoin")) && deliveryType.equals("selfpickup")){
				System.out.println("In Normal Price");
				OrderPriceCalculator calctotal = new ActualPrice();
				request.setAttribute("ActualPrice", calctotal.calculate(price));
			}
			else if((paymentOption.equals("creditcard")) && deliveryType.equals("ownerdrop")){
				System.out.println("In Credit Card");
				OrderPriceCalculator calctotal = new TaxTotal(new CreditCardTotal(new ActualPrice()));
				request.setAttribute("CreditCard", calctotal.calculate(price));
				
			}
			else if((paymentOption.equals("bitcoin")) && deliveryType.equals("ownerdrop")){
				System.out.println("In BitCoin");
				OrderPriceCalculator calctotal = new TaxTotal(new BitCoinTotal(new ActualPrice()));
				request.setAttribute("BitCoin", calctotal.calculate(price));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/placeorder.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
