<%@ page import="society.*,java.util.LinkedList" %>
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

<div class="container" style="margin-top:60px;width:500px;">
	<form action="http://localhost:8182/society_latierra/specificflat.jsp" method="post">
		<div class="input-group">
			<input type="text" class="form-control" style="height:40px;" placeholder="Enter Flat Number like A-101" name="flatno">
				<div class="input-group-btn">
		        	<button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i></button>
		      	</div>
		</div>
	</form>
</div>
<br><br>

<%
	String flatno = request.getParameter("flatno");
	String cid = request.getParameter("cid");
	//String id = request.getParameter("id");
	String q = request.getParameter("q");
	
	if(q!=null && cid!=null && flatno!=null)
	{
		if(q.equals("rem"))
		{
			int res = new dataManager().deleteCoowner(cid);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong>Co-Owner Info Deleted Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error!</strong>Co-Owner Info Not Deleted Successfully</div>");
			}
		}
	}
	

	if(flatno!=null)
	{
		if(!flatno.equals(""))
		{
			out.println("<div class=\"container\">");
			dataManager d = new dataManager();
			specificflat sf =  d.getSpecificFlatDetails(flatno);
			//--------START------------Flat Details
			if(sf!=null)
			{
				FlatModel f = sf.getFlatInfo();
				if(f!=null)
				{
					out.println("<div class=\"well well-sm\"><b>Flat Number : </b>"+f.getFlatNo().toUpperCase()+"</div>");
					out.println("<div class=\"well well-sm\"><b>Flat Owner : </b>"+f.getOwnerName().toUpperCase()+"</div>");
					out.println("<div class=\"well well-sm\"><b>Flat Area : </b>"+f.getArea()+" Sqft</div>");
					out.println("<div class=\"well well-sm\"><b>Flat Email : </b>"+f.getEmail()+"</div>");
					out.println("<div class=\"well well-sm\"><b>Mobile No. : </b>"+f.getMobile()+"</div>");
					out.println("<div class=\"well well-sm\"><b>Category : </b>"+f.getOwnerCategory().toUpperCase()+"</div>");
					out.println("<div><b><a style=\"font-size:20px;float:right;\" target=\"_blank\" href=\"http://localhost:8182/society_latierra/editflat.jsp?id="+f.getFlatID()+"\">Edit</a></b></div>");
					
					
					
					LinkedList<CoownerModel> cm = sf.getCoownerInfo();
					
					if(cm!=null)
					{
						out.println("<br><h3>CO-OWNER's INFO</h3><br>");
						out.println("<table class=\"table table-striped\"><thead><tr><th>Coowner-Name</th><th>E-Mail</th><th>Mobile</th></tr></thead><tbody>");
						while(!cm.isEmpty())
						{
							CoownerModel c = cm.removeFirst();
							out.println("<tr>");
							out.println("<td>"+c.getCoowner_name()+"</td>");
							out.println("<td>"+c.getEmail()+"</td>");
							out.println("<td>"+c.getMobile()+"</td>");
							out.println("<td><b><a style=\"font-size:20px;float:right;\" target=\"_blank\" href=\"http://localhost:8182/society_latierra/coowner.jsp?q=edit&id="+c.getFid()+"&cid="+c.getId()+"\">Edit</a></b></td>");
							out.println("<td><b><a style=\"font-size:20px;float:right;\" target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?q=rem&flatno="+f.getFlatNo()+"&cid="+c.getId()+"\">Remove</a></b></td>");
							out.println("</tr>");
							//out.println("<div class=\"well well-sm\"><b>Coowner Name : </b>"+c.getCoowner_name()+"</div>");
							//out.println("<div class=\"well well-sm\"><b>Coowner E-Mail : </b>"+c.getEmail()+"</div>");
							//out.println("<div class=\"well well-sm\"><b>Coowner Mobile : </b>"+c.getMobile()+"</div>");
							//out.println("<div>");
							
							//out.println("</div><br><br>");
						}
						
						out.println("</tbody></table>");
						out.println("<b><a style=\"font-size:20px;\" target=\"_blank\" href=\"http://localhost:8182/society_latierra/coowner.jsp?q=add&id="+f.getFlatID()+"\">ADD</a></b>");
					}
					else
					{
						out.println("<br><h3 style=\"color:red;\">No-Coowner Info</h3>");
						out.println("<b><a style=\"font-size:20px;\" target=\"_blank\" href=\"http://localhost:8182/society_latierra/coowner.jsp?q=add&id="+f.getFlatID()+"\">ADD</a></b>");
					}
				}
				else
				{
					out.println("<div class=\"well well-sm\"><b>INVALID FLAT NUMBER</b></div>");
				}
						
			}
			else
			{
				out.println("<div class=\"well well-sm\"><b>INVALID FLAT NUMBER</b></div>");
			}
			//--------START------------Flat Details
			
			//--------START------------Tenants Details
		
			
			out.println("</div>");
		}
	}
%>

</body>
</html>