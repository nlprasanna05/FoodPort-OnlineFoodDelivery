<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
    <div class="form-container">
        <h1>Sign In</h1>
        <form action="loginServlet" method="post">
            <input type="email" id="email" name="email" placeholder="Email" required>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <button type="submit">Sign In</button>
        </form>
             <p class="register-prompt">Don't have an account? <a href="register.jsp">Sign Up</a></p>
    </div>
</body>
</html>
