<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
							<li><a href="/SocialLibrary/AddDeleteOperationServlet">Add/Delete</a></li>
						</ul>
					</div>
                    
				</div>
				</nav>
				<div class="row container">
                      <%@page import="java.sql.*" %>
                    
                    </div>
			</div>
		</div>
	</div>
</body>

</html>