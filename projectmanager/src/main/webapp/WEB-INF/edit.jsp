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
				<h1 class="display-5 mb-5"> Edit Project </h1>
				<form:form action="/projects/update" modelAttribute="newProject" method="post" class="form" style="width: 80%;">
				<input type="hidden" name="_method" value="put" >
				<div class="col text-end mb-1">
					<form:errors path="title" class="text-danger text-end"></form:errors>
					<form:errors path="description" class="text-danger text-end"></form:errors>
					<form:errors path="dueDate" class="text-danger text-end"></form:errors>
				</div>
				<div class="d-flex flex-row justify-content-between mb-3">
					<form:label path="title" class="form-label fs-4 me-4" >Project Title</form:label>
					<form:input path="title" class="form-control ms-5" style="max-width: 550px;" />
				</div>
				<div class="d-flex flex-row justify-content-between mb-3">
					<form:label path="description" class="form-label fs-4 me-4" >Project Description</form:label>
					<form:textarea path="description" class="form-control" style="max-width: 550px; height: 200px;" />
				</div>
				<div class="d-flex flex-row justify-content-between mb-3">
					<form:label path="dueDate" class="form-label fs-4 me-4" >Due Date</form:label>
					<form:input path="dueDate" class="form-control ms-4" style="max-width: 550px;" type="date" />
				</div>
				<div class="row justify-content-between pe-3">
					<button type="reset" class="btn btn-outline-secondary">Clear</button>
					<a href="/dashboard" class="btn btn-secondary">Cancel</a>
					<input type="submit" class="btn btn-sm btn-primary align-self-end" value="Submit" style="max-width: 80px;" />
				</div>
			</form:form>
		</div>
	</body>
</html>