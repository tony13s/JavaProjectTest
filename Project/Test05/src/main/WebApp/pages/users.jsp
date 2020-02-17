<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
</head>
<body>
<a>${username}</a>

<div  id="tasksDiv">

<div >
	<table border="1">
		<thead>
		<tr>
			<th>Id</th>
			<th>User Name:</th>
			<th></th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${users2}">
		<tr>
			<td>${user.id_user}</td>
			<td>${user.name_user}</td>
			<td></td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>
</body>
</html>