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
<script>

function enableM()
{
	document.getElementById("paymentnumber").disabled = false;
	document.getElementById("paymentdate").disabled = true;
}

function enableQ()
{
	document.getElementById("paymentdate").disabled = false;
	document.getElementById("paymentnumber").disabled = true;
}


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
      <li><a href="http://localhost:8182/society_latierra/maintenance.jsp">Create Maintenance</a></li>
      <li><a href="http://localhost:8182/society_latierra/showmaint.jsp">Show Maintenance</a></li>
      <li><a href="http://localhost:8182/society_latierra/balancepay.jsp">Balance</a></li>
      <li><a href="http://localhost:8182/society_latierra/advancepay.jsp">Advance</a></li>
      <li><a href="http://localhost:8182/society_latierra/finalmaint.jsp">Finalize</a></li>
      <li><a href="http://localhost:8182/society_latierra/paymentinfo.jsp">Payment</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8181/La-Tierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<p style="margin-top:60px;"></p>
<%
	dataManager d = new dataManager();
	String id = request.getParameter("id");
	payment p = d.getPaymentByID(id);
	
	
	
	
	if(p!=null)
	{

		if(p.getType().equalsIgnoreCase("cheque"))
		{
			
			String chqno = request.getParameter("chqnumber");
			String chqdate = request.getParameter("chqdate");
			String chqbounce = request.getParameter("chqbounce");
			String chqpenalty = request.getParameter("chqpenalty");
			String paydesc = request.getParameter("paydesc");
			
			String s = p.getType_detail();
			String data[] = s.split(",");
			
			//out.println("no: "+chqno+" date: "+chqdate+" bounce: "+chqbounce);
			
			if(chqno!=null && chqdate!=null)
			{
				String us = "";
				if(chqbounce!=null)
					us = chqno+","+chqdate+","+1;
				else
				{
					if(chqpenalty!=null && paydesc!=null)
					{
						us = chqno+","+chqdate+","+0+","+chqpenalty+","+paydesc;
					}
					else
					{
						us = chqno+","+chqdate;
					}
				}
					
				
				int res = d.updatePayment(id,"cheque",s,us);
				
				if(res == 1)
				{
					out.println("<div class=\"alert alert-success\">"+
							  "<strong>Success!</strong> Updated Successfully</div>");
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Success!</strong> Updation Failed</div>");
				}
			}
			
			
			
			
			String number = "";
			for(int i=0;i<data[0].length();i++)
			{
				if(data[0].charAt(i) == ':')
				{
					number = data[0].substring(i+1);
					break;
				}
			}
			
			String date = "";
			for(int i=0;i<data[1].length();i++)
			{
				if(data[1].charAt(i) == ':')
				{
					date = data[1].substring(i+1);
					break;
				}
			}
			date = date.trim();
			out.println("<div class=\"container\">");
			out.println("<form action=\"http://localhost:8182/society_latierra/paymentinfoedit.jsp?id="+p.getId()+"\" method=\"post\">");
			out.println("<input type=\"text\" value=\""+number+"\" placeholder=\"Enter the Cheque Number\" class=\"form-control\" name=\"chqnumber\" id=\"chqnumber\">");
			out.println("<br>");
			out.println("<input type=\"date\" value=\""+date+"\" class=\"form-control\" name=\"chqdate\" id=\"chqdate\">");
			out.println("<div class=\"checkbox\"><label><input name=\"chqbounce\" type=\"checkbox\">Bounced</label></div>");
			
			if(data.length > 2)
			{
				String bouncedvalue = "";
				for(int i=0;i<data[2].length();i++)
				{
					if(data[2].charAt(i) == ':')
					{
						bouncedvalue = data[2].substring(i+1, i+2);
						break;
					}
				}
				
				if(bouncedvalue.equalsIgnoreCase("1"))
				{
					out.println("<input type=\"text\" placeholder=\"Enter the Bounce Penalty\" class=\"form-control\" name=\"chqpenalty\">");
					out.println("<textarea class=\"form-control\" rows=\"5\" name=\"paydesc\"></textarea>");
				}
			}
			
			
			out.println("<button type=\"submit\" class=\"btn btn-success\">Update Payment Info</button>");
			out.println("</form>");
			out.println("</div>");
						
		}
		else if(p.getType().equalsIgnoreCase("neft"))
		{
			String s = p.getType_detail();
			String data[] = s.split(",");
			
			String neftnumber = request.getParameter("neftnumber");
			
			int res = d.updatePayment(id, "neft", s, neftnumber);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong> Updated Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Success!</strong> Updation Failed</div>");
			}
			
			
			String number = "";
			for(int i=0;i<data[0].length();i++)
			{
				if(data[0].charAt(i) == ':')
				{
					number = data[0].substring(i+1);
					break;
				}
			}
			
			out.println("<div class=\"container\">");
			out.println("<form  action=\"http://localhost:8182/society_latierra/paymentinfoedit.jsp?id="+p.getId()+"\" method=\"post\">");
			out.println("<input type=\"text\" value=\""+number+"\" placeholder=\"Enter the NEFT Number\" class=\"form-control\" name=\"neftnumber\" id=\"neftnumber\">");
			out.println("<br>");
			out.println("<button type=\"submit\" class=\"btn btn-success\">Update Payment Info</button>");
			out.println("</form>");
			out.println("</div>");
		}
		else if(p.getType().equalsIgnoreCase("imps"))
		{
			String s = p.getType_detail();
			String data[] = s.split(",");
			
			String impsnumber = request.getParameter("impsnumber");
			
			int res = d.updatePayment(id, "neft", s, impsnumber);
			
			if(res == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong> Updated Successfully</div>");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Success!</strong> Updation Failed</div>");
			}
			
			String number = "";
			for(int i=0;i<data[0].length();i++)
			{
				if(data[0].charAt(i) == ':')
				{
					number = data[0].substring(i+1);
					break;
				}
			}
			
			out.println("<div class=\"container\">");
			out.println("<form  action=\"http://localhost:8182/society_latierra/paymentinfoedit.jsp?id="+p.getId()+"\" method=\"post\">");
			out.println("<input type=\"text\" value=\""+number+"\" placeholder=\"Enter the IMPS Number\" class=\"form-control\" name=\"impsnumber\" id=\"impsnumber\">");
			out.println("<br>");
			out.println("<button type=\"submit\" class=\"btn btn-success\">Update Payment Info</button>");
			out.println("</form>");
			out.println("</div>");
		}
		
		
			
		
	}
	
%>
</body>
</html>