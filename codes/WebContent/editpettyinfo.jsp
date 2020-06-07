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
	<script>
function Payfunc()
{
	var val = document.getElementById("payopt").value;
	console.log("hello "+val);
	
	if(val == 1)
	{
		console.log(val)
		document.getElementById("container1").style.display="block";
		document.getElementById("container2").style.display="none";
		document.getElementById("container3").style.display="none";
		document.getElementById("container4").style.display="none";
	}
	else if(val == 2)
	{
		console.log(val)
		document.getElementById("container1").style.display="none";
		document.getElementById("container2").style.display="block";
		document.getElementById("container3").style.display="none";
		document.getElementById("container4").style.display="none";
	}
	else if(val == 3)
	{
		console.log(val)
		document.getElementById("container1").style.display="none";
		document.getElementById("container2").style.display="none";
		document.getElementById("container3").style.display="block";
		document.getElementById("container4").style.display="none";
	}
	else if(val == 4)
	{
		console.log(val)
		document.getElementById("container1").style.display="none";
		document.getElementById("container2").style.display="none";
		document.getElementById("container3").style.display="none";
		document.getElementById("container4").style.display="block";
	}
}
</script>
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
		String id = request.getParameter("id");
		
		if(id!=null)
		{
			dataManager d = new dataManager();
			
			petty_info pi = d.getPettyInfoByID(id);
			
			if(pi!=null)
			{
				String pettyamount = request.getParameter("pettyamount");
				String spendheader = request.getParameter("spendheader");
				String pettydesc = request.getParameter("pettydesc");
				String pettydate = request.getParameter("pettydate");
				
				if(pettyamount!=null && spendheader!=null && pettydesc!=null && pettydate!=null)
				{
					int res = d.updatePettyBalance(id,String.valueOf(pi.getAmt()),pettyamount,spendheader,pettydesc,pettydate);
					
					if(res == 1)
					{
						out.println("<div class=\"alert alert-success\">"+
								  "<strong>Success!</strong>Petty Info Updated Successfully</div>");
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Petty Info Not Updated Successfully</div>");
					}
				}
				pi = d.getPettyInfoByID(id);
				out.println("<form action=\"http://localhost:8182/society_latierra/editpettyinfo.jsp?id="+id+"\" method=\"post\">");
				out.println("<input type=\"text\" value=\""+pi.getAmt()+"\" placeholder=\"Enter the Amount to be Updated\" class=\"form-control\" name=\"pettyamount\"><br>");
				out.println("<input type=\"date\" value=\""+pi.getDate()+"\" class=\"form-control\" name=\"pettydate\"><br>");
				out.println("<select class=\"form-control\" name=\"spendheader\">");
				
					LinkedList<String> ll = database.getDatabase().getPettyHeaders();
				
					if(ll !=null)
					{
						while(!ll.isEmpty())
						{
							String s = ll.removeFirst();
							if(pi.getHeader().equalsIgnoreCase(s))
								out.println("<option value=\""+s+"\" selected=\"selected\">"+s+"</option>");
							else	
								out.println("<option value=\""+s+"\">"+s+"</option>");	
						}
						
					}
				out.println("</select><br>");
				out.println("<textarea class=\"form-control\" rows=\"5\" placeholder=\"Spending description...\" name=\"pettydesc\" id=\"comment\">"+pi.getDesc()+"</textarea><br>");
				out.println("<button style=\"margin-left:500px;\" type=\"submit\" class=\"btn btn-success\">Update Info</button>");
				out.println("</form>");	
			}
			
			
		}
		
	%>	
	</div>	
</body>
</html>