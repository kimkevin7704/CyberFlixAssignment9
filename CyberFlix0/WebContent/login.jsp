<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%@page import="java.util.List, java.util.ArrayList, edu.txstate.internet.cyberflix.data.film.*, 
edu.txstate.internet.cyberflix.data.db.*, java.sql.*, edu.txstate.internet.cyberflix.*"%>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Login to CyberFlix</title>
</head>
<body>
<%
if(CyberFlixLoginServlet.isLogged == false)
{
%>
<h1>Log in to CyberFlix!</h1>
<%
}
else
{
%>
<h1>Failed to log you in. Check email and password.</h1>
<%
}
%>

<form action="http://localhost:8080/CyberFlix0/CyberFlixLoginServlet" method="get">
  Email: <input type="text" name="email"><br>
  Password: <input type ="password" name= "password" size="20"><br>
  <input type="submit" value="Submit">
  </form>
</body>
</html>