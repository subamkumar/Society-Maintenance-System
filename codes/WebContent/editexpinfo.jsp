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
		String id = request.getParameter("id");
		
		if(id!=null)
		{
			dataManager d = new dataManager();
			
			
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
			
			//out.println("desc: "+expdesc);
			//out.println("cash: "+cashamt);
			//out.println("dop: "+cashdop);
			//out.println("desc: "+expdesc);
			//out.println("desc: "+expdesc);
			
			if(expdesc!=null)
			{
				if(payopt.equals("1"))
				{
					if(chqno!=null && chqdate!=null && chqamt!=null && chqdop!=null)
					{
						String s = "Cheque No: "+chqno+", Cheque Date: "+chqdate;
						
						int res = d.updateExpenditureSpecific(id,chqamt,"cheque",s,expdesc,chqdop);
						
						if(res == 1)
						{
							out.println("<div class=\"alert alert-success\">"+
									  "<strong>Success!</strong>Expenditure Updated Successfully</div>");
						}
						else
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong>Expenditure Not Updated Successfully</div>");
						}
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Expenditure Transaction Fails</div>");
					}
				}
				else if(payopt.equals("2"))
				{
					if(neftno!=null && neftdate!=null && neftamt!=null)
					{
						String s = "NEFT No: "+neftno+", NEFT Date: "+neftdate;
						
						int res = d.updateExpenditureSpecific(id,neftamt,"neft",s,expdesc,neftdate);
						
						if(res == 1)
						{
							out.println("<div class=\"alert alert-success\">"+
									  "<strong>Success!</strong>Expenditure Updated Successfully</div>");
						}
						else
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong>Expenditure Not Updated Successfully</div>");
						}
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Expenditure Transaction Fails</div>");
					}
				}
				else if(payopt.equals("3"))
				{
					if(impsno!=null && impsdate!=null && impsamt!=null)
					{
						String s = "IMPS No: "+impsno+", IMPS Date: "+impsdate;
						
						int res = d.updateExpenditureSpecific(id,impsamt,"imps",s,expdesc,impsdate);
						
						if(res == 1)
						{
							out.println("<div class=\"alert alert-success\">"+
									  "<strong>Success!</strong>Expenditure Updated Successfully</div>");
						}
						else
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong>Expenditure Not Updated Successfully</div>");
						}
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Expenditure Transaction Fails</div>");
					}
				}
				else if(payopt.equals("4"))
				{
					if(cashamt!=null && cashdop!=null)
					{
						//out.println(cashamt+","+cashdop+","+expdesc);
						int res = d.updateExpenditureSpecific(id,cashamt,"cash","",expdesc,cashdop);
						
						if(res == 1)
						{
							out.println("<div class=\"alert alert-success\">"+
									  "<strong>Success!</strong>Expenditure Updated Successfully</div>");
						}
						else
						{
							out.println("<div class=\"alert alert-danger\">"+
									  "<strong>Error!</strong>Expenditure Not Updated Successfully</div>");
						}
					}
					else
					{
						out.println("<div class=\"alert alert-danger\">"+
								  "<strong>Error!</strong>Expenditure Transaction Fails</div>");
					}
				}
			}
						
			expenditure_info i = d.getExpenditureById(id);
			
			if(i!=null)
			{
				out.println("<form action=\"http://localhost:8182/society_latierra/editexpinfo.jsp?id="+i.getId()+"\" method=\"post\">");
				out.println("<textarea class=\"form-control\" rows=\"5\" placeholder=\"Income description...\" name=\"expdesc\">"+i.getDesc()+"</textarea>");
				
				out.println("<div class=\"row\">");
				out.println("<div class=\"col-md-4\">");
				out.println("<label>Select Payment Type:</label>");
				out.println("<select id=\"payopt\" name=\"payopt\" class=\"form-control\" onchange=\"Payfunc()\">");
				
				if(i.getType().equalsIgnoreCase("cheque"))
				{
					out.println("<option selected=\"selected\" value=1>CHEQUE</option>");
					out.println("<option value=2>NEFT</option>");
					out.println("<option value=3>IMPS</option>");
					out.println("<option value=4>CASH</option>");
					
				}
				else if(i.getType().equalsIgnoreCase("neft"))
				{
					out.println("<option value=1>CHEQUE</option>");
					out.println("<option selected=\"selected\" value=2>NEFT</option>");
					out.println("<option value=3>IMPS</option>");
					out.println("<option value=4>CASH</option>");
					
				}
				else if(i.getType().equalsIgnoreCase("imps"))
				{
					out.println("<option value=1>CHEQUE</option>");
					out.println("<option value=2>NEFT</option>");
					out.println("<option selected=\"selected\" value=3>IMPS</option>");
					out.println("<option value=4>CASH</option>");
					
					
				}
				else if(i.getType().equalsIgnoreCase("cash"))
				{
					out.println("<option value=1>CHEQUE</option>");
					out.println("<option value=2>NEFT</option>");
					out.println("<option value=3>IMPS</option>");
					out.println("<option selected=\"selected\" value=4>CASH</option>");
					
					
				}
				
				
				
				out.println("</select>");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"container\" id=\"container1\" style=\"display:none;\"><br>");//container
				
				out.println("<div class=\"row\">");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Number:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the Cheque No\""+
				"name=\"chqno\" id=\"chqno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Date:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"chqdate\" id=\"chqdate\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Cheque Amount: </label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the Cheque Amount\""+
				"name=\"chqamt\" id=\"chqamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("</div>");
				
				
				out.println("<div class=\"row\">");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Date of Payment:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"chqdop\" id=\"chqdop\">");
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
				"name=\"neftno\" id=\"neftno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>NEFT Date:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"neftdate\" id=\"neftdate\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>NEFT Amount:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the NEFT Amount\""+
				"name=\"neftamt\" id=\"neftamt\">");
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
				"name=\"impsno\" id=\"impsno\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>IMPS Date:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"impsdate\" id=\"impsdate\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>IMPS Amount:</label>"+
						   "<input type=\"text\" class=\"form-control\" style=\"height:40px;\" placeholder=\"Enter the IMPS Amount\""+
				"name=\"impsamt\" id=\"impsamt\">");
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
				"name=\"cashamt\" id=\"cashamt\">");
				out.println("</div>");
				out.println("</div>");
				
				out.println("<div class=\"col-md-4\">");
				out.println("<div class=\"input-group\"><label>Date of Payment:</label>"+
						   "<input type=\"date\" class=\"form-control\" style=\"height:40px;\""+
				"name=\"cashdop\" id=\"cashdop\">");
				out.println("</div>");
				out.println("</div>");
				
			
				out.println("</div>");
				
				out.println("<button style=\"margin-top:10px;\" type=\"submit\" class=\"btn btn-success\">Pay Your Money</button>");
				
				
				out.println("</div>");//container 4
				
				
				if(i.getType().equalsIgnoreCase("cheque"))
				{
					String amt = String.valueOf(i.getAmt());
					String date = i.getDate();
					String s[] = i.getType_detail().split(",");
					
					String number = s[0].substring(s[0].indexOf(":")+1).trim();
					String chqdate1 = s[1].substring(s[1].indexOf(":")+1).trim();
					
					out.println("<script>");
					out.println("(function(){");
					out.println("document.getElementById(\"container1\").style.display=\"block\";");
					out.println("document.getElementById(\"chqno\").value = \""+number+"\"");
					out.println("document.getElementById(\"chqamt\").value = \""+amt+"\"");
					out.println("document.getElementById(\"chqdate\").value = \""+chqdate1+"\"");
					out.println("document.getElementById(\"chqdop\").value = \""+date+"\"");
					out.println("})();");
					out.println("</script>");
				}
				else if(i.getType().equalsIgnoreCase("neft"))
				{
					Double a = i.getAmt();
					String amt = String.valueOf(a);
					String s[] = i.getType_detail().split(",");
					String  number = s[0].trim();
					String date = i.getDate();
					
					out.println("<script>");
					out.println("(function(){");
					out.println("document.getElementById(\"container2\").style.display=\"block\";");
					out.println("document.getElementById(\"neftno\").value = \""+number+"\"");
					out.println("document.getElementById(\"neftamt\").value = \""+amt+"\"");
					out.println("document.getElementById(\"neftdate\").value = \""+date+"\"");
					out.println("})();");
					out.println("</script>");
				}
				else if(i.getType().equalsIgnoreCase("imps"))
				{
					Double a = i.getAmt();
					String amt = String.valueOf(a);
					String s[] = i.getType_detail().split(",");
					String  number = s[0].trim();
					String date = i.getDate();
					
					out.println("<script>");
					out.println("(function(){");
					out.println("document.getElementById(\"container3\").style.display=\"block\";");
					out.println("document.getElementById(\"impsno\").value = \""+number+"\"");
					out.println("document.getElementById(\"impsamt\").value = \""+amt+"\"");
					out.println("document.getElementById(\"impsdate\").value = \""+date+"\"");
					out.println("})();");
					out.println("</script>");
				}
				else if(i.getType().equalsIgnoreCase("cash"))
				{
					String amt = String.valueOf(i.getAmt());
					String date = i.getDate();
					
					out.println("<script>");
					out.println("(function(){");
					out.println("document.getElementById(\"container4\").style.display=\"block\";");
					out.println("document.getElementById(\"cashamt\").value = \""+amt+"\"");
					out.println("document.getElementById(\"cashdop\").value = \""+date+"\"");
					out.println("})();");
					out.println("</script>");
				}
				
				out.println("</form>");
			}
		}
		
	%>	
	</div>	
</body>
</html>