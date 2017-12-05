<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Movie Search Results</title>
</head>
<body>
	<h1>Movies Matching Your Search</h1>
	<div class="w3-container">
		<c:forEach items="${films}" var="film">
			<div class="w3-card-4" style="column-count: 2; column-gap: 40px">
				<a href="${detailServlet}?film_id=${film.filmID}">
  					<img src="http://localhost:8080/CyberFlix0/images/3.jpg" style="float:left; height: 300px; width: 200px">
  				</a>
  				<div>
					<h3><b>${film.title}</b></h3><br>
					<b> Year: </b>${film.releaseYear} <br><br>
					<b> Rating: </b>${film.rating} <br><br>
					<b> Running Time: </b> ${film.length} minutes<br><br> 
					${film.description}

				</div>
			</div>
			<br><br>
		</c:forEach>
	</div>
</body>
</html>