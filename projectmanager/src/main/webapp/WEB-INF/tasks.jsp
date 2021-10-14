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
				<div>
					<h1 class="display-4">Project: <c:out value="${ project.title }" /> </h1>
					<p>Project Lead: <c:out value="${ project.lead.firstName }" /> </p>
				</div>
				<a href="/bookclub/dashboard" class="link link-light mt-4">back to dashboard</a>
			</div>
			<div class="d-flex flex-column" style="width: 80%;">
				<form:form action="/projects/${ project.id }/tasks/add" modelAttribute="newTask" method="post" class="form">
					<div class="d-flex flex-row justify-content-between">
						<form:label path="task" class="mt-4 form-label">Add a task to this project</form:label>
						<form:textarea path="task" class="form-control" style="max-width: 550px; height: 200px;" />
						<form:errors path="task" class="text-danger text-end"></form:errors>
					</div>
					<div class="d-flex flex-row justify-content-end">
						<input type="submit" class="btn btn-primary" value="Submit"/>
					</div>
				</form:form>
				<div class="d-flex flex-column mt-3" style="width: 57%;">
					<ul>
						<c:forEach var="task" items="${ allTasks }">
							<li class="fw-bold"> <c:out value="${ task.createdAt }" /> </li>
							<li> <c:out value="${ task.description }" /> </li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>