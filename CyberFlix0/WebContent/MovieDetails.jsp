<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${film.title} Details</title>
</head>
<body>
	<h1><b>${film.title}</b></h1>
	<div class="w3-container">
		<div class="w3-card-4" style="column-count: 2; column-gap: 40px">
 			<img src="http://localhost:8080/CyberFlix0/images/${film.posterImage}" alt="${films}" style="float:left; height: 300px; width: 200px"><br><br>
			<b>Release Year: </b>${film.releaseYear}<br><br>
			<b>Running Time: </b>${film.length}<br><br>
			<b>Rating: </b>${film.rating}<br><br>
			${film.description}<br><br>
			<h4>Actors</h4><br>
			${film.actorsString}
		</div>
	</div>
</body>
</html>