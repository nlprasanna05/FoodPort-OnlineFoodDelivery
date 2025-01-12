<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.tapfoods.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <nav>
            <ul class="nav-links">
                <li><a href="homeServlet"><i class="fas fa-home"></i> Home</a></li>
                <li><a href="cartServlet"><i class="fas fa-shopping-cart"></i> View Cart</a></li>
                <li><a href="orderHistoryServlet"><i class="fas fa-history"></i> View Order History</a></li>
                <li><a href="updateProfile.jsp"><i class="fas fa-user"></i> Update Profile</a></li>
                <li><a href="logoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
    </header>
    <h1>Menu</h1>
    <%
        ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");
        if (menuList != null && !menuList.isEmpty()) {
            for (Menu menu : menuList) {
    %>
    <div class="menu-item">
        <img src="<%= menu.getImgPath() %>" alt="<%= menu.getMenuName() %> Image" class="menu-image">
        <div class="menu-details">
            <h3 class="menu-name"><%= menu.getMenuName() %></h3>
            <p class="menu-description"><%= menu.getDescription() %></p>
            <p class="menu-price">Price: ₹<%= menu.getPrice() %></p>
            <form action="cartServlet" method="post" class="quantity-form">
                <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
                <input type="hidden" name="action" value="add">
                <label for="quantity-<%= menu.getMenuId() %>">Quantity:</label>
                <input type="number" id="quantity-<%= menu.getMenuId() %>" name="quantity" value="1" min="1" class="quantity-input">
        </div>
        <div class="add-to-cart">
                <input type="submit" value="Add to Cart" class="add-to-cart-btn">
            </form>
        </div>
    </div>
    <%
            }
        } else {
    %>
    <p>No menu items available</p>
    <%
        }
    %>
    <form action="home.jsp" method="get">
        <input type="submit" value="Back to Restaurants" class="back-to-restaurant-btn">
    </form>
</body>
</html>
