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
      <li><a href="http://localhost:8182/society_latierra/pettyinfo.jsp">Petty Info</a></li>
      <li><a href="http://localhost:8182/society_latierra/spendpettycash.jsp">Spend Petty Cash</a></li>
      <li><a href="http://localhost:8182/society_latierra/addpettycash.jsp">Add Petty Cash</a></li>
      <li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Petty Headers</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
	<div class="container" style="margin-top:60px;">
		
		<%
			String pettyamount = request.getParameter("pettyamount");
			String pettydate = request.getParameter("pettydate");
			String pettydesc = request.getParameter("pettydesc");
			
			dataManager d = new dataManager();
			if(pettyamount!=null && pettydate!=null && pettydesc!=null)
			{
				int res = d.addPettyBalance(pettyamount,pettydate,pettydesc);
				
				if(res == 1)
				{
					out.println("<div class=\"alert alert-success\">"+
							  "<strong>Success!</strong>Added Petty Cash Successfully</div>");
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong> Not Added Petty Cash Successfully</div>");
				}
			}
		%>
	
		<div class="well well-sm">
			<b>Current Petty Balance: </b><span></span><%=database.getDatabase().getPettyBalance() %></span>
		</div>
		<form action="http://localhost:8182/society_latierra/addpettycash.jsp" method="post">
			<input type="text" placeholder="Enter the Amount to be added" class="form-control" name="pettyamount">
			<br>
			<input type="date" class="form-control" name="pettydate">
			<br>
			<textarea class="form-control" rows="5" placeholder="Payment description..." name="pettydesc" id="comment"></textarea>
			<br>
			<button style="margin-left:500px;" type="submit" class="btn btn-success">Add Petty Amount</button>
		</form>
		
		<br><br><br>
		<h3>Petty Cash Details</h3>
		<%
			LinkedList<String> ll = database.getDatabase().getPettyCash();
		
			if(ll!=null)
			{
				out.println("<table class=\"table table-striped\"><thead><tr><th>Sr No</th><th>Amount</th><th>Date</th><th>Description</th></tr></thead><tbody>");
				int count = 0;
				while(!ll.isEmpty())
				{
					count++;
					String p[] = ll.removeLast().split(",");
					
					out.println("<tr><td>"+count+"</td>");
					out.println("<td>"+p[0]+"</td>");
					out.println("<td>"+p[1]+"</td>");
					out.println("<td>");
					for(int i=2;i<p.length;i++)
					{
						out.print(p[i]+" ");
					}
					out.println("</td></tr>");
				}
				out.println("</tbody></table>");
			}
			else
			{
				out.println("<b>No Records Found</b>");
			}
		%>
	</div>
</body>
</html>