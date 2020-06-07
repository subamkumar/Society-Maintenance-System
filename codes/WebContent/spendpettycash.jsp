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
		String spendheader = request.getParameter("spendheader");
		String pettydesc = request.getParameter("pettydesc");
		String pettydate = request.getParameter("pettydate");
		
		dataManager d = new dataManager();
		
		if(pettyamount!=null && spendheader!=null && pettydesc!=null && pettydate!=null)
		{
			int res = d.spendPettyBalance(pettyamount,spendheader,pettydesc,pettydate);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong>Petty Cash Updated Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error!</strong>Petty Cash Not Updated Successfully</div>");
			}
		}
	%>



	<div class="well well-sm">
			<b>Current Petty Balance: </b><span></span><%=database.getDatabase().getPettyBalance() %></span>
		</div>
		<form action="http://localhost:8182/society_latierra/spendpettycash.jsp" method="post">
			<input type="text" placeholder="Enter the Amount to be Spend" class="form-control" name="pettyamount">
			<br>
			<input type="date" class="form-control" name="pettydate">
			<br>
			<select class="form-control" name="spendheader">
			<%
				LinkedList<String> ll = database.getDatabase().getPettyHeaders();
			
				if(ll !=null)
				{
					while(!ll.isEmpty())
					{
						String s = ll.removeFirst();
						out.println("<option value=\""+s+"\">"+s+"</option>");	
					}
					
				}
			%>
      		</select>
			<br>
			<textarea class="form-control" rows="5" placeholder="Spending description..." name="pettydesc" id="comment"></textarea>
			<br>
			<button style="margin-left:500px;" type="submit" class="btn btn-success">Spend Amount</button>
		</form>
</div>
</body>
</html>