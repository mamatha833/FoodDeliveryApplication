package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.daoImplementation.RestaurantDAOImplementation;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		RestaurantDAOImplementation restaurant = new RestaurantDAOImplementation();
		List<Restaurant> allRestaurants = restaurant.getAllRestaurants();
		
		req.setAttribute("allRestaurants", allRestaurants);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("JspFiles/home.jsp");
		requestDispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
