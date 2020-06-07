<%@ page import="society.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/side-nav.css"/>
</head>
<body>

<%
	if(request.getSession().getAttribute("userID") == null)
	{
		response.sendRedirect("http://localhost:8182/society_latierra/");
	}
%>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a href="#">
          <span onclick="openNav()" style="color:white;font-size:30px;margin-top:10px;margin-right:30px;" class="glyphicon glyphicon-menu-hamburger"></span>
      </a>
    </div>
    <ul class="nav navbar-nav">
     	<%
     		String url = request.getHeader("referer");
     		if(url.contains("pettyinfo.jsp"))
     		{
     	%>
      		<li><a href="http://localhost:8182/society_latierra/pettyinfo.jsp">Petty Info</a></li>
      		<li><a href="http://localhost:8182/society_latierra/spendpettycash.jsp">Spend Petty Cash</a></li>
      		<li><a href="http://localhost:8182/society_latierra/addpettycash.jsp">Add Petty Cash</a></li>
      		<li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Petty Headers</a></li>
      	<%
     		}
     		else if(url.contains("expinfo.jsp"))
     		{
      	%>
      		<li><a href="http://localhost:8182/society_latierra/expinfo.jsp">Expenditure Info</a></li>
      		<li><a href="http://localhost:8182/society_latierra/addexpenditure.jsp">Add Expenditure</a></li>
      		<li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Headers</a></li>
      	<%
     		}
     		else if(url.contains("incomeinfo.jsp"))
     		{
      	%>
      		<li><a href="http://localhost:8182/society_latierra/incomeinfo.jsp">Income Info</a></li>
      		<li><a href="http://localhost:8182/society_latierra/addincome.jsp">Add Income</a></li>
      		<li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Headers</a></li>
      	<%
     		}
     		else
     		{
      	%>
      		<li><a href="http://localhost:8182/society_latierra/pettyinfo.jsp">Petty Info</a></li>
      		<li><a href="http://localhost:8182/society_latierra/spendpettycash.jsp">Spend Petty Cash</a></li>
      		<li><a href="http://localhost:8182/society_latierra/addpettycash.jsp">Add Petty Cash</a></li>
      		<li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Petty Headers</a></li>
      	<%
     		}
      	%>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<div class="container" style="margin-top:60px;">

	<%
		String header = request.getParameter("header");
				
		
		if(header!=null)
		{
			int res = database.getDatabase().insertPettyHeaders(header);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong>Header Added Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error!</strong>Header Not Added Successfully</div>");
			}
		}
	%>


	<form action="http://localhost:8182/society_latierra/pettyheader.jsp" method="post">
		<input type="text" placeholder="Enter the Header to be added" class="form-control" name="header">
		<br>
		<button style="margin-left:500px;" type="submit" class="btn btn-success">Add Header</button>
	</form>
	<h2 style="text-decoration:underline;">LIST OF HEADERS</h2>
	<%
		LinkedList<String> ll = database.getDatabase().getPettyHeaders();
	
		if(ll == null)
		{
			out.println("<b>No Records Found</b>");
		}
		else
		{
			int count = 0;
			while(!ll.isEmpty())
			{
				count++;
				out.println("<p>"+count+". "+ll.removeFirst()+"</p>");	
			}
		}
	%>
</div>
</body>
</html>