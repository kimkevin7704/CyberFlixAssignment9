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
		FilmCatalog catalog    = FilmCatalog.getInstance();
		
		String      filmIDStr  = (String)request.getParameter("film_id");
		int         filmID     = Integer.valueOf(filmIDStr);
		Film        film       = catalog.get(filmID);
		
		Connection conn = null;
		conn = DAO.getDBConnection();
		FilmDAO filmDAO = new FilmDAO();
		Film detailFilm = null;

		detailFilm = filmDAO.getFilmDetail(film);
		
		film.setActorsString(film.getActors());
		request.setAttribute("film", detailFilm);
		

		request.getRequestDispatcher("MovieDetails.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
