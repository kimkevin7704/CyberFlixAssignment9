package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.db.DAO;
import edu.txstate.internet.cyberflix.data.db.FilmDAO;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.helper.FilmFactory;

/**
 * Servlet implementation class CyberFlixAddServlet
 */
@WebServlet("/CyberFlixAddServlet")
public class CyberFlixAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
			
			String addFilmID = request.getParameter("filmIDToAdd");
			try {
			Connection conn = DAO.getDBConnection();
			Statement statement = conn.createStatement();
			String statementString = "SELECT film.film_id, film.title, film.description, film.length, film.rating, film.release_year FROM film WHERE film.film_id = " + addFilmID;
			System.out.println("SQL STATEMENT" + statementString);
			ResultSet result = statement.executeQuery(statementString);
			int length, filmID;
			String title, description, releaseYear, rating;
			FilmRating rawRating;
			filmID = result.getInt("film.film_id");
			title = result.getString("film.title");
			description = result.getString("film.description");
			releaseYear = result.getString("film.release_year");
			length = result.getInt("film.length");
			rating = result.getString("film.rating");
			
			rawRating = FilmFactory.convert(rating);
			
			Film filmToAdd = new Film(filmID, title, description, releaseYear, length, rawRating);
			CyberFlixLoginServlet.cart.addToCart(filmToAdd);
		//	request.getRequestDispatcher("splashPage.jsp").forward(request, response);
			} catch (SQLException e) {

		}

			//go back to screen
			request.getRequestDispatcher("splashPage.jsp").forward(request, response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
