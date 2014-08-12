<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Box</title>
</head>
<body>
	<form action="SearchServlet" method="get">
		<h4>Search Box</h4>
		<table>
			<TR>
				<TD>Search</TD>
				<TD ALIGN="center"><INPUT TYPE="text" SIZE="25" NAME="search">
				</TD>
				<TD><select name="select">
						<option value="bookname">by BookName</option>
						<option value="ownername">by OwnerName</option>
						<option value="booktype">by BookType</option>
				</select></TD>
			</TR>



		</table>
		<INPUT TYPE="submit" VALUE="Submit">
	</form>
</body>
</html>