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
		<strong><large> Social Library</large></strong> 
	</div>

	<div class="row container-fluid">
		<div class="col-md-4 container-fluid">
			<%@page import="com.sociallibrary.*"%>
			Profile Information<br> Name:<%=request.getAttribute("name").toString()%><br>
			Address:<%=request.getAttribute("address").toString()%><br>
			E-Mail:<%=request.getAttribute("email").toString()%><br>
		</div>
		<div class="col-md-8">
		     <div class="row">
		     <a href="/SocialLibrary/create_group.jsp"><button type="button" class="btn btn-warning">Create Group</button></a>
		     <a href="/SocialLibrary/MainPage?operation=signout"><button type="button" class="btn btn-warning">SignOut</button></a>
		     </div> <br><br>
		     
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
							<li class="active"><a href="/SocialLibrary/HomeServlet">Home</a></li>
							<li><a href="/SocialLibrary/MemberBooksServlet">Books</a></li>
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
			<%@page import="java.sql.*"%>
			<%
				ResultSet rs = (ResultSet) request.getAttribute("ownedbooks");
			%>
			<div class="row container">
				<div class="col-md-8 container">

					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"> <strong><large>Books
											You Own</large></strong>
									</a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="container-fluid">
                                      <table class="table">
                                      <tr>
                                        <th> Book Name</th>
                                        
                                      </tr>
                                      
										<%if(rs!=null){
											
										
											try { 
												
										while (rs.next()) {
											 String url="/SocialLibrary/BookInformationServlet?id="+rs.getInt("memberbookid");
											 String operationUrl="/SocialLibrary/HomeServlet?id="+rs.getInt("memberbookid")+"&operation=Delete";
										%>
										<tr><td>
										<a href=<%=url%>> <%=rs.getString("bookname")%></a></td>
										<td><a href=<%=operationUrl %>><button type="button" class="btn btn-warning">Delete</button></a></td></tr>
										<%
											}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}}
										%></table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row container">
				<div class="col-md-8 container">

					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne"> <strong><large>Books
											You Requested</large></strong>
									</a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="container-fluid">
                                      <table class="table">
                                      <tr>
                                        <th> Book Name</th>
                                        
                                      </tr>
                                      <tr><td>
										<%
										ResultSet myrequestedBooks = (ResultSet) request.getAttribute("requestedbooks");
									%>
									<%if(myrequestedBooks!=null){
										try {
											while (myrequestedBooks.next()) {
												String url="/SocialLibrary/BookInformationServlet?id="+myrequestedBooks.getInt("member_book_id");
									%>
									<a href=<%=url%>><%=myrequestedBooks.getString("bookname")%></a><br>
									<%
										}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}}
									%></td></tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row container">
					<div class="col-md-8">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo"><strong><large>Groups
											you Belong to</large></strong> <br></a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
								<div class="panel-body">

									<%
										ResultSet mygroups = (ResultSet) request.getAttribute("groups");
									  if(mygroups!=null){
									%>
									<%
										try {
											while (mygroups.next()) {
									%>
									<%=mygroups.getString("groupname")%><br>
									<%
										}
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}}
									%>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row container">
					<div class="col-md-8">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree"><strong><large>Books
											Borrowed </large></strong> <br></a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse">
								<div class="panel-body">

									<%
											ResultSet borrowedBooks = (ResultSet) request.getAttribute("borrowedbooks");
									      if(borrowedBooks!=null){
									%>
									<%
											try {
												while (borrowedBooks.next()) {
									%>
									<%=borrowedBooks.getString("bookname")%><br>
									<%
											}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}}
										%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div></div>
</body>
</html>