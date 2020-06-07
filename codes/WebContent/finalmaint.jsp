<%@ page import="society.dataManager" %>
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
	<div class="container" style="width:600px;margin-top:60px;">
	
	<%
		String quarter = request.getParameter("quarter");
		String quarteryear = request.getParameter("quarteryear");
		String month = request.getParameter("month");
		String monthyear = request.getParameter("monthyear");
		
		dataManager d = new dataManager();
		Integer result = 0;
		if(quarter!=null || month!=null)
		{
			
			result = d.finalizeMonthAndQuarter(quarter, quarteryear, month, monthyear);	
			//out.println(result);
		}
		
		
		if(quarter!=null && quarteryear!=null)
		{
			if(result == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success!</strong> Quarter "+quarter+" - "+quarteryear+"Setted Successfully</div>");
			}
			else if(result == -1)
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error!</strong>  Quarter "+quarter+" not setted Try Again!!!</div>");
			}
			else if(result == 0)
			{
				if(quarter.equalsIgnoreCase("1"))
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>  Quarter "+quarter+" not setted Because <strong>Previous Year December-"+(Integer.parseInt(quarteryear)-1)+"</strong> month is not Finalized</div>");
				}
				else if(quarter.equalsIgnoreCase("2"))
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>  Quarter "+quarter+" not setted Because <strong>March-"+quarteryear+"</strong> month is not Finalized</div>");
				}
				else if(quarter.equalsIgnoreCase("3"))
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>  Quarter "+quarter+" not setted Because <strong>June-"+quarteryear+"</strong> month is not Finalized</div>");
				}
				else if(quarter.equalsIgnoreCase("4"))
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>  Quarter "+quarter+" not setted Because <strong>September-"+quarteryear+"</strong> month is not Finalized</div>");
				}
				
			}	
		}
		else if(month!=null && monthyear!=null)
		{
			if(result == 1)
			{
				out.println("<div class=\"alert alert-success\">"+
						  "<strong>Success! "+month+"-"+monthyear+"</strong> Month Finalized Successfully</div>");
			}
			else if(result == -1)
			{
				out.println("<div class=\"alert alert-danger\">"+
						  "<strong>Error! "+month+"-"+monthyear+"</strong> Month <strong>Not</strong> Finalized Try Again.</div>");
			}
			else if(result == 0)
			{
				if(month.equalsIgnoreCase("1"))
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error! previous December "+(Integer.parseInt(monthyear)-1)+"</strong> Month <strong>Not</strong> Finalized Try Again.</div>");	
				}
				else
				{
					String prevm = "";
					if(month.equalsIgnoreCase("2"))
						prevm = "January";
					else if(month.equalsIgnoreCase("3"))
						prevm = "February";
					else if(month.equalsIgnoreCase("4"))
						prevm = "March";
					else if(month.equalsIgnoreCase("5"))
						prevm = "April";
					else if(month.equalsIgnoreCase("6"))
						prevm = "May";
					else if(month.equalsIgnoreCase("7"))
						prevm = "June";
					else if(month.equalsIgnoreCase("8"))
						prevm = "July";
					else if(month.equalsIgnoreCase("9"))
						prevm = "August";
					else if(month.equalsIgnoreCase("10"))
						prevm = "September";
					else if(month.equalsIgnoreCase("11"))
						prevm = "October";
					else if(month.equalsIgnoreCase("12"))
						prevm = "November";
					
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error! previous "+prevm+"-"+monthyear+"</strong> Month <strong>Not</strong> Finalized Try Again.</div>");
				}
				
			}
		}
	%>
	
		<h3>Finalize Month</h3>
			<form action="http://localhost:8182/society_latierra/finalmaint.jsp" method="post">
				<select class="form-control" name="month">
    				<option value=1>January</option>
    				<option value=2>February</option>
    				<option value=3>March</option>
    				<option value=4>April</option>
    				<option value=5>May</option>
    				<option value=6>June</option>
    				<option value=7>July</option>
    				<option value=8>August</option>
    				<option value=9>September</option>
    				<option value=10>October</option>
    				<option value=11>November</option>
    				<option value=12>December</option>
    			</select>
    			<br>
    			<select class="form-control" name="monthyear">
    				<option value=2018>2018</option>
    				<option value=2019>2019</option>
    				<option value=2020>2020</option>
    				<option value=2021>2021</option>
    				<option value=2022>2022</option>
    				<option value=2023>2023</option>
    				<option value=2024>2024</option>
    				<option value=2025>2025</option>
    				<option value=2026>2026</option>
    				<option value=2027>2027</option>
    			</select>
    			<br>
    			<button style="margin-left:230px;" type="submit" class="btn btn-success">Finalize Month</button>
			</form>
		</div>
		<div class="container" style="width:600px;">
			<h3>Set Quarter</h3>
			<form action="http://localhost:8182/society_latierra/finalmaint.jsp" method="post">
				<select class="form-control" name="quarter">
    				<option value=1>Quarter 1</option>
    				<option value=2>Quarter 2</option>
    				<option value=3>Quarter 3</option>
    				<option value=4>Quarter 4</option>
    			</select>
    			<br>
    			<select class="form-control" name="quarteryear">
    				<option value=2018>2018</option>
    				<option value=2019>2019</option>
    				<option value=2020>2020</option>
    				<option value=2021>2021</option>
    				<option value=2022>2022</option>
    				<option value=2023>2023</option>
    				<option value=2024>2024</option>
    				<option value=2025>2025</option>
    				<option value=2026>2026</option>
    				<option value=2027>2027</option>
    			</select>
    			<br>
    			<button style="margin-left:230px;" type="submit" class="btn btn-success">Set Quarter</button>
			</form>
			<br><br>
			<div class="well well-sm"><b>Current Running Month and Year: <%=d.getFinalMonth()%></b></div>
			<div class="well well-sm"><b>Current Running Quarter and Year: <%=d.getFinalQuarter()%></b></div>
		</div>
</body>
</html>