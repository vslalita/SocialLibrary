<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/custom.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Social Library</title>
</head>
<body>
<div class="jumbotron page-header"
		style="background-color: #f0ad4e;">
		<img src="http://www.mtzion.lib.il.us/logo15.jpg/image_preview">
			<strong><large> Social Library</large></strong>
		</img> 
		
	</div>
  <div class="container">
	<div class="row">
		<div class="col-md-4 container">
			<form action="RegisterServlet" role="form" method="POST">
				<div class="form-group">
					<label>First Name:</label> <input
						type="input" class="form-control" name="firstname"
						placeholder="Enter first name">
				</div>
				<div class="form-group">
					<label>Last Name:</label> <input
						type="input" class="form-control" name="lastname"
						placeholder="Enter last name">
				</div>
				
				<div class="form-group">
					<label>Address:</label> <input
						type="input" class="form-control" name="address"
						placeholder="Enter address">
				</div>
				
				<div class="form-group">
					<label>Email:</label> <input
						type="email" class="form-control" name="email"
						placeholder="Enter E-Mail">
				</div>
				
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
				
				<button type="submit" class="btn btn-default">Sign up</button>
			</form>
			<br>
			<br>
		</div>
		<div class="col-md-5 container">
		<large>This is a Social Library which allows you to share and borrow books among your group of friends. It also helps you keep track of your books making it.
</br></br>
Get started by registering and creating a group.
		</large>
		</div>
		
	</div>
  </div>


</body>
</html>