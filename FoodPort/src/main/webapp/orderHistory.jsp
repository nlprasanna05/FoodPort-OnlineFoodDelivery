<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tapfoods.model.OrderHistory" %>
<html>
<head>
    <title>Order History</title>
    <link rel="stylesheet" href="orderHistory1.css">
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
    <h1>Your Order History</h1>
    
    <%
        List<OrderHistory> historyList = (List<OrderHistory>) session.getAttribute("historyList");
        if (historyList == null || historyList.isEmpty()) {
    %>
        <p>You have no order history.</p>
    <%
        } else {
            for (OrderHistory order : historyList) {
    %>
                <div class="order-box">
                    <h3>Order ID: <%= order.getOrderId() %></h3>
                    <p>Order Date: <%= order.getOrderDate() %></p>
                    <p>Total Amount: $<%= order.getTotalAmount() %></p>
                    <p>Status: <%= order.getStatus() %></p>
                    <form action="orderItemServlet" method="get">
                        <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                        <button type="submit">View Details</button>
                    </form>
                </div>
    <%
            }
        }
    %>
</body>
</html>