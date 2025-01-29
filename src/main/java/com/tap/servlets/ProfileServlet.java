package com.tap.servlets;

import java.io.IOException;

import org.apache.catalina.startup.UserDatabase;

import com.tap.daoImplementation.UserDAOImplementation;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet	("/profile")
public class ProfileServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String currentPassword = req.getParameter("current-password");
		String newPassword = req.getParameter("new-password");
		
		UserDAOImplementation udi = new UserDAOImplementation();
		User user = udi.getUser(userId);
		
		if(currentPassword.equals(user.getPassword())) {
			
			user.setName(name);
			user.setPhone(phone);
			user.setAddress(address);
			user.setPassword(newPassword);	
			udi.updateUser(user);
			req.setAttribute("message", "Details Updated Successfully!!");
			req.getRequestDispatcher("JspFiles/profile.jsp").forward(req, resp);
			
			
		}
		else {
			req.setAttribute("error", "Current Password is Inocrrect!!");
			req.getRequestDispatcher("JspFiles/profile.jsp").forward(req, resp);
			
		}
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
