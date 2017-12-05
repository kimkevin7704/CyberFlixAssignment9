<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="java.util.List, edu.txstate.internet.cyberflix.*, edu.txstate.internet.cyberflix.data.*"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cart Checkout</title>
</head>
<body>
	<h1><b>What's in your cart?</b></h1>
	<div class="w3-container">
		<%
			for(int i = 0; i < CyberFlixLoginServlet.cart.getTotalItems(); i++)
			{
		%>
			<div class="w3-card-4" style="column-count: 2; column-gap: 40px; background: white">
				<a href="${detailServlet}?film_id=${CyberFlixLoginServlet.cart.getFilmByIndex(i).getFilmID()}">
  					<img src="http://localhost:8080/CyberFlix0/images/<%=i%>.jpg" style="float:left; height: 300px; width: 200px">
  				</a>
  				<div id="card-info" style="margin-left:30%">
					<h3><b>${CyberFlixLoginServlet.cart.getFilmByIndex(i).getTitle()}</b></h3><br>
					<b> Year: </b>${CyberFlixLoginServlet.cart.getFilmByIndex(i).getReleaseYear()} <br><br>
					<b> Rating: </b>${CyberFlixLoginServlet.cart.getFilmByIndex(i).getRating().toString()} <br><br>
					<b> Running Time: </b> ${CyberFlixLoginServlet.cart.getFilmByIndex(i).getLength()} minutes<br><br> 
					${CyberFlixLoginServlet.cart.getFilmByIndex(i).getDescription()}
				</div>
			</div>
			<br><br>
		<% 
			
			}
		%>
	</div>
</body>
</html>