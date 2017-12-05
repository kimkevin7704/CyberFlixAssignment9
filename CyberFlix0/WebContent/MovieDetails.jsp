<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%@page import="java.util.List, java.util.ArrayList, edu.txstate.internet.cyberflix.data.film.*, edu.txstate.internet.cyberflix.data.db.FilmDAO, java.sql.*, edu.txstate.internet.cyberflix.data.db.DAO "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cyberflix</title>

<!-- Custom Stylesheet -->
<link rel="stylesheet" href="Style/style.css">
<!-- W3 Schools CSS -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Google Fonts CSS -->
<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
</head>

<%
	Connection conn = null;
	conn = DAO.getDBConnection();
	FilmDAO filmDAO = new FilmDAO();
	film.getTitle(filmID);
%>

<body>

  <div id="titleposition" class="w3-display-topmiddle w3-animate-opacity">
    <div id="titlesmall"><a href="splashPage.jsp">C Y B E R F L I X</a></div>
  </div>
  
 <div id="cardcontainer" class="w3-container" style="margin-top: 5%; margin-bottom: 5%;">
<c:forEach var="film" items="${film}">
<div class="w3-row">
  <div id="movie-cards" class="w3-container w3-col s8" style="-webkit-margin-before: 3%;">
  <div class="w3-card-4">
    <div class="w3-container w3-light-grey">
      <h3 style="border-bottom-style:ridge;">${film.title}    <small><i>(${film.releaseYear}) </i></small></h3>
    </div>
    <div class="w3-container" style="background-color:white;">
   	  <p>Rating:<b> ${film.rating}</b></p>
      <p>${film.description}</p>
    </div>
      <a href=${detailServlet}${film.filmID}><button class="w3-button w3-block w3-dark-grey">Select</button></a>
    </div>
  </div>
      <div class="w3-col s4">
    	<a href=${detailServlet}${film.filmID}><img id="image" src="Images/movie3.jpg"></a>
    </div>
</div>
</c:forEach>
</div>
</body>
</html>