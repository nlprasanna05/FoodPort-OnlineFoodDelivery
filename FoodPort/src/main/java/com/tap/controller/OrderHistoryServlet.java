package com.tap.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.User;

@WebServlet("/orderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Retrieve the session and get the logged-in user from the session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedInUser");

        // Check if the user is logged in
        if (user != null) {
            int userId = user.getUserId();
            
            // Create an instance of OrderHistoryDAO to fetch the user's order history
            OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();
            
            // Get the order history for the logged-in user
            List<OrderHistory> historyList = orderHistoryDAO.getOrderHistoryByUserId(userId);
            
            // Store the order history list in the session
            session.setAttribute("historyList", historyList);
            
            // Redirect to the order history page to display the user's order history
            resp.sendRedirect("orderHistory.jsp");
        } 
        else {
            // If the user is not logged in, redirect to the login page with an error message
            resp.sendRedirect("login.jsp?error=Please log in to view your order history.");
        }
    }
}
