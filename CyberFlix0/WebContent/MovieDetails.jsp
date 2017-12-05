<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%@page import="java.util.List, java.util.ArrayList, edu.txstate.internet.cyberflix.data.film.*, 
edu.txstate.internet.cyberflix.data.db.FilmDAO, java.sql.*, 
edu.txstate.internet.cyberflix.data.db.DAO, edu.txstate.internet.cyberflix.data.actor.*"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- Custom Stylesheet -->
<link rel="stylesheet" href="Style/style.css">
<!-- W3 Schools CSS -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Google Fonts CSS -->
<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${film.title}</title>
</head>
<body>
  <div class="w3-display-topmiddle w3-animate-opacity">
    <div id="title"><a href="splashPage.jsp">C Y B E R F L I X</a></div>
  </div>
  
<div id="detailinfo" class="w3-display-topmiddle" style="margin-top:10%;">
    <div class="w3-card-4 w3-center" style="background-color:white">
    <h2 class="w3-container w3-black"><i>${film.title}</i></h2>
    <img class="w3-round" src="images/2.jpg">
    <hr>
    <p id="description" class="lead" style="margin:5%"]>${film.description}</p>
    <hr>
    <ul>
      <li class="w3-left-align"><b>Rating: </b>${film.rating}</li>
      <li class="w3-left-align"><b>Release Year: </b>${film.releaseYear}</li>
      <li class="w3-left-align"><b>Runtime: </b>${film.length} minutes</li>
      <li class="w3-left-align"><b>Cast: </b>${actors}</li>
    </ul>
  </div>
</div>

</body>
</html>