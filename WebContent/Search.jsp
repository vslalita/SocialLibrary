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
<title>Social Library</title>
</head>
<body>
<body>
	<div class="jumbotron page-header" style="background-color: #f0ad4e;">
		<img src="http://www.mtzion.lib.il.us/logo15.jpg/image_preview">
		<strong><large> Social Library</large></strong> </img>

	</div>

	<div class="row container-fluid">
		<div class="col-md-4 container-fluid"></div>
		<div class="col-md-8">
			<div class="row">
				<nav class="navbar navbar-default" role="navigation">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li><a href="/SocialLibrary/HomeServlet">Home</a></li>
							<li class="active"><a
								href="/SocialLibrary/MemberBooksServlet">Books</a></li>
							<li><a href="/SocialLibrary/GroupServlet">Groups</a></li>
							<li><a href="/SocialLibrary/SearchingServlet">Search</a></li>
							<li><a href="/SocialLibrary/AddDeleteOperationServlet">Add/Delete</a></li>
							<li><a href="/SocialLibrary/NewsFeedServlet">News Feed</a></li>
							<li><a href="/SocialLibrary/BuyBooksServlet">Buy Book</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->
				</div>
				</nav>
				<div class="row">
					<form class="form" action="SearchServlet2" method="GET">
						<div class="form-group">
						 <label>Search by</label>
							<select name="searchtype">
							   <option  value=" "></option>
								<option  value="bookname">BookName</option>
								<option  value="isbn">ISBN</option>
								<option value="membername">Membername</option>
							</select><br>
							<br>
							<div class="form-group">
								 <input
									type="input" class="form-control" name="searchattribute"
									placeholder="Search">
							</div>
							<button class="btn btn-default">Search</button>
						</div>
					</form>
				</div>
				<div class="row">
				   <%@page import="java.sql.*" %>
				   <%ResultSet bookList=(ResultSet)request.getAttribute("bookresult"); %>
				   <%if(bookList!=null){
					  while(bookList.next()){
						  %>
						  <%=bookList.getString("bookname") %>
					  <%}%>
					   
				  <%} %>
				  
				  <%ResultSet memberList=(ResultSet)request.getAttribute("memberresult"); %>
				   <%if(memberList!=null){
					  while(memberList.next()){
						  %>
						  <%=memberList.getString("firstname")+" "+memberList.getString("lastname") %>
					  <%}%>
					   
				  <%} %>
				  
				  <%ResultSet nullresult=(ResultSet)request.getAttribute("nullresult"); %>
				   <%if(nullresult!=null){
					  while(nullresult.next()){
						  %>
						  <%=nullresult.getString("nullvalu") %>
					  <%}%>
					   
				  <%} %>
				</div>
			</div>
			</div>
</body>
</html>