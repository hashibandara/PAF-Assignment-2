<%@ import="model.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
</head>
<body>
	<h1>User Management V10.1</h1>
	
	<form id="formUser" name="formUser">
 	Name: 
 	<input id="name" name="name" type="text" class="form-control form-control-sm">
 	<br>  Contact: 
 	<input id="contact" name="contact" type="text" class="form-control form-control-sm">
	 <br> Email: 
 	<input id="email" name="email" type="text" class="form-control form-control-sm">
	 br> Password: 
	 <input id="password" name="password" type="text" class="form-control form-control-sm">
 	<br>
 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divUserGrid">
<%
	User userObj = new User();
	out.print(userObj.readUsers();
%>

</div> </div> </div> 

</body>
</html>