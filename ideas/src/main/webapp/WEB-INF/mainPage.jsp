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
<title>Ideas</title>
</head>
<body>
	<div class="container">
		<p class="text-right"><a href="/logout">Log out</a></p>
		<h1 class="text-center">Welcome ${user.name}</h1>
		<hr><hr>
		<h3>Ideas</h3>
		<br>
		<table class="table table-dark table-striped">
			<thead>
				<tr>
					<th>Idea</th>
					<th>Created by</th>
					<th>Likes</th>
					<th>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ideass}" var="ide">
					<tr>
						<td><a href="/ideas/${ide.id}">${ide.content}</a></td>
						<td>${ide.uses.name}</td>
						<td>${ide.likers.size()}</td>
						<c:choose>
 							<c:when test="${ide.likers.contains(user)}">
							 	<td><a href="/unlike/${ide.id }">Unlike</a></td>
							 </c:when>
							 <c:otherwise>
							 	<td><a href="/like/${ide.id}">Like</a><td>
							 </c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/ideas/new"><button type="submit" class="btn btn-primary center">Create Idea</button></a>
	</div>
</body>
</html>