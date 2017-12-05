package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.db.*;
import edu.txstate.internet.cyberflix.*;

/**
 * Servlet implementation class CyberFlixLoginServlet
 */
@WebServlet("/CyberFlixLoginServlet")
public class CyberFlixLoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static boolean isLogged = false;
	public static CartManager topCartManager;
	public static Cart cart;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CyberFlixLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		if(email.indexOf("@sakilacustomer.org") >= 0)
		{
		String password = request.getParameter("password");
        Customer target; 
        target = CustomerDAO.findCustomerWithEmail(email);
        if (target.getEmail() != "nobody")
        {  
        	if (password.equals(target.getPassword()))
        	{
        		isLogged = true;
        		HttpSession session = request.getSession();
        		cart = null;
        		try {
					if(topCartManager.carts.size() <= 0)
	        		{
	        			cart = new Cart(email, session);
	        			topCartManager.addCart(cart);
	        		}
	        		else
	        		{
	        			cart = topCartManager.getCart(session, isLogged);        			
	        		}
        		}catch (Exception e) {
        		}
        		session.setAttribute("cart", cart);
        		session.setAttribute("sessionID", session);
        		session.setAttribute("email", email);
        		request.getRequestDispatcher("splashPage.jsp").forward(request, response);
        	}
        	else
        	{
        		isLogged = false;
        		request.setAttribute("isLogged", false);
        		request.getRequestDispatcher("login.jsp").forward(request, response);
        		//send back to log in page
        	}
        }
        else
        {
        	isLogged = false;
        	request.setAttribute("isLogged", false);
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        	//send back to log in page
        }
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
