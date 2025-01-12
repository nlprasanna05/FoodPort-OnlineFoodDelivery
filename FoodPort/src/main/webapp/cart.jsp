<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.tapfoods.model.Cart, com.tapfoods.model.CartItem" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" type="text/css" href="cart.css">
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
    <h1>Cart Items</h1>
    <div class="cart-items">
        <% 
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) {
        %>
        <div class="cart-item">
            <p class="item-name">Name: <%= item.getName() %></p>
            <p class="item-price">Price: <%= item.getPrice() %></p>
            <form action="cartServlet" method="post">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <label>Quantity: <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1"></label>
                <p class="item-subtotal">Subtotal: <%= item.getSubTotal() %></p>
                <input type="submit" name="action" value="update" class="update-btn">
                <input type="submit" name="action" value="remove" class="remove-btn">
            </form>
        </div>
        <% 
            }
        } else {
        %>
        <p>Cart is empty</p>
        <% } %>
    </div>

 <div id="add-proceed" >
 <button id="ap-b1"><a href="menu.jsp">Add More Items</a></button>
 <button id="ap-b2"><a href="checkout.jsp">Proceed to pay</a></button>
 
 </div>


</body>
<style>
#add-proceed{

align-items:center;
justify-content: center;
margin:50px;
}
#ap-b1{

position:relative;
left:35%;
top:50%;
margin:30px;
padding:8px;
background-color:#fc8019;
border: 2px solid #fc8019;
border-radius:5px;
}
#ap-b2{
position:relative;
left:35%;
top:50%;
margin:30px;
padding:8px;
text-decoration:none;
background-color:#fc8019;
border: 2px solid #fc8019;
border-radius:5px;
}
#ap-b2 a,#ap-b1 a{
text-decoration:none;

color:white;
font-size:16px;
}
</style>

</html>