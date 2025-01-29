package com.tap.servlets;

import com.mysql.cj.Session;
import com.tap.daoImplementation.MenuDAOImplementation;
import com.tap.model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		int  restaurantId = 0;

		String restaurantIdParam = req.getParameter("restaurantId");
		

		if( restaurantIdParam.equals("null")) {
			
			HttpSession session = req.getSession();
			restaurantId = (Integer)session.getAttribute("restaurantId");
			
		}
		else {
			
			restaurantId = Integer.parseInt(restaurantIdParam);
		}



		if (restaurantIdParam != null) {
			try {

				req.setAttribute("restaurantId", restaurantId);

				MenuDAOImplementation menuDAO = new MenuDAOImplementation();
				List<Menu> menuList = menuDAO.getMenuListByRestaurantId(restaurantId);

				req.setAttribute("menuList", menuList);


				req.getRequestDispatcher("JspFiles/menu.jsp").forward(req, resp);
			} catch (NumberFormatException e) {

				

				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID.");
			}
		} 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

