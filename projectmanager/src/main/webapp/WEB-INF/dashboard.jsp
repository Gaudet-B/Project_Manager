<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<meta charset="ISO-8859-1">
		<title> PR0JECT MAN@GER </title>
	</head>
	<body class="p-3 bg-secondary text-light">
		<div class="container">
			<div class="d-flex flex-row justify-content-between">
				<div class="d-flex flex-column">
					<div class="d-flex flex-row">
						<h1> Welcome, <span class="display-4"> <c:out value="${ loggedInUser.firstName }"/> </span> </h1>
					</div>
				</div>
				<div class="d-flex flex-column text-end">
					<a href="/logout" class="link link-light mt-4">Logout</a>								
				</div>
			</div>
			<div class="d-flex flex-row justify-content-between">
				<h5 class="my-4">All Projects</h5>
				<a href="/new" class="btn btn-sm btn-outline-dark" style="width: 120px;">+ new project</a>
			</div>
			<table class="table table-secondary table-hover">
				<thead>
					<tr>
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ allProjects }">
						<tr>
							<td> <a href="/projects/${ project.id }" class="link link-light"> <c:out value="${ project.title }" /> </a> </td>
							<td> <c:out value="${ project.lead.firstName } ${ project.lead.lastName }" /> </td>
							<td> <c:out value="${ project.dueDate }" /> </td>
							<td> <a href="/projects/${ project.id }/join" class="link link-light"> Join Team </a> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<h3 class="my-4">Your Projects</h3>
			<table class="table table-dark table-hover">
				<thead>
					<tr>
						<th>Project</th>
						<th>Team Lead</th>
						<th>Due Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="project" items="${ loggedInUser.projects }">
						<tr>
							<td> <a href="/projects/${ project.id }" class="link link-light"> <c:out value="${ project.title }" /> </a> </td>
							<td> <c:out value="${ project.lead.firstName } ${ project.lead.lastName }" /> </td>
							<td> <c:out value="${ project.dueDate }" /> </td>
							<td> <a href="/projects/${ project.id }/leave" class="link link-light"> Leave Team </a> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>