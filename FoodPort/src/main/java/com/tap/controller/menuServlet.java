package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Menu;

@WebServlet("/menuServlet")
public class menuServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Retrieve the restaurant ID from the request parameters
		int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
		
		// Create MenuDAO object to fetch menu items for the specified restaurant
		MenuDAO menuDAO = new MenuDAOImpl();
		
		// Get the list of menu items for the given restaurant ID
		ArrayList<Menu> menuList = menuDAO.getMenuOnRestaurantId(restaurantId);
		
		// Store the menu list in the session for further use in the JSP
		HttpSession session = req.getSession();
		session.setAttribute("menuList", menuList);
		
		// Redirect to the menu page to display the menu items
		resp.sendRedirect("menu.jsp");
	}
}
