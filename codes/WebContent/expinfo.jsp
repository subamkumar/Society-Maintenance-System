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
      <li><a href="http://localhost:8182/society_latierra/expinfo.jsp">Expenditure Info</a></li>
      <li><a href="http://localhost:8182/society_latierra/addexpenditure.jsp">Add Expenditure</a></li>
      <li><a href="http://localhost:8182/society_latierra/pettyheader.jsp">Add Headers</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="http://localhost:8182/society_latierra/signout.jsp"><span class="glyphicon glyphicon-user"></span> Sign Out</a></li>
    </ul>
  </div>
</nav>
<jsp:include page="side-nav.jsp"/>
<div class="container" style="margin-top:60px;">
	<form action="http://localhost:8182/society_latierra/expinfo.jsp" method="post">
	<div class="row">
		<div class="col-md-3">
			<select style="margin-left:100px;" name="pmonths" class="form-control">
        		<option value=01>January</option>
        		<option value=02>February</option>
        		<option value=03>March</option>
        		<option value=04>April</option>
        		<option value=05>May</option>
        		<option value=06>June</option>
        		<option value=07>July</option>
        		<option value=08>August</option>
        		<option value=09>September</option>
        		<option value=10>October</option>
        		<option value=11>November</option>
        		<option value=12>December</option> 
			</select>
		</div>
		
		<div class="col-md-3">
			<select style="margin-left:100px;" class="form-control" name="pyears">
    			<option value=2018>2018</option>
    			<option value=2019>2019</option>
    			<option value=2020>2020</option>
    			<option value=2021>2021</option>
    			<option value=2022>2022</option>
    			<option value=2023>2023</option>
  			</select>
		</div>
		<div class="col-md-3">
			<label><input style="margin-left:100px;" type="checkbox" name="chkyear">Only Year</label>
		</div>
		<div class="col-md-3">
			<button type="submit" class="btn btn-success">Show Expenditure Info</button>
		</div>
	</div>
	</form>
	<br>
	<%
		String pmonths = request.getParameter("pmonths");
		String pyears = request.getParameter("pyears");
		String chkyear = request.getParameter("chkyear");
		
		dataManager d = new dataManager();
		if(pmonths!=null && pyears!=null)
		{
			LinkedList<expenditure_info> ll = null;
			String link = "";
			String header = "";
			if(chkyear!=null)
			{
				ll = d.getExpenditureInfoByYear(pyears);
				header = String.valueOf(pyears);
				link = "http://localhost:8182/society_latierra/generatePDF.jsp?type=exp&y="+pyears;
			}
			else
			{
				ll = d.getExpenditureInfo(pmonths, pyears);
				header  = pmonths+"-"+pyears;
				link = "http://localhost:8182/society_latierra/generatePDF.jsp?type=exp&m="+pmonths+"&y="+pyears;
			}
			
			if(ll!=null)
			{
				out.println("<h3>"+header+"</h3>");
				out.println("<div><a target=\"_blank\" href=\""+link+"\">Download Expenditure Report</a></div><br>");
				out.println("<table class=\"table table-striped\"><thead><tr><th>Sr No</th><th>Description</th><th>Header</th><th>Amount</th><th>Date</th><th>Type</th><th>Type Details</th><th>Voucher</th></tr></thead><tbody>");
				int count = 0;
				while(!ll.isEmpty())
				{
					count++;
					expenditure_info p = ll.removeFirst();
					out.println("<tr><td>"+count+"</td>");
					out.println("<td>"+p.getDesc()+"</td>");
					out.println("<td>"+p.getHeader()+"</td>");
					out.println("<td>"+p.getAmt()+"</td>");
					out.println("<td>"+p.getDate()+"</td>");
					out.println("<td>"+p.getType()+"</td>");
					out.println("<td>"+p.getType_detail()+"</td>");
					out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=voucher&q=exp&id="+p.getId()+"\">Voucher</td>");
					out.println("<td><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/editexpinfo.jsp?id="+p.getId()+"\">Edit</a></td></tr>");
				}
				out.println("</tbody></table>");	
			}
			else
			{
				out.println("<b>No Records Found</b>");
			}
			
			
		}
	%>
</div>
</body>
</html>