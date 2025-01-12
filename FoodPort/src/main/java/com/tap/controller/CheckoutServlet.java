package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.dao.OrderTableDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.daoimpl.OrderTableDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.OrderItem;
import com.tapfoods.model.OrderTable;
import com.tapfoods.model.User;

@WebServlet("/checkoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Get session and retrieve cart and logged-in user
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");
        
        // Get payment method and grand total from the request
        String paymentMethod = request.getParameter("paymentMethod");
        float grandTotal = Float.parseFloat(request.getParameter("grandTotal"));
        
        // Get restaurant ID from the first item in the cart (assuming all items are from the same restaurant)
        int restaurantId = cart.getItems().values().iterator().next().getRestaurantId();

        // Check if cart and user are valid
        if (cart != null && user != null) {
            try {
                // Initialize DAO objects to interact with the database
                OrderTableDAO orderTableDAO = new OrderTableDAOImpl();
                OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
                OrderHistoryDAO orderHistoryDAO = new OrderHistoryDAOImpl();

                // Create and set up a new OrderTable object to represent the order
                OrderTable orderTable = new OrderTable();
                orderTable.setRestaurantId(restaurantId);
                orderTable.setUserId(user.getUserId());
                orderTable.setTotalAmount(grandTotal); 
                orderTable.setStatus("Pending");
                orderTable.setPaymentMode(paymentMethod);
                
                // Add the order table to the database
                orderTableDAO.addOrder(orderTable);

                // Get the newly created order ID
                int orderId = orderTableDAO.getMaxOrderId();

                // Loop through each item in the cart and create an OrderItem entry in the database
                for (CartItem item : cart.getItems().values()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setMenuId(item.getItemId());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setSubTotal((float) item.getSubTotal());
                    orderItemDAO.addOrderItem(orderItem);
                }

                // Create and add an OrderHistory entry to mark the order as completed
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setOrderId(orderId);
                orderHistory.setUserId(user.getUserId());
                orderHistory.setTotalAmount(grandTotal); 
                orderHistory.setStatus("Completed");
                orderHistoryDAO.addOrderHistory(orderHistory);

                // Clear the cart from the session
                session.removeAttribute("cart");

                // Redirect to order confirmation page
                response.sendRedirect("orderConfirmation.jsp");

            } catch (Exception e) {
                // Print stack trace for debugging and redirect in case of an error
                e.printStackTrace();
                response.sendRedirect("checkout.jsp?error=Order failed. Please try again.");
            }
        } else {
            // Redirect if the cart is empty or user is not logged in
            response.sendRedirect("cart.jsp?error=Cart is empty or user not logged in.");
        }
    }
}
