package edu.txstate.internet.cyberflix.data.db;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.CyberFlixLoginServlet;
import edu.txstate.internet.cyberflix.data.db.*;

public class Cart extends CartManager{
	
	private String user;
	private int totalItems;
	private String sessionID;
	private List<Film> filmsInCart;
	private String userEmail;
	
	public Cart(String currentUser)
	{
		filmsInCart = null;
		user = currentUser;
	}
	
	public Cart(String currentUser, HttpSession currentSession)
	{
		filmsInCart = null;
		user = currentUser;
		sessionID = currentSession.getId();
	}
	
	public Cart(HttpSession currentSession)
	{
		filmsInCart = null;
		sessionID = currentSession.getId();
	}
	
	public void addToCart(Film film)
	{
		filmsInCart.add(film);
	}
	
	public String getUser()
	{
		return user;
	}
	
	public String getUserEmail()
	{
		return userEmail;
	}
	
	public void setUserEmail(String email)
	{
		userEmail = email;
	}
	
	public Cart getCartWithSession(HttpSession currentSession, CartManager cartManager)
	{
		Cart cart = null;
		for(int i = 0; i < cartManager.carts.size(); i++)
		{
			if(cartManager.carts.get(i).getSessionID() == currentSession.getId())
			{
				cart = cartManager.carts.get(i);
			}
		}
		return cart;
	}
	
	public int getTotalItems()
	{
		return totalItems;
	}
	
	public List<Film> getCheckout()
	{
		return filmsInCart;
	}
	
	public String getSessionID()
	{
		return sessionID;
	}
	
	public Film getFilmByIndex(int i)
	{
		Film film = filmsInCart.get(i);
		return film;
	}
}
