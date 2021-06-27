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
<title>New Idea</title>
</head>
<body>
	<div class="container">
		<a href="/ideas">Main page</a>
		<p class="text-right"><a href="/logout">Log out</a></p>
		<h1>Create New Idea</h1>
		<hr>
		<form:form method="post" action="/create/idea" modelAttribute="idea">
			<div class="form-group">
				<form:label path="content">Content: </form:label>
				<form:errors path="content"/>
				<form:input path="content" class="form-control"/>
			</div>
			<button type="submit" class="btn btn-primary center">Create</button>
		</form:form>
	</div>
</body>
</html>