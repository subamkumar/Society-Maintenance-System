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

		String query = request.getParameter("q");
		
		
		dataManager d = new dataManager();
		
		if(query.equals("edit"))
		{
			String id = request.getParameter("id");
			String cid = request.getParameter("cid");
			
			String cname = request.getParameter("cname");
			String cmail = request.getParameter("cmail");
			String cmobile = request.getParameter("cmobile");
			
			if(cname!=null && cmail!=null && cmobile!=null)
			{
				
				int res = 0;
				
				if(!cname.equals(""))
					res = d.updateCoowner(cid, cname,cmail,cmobile);
				
				if(res == 1)
				{
					out.println("<div class=\"alert alert-success\">"+
							  "<strong>Success!</strong>Coowner Updated Successfully</div>");
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Coowner Not Updated Successfully</div>");
				}
			}
			
			FlatModel f = d.getFlatByID(id);
			
			if(f!=null)
			{
				specificflat s = d.getSpecificFlatDetails(f.getFlatNo());
				LinkedList<CoownerModel> cm = s.getCoownerInfo();
				
				if(cm!=null)
				{
					//System.out.println("hello");
					CoownerModel c = null;
					
					while(!cm.isEmpty())
					{
						CoownerModel p  = cm.removeFirst();
						
						if(p.getId().equals(Integer.parseInt(cid)))
						{
							c = p;
							break;
						}
							
					}
					
					if(c!=null)
					{
						out.println("<div class=\"container\">");
						out.println("<form action=\"http://localhost:8182/society_latierra/coowner.jsp?q=edit&id="+id+"&cid="+cid+"\" method=\"post\">");
						out.println("<br><input type=\"text\" class=\"form-control\" value=\""+c.getCoowner_name()+"\" placeholder=\"Enter Coowner name\" name=\"cname\"><br>");
						out.println("<input type=\"text\" class=\"form-control\" value=\""+c.getEmail()+"\" placeholder=\"Enter Email Address\" name=\"cmail\"><br>");
						out.println("<input type=\"text\" class=\"form-control\" value=\""+c.getMobile()+"\" placeholder=\"Enter Mobile number\" name=\"cmobile\"><br>");
						out.println("<button style=\"margin-left:500px;\" type=\"submit\" class=\"btn btn-success\">Update Coowner</button>");
						out.println("</form>");
						out.println("</div>");	
					}	
				}
					
			}
			
			
			
		}
		else if(query.equals("add"))
		{
			String id = request.getParameter("id");
			
			String cname = request.getParameter("cname");
			String cmail = request.getParameter("cmail");
			String cmobile = request.getParameter("cmobile");
			
			
			if(cname!=null && cmail!=null && cmobile!=null)
			{
				
				int res = 0;
				
				if(!cname.equals(""))
					res = d.insertCoowner(id, cname,cmail,cmobile);
		
				if(res == 1)
				{
					out.println("<div class=\"alert alert-success\">"+
							  "<strong>Success!</strong>Coowner Added Successfully</div>");
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Coowner Not Added Successfully</div>");
				}
			}
			
			out.println("<div class=\"container\">");
			
			out.println("<form action=\"http://localhost:8182/society_latierra/coowner.jsp?q=add&id="+id+"\" method=\"post\">");
			
			out.println("<br><input type=\"text\" class=\"form-control\" placeholder=\"Enter Coowner name\" name=\"cname\"><br>");
			out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Enter Email Address\" name=\"cmail\"><br>");
			out.println("<input type=\"text\" class=\"form-control\" placeholder=\"Enter Mobile number\" name=\"cmobile\"><br>");
			out.println("<button style=\"margin-left:500px;\" type=\"submit\" class=\"btn btn-success\">Add Coowner</button>");
			//out.println("<input type=\"submit\">");
			out.println("</form>");
			
			out.println("</div>");
		}
	%>
</body>
</html>