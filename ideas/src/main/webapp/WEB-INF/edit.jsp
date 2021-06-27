<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<title>Edit Idea</title>
</head>
<body>
	<div class="container">
		<p class="text-right"><a href="/logout">Log out</a></p>
		<a href="/ideas">Main page</a>
		<h1>Edit ${idea.content}</h1>
		<br>
		<form:form method="post" action="/update/${idea.id}" modelAttribute="idea">
			<div class="form-group">
				<form:label path="content">Content: </form:label>
				<form:errors path="content"/>
				<form:input path="content" class="form-control" value="${idea.content}"/>
			</div>
			<button type="submit" class="btn btn-primary center">Update</button>
		</form:form>
		<br>
		<a href="/delete/idea/${idea.id }"><button type="submit" class="btn btn-primary center">Delete Idea</button></a>
	</div>
</body>
</html>
