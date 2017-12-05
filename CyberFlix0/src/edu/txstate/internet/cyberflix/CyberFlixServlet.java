package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import edu.txstate.internet.cyberflix.data.DataSource;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.utils.ServletUtils;
import edu.txstate.internet.cyberflix.utils.HTMLTags;
import edu.txstate.internet.cyberflix.data.db.*;


/**
 * Servlet implementation class CyberFlixServlet
 */
@WebServlet("/CyberFlixServlet")
public class CyberFlixServlet extends HttpServlet {
	
	public static Connection conn;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CyberFlixServlet() {
		super();

	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletUtils.setAbsolutePath(config);
		
	    conn = null;
	    conn = DAO.getDBConnection();
		FilmDAO filmDAO = new FilmDAO();
		List<Film> n = new ArrayList<Film>();
		n = filmDAO.findNewestFilms(5);
        System.out.print(n.get(0));

		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	    conn = null;
	    conn = DAO.getDBConnection();
		FilmDAO filmDAO = new FilmDAO();
		List<Film> n = new ArrayList<Film>();
		n = filmDAO.findNewestFilms(5);
		PrintWriter writer = response.getWriter();
		
		writer.println(n);
		
		
	}

	private String createLink(String target, String text) {
		final String ANCHOR_TAG_START = "<a href=" + "\"";
		final String ANCHOR_TAG_CLOSE = "\"" + ">";
		final String ANCHOR_TAG_END = "</a>";

		StringBuilder stringBuilder = new StringBuilder(ANCHOR_TAG_START);
		stringBuilder.append(target);
		stringBuilder.append(ANCHOR_TAG_CLOSE);
		stringBuilder.append(text);
		stringBuilder.append(ANCHOR_TAG_END);
		return stringBuilder.toString();
	}
	
}
