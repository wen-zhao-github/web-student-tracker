<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student info tracker</title>
<link type = "text/css" rel = "stylesheet" href = "css/style.css"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>King George University</h2>
		</div>
	</div>

	<div id="container">
		<div id=content>
			
		    <input class="add-student-button" type="submit" value="Add Student"
		    onclick = "window.location.href='add-student-form.jsp';return false;"/>
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
					<c:forEach var = "temp" items = "${student_info_list}">				
				    <tr>
						<td>${temp.getFirstName()}</td>
						<td>${temp.getLastName()}</td>
						<td>${temp.getEmail()}</td>
				    </tr>
				    </c:forEach>
			</table>

		</div>
	</div>
	
</body>
</html>