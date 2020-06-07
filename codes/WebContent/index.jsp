<%@ page import="society.database" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>LA-TIERRA HOUSING SOCIETY | Management System</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<script>
  	function check()
  	</script>
</head>
<body>

<div class="container" style="margin-top:100px;width:300px;">
	<%
	
	if(request.getSession().getAttribute("userID") != null)
	{
		response.sendRedirect("http://localhost:8182/society_latierra/specificflat.jsp");
	}
	
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		if(username!=null && password!=null)
		{
			int res = database.getDatabase().checkAuthentication(username, password);
			if(res!=0)
			{
				request.getSession().setAttribute("userID", res);
				response.sendRedirect("http://localhost:8182/society_latierra/specificflat.jsp");
			}
			else
			{
				out.println("<div class=\"alert alert-danger\">"+
					    "<strong>Error!</strong> Incorrect Username and password"+
					    "</div>");
			}
		}
	%>
<h1 style="margin-left:30px;font-family: comic sans MS;color:red;">LA-TIERRA</h1>
<br>
  <form action="/society_latierra/" method="post">
    <div class="form-group">
      <label for="usr">UserName:</label>
      <input type="text" name="user" class="form-control" id="usr">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" name="pass" class="form-control" id="pwd">
    </div>
    <button type="submit" class="btn btn-success" style="margin-left:90px;">Login</button>
  </form>
</div>

</body>
</html>