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
							<li class="active"><a href="/SocialLibrary/HomeServlet">Home</a></li>
							<li><a href="/SocialLibrary/MemberBooksServlet">Books</a></li>
							<li><a href="#">Groups</a></li>
							<li><a href="#">Add/Delete</a></li>
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
										<%
											try {
												while (rs.next()) {
										%>
										<%=rs.getString("bookname")%><br>
										<%
											}
											} catch (SQLException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										%>
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
											}
										%>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row container">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseThree"> Recently Added Book</a>
							</h4>
						</div>
					</div>
				</div>
</body>
</html>