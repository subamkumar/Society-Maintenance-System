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
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<p style="margin-top:60px;"></p>

<div class="container" style="margin-top:60px;">
<form action="http://localhost:8182/society_latierra/paymentinfo.jsp" method="post">		
 
 	<div class="row">
 		<div class="col-md-1">
 			<label class="radio-inline">Payment Type</label>
 		</div>
 		<div class="col-md-2">
 			<select class="form-control" name="paymenttype">
    			<option value="all">All Type</option>
    			<option value="cheque">CHEQUE</option>
    			<option value="neft">NEFT</option>
    			<option value="imps">IMPS</option>
  			</select>
 		</div>
 		<div class="col-md-1">
 			<label class="radio-inline"><input type="radio" onClick="enableM()" value="n" name="optradio">By Number</label>
 		</div>
 		<div class="col-md-4">
 			<input type="text" placeholder="Enter the flat no like A-101" class="form-control" name="paymentnumber" id="paymentnumber" disabled>
 		</div>
 		<div class="col-md-1">
 			<label class="radio-inline"><input onClick="enableQ()" type="radio" value="d" name="optradio">By Date</label>
 		</div>
 		<div class="col-md-2">
 			<input type="date" class="form-control" name="paymentdate" id="paymentdate" disabled>
 		</div>
 	</div> 		

 	<br>
 	<button style="margin-left:500px;" type="submit" class="btn btn-success">Show Payment Info</button>
 </form>		
</div>


<%
	String type = request.getParameter("paymenttype");
	String number = request.getParameter("paymentnumber");
	String date = request.getParameter("paymentdate");
	
	//out.println("Type: "+type+", Number: "+number+", Date: "+date);
	if(type!=null)
	{
		dataManager d = new dataManager();
		if(number!=null)
		{
			payment p[] = d.paymentInfo(type, number, "");
			
			out.println("<table class=\"table table-striped\">");
			out.println("<thead><tr><th>Sr.No</th><th>Type</th><th>Info</th><th>Paid Date</th><th>Payment Info</th><th>Edit</th></tr></thead><tbody>");
			if(p!=null)
			{
				int count = 0;
				for(payment p1:p)
				{
					count++;
					out.println("<tr>");
					out.println("<td>"+count+"</td>");
					out.println("<td>"+p1.getType()+"</td>");
					out.println("<td>"+p1.getType_detail()+"</td>");
					out.println("<td>"+p1.getPaid_date()+"</td>");
					out.println("<td>"+p1.getPayment_info()+"</td>");
					out.println("<td><a target=\"_blank\" href=\"paymentinfoedit.jsp?id="+p1.getId()+"\">Edit</a></td>");
					out.println("</tr>");
				}
			}
			else
			{
				out.println("<tr><td><b>No Records Found</b></td></tr>");
			}
			out.println("</tbody><table>");
			out.println("</div>");
			
		}
		else if(date!=null)
		{
			payment p[] = d.paymentInfo(type, "", date);
			out.println("<table class=\"table table-striped\">");
			out.println("<thead><tr><th>Sr.No</th><th>Type</th><th>Info</th><th>Paid Date</th><th>Payment Info</th><th>Edit</th></tr></thead><tbody>");
			if(p!=null)
			{
				int count = 0;
				for(payment p1:p)
				{
					count++;
					out.println("<tr>");
					out.println("<td>"+count+"</td>");
					out.println("<td>"+p1.getType()+"</td>");
					out.println("<td>"+p1.getType_detail()+"</td>");
					out.println("<td>"+p1.getPaid_date()+"</td>");
					out.println("<td>"+p1.getPayment_info()+"</td>");
					out.println("<td><a target=\"_blank\" href=\"paymentinfoedit.jsp?id="+p1.getId()+"\">Edit</a></td>");
					out.println("</tr>");
				}
			}
			else
			{
				out.println("<tr><td><b>No Records Found</b></td></tr>");
			}
			out.println("</tbody><table>");
			out.println("</div>");
		}
	}
	
%>




</body>
</html>