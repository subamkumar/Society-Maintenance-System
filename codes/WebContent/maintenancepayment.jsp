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

	String id = request.getParameter("id");
	
	dataManager d = new dataManager();
	maintenance_model m = d.getMaintenancePaymentInfo(id);
	
	if(m!=null)
	{
		FlatModel f = database.getDatabase().getFlat(m.getFlatid());	
		
		out.println("<div class=\"container\">");
		out.println("<div class=\"well well-sm\">");
		out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+f.getFlatNo()+"\">");
		out.println("<b>Flat Number: </b>"+f.getFlatNo().toUpperCase()+"</a>");
		out.println("</div>");
		
		out.println("<div class=\"well well-sm\">");
		out.println("<table class=\"table\"><tbody>"); 
		out.println("<tr><td><b>Maintenance Base Charge: </b></td><td>"+m.getMaint_charge()+"</td></tr>");		
		out.println("<tr><td><b>Sinking Charge: </b></td><td>"+m.getSink_charge()+"</td></tr>");		
		out.println("<tr><td><b>Occupation Charge: </b></td><td>"+m.getOccp_charge()+"</td></tr>");		
		out.println("<tr><td><b>Total Charge: </b></td><td>"+(m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge())+"</td></tr>");
		
		Double payable_amt = (m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge()-m.getHow_much_paid());
		
		if(m.getHow_much_paid() > 0)
		{
			out.println("<tr><td><b>Already Paid: </b></td><td>"+m.getHow_much_paid()+"</td></tr>");
			out.println("<tr><td><b>Payable Amount: </b></td><td>"+(m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge()-m.getHow_much_paid())+"</td></tr>");
		}
		
		if(m.getStatus() == 1)
		{
			out.println("<tr><td><b>Maintenance Paid Status: </b></td><td><b><span style=\"color:green;\">PAID</span></b></td></tr>");
		}
		else
		{
			out.println("<tr><td><b>Maintenance Paid Status: </b></td><td><b><span style=\"color:red;\">NOT PAID</span></b></td></tr>");
		}
		out.println("</tbody></table></div>");
		out.println("</div>");
		
		
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
		
		
		
		
		
		
		
		
		
		
		
		if(m.getStatus() == 0 && m.getPrev_balance() == 0)
		{
			out.println("<div class=\"container\">");
			out.println("<form action=\"http://localhost:8182/society_latierra/maintenancepayment.jsp?id="+m.getId()+"\" method=\"post\" >");
			
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
					   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the CASH amt\""+
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
			out.println("</div>");	
		}
		
	}
	
%>

</body>
</html>