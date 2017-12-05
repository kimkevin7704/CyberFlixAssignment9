package edu.txstate.internet.cyberflix;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;

import edu.txstate.internet.cyberflix.data.film.*;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.helper.FilmFactory;
import edu.txstate.internet.cyberflix.utils.HTMLTags;
import edu.txstate.internet.cyberflix.data.db.*;

/**
 * Servlet implementation class CyberFlixMovieDetailServlet
 */
@WebServlet("/CyberFlixMovieDetailServlet")
public class CyberFlixMovieDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixMovieDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DAO.getDBConnection();
			Statement statement = conn.createStatement();
			String      filmIDStr  = (String)request.getParameter("film_id");
			int         filmID     = Integer.valueOf(filmIDStr);
			String statementString = "SELECT film.film_id, film.title, film.description, film.length, film.rating, film.release_year FROM film WHERE film.film_id = " + filmIDStr;
			ResultSet result = statement.executeQuery(statementString);
			
			int length;
			String title, description, releaseYear, rating;
			FilmRating rawRating;
			
			filmID = result.getInt("film.film_id");
			title = result.getString("film.title");
			description = result.getString("film.description");
			releaseYear = result.getString("film.release_year");
			length = result.getInt("film.length");
			rating = result.getString("film.rating");
			rawRating = FilmFactory.convert(rating);
	
			Film film = new Film(filmID, title, description, releaseYear, length, rawRating);
			request.setAttribute("film", film);
			
			request.getRequestDispatcher("MovieDetails.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
