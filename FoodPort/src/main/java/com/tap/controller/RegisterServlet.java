package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get the registration details from the request parameters
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String phoneno = req.getParameter("phoneno");
		String password = req.getParameter("password");
		String confirmpassword = req.getParameter("confirmpassword");
		String address = req.getParameter("address");

		// Check if password and confirm password match
		if (password.equals(confirmpassword)) {
			// Create a new User object with the registration details
			User user = new User(username, email, phoneno, password, address);
			
			// Initialize the UserDAO to add the user to the database
			UserDAO userDAO = new UserDAOImpl();
			int status = userDAO.addUser(user);

			// Redirect based on the result of adding the user
			if (status != 0) {
				resp.sendRedirect("registerSuccess.jsp"); // Registration successful
			} else {
				resp.sendRedirect("registerFailure.jsp"); // Registration failed
			}
		} else {
			// If password and confirm password do not match, redirect to incorrectPassword page
			resp.sendRedirect("incorrectPassword.jsp");
		}	
	}
}
