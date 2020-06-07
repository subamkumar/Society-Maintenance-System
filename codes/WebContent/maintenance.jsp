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
<%
	dataManager d = new dataManager();

	//Create Maintenance
	String create_month = request.getParameter("cmonths");
	String create_year = request.getParameter("cyear");
	
	if(create_month!=null && create_year!=null)
	{
		Integer res = d.createMaintenance(Integer.parseInt(create_month), Integer.parseInt(create_year));
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Maintenance Created Successfully For "+create_month+"/"+create_year+"</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Maintenance Not Created Successfully</div>");
		}
		
	}


	//Maintenance Charge
	String maint_charge = request.getParameter("mcharge");
	String maint_charge_month = request.getParameter("mmonths");
	String maint_charge_year = request.getParameter("myear");
	
	if(maint_charge!=null && maint_charge_month!=null && maint_charge_year!=null)
	{
		Integer res = d.AddMaintenanceCharge(maint_charge, maint_charge_month, maint_charge_year);
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Maintenance Charge Added Successfully</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Maintenance Charge Not Added Successfully</div>");
		}
		else if(res == -1)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Enter the data properly</div>");
		}
	}
	
	//Sinking Charge
	String sinking_charge = request.getParameter("scharge");
	String sinking_percentage = request.getParameter("spercentage");
	String sinking_charge_month = request.getParameter("smonths");
	String sinking_charge_year = request.getParameter("syear");
	
	if(sinking_charge!=null && sinking_percentage!=null && sinking_charge_month!=null && sinking_charge_year!=null)
	{
		Integer res = d.AddSinkingCharge(sinking_charge, sinking_percentage, sinking_charge_month, sinking_charge_year);
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Sinking Charge Added Successfully</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Sinking Charge Not Added Successfully</div>");
		}
		else if(res == -1)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Enter the data properly</div>");
		}
	}
	
	//Occupancy Charge
	String occupancy_percentage = request.getParameter("opercentage");
	String occupancy_charge_month = request.getParameter("omonths");
	String occupancy_charge_year = request.getParameter("oyear");
	
	if(occupancy_percentage!=null && occupancy_charge_month!=null && occupancy_charge_year!=null)
	{
		Integer res = d.AddOccupancyCharge(occupancy_percentage, occupancy_charge_month, occupancy_charge_year);
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Occupancy Charge Added Successfully</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Occupancy Charge Not Added Successfully</div>");
		}
		else if(res == -1)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Enter the data properly</div>");
		}
	}
	
	
	//Interest Percentage
	String interest_percentage = request.getParameter("ipercentage");
	String interest_charge_month = request.getParameter("imonths");
	String interest_charge_year = request.getParameter("iyear");
	
	if(interest_percentage!=null && interest_charge_month!=null && interest_charge_year!=null)
	{
		Integer res = d.AddInterestPercentage(interest_percentage, interest_charge_month, interest_charge_year);
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Interest Percentage Added Successfully</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Interest Percentage Not Added Successfully</div>");
		}
		else if(res == -1)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Enter the data properly</div>");
		}
	}
	
	//Advance Discount Percentage
	String advance_percentage = request.getParameter("apercentage");
	String advance_charge_month = request.getParameter("amonths");
	String advance_charge_year = request.getParameter("ayear");
	
	if(advance_percentage!=null && advance_charge_month!=null && advance_charge_year!=null)
	{
		Integer res = d.AddAdvanceDiscount(advance_percentage, advance_charge_month, advance_charge_year);
		
		if(res == 1)
		{
			out.println("<div class=\"alert alert-success\">"+
					  "<strong>Success! </strong> Advance Discount Percentage Added Successfully</div>");
		}
		else if(res == 0)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Advance Discount Percentage Not Added Successfully</div>");
		}
		else if(res == -1)
		{
			out.println("<div class=\"alert alert-danger\">"+
					  "<strong>Error! </strong> Enter the data properly</div>");
		}
	}
	
	
	/*
	out.println("Maintenance Charge: "+maint_charge);
	out.println("Maintenance Charge Month: "+maint_charge_month);
	out.println("Maintenance Charge Year: "+maint_charge_year);
	
	out.println("Sinking Charge: "+sinking_charge);
	out.println("Sinking Percentage: "+sinking_percentage);
	out.println("Sinking Charge Month: "+sinking_charge_month);
	out.println("Sinking Charge Year: "+sinking_charge_year);
	
	out.println("Occupancy Percentage: "+occupancy_percentage);
	out.println("Occupancy Charge Month: "+occupancy_charge_month);
	out.println("Occupancy Charge Year: "+occupancy_charge_year);
	
	out.println("Interest Percentage: "+interest_percentage);
	out.println("Interest Charge Month: "+interest_charge_month);
	out.println("Interest Charge Year: "+interest_charge_year);
	
	out.println("Advance Percentage: "+advance_percentage);
	out.println("Advance Charge Month: "+advance_charge_month);
	out.println("Advance Charge Year: "+advance_charge_year);
	*/
%>




<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">CREATE MAINTENANCE</label>
<div class="container" style="margin-top:0px;">
	<label for="usr">Month: </label>
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
	<select name="cmonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="cyear">
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
  	<button style="margin-left:500px;" type="submit" class="btn btn-success">Create Maintenance</button>
  	</form>
</div>

<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">MAINTENANCE CHARGE</label>
<div class="container">
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
		<label for="usr">Charge: </label>
		<input name="mcharge" type="text" class="form-control" id="mcharge">
		<br>
		<label for="usr">With Effect From: </label>
		<br>
		<label for="usr">Month: </label>
		<select name="mmonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="myear">
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
  		<button style="margin-left:500px;" type="submit" class="btn btn-success">Update Maintenance Charge</button>
  	</form>
  	<br>
  	<button style="margin-left:475px;" type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">Show Previous Maintenance Charge</button>
  	<div id="demo" class="collapse">
	<table class="table">
    <thead>
    <tr><th>Maintenance Charge</th><th>Month</th><th>Year</th></tr>
    </thead>
    <tbody>
    	<%
    		HashMap<Integer,String> month = new HashMap<Integer,String>();
			month.put(1, "January");
			month.put(2, "February");
			month.put(3, "March");
			month.put(4, "April");
			month.put(5, "May");
			month.put(6, "June");
			month.put(7, "July");
			month.put(8, "August");
			month.put(9, "September");
			month.put(10, "October");
			month.put(11, "November");
			month.put(12, "December");
    		
			
    		maintenance_charge_model m[] = d.getMaintenanceCharge();
    		if(m!=null)
    		{
    			for(maintenance_charge_model m1:m)
        		{
        			out.println("<tr><td>"+m1.getCharge()+"</td><td>"+month.get(m1.getMonth())+"</td><td>"+m1.getYear()+"</td></tr>");
        		}	
    		}
    		else
    			out.println("<tr><td><b>No Records Found</b></td></tr>");
    		
    	%>
   	</tbody>
    </table>
	</div>
</div>


<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">SINKING CHARGE</label>
<div class="container">
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
		<label for="usr">Charge: </label>
		<input name="scharge" type="text" class="form-control" id="scharge">
		<label for="usr">Percentage: </label>
		<input name="spercentage" type="text" class="form-control" id="mcharge">
		<br>
		<label for="usr">With Effect From: </label>
		<br>
		<label for="usr">Month: </label>
		<select name="smonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="syear">
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
  		<button style="margin-left:500px;" type="submit" class="btn btn-success">Update Sinking Charge</button>
  	</form>
  	<br>
  	<button style="margin-left:475px;" type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo1">Show Previous Sinking Charge</button>
  	<div id="demo1" class="collapse">
  	<table class="table">
    <thead>
    <tr><th>Sinking Charge</th><th>Sinking Percentage</th><th>Month</th><th>Year</th></tr>
    </thead>
    <tbody>
    
    <%
    	sinking_charge_model sm[] = d.getSinkingCharge();
    	
    	if(sm!=null)
    	{
    		for(sinking_charge_model m1:sm)
    		{
    			out.println("<tr><td>"+m1.getCharge()+"</td><td>"+m1.getPercentage()+"</td><td>"+month.get(m1.getMonth_fixed())+"</td><td>"+m1.getYear_fixed()+"</td></tr>");
    		}
    	}
    	else
    		out.println("<tr><td><b>No Records Found</b></td></tr>");
    %>
    
    </tbody>
    </table>
  	</div>
</div>


<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">OCCUPANCY CHARGE</label>
<div class="container">
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
		<label for="usr">Percentage: </label>
		<input name="opercentage" type="text" class="form-control" id="mcharge">
		<br>
		<label for="usr">With Effect From: </label>
		<br>
		<label for="usr">Month: </label>
		<select name="omonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="oyear">
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
  		<button style="margin-left:500px;" type="submit" class="btn btn-success">Update Occupancy Charge</button>
  	</form>
  	<br>
  	<button style="margin-left:475px;" type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo2">Show Previous Occupancy Charge</button>
  	<div id="demo2" class="collapse">
  	<table class="table">
    <thead>
    <tr><th>Occupation Percentage</th><th>Month</th><th>Year</th></tr>
    </thead>
    <tbody>
    
    <%
    	occupancy_charge_model om[] = d.getOccupancyCharge();
    	
    	if(om!=null)
    	{
    		for(occupancy_charge_model m1:om)
    		{
    			out.println("<tr><td>"+m1.getPercentage()+"</td><td>"+month.get(m1.getMonth())+"</td><td>"+m1.getYear()+"</td></tr>");
    		}
    	}
    	else
    		out.println("<tr><td><b>No Records Found</b></td></tr>");
    %>
    
    </tbody>
    </table>
    </div>
</div>



<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">INTEREST PERCENTAGE</label>
<div class="container">
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
		<label for="usr">Percentage: </label>
		<input name="ipercentage" type="text" class="form-control" id="mcharge">
		<br>
		<label for="usr">With Effect From: </label>
		<br>
		<label for="usr">Month: </label>
		<select name="imonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="iyear">
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
  		<button style="margin-left:500px;" type="submit" class="btn btn-success">Update Interest Percentage</button>
  	</form>
  	<br>
  	<button style="margin-left:475px;" type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo3">Show Previous Interest Percentage</button>
  	<div id="demo3" class="collapse">
  	<table class="table">
    <thead>
    <tr><th>Interest Percentage</th><th>Month</th><th>Year</th></tr>
    </thead>
    <tbody>
    
    <%
    	interest_percentage_model im[] = d.getInterestPercentage();
    
    	if(im!=null)
    	{
    		for(interest_percentage_model m1:im)
    		{
    			out.println("<tr><td>"+m1.getPercentage()+"</td><td>"+month.get(m1.getMonth_fixed())+"</td><td>"+m1.getYear_fixed()+"</td></tr>");
    		}
    	}
    	else
    		out.println("<tr><td><b>No Records Found</b></td></tr>");
    %>
    
    </tbody>
    </table>
    </div>
</div>

<label style="margin-top:60px;margin-left:200px;text-decoration:underline;color:#E34C14;" for="usr">ADVANCE DISCOUNT PERCENTAGE</label>
<div class="container">
	<form action="http://localhost:8182/society_latierra/maintenance.jsp" method="post">
		<label for="usr">Percentage: </label>
		<input name="apercentage" type="text" class="form-control" id="mcharge">
		<br>
		<label for="usr">With Effect From: </label>
		<br>
		<label for="usr">Month: </label>
		<select name="amonths" class="form-control" id="sel1">
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
	<label for="usr">Year: </label>
  	<select class="form-control" name="ayear">
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
  		<button style="margin-left:500px;" type="submit" class="btn btn-success">Update Discount Percentage</button>
  	</form>
  	<br>
  	<button style="margin-left:475px;" type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo4">Show Previous Discount Percentage</button>
  	<div id="demo4" class="collapse">
  	<table class="table">
    <thead>
    <tr><th>Discount Percentage</th><th>Month</th><th>Year</th></tr>
    </thead>
    <tbody>
    
    <%
    	advance_discount_model am[] = d.getAdvanceDiscount();
    
    	if(am!=null)
    	{
    		for(advance_discount_model m1:am)
    		{
    			out.println("<tr><td>"+m1.getPercentage()+"</td><td>"+month.get(m1.getMonth_fixed())+"</td><td>"+m1.getYear_fixed()+"</td></tr>");
    		}
    	}
    	else
    		out.println("<tr><td><b>No Records Found</b></td></tr>");
    %>
    </tbody>
    </table>

    </div>
</div>
<br><br><br>
</body>
</html>