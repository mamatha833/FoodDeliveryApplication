package com.tap.servlets;

import java.io.IOException;

import com.tap.dao.OrderDAO;
import com.tap.dao.OrderItemDAO;
import com.tap.daoImplementation.OrderDAOImplementation;
import com.tap.daoImplementation.OrderItemDAOImplementation;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckOutServlet extends HttpServlet {


	private OrderDAO orderDAOImplementation;
	private OrderItemDAO orderItemDAOImplementation; 
	@Override
	public void init(){

		orderDAOImplementation = new OrderDAOImplementation();
		orderItemDAOImplementation = new OrderItemDAOImplementation();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		Integer userId = (Integer)session.getAttribute("userId");

		if(cart != null && userId != null && !cart.getItems().isEmpty()) {

			int restaurantId = (Integer)session.getAttribute("restaurantId");
			
			String paymentMode = req.getParameter("payment");
			String phone = req.getParameter("phone");
			
			String address = req.getParameter("street")+", ";
			address += req.getParameter("city")+", ";
			address += req.getParameter("state")+", ";
			address += req.getParameter("zip");
			



			Order order = new Order();

			order.setUserId(userId);
			order.setRestaurantId(restaurantId);
			order.setTotalAmount(cart.getSubTotalPrice()+5);
			order.setPaymentMode(paymentMode);
			order.setPhone(phone);
			order.setAddress(address);


			//inserting order

			int orderId = orderDAOImplementation.addOrder(order);
			order = orderDAOImplementation.getOrder(orderId);



			if(orderId != -1) {

				for(CartItem cartItem : cart.getItems().values()) {

					OrderItem orderItem = new OrderItem();
					orderItem.setOrderId(orderId);
					orderItem.setMenuId(cartItem.getMenuId());
					orderItem.setQuantity(cartItem.getQuantity());
					orderItem.setTotalPrice(cartItem.getQuantity()*cartItem.getPrice());

					//inserting orderitem

					orderItemDAOImplementation.addOrderItem(orderItem);

				}
				session.removeAttribute("cart");
				session.setAttribute("order", order);


				resp.sendRedirect("JspFiles/confirmation.jsp");

			}




		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
