package com.project.serch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BookSearch implements SearchOperation{
	JDBCConnectionFactory factory= JDBCConnectionFactory.getFactory();
	Statement statement = null;
	Connection connection = factory.setConnection();
	@Override
	public ArrayList<String> doSearch(String name, String type) {
		// TODO Auto-generated method stub
		String xyz = null;
		String sql = "SELECT * FROM books where bookname= '"+name+ "'";
	     try {
			ResultSet rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
	}
}
	 }
