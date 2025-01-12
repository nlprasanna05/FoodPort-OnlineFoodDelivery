<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.tapfoods.model.Cart, com.tapfoods.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
<link rel="stylesheet" type="text/css" href="checkout.css">
</head>
<body>
    <div class="checkout-container">
        <h2>Checkout</h2>
        <form action="checkoutServlet" method="post">
            <table class="cart-summary">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    Cart cart = (Cart) session.getAttribute("cart");
                    float grandTotal = 0;
                    if (cart != null && !cart.getItems().isEmpty()) {
                        for (CartItem item : cart.getItems().values()) {
                            grandTotal += item.getSubTotal();
                    %>
                    <tr>
                        <td><%= item.getName() %></td>
                        <td>$<%= item.getPrice() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>$<%= item.getSubTotal() %></td>
                    </tr>
                    <%
                        }
                    }
                    %>
                    <tr class="grand-total">
                        <td colspan="3">Grand Total:</td>
                        <td>$<%= grandTotal %></td>
                    </tr>
                </tbody>
            </table>
            <div class="form-group">
                <label for="address">Delivery Address:</label>
                <textarea rows="4" id="address" name="address" required></textarea>
            </div>
            <div class="form-group">
                <label for="paymentMethod">Payment Method:</label>
                <select id="paymentMethod" name="paymentMethod" required>
                    <option value="CreditCard">Credit Card</option>
                    <option value="DebitCard">Debit Card</option>
                    <option value="UPI">UPI</option>
                    <option value="CashOnDelivery">Cash On Delivery</option>
                </select>
            </div>
            <input type="hidden" name="grandTotal" value="<%= grandTotal %>">
            <button type="submit" class="confirm-btn">Confirm Order</button>
        </form>
    </div>
</body>
</html>
