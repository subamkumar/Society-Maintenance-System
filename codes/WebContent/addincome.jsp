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
      <li><a href="http://localhost:8182/society_latierra/incomeinfo.jsp">Income Info</a></li>
      <li><a href="http://localhost:8182/society_latierra/addincome.jsp">Add Income</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<div class="container" style="margin-top:60px;">
		
		<%
		
		String expdesc = request.getParameter("expdesc");
		String payopt = request.getParameter("payopt");
		String chqno = request.getParameter("chqno");
		String chqdate = request.getParameter("chqdate");
		String chqamt = request.getParameter("chqamt");
		String chqdop = request.getParameter("chqdop");
		String neftno = request.getParameter("neftno");
		String neftdate = request.getParameter("neftdate");
		String neftamt = request.getParameter("neftamt");
		String impsno = request.getParameter("impsno");
		String impsdate = request.getParameter("impsdate");
		String impsamt = request.getParameter("impsamt");
		String cashamt = request.getParameter("cashamt");
		String cashdop = request.getParameter("cashdop");
		String incomeheader = request.getParameter("incomeheader");
		
		dataManager d = new dataManager();
		
		if(expdesc!=null)
		{
			if(payopt.equals("1"))
			{
				if(chqno!=null && chqdate!=null && chqamt!=null && chqdop!=null)
				{
					String s = "Cheque No: "+chqno+", Cheque Date: "+chqdate;
					
					int res = d.addIncome(expdesc,"cheque",s,chqamt,chqdop,incomeheader);
					
					if(res == 1)
					{
						out.println("<div class=\"alert alert-success\">"+
								  "<strong>Success!</strong>Income Added Successfully</div>");
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Income Not Added Successfully</div>");
					}
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Income Transaction Fails</div>");
				}
			}
			else if(payopt.equals("2"))
			{
				if(neftno!=null && neftdate!=null && neftamt!=null)
				{
					String s = "NEFT No: "+neftno+", NEFT Date: "+neftdate;
					
					int res = d.addIncome(expdesc,"neft",s,neftamt,neftdate,incomeheader);
					
					if(res == 1)
					{
						out.println("<div class=\"alert alert-success\">"+
								  "<strong>Success!</strong>Income Added Successfully</div>");
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Income Not Added Successfully</div>");
					}
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Income Transaction Fails</div>");
				}
			}
			else if(payopt.equals("3"))
			{
				if(impsno!=null && impsdate!=null && impsamt!=null)
				{
					String s = "IMPS No: "+impsno+", IMPS Date: "+impsdate;
					
					int res = d.addIncome(expdesc,"imps",s,impsamt,impsdate,incomeheader);
					
					if(res == 1)
					{
						out.println("<div class=\"alert alert-success\">"+
								  "<strong>Success!</strong>Income Added Successfully</div>");
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Income Not Added Successfully</div>");
					}
				}
				else
				{
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Income Transaction Fails</div>");
				}
			}
			else if(payopt.equals("4"))
			{
				System.out.println(cashdop+" "+cashamt);
				if(cashamt!=null && cashdop!=null)
				{
					int res = d.addIncome(expdesc,"cash","",cashamt,cashdop,incomeheader);
					
					if(res == 1)
					{
						out.println("<div class=\"alert alert-success\">"+
								  "<strong>Success!</strong>Income Added Successfully</div>");
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Income Not Added Successfully</div>");
					}
				}
				else
				{
					System.out.println("hello");
					out.println("<div class=\"alert alert-danger\">"+
							  "<strong>Error!</strong>Income Transaction Fails</div>");
				}
			}
		}
	%>
		
		<form action="http://localhost:8182/society_latierra/addincome.jsp" method="post">
		<br>
		<select class="form-control" name="incomeheader">
			<%
				LinkedList<String> ll = database.getDatabase().getPettyHeaders();
			
				if(ll !=null)
				{
					while(!ll.isEmpty())
					{
						String s = ll.removeFirst();
						out.println("<option value=\""+s+"\">"+s+"</option>");	
					}
					
				}
			%>
      	</select>
      	<br>
		<textarea class="form-control" rows="5" placeholder="Income description..." name="expdesc" id="comment"></textarea>
		<br>
			<div class="row">
				<div class="col-md-4">
					<label>Select Payment Type:</label>
				  	<select id="payopt" name="payopt" class="form-control" onchange="Payfunc()">
				    	<option value=1>CHEQUE</option>
				    	<option value=2>NEFT</option>
				    	<option value=3>IMPS</option>
				    	<option value=4>CASH</option>
				  	</select>
				</div>
		
			</div>
				
				
				
		<div class="container" id="container1" style="display:block;"><br>
				
			<div class="row">
				
				<div class="col-md-4">
					<div class="input-group"><label>Cheque Number:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the Cheque No" name="chqno">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>Cheque Date:</label>
						<input type="date" class="form-control" style="height:40px;" name="chqdate">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>Cheque Amount: </label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the Cheque Amount" name="chqamt">
					</div>
				</div>
				
			</div>
				
				
			<div class="row">
				
				<div class="col-md-4">
					<div class=\"input-group\"><label>Date of Payment:</label>
						<input type="date" class="form-control" style="height:40px;" name="chqdop">
					</div>
				</div>
						
			</div>
				
				
				<button style="margin-top:10px;" type="submit" class="btn btn-success">Add Income</button>
				
		</div>
				
				
		<div class="container" id="container2" style="display:none;">
				
			<div class="row">
				
				<div class="col-md-4">
					<div class="input-group"><label>NEFT Number:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the NEFT No" name="neftno">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>NEFT Date:</label>
						<input type="date" class="form-control" style="height:40px;" name="neftdate">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>NEFT Amount:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the NEFT Amount" name="neftamt">
					</div>
				</div>
				
			</div>
				
				<button style="margin-top:10px;" type="submit" class="btn btn-success">Add Income</button>
				
		</div>
				
		<div class="container" id="container3" style="display:none;">
				
			<div class="row">
				
				<div class="col-md-4">
					<div class="input-group"><label>IMPS Number:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the IMPS No" name="impsno">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>IMPS Date:</label>
						<input type="date" class="form-control" style="height:40px;" name="impsdate">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>IMPS Amount:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the IMPS Amount" name="impsamt">
					</div>
				</div>
				
			</div>
				
				<button style="margin-top:10px;" type="submit" class="btn btn-success">Add Income</button>
				
				
		</div>
				
		<div class="container" id="container4" style="display:none;">
				
			<div class="row">
				
				<div class="col-md-4">
					<div class="input-group"><label>Cash Amount:</label>
						<input type="text" class="form-control" style="height:40px;" placeholder="Enter the CASH amt" name="cashamt">
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="input-group"><label>Date of Payment:</label>
						<input type="date" class="form-control" style="height:40px;" name="cashdop">
					</div>
				</div>
				
			
			</div>
				
				<button style="margin-top:10px;" type="submit" class="btn btn-success">Add Income</button>
				
				
		</div>
				
</form>
		
</div>
</script>
</body>
</html>