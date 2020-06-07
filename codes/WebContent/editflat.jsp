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

	String id = request.getParameter("id");
	
	if(id!=null)
	{
		dataManager d = new dataManager();
		
		String oname = request.getParameter("oname");
		String oarea = request.getParameter("oarea");
		String omail = request.getParameter("omail");
		String omobile = request.getParameter("omobile");
		String ocat = request.getParameter("ocat");
		
		if(oname!=null && oarea!=null && omail!=null && omobile!=null && ocat!=null)
		{
			int res = d.updateFlat(id, oname, oarea, omail, omobile, ocat);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong>Flat Info Updated Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error!</strong>Flat Info Not Updated Successfully</div>");
			}
		}
		
		FlatModel f = d.getFlatByID(id);
		
		if(f!=null)
		{
			out.println("<div class=\"container\">");
			out.println("<form action=\"http://localhost:8182/society_latierra/editflat.jsp?id="+f.getFlatID()+"\" method=\"post\">");
			out.println("<b>Name: </b><input class=\"form-control\" type=\"text\" placeholder=\"Owner Name\" value=\""+f.getOwnerName()+"\" name=\"oname\"><br>");
			out.println("<b>Area(Sqft): </b><input class=\"form-control\" type=\"text\" placeholder=\"Flat Area in Sqft\" value=\""+f.getArea()+"\" name=\"oarea\"><br>");
			out.println("<b>Email: </b><input class=\"form-control\" type=\"text\" placeholder=\"Owner Email\" value=\""+f.getEmail()+"\" name=\"omail\"><br>");
			out.println("<b>Mobile: </b><input class=\"form-control\" type=\"text\" placeholder=\"Owner Mobile\" value=\""+f.getMobile()+"\" name=\"omobile\"><br>");
			out.println("<b>Category: </b><select class=\"form-control\" name=\"ocat\">");
			if(f.getOwnerCategory().equalsIgnoreCase("owner"))
			{
				out.println("<option value=\"Owner\" selected=\"selected\">OWNER</option>");
				out.println("<option value=\"Tenant\">TENANT</option>");
			}
			else
			{
				out.println("<option value=\"Tenant\" selected=\"selected\">TENANT</option>");
				out.println("<option value=\"Owner\">OWNER</option>");
			}
			out.println("</select><br>");
			out.println("<button style=\"margin-left:500px;\" type=\"submit\" class=\"btn btn-success\">Update Info</button>");
			out.println("</form>");
			out.println("</div>");
		}
	}

%>


</body>
</html>