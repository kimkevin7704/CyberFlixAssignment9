package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.db.FilmDAO;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;

/**
 * Servlet implementation class CyberFlixAlphaServlet
 */
@WebServlet("/CyberFlixAlphaServlet")
public class CyberFlixAlphaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixAlphaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

		FilmDAO filmDAO = new FilmDAO();
		List<Film> found = new ArrayList<Film>();
			
			request.setAttribute("detailServlet", "http://localhost:8080/CyberFlix0/CyberFlixMovieDetailServlet");
			String charIndex = request.getParameter("letter");
			String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			int index = Integer.valueOf(charIndex);
			found = filmDAO.findFilmsAlphabetically(String.valueOf(alphabet.charAt(index)));
			request.setAttribute("films", found);
			request.getRequestDispatcher("MovieSearchResults.jsp").forward(request, response);			
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
