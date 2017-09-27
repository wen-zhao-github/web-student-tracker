<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Student</title>
<link type = "text/css" rel = "stylesheet" href = "css/style.css"/>
<link type = "text/css" rel = "stylesheet" href="css/add-student-style.css"/>
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>King George University</h2>
		</div>
	</div>
	<div id = "container">
	<h3>Update Student info</h3>
		<form action="StudentControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type = "hidden" name="student-id" value="${THE_CURRENT_STUDENT.id}"/>	
			
			<table>
				<tbody>
					<tr>
						<td><label>First Name</label></td>
						<td><input type="text" name="firstName" value = "${THE_CURRENT_STUDENT.firstName}"></td>
					</tr>
					<tr>
						<td><label>Last Name</label></td>
						<td><input type="text" name="lastName" value = "${THE_CURRENT_STUDENT.lastName}"></td>
					</tr>
					<tr>
						<td><label>Email</label></td>
						<td><input type="text" name="email" value = "${THE_CURRENT_STUDENT.email}"></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class = "save"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<a href = "StudentControllerServlet">Back to List</a>
</body>
</html>