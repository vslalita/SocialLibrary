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
				<button type="button" onclick="showaddform()"
					class="btn btn-warning"
					href="/SocialLibrary/AddDeleteOperationServlet">Add</button>
			</div>
			<br>
			<div style="display: none" id="addform">

				<form action="AddDeleteOperationServlet" role="form" method="POST">
					<div class="form-group">
						<label>Book Name:</label> <input type="input" class="form-control"
							name="bookname" placeholder="Enter book name">
					</div>
					<div class="form-group">
						<label>ISBN:</label> <input type="input" class="form-control"
							name="isbn" placeholder="Enter ISBN name">
					</div>

					<div class="form-group">
						<label>Rating:</label> <input type="input" class="form-control"
							name="rating" placeholder="Rate your book">
					</div>

					<div class="form-group">
						<label>Book Category:</label> <input type="input"
							class="form-control" name="category"
							placeholder="Enter Book Category">
					</div>

					<button type="submit" class="btn btn-warning">Add</button>
				</form>
				<br>
				<br>
			</div>
			<div class="row">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne"> Books Added </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<%@page import="java.util.*"%>
								<%@page import="com.sociallibrary.*"%>

							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo"> Books be Deleted </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<%
								
								String[] deleteList=(String[])request.getAttribute("deletedBooks");
							    if(deleteList!=null){
							    for(int i=0;i<deleteList.length;i++){
							    	String bookname=deleteList[i];
								%>
								<%=bookname%>
								
							<%}} %>
							
							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseThree"> Books Requested </a>
							</h4>
						</div>
						<div id="collapseThree" class="panel-collapse collapse">
							<div class="panel-body">
								<%
								String[] requestList=(String[])request.getAttribute("requestedBooks");
							    if(deleteList!=null){
							    for(int i=0;i<requestList.length;i++){
							    	String bookname=requestList[i];
								%>
								<%=bookname%>
								
							<%}} %>
								
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
			 <a href="/SocialLibrary/AddDeleteOperationServlet?operation=ExecuteAll"><button type="button" class="btn btn-warning">Execute all</button></a>
			  <a href="/SocialLibrary/AddDeleteOperationServlet?operation=CancelAll"><button type="button" class="btn btn-warning">Cancel all</button></a>
			</div>
		</div>
	</div>
	<script>
		function showaddform() {
			document.getElementById("addform").style.display = "block";
		}
	</script>
</body>
</html>