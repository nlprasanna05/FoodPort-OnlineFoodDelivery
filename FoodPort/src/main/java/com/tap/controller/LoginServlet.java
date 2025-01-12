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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Retrieve email and password from the request parameters
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Create a UserDAO object to interact with the database
		UserDAO userDAO = new UserDAOImpl();
		
		// Retrieve the user object based on the provided email
		User user = userDAO.getUser(email);
		
		// If the user exists and the password matches, proceed to login
		if (user != null && password.equals(user.getPassword())) {
			// Create a session and set logged-in user information
			HttpSession session = req.getSession();
			session.setAttribute("loggedInUser", user);
			session.setAttribute("justLoggedIn", true);  // Flag to show welcome notification

			// Redirect to the home page after successful login
			resp.sendRedirect("homeServlet");

		} else {
			// Redirect to the sign-in failure page if login fails
			resp.sendRedirect("signInFailure.jsp");
		}	
	}
}
