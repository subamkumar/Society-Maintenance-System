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
	document.getElementById("maintmonths").disabled = false;
	document.getElementById("maintquarter").disabled = true;
}

function enableQ()
{
	document.getElementById("maintquarter").disabled = false;
	document.getElementById("maintmonths").disabled = true;
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


<div class="container" style="margin-top:60px;">
<form action="http://localhost:8182/society_latierra/showmaint.jsp" method="post">		
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
		if(flatno!=null && !flatno.equals(""))
		{
			
			specificflat sf = d.getSpecificFlatDetails(flatno);
			FlatModel f = null;
			if(sf!=null)
				f = sf.getFlatInfo();
			
		
			if(f!=null)
			{
				
				if(radioopt!=null && radioopt.equals("m"))
				{
					//Flatno,month,year
					out.println("<br><div class=\"container\">");
					out.println("<div class=\"well well-sm\">");
					out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+f.getFlatNo()+"\">");
					out.println("<b>Flat Number: </b>"+f.getFlatNo().toUpperCase()+"</a>");
					out.println("</div>");
					out.println("<div class=\"well well-sm\">");
					out.println("<b>  MONTH: "+months1.get(Integer.parseInt(radiomonth)).toUpperCase()+" - "+maintyear+"</b>");
					out.println("</div>");
					out.println("<br>");
					
					LinkedList<maintenance_model> ll = d.getMaintenanceReport(f.getFlatNo(), Integer.parseInt(radiomonth), Integer.parseInt(maintyear), 0, "","");
					
					if(ll!=null)
					{
						out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&f="+f.getFlatNo()+"&m="+radiomonth+"&y="+maintyear+"\">Maintenance Bill</a></b><br>");
						out.println("<table class=\"table table-striped\">");
						out.println("<thead><tr><th>Total Amount</th><th>Prev Balance</th><th>Paid Status</th><th>Payment</th>"
									+"</tr></thead>");
						out.println("<tbody>");
						while(!ll.isEmpty())
						{
							maintenance_model m = ll.removeFirst();
							Double total = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge();
							Double prevBalance = m.getPrev_balance();
							String status = "";
							String paylink;
							String fno = f.getFlatNo();
							
							if(m.getStatus() == 1)
							{
								status = "<b style=\"color:green;\">PAID</b>";
								paylink = "";
							}
							else
							{
								status = "<b style=\"color:red\">NOT PAID</b>";
								paylink = "<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+fno+"\">Pay Now</a>";
							}
							
							out.println("<tr><td>"+total+"</td><td>"+prevBalance+"</td><td>"+status+"</td><td>"+paylink+"</td></tr>");
						}
						out.println("</tbody>");
						out.println("</table>");	
					}
					
					out.println("</div>");	
				}
				else if(radioopt!=null && radioopt.equals("q"))
				{
					//Flatno,quarter,year
					out.println("<br><div class=\"container\">");
					out.println("<div class=\"well well-sm\">");
					out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+f.getFlatNo()+"\">");
					out.println("<b>Flat Number: </b>"+f.getFlatNo().toUpperCase()+"</a>");
					out.println("</div>");
					out.println("<div class=\"well well-sm\">");
					out.println("<b>  QUARTER - "+radioquarter+" - "+maintyear+"</b>");
					out.println("</div>");
					out.println("<br>");
					
					LinkedList<maintenance_model> ll = d.getMaintenanceReport(f.getFlatNo(),0, Integer.parseInt(maintyear),  Integer.parseInt(radioquarter), "","");
					
					if(ll!=null)
					{
						out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&f="+f.getFlatNo()+"&q="+radioquarter+"&y="+maintyear+"\">Maintenance Bill</a></b><br>");
						out.println("<table class=\"table table-striped\">");
						out.println("<thead><tr><th>Total Amount</th><th>Prev Balance</th><th>Paid Status</th><th>Payment</th>"
									+"</tr></thead>");
						out.println("<tbody>");
						Double total = 0.0;
						int flag = 0;
						int statusflag = 1;
						Double prev = 0.0;
						String status = "";
						String paylink;
						String fno = f.getFlatNo();
						while(!ll.isEmpty())
						{
							maintenance_model m = ll.removeFirst();
							Double t = m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge();
							total += t;
							if(flag == 0)
								prev = m.getPrev_balance();
							
							if(statusflag == 1)
								statusflag = m.getStatus();
						}
						
						if(statusflag == 1)
						{
							status = "<b style=\"color:green;\">PAID</b>";
							paylink = "";
						}
						else
						{
							status = "<b style=\"color:red\">NOT PAID</b>";
							paylink = "<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+fno+"\">Pay Now</a>";
						}
						
						out.println("<tr><td>"+total+"</td><td>"+prev+"</td><td>"+status+"</td><td>"+paylink+"</td></tr>");
						out.println("</tbody>");
						out.println("</table>");
					}
					
					
					out.println("</div>");
				}
				else
				{
					//Flatno,year
					out.println("<br><div class=\"container\">");
					out.println("<div class=\"well well-sm\">");
					out.println("<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/specificflat.jsp?flatno="+f.getFlatNo()+"\">");
					out.println("<b>Flat Number: </b>"+f.getFlatNo().toUpperCase()+"</a>");
					out.println("</div>");
					out.println("<div class=\"well well-sm\">");
					out.println("<b>  YEAR: "+maintyear+"</b>");
					out.println("</div>");
					out.println("<br>");
					
					LinkedList<maintenance_model> ll = d.getMaintenanceReport(f.getFlatNo(), 0, Integer.parseInt(maintyear), 0, "","");
					
					if(ll!=null)
					{
						
						out.println("<table class=\"table table-striped\">");
						out.println("<thead><tr><th>Month</th><th>Total Amount</th><th>Prev Balance</th><th>Paid Status</th><th>Payment</th>"
									+"</tr></thead>");
						out.println("<tbody>");
						
						while(!ll.isEmpty())
						{
							maintenance_model m = ll.removeFirst();
							String mon = months1.get(m.getMonth());
							Double total = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge();
							Double prevBalance = m.getPrev_balance();
							String status = "";
							String paylink;
							String fno = f.getFlatNo();
							if(m.getStatus() == 1)
							{
								status = "<b style=\"color:green;\">PAID</b>";
								paylink = "";
							}
							else
							{
								status = "<b style=\"color:red\">NOT PAID</b>";
								paylink = "<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+fno+"\">Pay Now</a>";
							}
							out.println("<tr><td>"+mon+"</td><td>"+total+"</td><td>"+prevBalance+"</td><td>"+status+"</td><td>"+paylink+"</td></tr>");
						}
						out.println("</tbody>");
						out.println("</table>");
					}
					
					out.println("</div>");
				}
			}
		}
		else
		{
			
			//Month,Year,All Flat,All Owner
			//Month,Year,All Flat,Unpaid Owner
			//Month,Year,All Flat,Paid Owner
			
			//Month,Year,A Block,All Owner
			//Month,Year,A Block,Unpaid Owner
			//Month,Year,A Block,Paid Owner
			
			//Month,Year,B Block,All Owner
			//Month,Year,B Block,Unpaid Owner
			//Month,Year,B Block,Paid Owner
			
			//Month,Year,C Block,All Owner
			//Month,Year,C Block,Unpaid Owner
			//Month,Year,C Block,Paid Owner
			if(radioopt!=null && radioopt.equals("m"))
			{
				out.println("<br><div class=\"container\">");
				out.println("<div class=\"well well-sm\">");
				out.println("<b>MONTH: "+months1.get(Integer.parseInt(radiomonth)).toUpperCase()+" - "+maintyear+" - "+maintblock.toUpperCase()+" BLOCKS - "+maintowner.toUpperCase()+" OWNERS </b>");
				out.println("</div>");
				
				LinkedList<maintenance_model> ll = d.getMaintenanceReport("", Integer.parseInt(radiomonth),Integer.parseInt(maintyear), 0, maintblock, maintowner);
				
				if(ll!=null)
				{
					out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&m="+radiomonth+"&y="+maintyear+"&b="+maintblock+"&o="+maintowner+"&d=m\">Maintenance Bills</a></b>&nbsp&nbsp&nbsp&nbsp");
					out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&m="+radiomonth+"&y="+maintyear+"&b="+maintblock+"&o="+maintowner+"&d=r\">Download Report List</a></b><br>");
					out.println("<table class=\"table table-striped\">");
					out.println("<thead><tr><th>Flatno</th><th>Total Amount</th><th>Prev Balance</th><th>Paid Status</th><th>Payment</th>"
								+"</tr></thead>");
					out.println("<tbody>");
					
					while(!ll.isEmpty())
					{
						maintenance_model m = ll.removeFirst();
						String mon = months1.get(m.getMonth());
						Double total = m.getMaint_charge()+m.getOccp_charge()+m.getSink_charge();
						Double prevBalance = m.getPrev_balance();
						String status = "";
						String paylink;
						String fno = d.getFlatByID(m.getFlatid().toString()).getFlatNo().toUpperCase();
						if(m.getStatus() == 1)
						{
							status = "<b style=\"color:green;\">PAID</b>";
							paylink = "";
						}
						else
						{
							status = "<b style=\"color:red\">NOT PAID</b>";
							paylink = "<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+fno+"\">Pay Now</a>";
						}
						out.println("<tr><td>"+fno+"</td><td>"+total+"</td><td>"+prevBalance+"</td><td>"+status+"</td><td>"+paylink+"</td></tr>");
					}
					out.println("</tbody>");
					out.println("</table>");
				}
				
			
				out.println("</div>");
			}
			else if(radioopt!=null && radioopt.equals("q"))
			{
				
				//Quarter,Year,All Flat,All Owner
				//Quarter,Year,All Flat,Unpaid Owner
				//Quarter,Year,All Flat,Paid Owner
				
				//Quarter,Year,A Block,All Owner
				//Quarter,Year,A Block,Unpaid Owner
				//Quarter,Year,A Block,Paid Owner
				
				//Quarter,Year,B Block,All Owner
				//Quarter,Year,B Block,Unpaid Owner
				//Quarter,Year,B Block,Paid Owner
				
				//Quarter,Year,C Block,All Owner
				//Quarter,Year,C Block,Unpaid Owner
				//Quarter,Year,C Block,Paid Owner
				
				out.println("<br><div class=\"container\">");
				out.println("<div class=\"well well-sm\">");
				out.println("<b>QUARTER: "+Integer.parseInt(radioquarter)+" - "+maintyear+" - "+maintblock.toUpperCase()+" BLOCKS - "+maintowner.toUpperCase()+" OWNERS </b>");
				out.println("</div>");
				
				LinkedList<maintenance_model> ll = d.getMaintenanceReport("", 0,Integer.parseInt(maintyear), Integer.parseInt(radioquarter), maintblock, maintowner);
				if(ll!=null)
				{
					out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&q="+radioquarter+"&y="+maintyear+"&b="+maintblock+"&o="+maintowner+"&d=m\">Maintenance Bills</a></b>&nbsp&nbsp&nbsp&nbsp");
					out.println("<b><a target=\"_blank\" href=\"http://localhost:8182/society_latierra/generatePDF.jsp?type=maint&q="+radioquarter+"&y="+maintyear+"&b="+maintblock+"&o="+maintowner+"&d=r\">Download Report List</a></b><br>");
					out.println("<table class=\"table table-striped\">");
					out.println("<thead><tr><th>Flatno</th><th>Total Amount</th><th>Paid Status</th><th>Payment</th>"
										+"</tr></thead>");
					out.println("<tbody>");
					Double total = 0.0;
					int flag = 0;
					int statusflag = 1;
					Double prev = 0.0;
					String status = "";
	
					HashMap<String,String> hp = new HashMap<String,String>();
					
					while(!ll.isEmpty())
					{
						maintenance_model m = ll.removeFirst();
						Double t = m.getMaint_charge()+m.getSink_charge()+m.getOccp_charge();
						
						if(hp.containsKey(m.getFlatid().toString()))
						{
							String str = hp.get(m.getFlatid().toString());
							String str1[] = str.split(",");
							
							Double v = Double.parseDouble(str1[0]);
							v += t;
							Integer st = Integer.parseInt(str1[1]);
							
							if(m.getStatus() == 0)
							{
								st = 0;
							}
							
							hp.replace(m.getFlatid().toString(), v.toString()+","+st.toString());
							
						}
						else
						{
							hp.put(m.getFlatid().toString(), t.toString()+","+m.getStatus().toString());
						}
						
						
					}
					
					
					Set<String> set = hp.keySet();
					SortedSet<String> sset = new TreeSet<String>();
					sset.addAll(set);
					Iterator<String> itr = sset.iterator();
						
					while(itr.hasNext())
					{
						String k = itr.next();
						String val = hp.get(k);
						
						String sd[] = val.split(",");
						total = Double.parseDouble(sd[0]);
						statusflag = Integer.parseInt(sd[1]);
						String paylink;
						String fno = d.getFlatByID(k).getFlatNo().toUpperCase();
						if(statusflag == 1)
						{
							status = "<b style=\"color:green;\">PAID</b>";
							paylink = "";
						}
						else
						{
							status = "<b style=\"color:red\">NOT PAID</b>";
							paylink = "<a target=\"_blank\" href=\"http://localhost:8182/society_latierra/balancepay.jsp?flatno="+fno+"\">Pay Now</a>";
						}		
						out.println("<tr><td>"+fno+"</td><td>"+total+"</td><td>"+status+"</td><td>"+paylink+"</td></tr>");
					}
					
							
					
					out.println("</tbody>");
					out.println("</table>");
				}
			}
			else
			{
				//Year,All Flat,All Owner
				//Year,All Flat,Unpaid Owner
				//Year,All Flat,Paid Owner
				
				//Year,A Block,All Owner
				//Year,A Block,Unpaid Owner
				//Year,A Block,Paid Owner
				
				//Year,B Block,All Owner
				//Year,B Block,Unpaid Owner
				//Year,B Block,Paid Owner
				
				//Year,C Block,All Owner
				//Year,C Block,Unpaid Owner
				//Year,C Block,Paid Owner
				
			}
			
			
			
			
			
			
			
			
			
			
			
		}
		
	}
%>


</body>
</html>