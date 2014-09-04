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
	<div class="jumbotron page-header" style="background-color: #f0ad4e;">
		<img src="http://www.mtzion.lib.il.us/logo15.jpg/image_preview">
		<strong><large> Social Library</large></strong> </img>

	</div>

	<div class="row container-fluid">
		<div class="col-md-4 container-fluid">
			Profile Information<br> Name:<%=request.getAttribute("name").toString()%><br>
			Address:<%=request.getAttribute("address").toString()%><br>
			E-Mail:<%=request.getAttribute("email").toString()%><br>
		</div>
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
				<!-- /.container-fluid --> </nav>
			</div>

			<div class="row">
				<div class="col-md-2">
					<form action="MemberBooksServlet" method="POST">
						<select name="groupname" class="form-control">
							<%@page import="java.sql.*"%>
							<%
								ResultSet mygroups = (ResultSet) request.getAttribute("groups");
							%>
							<%  if(mygroups!=null){
								try {
									while (mygroups.next()) {
							%>
							<option value=<%=mygroups.getString("groupname").toString()%>>
								<%=mygroups.getString("groupname")%><br>
							</option>
							<%
								}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}}
							%>
						</select><br>
						<button type="submit" class="btn btn-default">Search
							Books</button>
					</form>
					<br> <br>
					
				</div>

			</div>
			<div class="row">
						<div class="col-md-8">
							<table class="table">
								<thead>
									<tr>
										<strong>Bookname </strong>
									</tr>
								</thead>
								<tbody>
									<%
										ResultSet groupBooks = (ResultSet) request
												.getAttribute("groupbooks");
									%>
									<% if(groupBooks!=null){
										try {
											while (groupBooks.next()) {
									%><tr>
										<td><%=groupBooks.getString("bookname")%><br></td>
										<%String url="/SocialLibrary/MemberBooksServlet?operation=Request&id="+groupBooks.getInt("id"); %>
										<td><a href=<%=url%>><button type="button" class="btn btn-warning">Request</button></a></td>
									</tr>
									<%
										}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}}
									%>
								</tbody>
							</table>
						</div>
					</div>
		</div>
	</div>
</body>
</html>