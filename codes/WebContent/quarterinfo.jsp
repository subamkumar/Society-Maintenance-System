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


<div class="container" style="margin-top:60px;">
<form action="http://localhost:8181/La-Tierra/maint.jsp?nav=show" method="post">		
 	<input type="text" placeholder="Enter the flat no like A-101" class="form-control" name="flatnumber" id="flatnumber">
 	<br>
 	<div class="row">
 		<div class="col-md-1">
 			<label class="radio-inline"><input type="radio" onClick="enableM()" value="m" name="optradio">By Month</label>
 		</div>
 		<div class="col-md-2">
 			<select name="maintmonths" id="maintmonths" class="form-control" disabled>
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
 		</div>
 		<div class="col-md-1">
 			<label class="radio-inline"><input onClick="enableQ()" type="radio" value="q" name="optradio">By Quarter</label>
 		</div>
 		<div class="col-md-2">
 			<select name="maintquarter" class="form-control" id="maintquarter" disabled>
    			<option value=1>Quarter 1</option>
    			<option value=2>Quarter 2</option>
    			<option value=3>Quarter 3</option>
    			<option value=4>Quarter 4</option>
  			</select>
 		</div>
 		
 		<div class="col-md-2" >
 			<select class="form-control" name="maintyears">
    			<option value=2018>2018</option>
    			<option value=2019>2019</option>
    			<option value=2020>2020</option>
    			<option value=2021>2021</option>
    			<option value=2022>2022</option>
    			<option value=2023>2023</option>
  			</select>
 		</div>
		
		<div class="col-md-2" >
 			<select class="form-control" name="maintblocks">
    			<option value="all">All Flats</option>
    			<option value="a">A - Block</option>
    			<option value="b">B - Block</option>
    			<option value="c">C - Block</option>
  			</select>
 		</div>
 		
 		<div class="col-md-2">
 			<select class="form-control" name="maintowners">
    			<option value="all">All Owners</option>
    			<option value="paid">Paid Owners</option>
    			<option value="unpaid">Unpaid Owners</option>
  			</select>
 		</div>
 	</div>
 	<br>
 	<button style="margin-left:500px;" type="submit" class="btn btn-success">Show Maintenance Records</button>
 </form>		
</div>

<%
	String flatno = request.getParameter("flatnumber");
	String radioopt = request.getParameter("optradio");
	String radiomonth = request.getParameter("maintmonths");
	String radioquarter = request.getParameter("maintquarter");
	String maintyear = request.getParameter("maintyears");
	String maintblock = request.getParameter("maintblocks");
	String maintowner = request.getParameter("maintowners");
	
	HashMap<Integer,String> months1 = new HashMap<Integer,String>();
	months1.put(1, "January");
	months1.put(2, "February");
	months1.put(3, "March");
	months1.put(4, "April");
	months1.put(5, "May");
	months1.put(6, "June");
	months1.put(7, "July");
	months1.put(8, "August");
	months1.put(9, "September");
	months1.put(10, "October");
	months1.put(11, "November");
	months1.put(12, "December");
	
	if(flatno!=null || radioopt!=null || radiomonth!=null || radioquarter!=null || maintyear!=null || maintblock!=null || maintowner!=null)
	{
		dataManager d = new dataManager();
		if(flatno!=null)
		{
			//Flatno,month,year
			specificflat sf = d.getSpecificFlatDetails(flatno);
			FlatModel f = null;
			if(sf!=null)
				f = sf.getFlatInfo();
			
			
			
			if(f!=null)
			{
				out.println("<div class=\"container\">");
				out.println("<div class=\"well well-sm\">");
				out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+f.getFlatNo()+"\">");
				out.println("<b>Flat Number: </b>"+f.getFlatNo().toUpperCase()+"</a>");
				out.println("</div>");
				out.println("</div>");	
			}
			
			
			
			
			
			
			
			
			
			
			
			
			//Flatno,quarter,year
			//Flatno,year
		}
		else
		{
			
		}
	}
%>


</body>
</html>