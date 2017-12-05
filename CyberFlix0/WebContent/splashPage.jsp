<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<%@page import="java.util.List, java.util.ArrayList, edu.txstate.internet.cyberflix.data.film.*, 
edu.txstate.internet.cyberflix.data.db.FilmDAO, java.sql.*, 
edu.txstate.internet.cyberflix.data.db.DAO "%>   
<!DOCTYPE html>
<html>
<head>
<title>Cyberflix</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="style.css">

<!-- W3 Schools CSS -->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Google Fonts CSS -->
<link href="https://fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">

</head>
<body>
  <div class="w3-container">
    <div class="w3-row w3-left w3-animate-opacity">
      <div class="w3-col s12" id="title">C Y B E R F L I X</div>
    </div>
    <div id="login-icon" class="w3-right">
      <a href="Login.html" title="login page"><i class="fa fa-user-circle-o" aria-hidden="true"></i></a>
    </div>
  </div>

    <!-- Content -->
<div id="content" class="w3-row">
<%
Connection conn = null;
conn = DAO.getDBConnection();
FilmDAO filmDAO = new FilmDAO();
List<Film> n = new ArrayList<Film>();
if (conn != null)
{	
  n = filmDAO.findNewestFilms(8);
}
for (int i = 0; i < n.size() - 4; i++)
{
int var = n.get(i).getFilmID();
%>
<div class="w3-col m3 w3-center w3-hover-opacity"><a href="http://localhost:8080/CyberFlix0/CyberFlixMovieDetailServlet?film_id=<%=var%>"><img style="width: 100%" src="http://localhost:8080/CyberFlix0/images/<%=i%>.jpg"></a></div>
<%} %>
</div>
<div id="content" class="w3-row">
<%
for (int i = 4; i < n.size(); i++)
{
int var = n.get(i).getFilmID();
%>
<div class="w3-col m3 w3-center w3-hover-opacity"><a href="http://localhost:8080/CyberFlix0/CyberFlixMovieDetailServlet?film_id=<%=var%>"><img style="width: 100%" src="http://localhost:8080/CyberFlix0/images/<%=i%>.jpg"></a></div>
<%} %>
</div>

    <!-- Find a film button -->
    <div class="w3-row w3-center">
      <div class="w3-col s1 m3 l4"><p></p></div>
      <div class="w3-col s10 m6 l4">
        <button id='search-button' onclick="document.getElementById('modal').style.display='block'" class="w3-button w3-round-large">Find a Film
        </button>
      </div>
      <div class="w3-col s1 m3 l4"><p></p></div>
    </div>

    <!-- The modal -->
    <div id="modal" class="w3-modal w3-display-middle">
      <div class="w3-modal-content w3-animate-opacity w3-round-large w3-display-middle">

        <header class="w3-container w3-black"> 
              <p><b>Search CyberFlix</b></p>
          </header>

        <div class="w3-container">
          <form action="http://localhost:8080/CyberFlix0/CyberFlixServlet" method="get">
              Film Title: <input type="text" id="title-input" name="film_title"><br>

			  
              Film Description: <input type="text" id="film-desc" name="film_description"><br>
              

              Film Rating: 
                <select name="ratings" id="select-button">
                <option value="1">G</option>
                <option value="2">PG</option>
                <option value="3">PG-13</option>
                <option value="4">R</option>
              </select>
              <br>

              Runtime:
              <input type="checkbox" name="run_time" value="30"> 30 
              <input type="checkbox" name="run_time" value="60"> 60 
              <input type="checkbox" name="run_time" value="90"> 90 
              <input type="checkbox" name="run_time" value="120"> 120 
              <input type="checkbox" name="run_time" value="150"> 150 
              <input type="checkbox" name="run_time" value="200"> 200+ 
              <input type="checkbox" name="run_time" value="1000"> any<br><br>

            <input class="w3-button w3-blue w3-round-large" type="submit" id="submit-button" value="Search">
            </form>
        </div>
      </div>
    </div>
  </div>
  <!-- End Content -->
</body>

<script>
// Get the modal
var modalVar = document.getElementById('modal');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modalVar) {
        modalVar.style.display = "none";
    }
}
</script>

</html>