package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.db.CustomerDAO;
import edu.txstate.internet.cyberflix.data.db.DAO;

/**
 * Servlet implementation class CyberFlixRegServlet
 */
@WebServlet("/CyberFlixRegServlet")
public class CyberFlixRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixRegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		
		 Connection cn = null;
		 cn = DAO.getDBConnection();
		CustomerDAO.registerCustomer(first, last, email, password);
		writer.println("You have registered successfully!");
	   
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
