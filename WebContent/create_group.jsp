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
			
		</div>
		<div class="col-md-8">
			<div class="row">
				<form action="CreateGroupServlet" role="form" method="POST">
				<div class="form-group">
					<label>Group Name:</label> <input
						type="input" class="form-control" name="groupname"
						placeholder="Enter group name">
				</div>
				<button type="submit" class="btn btn-warning">create</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>