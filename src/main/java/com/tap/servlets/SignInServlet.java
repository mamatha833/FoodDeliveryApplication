package com.tap.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import com.tap.daoImplementation.UserDAOImplementation;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signInServlet")
public class SignInServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDAOImplementation udi = new UserDAOImplementation();
		User user = udi.getUserByEmail(email);

		if(user != null) {

			if(user.getPassword().equals(password)) {

				LocalDateTime now = LocalDateTime.now();
				
				user.setLastLoginDate(now);

				udi.updateLastLoginDate(user.getUserId(), now);

				HttpSession session = req.getSession();
				session.setAttribute("userId", user.getUserId());

				RequestDispatcher requestDispatcher = req.getRequestDispatcher("home");
				requestDispatcher.forward(req, resp);
			}
			else {
				req.setAttribute("error", "Invalid password");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("JspFiles/signIn.jsp");
				requestDispatcher.forward(req, resp);

			}
		}
		else {
			req.setAttribute("error", "User not found");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("JspFiles/signIn.jsp");
			requestDispatcher.forward(req, resp);
		}

	}

}
