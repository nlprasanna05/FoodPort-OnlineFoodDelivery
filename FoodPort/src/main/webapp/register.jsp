<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Here</title>
<link rel="stylesheet" type="text/css" href="register.css?v=1.0">
</head>
<body>
    <div class="form-container">
        <h1>Sign Up</h1>
        <form action="registerServlet" method="post">
            <input type="text" id="username" name="username" placeholder="Username" required>
            <input type="email" id="email" name="email" placeholder="Email" required>
            <input type="tel" id="phoneno" name="phoneno" placeholder="Phone Number" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm Password" required>
            <input type="text" id="address" name="address" placeholder="Address" required>
            <button type="submit">Sign Up</button>
        </form>
        <p class="login-prompt">Already have an account? <a href="login.jsp">Sign In</a></p>
    </div>
</body>
</html>
