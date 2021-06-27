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
<title>Show Idea</title>
</head>
<body>
	<div class="container">
		<p class="text-right"><a href="/logout">Log out</a></p>
		<a href="/ideas">Main page</a>
		<h1>${ide.content}</h1>
		<br>
		<p>Created by: ${ide.uses.name }</p>
		<c:choose>
			<c:when test="${ ide.uses.id == user.id }">
				<a href="/ideas/${ide.id }/edit"><button type="submit" class="btn btn-primary center">Edit Idea</button></a>
			</c:when>
		</c:choose>
		<hr><hr>
		<h3>Users who liked your idea</h3>
		<table class="table table-dark table-striped">
			<thead>
				<tr>
					<th>Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ide.likers}" var="usess">
					<tr>
						<td>${usess.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>