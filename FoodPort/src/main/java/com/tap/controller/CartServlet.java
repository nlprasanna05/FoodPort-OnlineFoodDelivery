package com.tap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.Menu;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Get the cart object from the session, or create a new one if it doesn't exist
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart(); // Create a new cart if none exists
			session.setAttribute("cart", cart);
		}
		
		// Get the action parameter from the request
		String action = req.getParameter("action");

		// Perform actions based on the 'action' parameter (add, update, remove)
		if ("add".equals(action)) {
			addItemToCart(req, cart); // Add item to the cart
		} else if ("update".equals(action)) {
			updateCartItem(req, cart); // Update item quantity in the cart
		} else if ("remove".equals(action)) {
			removeItemFromCart(req, cart); // Remove item from the cart
		}
		
		// Update the session with the modified cart and redirect to the cart page
		session.setAttribute("cart", cart);
		resp.sendRedirect("cart.jsp");
	}


	// Method to add an item to the cart
	private void addItemToCart(HttpServletRequest req, Cart cart) {
	    int itemId = Integer.parseInt(req.getParameter("itemId"));
	    int quantity = Integer.parseInt(req.getParameter("quantity"));
	    
	    // Fetch the menu item from the database using the item ID
	    MenuDAO menuDAO = new MenuDAOImpl();
	    Menu menuItem = menuDAO.getSpecificMenuItem(itemId); 
	    
	    // If the item is valid, create a CartItem and add it to the cart
	    if (menuItem != null) {
	        CartItem item = new CartItem(
	            menuItem.getMenuId(),
	            menuItem.getRestaurantId(),
	            menuItem.getMenuName(),
	            quantity,
	            menuItem.getPrice(),
	            quantity * menuItem.getPrice()
	        );
	        cart.addItem(item); // Add the item to the cart
	    }
	}

	// Method to update the quantity of an item in the cart
	private void updateCartItem(HttpServletRequest req, Cart cart) {
	    int itemId = Integer.parseInt(req.getParameter("itemId"));
	    int quantity = Integer.parseInt(req.getParameter("quantity"));

	    // If the item exists in the cart, update its quantity and recalculate the subtotal
	    if (cart.getItems().containsKey(itemId)) {
	        CartItem cartItem = cart.getItems().get(itemId);
	        cartItem.setQuantity(quantity); // Update the quantity
	        double newSubTotal = cartItem.getQuantity() * cartItem.getPrice(); // Recalculate subtotal
	        cartItem.setSubTotal(newSubTotal);
	    }
	}

	// Method to remove an item from the cart
	private void removeItemFromCart(HttpServletRequest req, Cart cart) {
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		cart.removeItem(itemId); // Remove the item from the cart
	}
}
