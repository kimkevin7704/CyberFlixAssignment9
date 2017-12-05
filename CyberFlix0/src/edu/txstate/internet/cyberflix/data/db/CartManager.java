package edu.txstate.internet.cyberflix.data.db;

import java.util.List;
import edu.txstate.internet.cyberflix.*;

import javax.servlet.http.HttpSession;

public class CartManager {
	
	public List<Cart> carts;
	
	public boolean isEmpty()
	{
		boolean empty;
		if(this.carts.size() <= 0)
			empty = true;
		else
			empty = false;
		return empty;
	}
	
	public void addCart(Cart cart)
	{
		carts.add(cart);
	}
	
	public List<Cart> getCarts()
	{
		return carts;
	}
	
	public Cart getCart(String sessionID)
	{
		for(int i = 0; i < carts.size(); i++)
		{
			if(carts.get(i).getSessionID() == sessionID)
			{
				return carts.get(i);
			}
		}
		return null;
	}
	
	public Cart getCart(HttpSession session, boolean isLogged) { 
		
		Cart cart = null;
		if(isLogged)
		{
			if(!session.isNew())
			{
				for(int i = 0; i < carts.size(); i++)
				{
					if(session.getId() == carts.get(i).getSessionID())
					{
						cart = carts.get(i);
					}
				}
			}
			
			if(session.isNew())
			{
				cart = new Cart(session);
				carts.add(cart);
			}
		}
		if(cart == null)
			return null;
		else
			return cart;
	}
}
