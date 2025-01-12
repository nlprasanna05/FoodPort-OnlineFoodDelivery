package com.tap.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.model.OrderItem;

@WebServlet("/orderItemServlet")
public class OrderItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Retrieve the session object
        HttpSession session = req.getSession();
        
        // Get the order ID from the request parameter
        int orderId = Integer.parseInt(req.getParameter("orderId"));

        // Check if the order ID is valid
        if (orderId > 0) {
            
            // Create an instance of OrderItemDAO to fetch the order items for the given order ID
            OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
            
            // Fetch the list of items for the order from the database
            List<OrderItem> orderList = orderItemDAO.getOrderItemByOrderItemId(orderId); 
            
            // Store the list of order items in the session to access it on the orderItem.jsp page
            session.setAttribute("orderList", orderList);
            
            // Redirect to the order item details page
            resp.sendRedirect("orderItem.jsp"); 
        } else {
            // If the order ID is invalid, redirect back to the order history page with an error message
            resp.sendRedirect("orderHistory.jsp?error=Invalid order ID."); 
        }
    }
}
