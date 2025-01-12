<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
    import="java.util.ArrayList ,com.tapfoods.model.User, com.tapfoods.model.Restaurant"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="home1.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <nav>
            <ul class="nav-links">
                <!-- Navigation links for home, cart, order history, profile update, and logout -->
                <li><a href="homeServlet"><i class="fas fa-home"></i> Home</a></li>
                <li><a href="cartServlet"><i class="fas fa-shopping-cart"></i> View Cart</a></li>
                <li><a href="orderHistoryServlet"><i class="fas fa-history"></i> View Order History</a></li>
                <li><a href="updateProfile.jsp"><i class="fas fa-user"></i> Update Profile</a></li>
                <li><a href="logoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
            </ul>
        </nav>
    </header>

    <%
        // Get the logged-in user and check if it's the first login for a welcome message
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Boolean justLoggedIn = (Boolean) session.getAttribute("justLoggedIn");
        
        if (loggedInUser != null) {
            // If the user just logged in, show the welcome notification
            if (justLoggedIn != null && justLoggedIn) {
            %>
                <div id="welcome-notification" class="notification">
                    Welcome,
                    <%= loggedInUser.getUserName() %>!
                </div>
            <%
                session.removeAttribute("justLoggedIn"); // Remove justLoggedIn flag after showing the message
            }
        } else {
            // If no user is logged in, redirect to the login page
            response.sendRedirect("login.jsp");
        }
    %>
    
    <h1>Available Restaurants</h1>

    <section>
        <%
            // Fetch the restaurant list from the session and display it
            ArrayList<Restaurant> restaurantList = (ArrayList<Restaurant>) session.getAttribute("restaurantList");
            
            if (restaurantList != null) {
                // Loop through each restaurant and display its details
                for (Restaurant restaurant : restaurantList) { %>
        <div class="restaurant">
            <img src="<%= restaurant.getImgPath() %>" alt="Image of <%= restaurant.getRestaurantName() %>">
            <h3><%= restaurant.getRestaurantName() %></h3>
            <p><%= restaurant.getCuisineType() %> - <%= restaurant.getDeliveryTime() %> mins</p>
            <!-- Link to view the menu of the restaurant -->
            <a href="menuServlet?restaurantId=<%= restaurant.getRestaurantId() %>">View Menu</a>
        </div>
        <%
                }
            } else { %>
        <!-- If no restaurants are available, display a message -->
        <p>No Restaurants available</p>
        <%
            }
        %>
    </section>

<script>
    window.onload = function () {
        // Get the notification element and style it if it exists
        const notification = document.getElementById('welcome-notification');

        if (notification) {
            // Style the notification like a dialog box
            notification.style.position = 'fixed';
            notification.style.top = '20px';
            notification.style.right = '10px';
            notification.style.padding = '20px';
            notification.style.backgroundColor = 'white';
            notification.style.color = '#ff4f00';
            notification.style.borderRadius = '8px';
            notification.style.boxShadow = '0 2px 10px rgba(0, 0, 0, 0.2)';
            notification.style.zIndex = '2000';
            notification.style.height='20px';
            notification.style.width='250px';
            notification.style.display = 'flex'; 
            notification.style.alignItems = 'center'; 
            notification.style.justifyContent = 'center'; 
            notification.style.textAlign = 'center';
            
            // Animation for showing the notification
            notification.style.opacity = '0';
            notification.style.transition = 'opacity 0.5s';
            setTimeout(() => {
                notification.style.opacity = '1';
            }, 100);

            // Hide the notification after 10 seconds
            setTimeout(() => {
                notification.style.transition = 'opacity 0.5s';
                notification.style.opacity = '0';
                setTimeout(() => {
                    notification.style.display = 'none';
                }, 500); 
            }, 10000);
        }
    };
</script>

</body>
</html>
