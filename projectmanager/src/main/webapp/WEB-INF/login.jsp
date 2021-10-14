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
	<body class="p-3 bg-secondary">
		<div class="bg-dark container text-light p-0" style="width: fit-content; margin: auto;">
			<div class="d-flex flex-column text-center bg-secondary" style="width: 100%;">
				<h1 class="display-2">Project Manager</h1>
				<p>A place for teams to manage projects</p>
			</div>
			<div class="d-flex flex-row border border-light rounded">
				<div class="d-flex flex-column p-4 border border-light rounded" style="width: fit-content;">
					<h1>Login</h1>
					<form:form action="/login" method="post" modelAttribute="newLogin" class="form">
						<div>
							<form:label path="email" class="form-label fs-4 my-2">Email:</form:label>
							<form:input path="email" class="form-control fs-3" style="max-width: 350px;" />
							<form:errors path="email" class="text-danger" />
						</div>
						<div>
							<form:label path="password" class="form-label fs-4 my-2">Password:</form:label>
							<form:password path="password" class="form-control fs-3" style="max-width: 350px;" />
							<form:errors path="password" class="text-danger" />
						</div>
						<input type="submit" value="Login" class="btn btn-lg btn-primary mt-3" />
					</form:form>
				</div>
				<div class="d-flex flex-column p-4 bg-light border border-dark rounded text-dark">
					<h3>Register a new account</h3>
					<form:form action="/register" method="post" modelAttribute="newUser" class="form">
						<div>
							<form:label path="firstName" class="form-label mt-2">First Name:</form:label>
							<form:input path="firstName" class="form-control bg-dark text-light" />
							<form:errors path="firstName" class="text-danger" />
						</div>
						<div>
							<form:label path="lastName" class="form-label mt-2">Last Name:</form:label>
							<form:input path="lastName" class="form-control bg-dark text-light" />
							<form:errors path="lastName" class="text-danger" />
						</div>
						<div>
							<form:label path="email" class="form-label mt-2">Email Address:</form:label>
							<form:input path="email" class="form-control bg-dark text-light" />
							<form:errors path="email" class="text-danger" />
						</div>
						<div>
							<form:label path="password" class="form-label mt-2">Password:</form:label>
							<form:password path="password" class="form-control bg-dark text-light" />
							<form:errors path="password" class="text-danger" />
						</div>
						<div>
							<form:label path="confirmPassword" class="form-label mt-2">Confirm Password:</form:label>
							<form:password path="confirmPassword" class="form-control bg-dark text-light" />
							<form:errors path="confirmPassword" class="text-danger" />
						</div>
						<input type="submit" value="Register" class="btn btn-outline-primary mt-3 fw-bold" />
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>