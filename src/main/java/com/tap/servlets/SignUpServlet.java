package com.tap.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tap.daoImplementation.UserDAOImplementation;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signUpServlet")
public class SignUpServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String address = request.getParameter("address");

		UserDAOImplementation udi = new UserDAOImplementation();
		User existingUser = udi.getUserByEmail(email);

		try {
			if (existingUser != null) {
				request.setAttribute("error", "User already exists with this email.");
				request.getRequestDispatcher("JspFiles/signUp.jsp").forward(request, response);

			}
			else {

				User user = new User(0, name, username, password, email, phone, address, null, null, null);
				udi.addUser(user);
				request.setAttribute("message", "Registration Sucessfull!!");
				request.getRequestDispatcher("JspFiles/signIn.jsp").forward(request, response);
      			
			}
		}
		
		catch (ServletException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
