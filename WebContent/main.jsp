<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

html {
	background:
		url(http://www.robkalescancer.com/wp-content/uploads/2014/05/Most-Frequently-Challenged-Book-ftr.jpg)
		no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

#page-wrap {
	width: 1000px;
	margin: 50px auto;
	padding: 20px;
	background: white;
	-moz-box-shadow: 0 0 20px black;
	-webkit-box-shadow: 0 0 20px black;
	box-shadow: 0 0 20px black;
}

p {
	font: 15px/2 Georgia, Serif;
	margin: 0 0 30px 0;
	text-indent: 40px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Library</title>
</head>
<body>
 
	<div id="page-wrap" class="page-header"
		style="background-color: #f0ad4e;">
		<img src="http://www.mtzion.lib.il.us/logo15.jpg/image_preview">
		<strong><large> Social Library</large></strong> </img>
	</div>
		<div class="row container">
			<div class="col-md-4  container">
				<form action="MainPage" role="form" method="POST">
					<div class="form-group">
						<label for="exampleInputEmail1">Username</label> <input
							type="input" class="form-control" name="username"
							placeholder="Enter username">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" class="form-control" name="password"
							placeholder="Enter Password">
					</div>
					<button type="submit" class="btn btn-default">Login</button>
					<br>
					<br>
					<a href="/SocialLibrary/register.jsp"><button class="btn btn-default">Register</button></a>
					<br>
					<br>
				</form>
			</div>
			<div class="col-md-5 container">
				<large>This is a Social Library which allows you to share
				and borrow books among your group of friends. It also helps you keep
				track of your books making it. </br>
				</br>
				Get started by registering and creating a group. </large>
			</div>
		</div>
	
</body>
</html>