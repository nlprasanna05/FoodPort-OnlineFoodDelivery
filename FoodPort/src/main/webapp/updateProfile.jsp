<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tapfoods.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
        <link rel="stylesheet" href="updateProfile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
    <h1>Update Your Profile</h1>

    <%
        User loggedInUser = (User) session.getAttribute("loggedInUser");
    %>

    <form action="updateServlet" method="post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username" value="<%= loggedInUser.getUserName() %>"><br><br>

        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" value="<%= loggedInUser.getPassword() %>"><br><br>

        <label for="address">Address:</label><br>
        <input type="text" id="address" name="address" value="<%= loggedInUser.getAddress() %>"><br><br>

        <label for="phoneNumber">Phone Number:</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber" value="<%= loggedInUser.getPhoneNumber() %>"><br><br>

        <input type="submit" value="Update Profile">
    </form>
</body>
</html>
