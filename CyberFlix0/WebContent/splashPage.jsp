<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%@page import="java.util.List, java.util.ArrayList, edu.txstate.internet.cyberflix.data.film.*, 
edu.txstate.internet.cyberflix.data.db.FilmDAO, java.sql.*, 
edu.txstate.internet.cyberflix.data.db.DAO "%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SplashPage</title>
</head>

<body>

	<h1> Welcome to CyberFlix!</h1>
	<h2><a href="Login.html">Login</a></h2>
<p>
<p>


<%
    Connection conn = null;
    conn = DAO.getDBConnection();
    FilmDAO filmDAO = new FilmDAO();
	List<Film> n = new ArrayList<Film>();
	if (conn != null)
	{	
	  n = filmDAO.findNewestFilms(8);

	}
   for (int i = 0; i < n.size(); i++)
   {
	   
%>	<h1><b><%out.print(n.get(i).getTitle()); %></b></h1>   
	<div class="w3-container">
		<div class="w3-card-4" style="column-count: 2; column-gap: 40px">
 			<img src="http://localhost:8080/CyberFlix0/images/2.jpg" style="float:left; height: 300px; width: 200px"><br><br>
			<b>Release Year: </b><%out.print(n.get(i).getReleaseYear()); %><br><br>
			<b>Running Time: </b><%out.print(n.get(i).getLength()); %><br><br>
			<b>Rating: </b><%out.print(n.get(i).getRating()); %><br><br>
			<%out.print(n.get(i).getDescription()); %><br><br>
			<h4>Actors</h4><br>
			<%
			n.get(i).setActorsString(filmDAO.findActorsInFilm(n.get(i)));
			out.print(n.get(i).getActorsString());
			%>
		</div>
	</div>	   
	   
	   
	   
<%} %>	   



</body>
</html>