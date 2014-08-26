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
<title>SocialLibrary</title>
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
			 <%@page import="java.sql.*" %>
			 <% ResultSet memberInfo=(ResultSet)request.getAttribute("memberinfo");
			  if(memberInfo!=null){
				  memberInfo.first();%>		  
			   Name: <%=memberInfo.getString("firstname")+" "+memberInfo.getString("lastname") %><br>
			   Address: <%=memberInfo.getString("address")%><br>
			   E-Mail: <%=memberInfo.getString("email")%><br><br>
			  <% }
			 %>
			</div>
			<div class="row container">
                <div class="row">
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseOne"> Books Owned </a>
							</h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in">
							<div class="panel-body">
								<%@page import="java.util.*"%>
								<%
								
								ResultSet ownedbooks=(ResultSet)request.getAttribute("ownedbooks");
							    if(ownedbooks!=null){
							    while(ownedbooks.next()){
							    	String url="/SocialLibrary/BookInformationServlet?id="+ownedbooks.getInt("memberbookid");
								%>
								<a href=<%=url%>><%=ownedbooks.getString("bookname")%><br></a>
								
							<%}} %>

							</div>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" data-parent="#accordion"
									href="#collapseTwo"> Books Borrowed </a>
							</h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse">
							<div class="panel-body">
								<%
								
								ResultSet borrowedBooks=(ResultSet)request.getAttribute("borrowedbooks");
							    if(borrowedBooks!=null){
							    while(borrowedBooks.next()){
								%>
								<%=borrowedBooks.getString("bookname")%><br>
								
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
								
								ResultSet requestedBooks=(ResultSet)request.getAttribute("requestedbooks");
							    if(requestedBooks!=null){
							    while(requestedBooks.next()){
							    	String url="/SocialLibrary/BookInformationServlet?id="+requestedBooks.getInt("member_book_id");
								%>
								<a href=<%=url%>><%=requestedBooks.getString("bookname")%><br></a>
								
							<%}} %>
								
							</div>
						</div>
					</div>
				</div>
			</div>     
                    
             </div>
		</div>
    </div>
</body>

</html>