package com.tap.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.tap.dao.MenuDAO;
import com.tap.daoImplementation.MenuDAOImplementation;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String action = req.getParameter("action");
		
		if(action == null) {
			RequestDispatcher rd = req.getRequestDispatcher("JspFiles/cart.jsp");
			rd.forward(req, resp);
			return;
			
		}
		
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		Object currentRestaurantIdSession = session.getAttribute("restaurantId");

		Integer newRestaurantId = Integer.parseInt(req.getParameter("restaurantId"));

		if(currentRestaurantIdSession == null) {

			session.setAttribute("restaurantId", newRestaurantId);
		}


		int currentRestaurantId = (Integer)session.getAttribute("restaurantId");

		if(currentRestaurantId != newRestaurantId || cart == null) {
			cart = new Cart();
			
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
			
		}

		

		if(action.equals("add")) {
			
			addItemsToCart(req,cart);
			
		}
		else if(action.equals("update")) {

			updateCartItems(req,cart);

		}
		else if(action.equals("remove")) {

			removeItemFromCart(req,cart);

		}
		
		RequestDispatcher rd = req.getRequestDispatcher("JspFiles/cart.jsp");
		rd.forward(req, resp);


	}		

	
	
	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		
		cart.removeCartItem(menuId);
		
	}



	private void updateCartItems(HttpServletRequest req, Cart cart) {
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		cart.updateCartItem(menuId, quantity);
	}



	private void addItemsToCart(HttpServletRequest  req,Cart cart) {
	  
		int menuId = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOImplementation mdi = new MenuDAOImplementation();
		Menu menuItem = mdi.getMenu(menuId);
		
		if(menuItem != null) {
			
			CartItem cartItem = new CartItem(
					menuId, 
					menuItem.getRestaurantId(),
					menuItem.getItemName(),
					menuItem.getImagePath(), 
					menuItem.getPrice(), 
					quantity);
			
			cart.addCartItem(cartItem);
			
			
		}
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp); 
	}
	

}
