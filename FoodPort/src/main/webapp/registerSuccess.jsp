<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Success</title>
<style>
    body, html {
        margin: 0;
        padding: 0;
        height: 100%;
        font-family: Arial, sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        background: url('https://wallpapercave.com/wp/wp7603919.jpg') no-repeat center center fixed;
        background-size: cover;
    }

    .success-container {
        background-color: rgba(0, 0, 0, 0.5);
        padding: 20px;
        border-radius: 10px;
        text-align: center;
        color: white;
        width: 320px;
    }

    .success-container h1 {
        margin-bottom: 20px;
    }

    .success-container a {
        color: #28a745;
        text-decoration: none;
        font-weight: bold;
    }

    .success-container a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="success-container">
        <h1>Registration Successful!</h1>
        <p>You have successfully registered.</p>
        <p><a href="homeServlet">Let's Explore</a></p>
    </div>
</body>
</html>
