<%@ page import="society.*,java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>LA-TIERRA HOUSING SOCIETY | Management System</title>
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
      <li><a href="http://localhost:8182/society_latierra/specificflat.jsp">By Flats</a></li>
      <li><a href="http://localhost:8182/society_latierra/allflats.jsp">All Flats</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<div class="container" style="margin-top:60px;">
<div class="row">
	<form action="http://localhost:8182/society_latierra/allflats.jsp" method="post">
	<div class="col-md-6">
		<select class="form-control" name="type" style="margin-left:200px;">
			<option value="all">All</option>
			<option value="owner">Owner</option>
			<option value="tenants">Tenants</option>
			<option value="coowners">Co-Owners</option>
		</select>
	</div>
		<button style="margin-left:200px;" type="submit" class="btn btn-success">Search</button>
	<div class="col-md-6">
	
	</div>
	</form>
</div>
	
		
		
	
	
	<%
		String type = request.getParameter("type");
		dataManager d = new dataManager();
		
		if(type!=null)
		{
			if(type.equals("all"))
			{
				out.println("<br><h3>ALL FLATS INFO</h3> - <a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=flat&q=all\">Download All Flat's PDF</a><br>");
				out.println("<table class=\"table table-striped\"><thead><tr><th>Flat-No</th><th>Name</th><th>Area</th><th>Mobile</th><th>E-mail</th><th>Category</th></tr></thead><tbody>");
				LinkedList<sortall> ll = d.getAllFlatsDetail();
				if(ll!=null)
				{
					while(!ll.isEmpty())
					{
						sortall s = ll.removeFirst();
						
						out.println("<tr>");
						
						out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+s.getFlatno()+"\">"+s.getFlatno().toUpperCase()+"</a></td>");
						out.println("<td>"+s.getName().toUpperCase()+"</td>");
						out.println("<td>"+s.getArea()+" Sqft</td>");
						out.println("<td>"+s.getMobile()+"</td>");
						out.println("<td>"+s.getEmail()+"</td>");
						out.println("<td>"+s.getCategory().toUpperCase()+"</td>");
						out.println("</tr>");
					}
				}
				else
				{
					out.println("<tr><b>No Records Found</b></tr>");
				}
				out.println("</tbody></table>");
			}
			else if(type.equals("owner"))
			{
				out.println("<br><h3>ALL OWNER's INFO</h3> - <a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=flat&q=owner\">Download All Owner Flat's PDF</a><br><br>");
				out.println("<table class=\"table table-striped\"><thead><tr><th>Flat-No</th><th>Name</th><th>Area</th><th>Mobile</th><th>E-mail</th></tr></thead><tbody>");
				LinkedList<sortowners> ll = d.getOwnerFlatsDetail();
				if(ll!=null)
				{
					while(!ll.isEmpty())
					{
						sortowners s = ll.removeFirst();
						
						out.println("<tr>");
						
						out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+s.getFlatno()+"\">"+s.getFlatno().toUpperCase()+"</a></td>");
						out.println("<td>"+s.getOwner_name().toUpperCase()+"</td>");
						out.println("<td>"+s.getArea()+" Sqft</td>");
						out.println("<td>"+s.getMobile()+"</td>");
						out.println("<td>"+s.getEmail()+"</td>");
						out.println("</tr>");
					}
				}
				else
				{
					out.println("<tr><b>No Records Found</b></tr>");
				}
				out.println("</tbody></table>");
			}
			else if(type.equals("tenants"))
			{
				out.println("<br><h3>ALL TENANT's INFO</h3> - <a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=flat&q=tenant\">Download All Tenants Flat's PDF</a><br><br>");
				out.println("<table class=\"table table-striped\"><thead><tr><th>Flat-No</th><th>Name</th><th>Area</th><th>Mobile</th><th>E-mail</th></tr></thead><tbody>");
				LinkedList<sortall> ll = d.getAllFlatsDetail();
				if(ll!=null)
				{
					while(!ll.isEmpty())
					{
						sortall s = ll.removeFirst();
						
						if(s.getCategory().equalsIgnoreCase("tenant"))
						{
							out.println("<tr>");
							
							out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+s.getFlatno()+"\">"+s.getFlatno().toUpperCase()+"</a></td>");
							out.println("<td>"+s.getName().toUpperCase()+"</td>");
							out.println("<td>"+s.getArea()+" Sqft</td>");
							out.println("<td>"+s.getMobile()+"</td>");
							out.println("<td>"+s.getEmail()+"</td>");
							out.println("</tr>");	
						}
						
					}
				}
				else
				{
					out.println("<tr><b>No Records Found</b></tr>");
				}
				out.println("</tbody></table>");
			}
			else if(type.equals("coowners"))
			{
				out.println("<br><h3>ALL CO-OWNER's INFO</h3> - <a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=flat&q=coowner\">Download All Coowners Flat's PDF</a><br><br>");
				out.println("<table class=\"table table-striped\"><thead><tr><th>Flat-No</th><th>Names</th><th>Area</th><th>Mobiles</th><th>E-mails</th></tr></thead><tbody>");
				LinkedList<sortcoowner> ll = d.getCoownerFlatsDetail();
				if(ll!=null)
				{
					while(!ll.isEmpty())
					{
						sortcoowner s = ll.removeFirst();
						
						
						out.println("<tr>");
							
						out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+s.getFlatno()+"\">"+s.getFlatno().toUpperCase()+"</a></td>");
						
						Iterator<String> itr = s.getCoowner_name().iterator();
						out.println("<td>");
						while(itr.hasNext())
						{
							out.println(itr.next().toUpperCase()+"<br>");
						}
						out.println("</td>");
						
						
						out.println("<td>"+s.getArea()+" Sqft</td>");
						
					
						Iterator<String> itr1 = s.getMobile().iterator();
						out.println("<td>");
						while(itr1.hasNext())
						{
							out.println(itr1.next()+"<br>");
						}
						out.println("</td>");
						
						
						Iterator<String> itr2 = s.getEmail().iterator();
						out.println("<td>");
						while(itr2.hasNext())
						{
							out.println(itr2.next()+"<br>");
						}
						out.println("</td>");
						
						
						out.println("</tr>");	
						
						
					}
				}
				else
				{
					out.println("<tr><b>No Records Found</b></tr>");
				}
				out.println("</tbody></table>");
			}	
		}
		
		
	%>
	
</div>


</body>
</html>