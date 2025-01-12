package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

// This servlet fetches all the restaurant details and sets them to the session
// After that, control is passed to the home page (home.jsp)
@WebServlet("/homeServlet")
public class HomeServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Initialize DAO and fetch all restaurant details
		RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
		ArrayList<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
		
		// Set the fetched restaurant list to the session for further use in the JSP page
		HttpSession session = req.getSession();
		session.setAttribute("restaurantList", restaurantList);
		
		// Redirect the user to the home page (home.jsp)
		resp.sendRedirect("home.jsp");
	}
}
