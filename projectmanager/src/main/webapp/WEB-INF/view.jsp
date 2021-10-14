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
		<title> BOOK CLUB </title>
	</head>
	<body class="p-3 bg-secondary text-light">
		<div class="container">
			<div class="d-flex flex-row justify-content-between">
				<h1 class="display-4">Project Details</h1>
				<a href="/bookclub/dashboard" class="link link-light mt-4">back to dashboard</a>
			</div>
			<div class="d-flex flex-column" style="width: 80%; max-width: 600px;">
				<div class="d-flex flex-row justify-content-between mt-4">
					<h5 class="text-decoration-underline">Project</h5>
					<h5 class="tex-start" style="width: 70%;"> <c:out value="${ project.title }"/> </h5>
				</div>
				<div class="d-flex flex-row justify-content-between mt-4">
					<h5 class="text-decoration-underline">Description</h5>
					<h5 class="tex-start" style="width: 70%;"> <c:out value="${ project.description }"/> </h5>
				</div>
				<div class="d-flex flex-row justify-content-between mt-4">
					<h5 class="text-decoration-underline">Due Date</h5>
					<h5 class="tex-start" style="width: 70%;"> <c:out value="${ project.dueDate }"/> </h5>
				</div>
				<div class="d-flex flex-row justify-content-between pe-5 mt-5" >
					<a href="/projects/${ project.id }/tasks" class="link link-light" style="width: fit-content;">see tasks</a>
					<form action="/projects/${ project.id }/delete" method="post">
						<input type="hidden" name="_method" value="delete">
						<input type="submit" class="btn btn-sm btn-outline-danger ms-3 text-light" value="Delete"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>