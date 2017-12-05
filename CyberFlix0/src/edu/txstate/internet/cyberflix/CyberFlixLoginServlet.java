package edu.txstate.internet.cyberflix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.txstate.internet.cyberflix.data.db.*;
import edu.txstate.internet.cyberflix.*;

/**
 * Servlet implementation class CyberFlixLoginServlet
 */
@WebServlet("/CyberFlixLoginServlet")
public class CyberFlixLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		PrintWriter writer = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
        Customer target; 
        target = CustomerDAO.findCustomerWithEmail(email);
        if (target.getEmail() != "nobody")
        {  
        	if (password.equals(target.getPassword()))
        	{
        	     writer.println("Login Success");
        	}
        	else
        	{
        		writer.println("Wrong Password");
        	}
        }
        else
        {
        	writer.println("Not in system.");
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
