<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<title>Login Page</title>
</head>
<body>
	<div class="container">
		<h1 class="text-left">Welcome</h1>
		<hr><hr>
		<div class="row">
			<div class="col">
				<h3 class="text-left">Register</h3>
				<hr>
				<form:form method="post" action="/register/create" modelAttribute="user">
					<div class="form-group">
						<form:label path="name">Name: </form:label>
						<form:errors path="name"/>
						<form:input path="name" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="email">Email: </form:label>
						<form:errors path="email"/>
						<form:input path="email" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password: </form:label>
						<form:errors path="password"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<div class="form-group">
						<form:label path="passwordConfirmation">Password Confirmation: </form:label>
						<form:errors path="passwordConfirmation"/>
						<form:input path="passwordConfirmation" type="password" class="form-control"/>
					</div>
					<button type="submit" class="btn btn-primary center">Register</button>
				</form:form>
			</div>
			<div class="col">
				<h3 class="text-center">Login</h3>
				<p>${loginError}</p>
				<hr>
				<form method="POST" action="login/user">
					<div class=form-group>
						<label>Email: </label>
						<input name="loginEmail" type="email" class="form-control"/>
					</div>
					<div class="form-group">
						<label>Password: </label>
						<input name="loginPass" type="password" class="form-control"/>
					</div>
				<button type="submit" class="btn btn-primary center">Login</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>