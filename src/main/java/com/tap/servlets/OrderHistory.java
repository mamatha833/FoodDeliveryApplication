package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import com.tap.daoImplementation.OrderDAOImplementation;
import com.tap.model.Order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderHistory")
public class OrderHistory extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	   OrderDAOImplementation odi = new OrderDAOImplementation();
	   
	   HttpSession session = req.getSession();
	   int userId = (Integer)session.getAttribute("userId");
	   List<Order> orders = odi.getOrderByUserId(userId);
	   
	   
	   req.setAttribute("orders", orders);
	  
	   RequestDispatcher requestDispatcher = req.getRequestDispatcher("JspFiles/orderHistory.jsp");
	   requestDispatcher.forward(req, resp);
	   
	   
	
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

}
