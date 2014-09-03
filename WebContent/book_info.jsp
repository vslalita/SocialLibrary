<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron page-header" style="background-color: #f0ad4e;">
		<img src="http://www.mtzion.lib.il.us/logo15.jpg/image_preview">
		<strong><large> Social Library</large></strong>

	</div>

	<div class="row container-fluid">
		<div class="col-md-4 container-fluid">
			
			Profile Information<br> Name:<%=request.getAttribute("name").toString()%><br>
			Address:<%=request.getAttribute("address").toString()%><br>
			E-Mail:<%=request.getAttribute("email").toString()%><br>
		</div>
		<div class="col-md-8">
		
		<%@page import="java.sql.*"%>
		 <% ResultSet bookDetails=(ResultSet)request.getAttribute("book"); %>
		 <%bookDetails.beforeFirst();%>
		 <%while(bookDetails.next()){ %>
		  Name: <%=bookDetails.getString("bookname") %><br>
		  ISBN: <%=bookDetails.getString("ISBN") %><br>
		  Availability: <%=bookDetails.getBoolean("availability") %>
		  <%} %>
		  <br>
		  <u><strong>OwnerInformation</strong></u><br>
		  <% ResultSet ownerinfo=(ResultSet)request.getAttribute("owner"); 
		   if(ownerinfo!=null){
			   
		   while(ownerinfo.next()){ %>
		  Name: <%=ownerinfo.getString("firstname") %></br>
		  Address: <%=ownerinfo.getString("address") %></br>
		  Email: <%=ownerinfo.getString("email") %></br>
		  <%} }%>
		
		   <u><strong>Borrower Information</strong></u><br>
		  <%ResultSet borrowerinfo=(ResultSet)request.getAttribute("borrower"); %>
		 <% if(borrowerinfo!=null){
		  while(borrowerinfo.next()){ %>
		  Name: <%=borrowerinfo.getString("firstname") %> <br>
		  Address: <%=borrowerinfo.getString("address") %> <br>
		  Email: <%=borrowerinfo.getString("email") %> <br>
		  <%} }%>
		  <br>
		  <div class="row">
		    <div class="col-md-3">
		    <%String url="/SocialLibrary/BookInformationServlet?operation=setAvailibility&id="+request.getAttribute("id"); %>
		     <%String deliveryurl="/SocialLibrary/BookInformationServlet?operation=setBorrower&id="+request.getAttribute("id"); %>
		      <a href=<%=url%> > <button type="button" class="btn btn-warning">Set Availability</button></a><br><br>
		      <a href=<%=deliveryurl%>> <button type="button" class="btn btn-warning">Delivered</button></a>
		    </div>
		  </div>
		  <div class="row">
		  People who requested for this book:
		  <% ResultSet requestors=(ResultSet)request.getAttribute("requestors"); 
		  if(requestors!=null){
			  int i=1;
			  while(requestors.next()){
				  %><%=i%><%=requestors.getString("firstname")+" "+requestors.getString("lastname")%>
			  <%}
		  }
		  %>
		  
		  </div>
		  
		</div>
	</div>
</body>
</html>