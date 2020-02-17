<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Expenses</title>
</head>
<body>
<div  id="tasksDiv">

<div >
	<h1 >Name: ${userName}</h1>
	<h2>Credit Score Category: ${letterCreditScore}</h2>
	<h2>Total: ${total}</h2>
	<table border="1" >
		<thead>
		<tr>
			<th>Id</th>
			<th>Description:</th>
			<th>Type</th>
			<th>Month</th>			
			<th>Year</th>			
			<th>Value</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="expenses" items="${expenses2}">
		<tr>
			<td>${expenses.id_expenses}</td>
			<td>${expenses.description}</td>			
			<td>${expenses.type}</td>
			<td>${expenses.month}</td>
			<td>${expenses.year}</td>
			<td>${expenses.value}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>
</body>
</html>