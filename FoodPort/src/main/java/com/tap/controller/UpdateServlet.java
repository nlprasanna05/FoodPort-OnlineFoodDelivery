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

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Fetch the session and the current user object
		HttpSession session = req.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");

		// Retrieve the updated values from the form
		String newUsername = req.getParameter("username");
		String newPassword = req.getParameter("password");
		String newAddress = req.getParameter("address");
		String newPhoneNumber = req.getParameter("phoneNumber");

		// If any value is left unchanged, keep the old value
		if (newUsername == null || newUsername.isEmpty()) {
			newUsername = loggedInUser.getUserName();
		}
		if (newPassword == null || newPassword.isEmpty()) {
			newPassword = loggedInUser.getPassword();
		}
		if (newAddress == null || newAddress.isEmpty()) {
			newAddress = loggedInUser.getAddress();
		}
		if (newPhoneNumber == null || newPhoneNumber.isEmpty()) {
			newPhoneNumber = loggedInUser.getPhoneNumber();
		}

		// Update the user object with the new values
		loggedInUser.setUserName(newUsername);
		loggedInUser.setPassword(newPassword);
		loggedInUser.setAddress(newAddress);
		loggedInUser.setPhoneNumber(newPhoneNumber);

		// Update the user in the database using the DAO
		UserDAO userDAO = new UserDAOImpl();
		int status = userDAO.updateUser(loggedInUser);

		// Check the update status and redirect accordingly
		if (status > 0) {
			// Update the session with the new user details
			session.setAttribute("loggedInUser", loggedInUser);
			resp.sendRedirect("homeServlet");
		} else {
			resp.sendRedirect("updateFailure.jsp");
		}
	}
}
