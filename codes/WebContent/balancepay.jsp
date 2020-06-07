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
<p style="margin-top:60px;"></p>

<form action="http://localhost:8182/society_latierra/balancepay.jsp" method="post">
	<div class="container" style="margin-top:60px;"> 
		<div class="input-group">
			<input type="text" class="form-control" style="height:40px;" placeholder="Enter the flat No for Balance Payment" name="flatno">
			<div class="input-group-btn">
		   		<button class="btn btn-default btn-lg" type="submit"><i class="glyphicon glyphicon-search"></i></button>
			</div>
		</div>
	</div> 
</form>
<br>

<%
	String flatno = request.getParameter("flatno");
	
	if(flatno!=null)
	{
		dataManager d = new dataManager();
		specificflat sf =  d.getSpecificFlatDetails(flatno);
		FlatModel f = sf.getFlatInfo();
		balance_UI_Info bui = null;
		if(f!=null)
			 bui = d.getBalanceInfo(f);
		
		if(f!=null && bui!=null)
		{
			
			String payOption = request.getParameter("payopt");
			
			if(payOption!=null)
			{
				if(payOption.equals("1"))
				{
					//cheque
					String chequeNo = request.getParameter("chqno");
					String chequedate = request.getParameter("chqdate");
					String chequeAmount = request.getParameter("chqamt");
					String chequedop = request.getParameter("chqdop");
					
					if(chequeNo!=null && chequedate!=null && chequeAmount!=null && chequedop!=null)
					{
						if(chequeNo.equals("") || chequedate.equals("") || chequeAmount.equals("") || chequedop.equals(""))
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong> All Fields are not Filled Properly</div>");
						}
						else
						{
							String type_detail = "Cheque No: "+chequeNo+",Cheque Date: "+chequedate;
							
							int r = d.payBalanceMaintenance(f.getFlatID(),chequeAmount,chequedop,"CHEQUE",type_detail,"Balance Payment");
							
							if(r == 0)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Try Again</div>");
							}
							else if(r == 1)
							{
								out.println("<div class=\"alert alert-success\">"+
										  "<strong>Success!</strong> Balance Paid worth "+Double.parseDouble(chequeAmount)+" Successfully</div>");
							}
							else if(r == -2)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Date of Payment Out Of Scope</div>");
							}
							
						}
					}
				}
				else if(payOption.equals("2"))
				{
					//NEFT
					String neftNo = request.getParameter("neftno");
					String neftdate = request.getParameter("neftdate");
					String neftAmount = request.getParameter("neftamt");
					
					if(neftNo!=null && neftdate!=null && neftAmount!=null)
					{
						if(neftNo.equals("") || neftdate.equals("") || neftAmount.equals(""))
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong> All Fields are not Filled Properly</div>");
						}
						else
						{
							String type_detail = "NEFT No: "+neftNo+",NEFT Date: "+neftdate;
							
							int r = d.payBalanceMaintenance(f.getFlatID(),neftAmount,neftdate,"NEFT",type_detail,"Balance Payment");
							
							if(r == 0)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Try Again</div>");
							}
							else if(r == 1)
							{
								out.println("<div class=\"alert alert-success\">"+
										  "<strong>Success!</strong> Balance Paid worth "+Double.parseDouble(neftAmount)+" Successfully</div>");
							}
							else if(r == -2)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Date of Payment Out Of Scope</div>");
							}
							
						}
					}
				}
				else if(payOption.equals("3"))
				{
					//IMPS
					String impsNo = request.getParameter("impsno");
					String impsdate = request.getParameter("impsdate");
					String impsAmount = request.getParameter("impsamt");
					
					if(impsNo!=null && impsdate!=null && impsAmount!=null)
					{
						if(impsNo.equals("") || impsdate.equals("") || impsAmount.equals(""))
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong> All Fields are not Filled Properly</div>");
						}
						else
						{
							String type_detail = "IMPS No: "+impsNo+",IMPS Date: "+impsdate;
							
							int r = d.payBalanceMaintenance(f.getFlatID(),impsAmount,impsdate,"IMPS",type_detail,"Balance Payment");
							
							if(r == 0)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Try Again</div>");
							}
							else if(r == 1)
							{
								out.println("<div class=\"alert alert-success\">"+
										  "<strong>Success!</strong> Balance Paid worth "+Double.parseDouble(impsAmount)+" Successfully</div>");
							}
							else if(r == -2)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Date of Payment Out Of Scope</div>");
							}
							
						}
					}
				}
				else if(payOption.equals("4"))
				{
					//CASH
					String cashAmount = request.getParameter("cashamt");
					String cashdop = request.getParameter("cashdop");
					
					if(cashAmount!=null && cashdop!=null)
					{
						if(cashAmount.equals("") || cashdop.equals(""))
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong> All Fields are not Filled Properly</div>");
						}
						else
						{
							String type_detail = "";
							
							int r = d.payBalanceMaintenance(f.getFlatID(),cashAmount,cashdop,"CASH",type_detail,"Balance Payment");
							
							if(r == 0)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Try Again</div>");
							}
							else if(r == 1)
							{
								out.println("<div class=\"alert alert-success\">"+
										  "<strong>Success!</strong> Balance Paid worth "+Double.parseDouble(cashAmount)+" Successfully</div>");
							}
							else if(r == -2)
							{
								out.println("<div class=\"alert alert-danger\">"+
										  "<strong>Error!</strong> Date of Payment Out Of Scope</div>");
							}
							
						}
					}
				}
			}
			
			
	
			
			out.println("<br>");	   
			out.println("<div class=\"container\">");
			
			out.println("<div class=\"well well-sm\">");
			out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+flatno+"\"><b>Flat Number: "+flatno.toUpperCase()+"</b></a>");
			out.println("</div>");
			
			bui = d.getBalanceInfo(f);
			out.println("<div class=\"well well-sm\">");
			out.println("<b>Current Remaining Balance: </b>"+bui.getBalance());
			out.println("</div>");
			
			if(bui.getBalance() > 0)
			{
				out.println("<div class=\"well well-sm\">");
				out.println("<b>Balance Details: </b><br>");
				out.println(bui.getBalance_details());
				out.println("</div>");
				
				
				out.println("<h4>Pay Balance</h4>");
				out.println("<form action=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+flatno+"\" method=\"post\" >");
				
				out.println("<div class=\"row\">");

				out.println("<div class=\"col-md-4\">");
				out.println("<label>Select Payment Type:</label>"+
				  "<select id=\"payopt\" name=\"payopt\" class=\"form-control\" onchange=\"Payfunc()\">"+
				    "<option value=1>CHEQUE</option>"+
				    "<option value=2>NEFT</option>"+
				    "<option value=3>IMPS</option>"+
				    "<option value=4>CASH</option>"+
				  "</select>");
				out.println("</div>");
				
				out.println("</div>");

				

				out.println("<div class=\"container\" id=\"container1\" style=\"display:block;\"><br>");//container

				out.println("<div class=\"row\">");

				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Number:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the Cheque No\""+
				"name=\"chqno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Date:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"chqdate\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Amount: </label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the Cheque Amount\""+
				"name=\"chqamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("</div>");
				
				
				out.println("<div class=\"row\">");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Date of Payment:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"chqdop\">");
				out.println("</div>");
				out.println("</div>");
						
				out.println("</div>");
				
				
				out.println("<button style=\"margin-top:10px;\" type=\"submit\" class=\"btn btn-success\">Pay Your Money</button>");
				
				out.println("</div>");//container

				
				out.println("<div class=\"container\" id=\"container2\" style=\"display:none;\">");//container 2
				
				out.println("<div class=\"row\">");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>NEFT Number:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the NEFT No\""+
				"name=\"neftno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>NEFT Date:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"neftdate\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>NEFT Amount:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the NEFT Amount\""+
				"name=\"neftamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("</div>");
				
				out.println("<button style=\"margin-top:10px;\" type=\"submit\" class=\"btn btn-success\">Pay Your Money</button>");
				
				out.println("</div>");//container 2
				
				out.println("<div class=\"container\" id=\"container3\" style=\"display:none;\">");//container 3
				
				out.println("<div class=\"row\">");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>IMPS Number:</label>"+
					   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the IMPS No\""+
				"name=\"impsno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>IMPS Date:</label>"+
					   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"impsdate\">");
				out.println("</div>");
				out.println("</div>");

				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>IMPS Amount:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the IMPS Amount\""+
				"name=\"impsamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("</div>");
				
				out.println("<button style=\"margin-top:10px;\" type=\"submit\" class=\"btn btn-success\">Pay Your Money</button>");


				out.println("</div>");//container 3
				
				out.println("<div class=\"container\" id=\"container4\" style=\"display:none;\">");//container 4
				
				out.println("<div class=\"row\">");

				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cash Amount:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the CASH Amt\""+
				"name=\"cashamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Date of Payment:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"cashdop\">");
				out.println("</div>");
				out.println("</div>");
				

				out.println("</div>");
				
				out.println("<button style=\"margin-top:10px;\" type=\"submit\" class=\"btn btn-success\">Pay Your Money</button>");
				
				
				out.println("</div>");//container 4
				
				out.println("</form>");
				
			}
			
			
			
			
			out.println("<h4>Previous Paid Balances</h4>");
			
			balance_table_info bti[] = d.balanceHistory(f);
			
			out.println("<table class=\"table table-striped\">");
			out.println("<thead><tr><th>Sr.No</th><th>Balance Amount</th><th>Payment Date</th><th>Payment Details</th><th>Balance Info</th><th>Receipt</th><th>Mail</th></tr></thead><tbody>");
			
			if(bti!=null)
			{
				int count = 0;
				for(balance_table_info b:bti)
				{
					count++;
					out.println("<tr>");
					out.println("<td>"+count+"</td>");
					out.println("<td>"+b.getBalance_amt()+"</td>");
					out.println("<td>"+b.getPayment_date()+"</td>");
					out.println("<td>"+b.getPayment_details()+"</td>");
					out.println("<td>"+b.getBalance_info()+"</td>");
					out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=balrec&f="+flatno+"&id="+b.getId()+"\">Receipt</td>");
					out.println("<td>"+b.getReceipt()+"</td>");
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