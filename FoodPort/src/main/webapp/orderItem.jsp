<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tapfoods.model.OrderItem" %>
<html>
<head>
    <title>Order Details</title>
    <link rel="stylesheet" href="orderItem.css"> 
</head>
<body>
    <h1>Order Details</h1>

    <%
        List<OrderItem> orderList = (List<OrderItem>) session.getAttribute("orderList");
        if (orderList == null || orderList.isEmpty()) {
    %>
        <p>No details found for this order.</p>
    <%
        } else {
            for (OrderItem item : orderList) {
    %>
                <div class="order-item-box">
                    <h3>Item ID: <%= item.getMenuId() %></h3>
                    <p>Quantity: <%= item.getQuantity() %></p>
                    <p>SubTotal: $<%= item.getSubTotal() %></p>
                </div>
    <%
            }
        }
    %>

    <form action="orderHistoryServlet" method="get">
        <button type="submit" class="back-button">Back to Order History</button>
    </form>

</body>
</html>
