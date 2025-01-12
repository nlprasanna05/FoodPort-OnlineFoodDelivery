<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Incorrect Password</title>
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

    .failure-container {
        background-color: rgba(0, 0, 0, 0.5); 
        padding: 20px;
        border-radius: 10px;
        text-align: center;
        color: white;
        width: 320px;
    }

    .failure-container h1 {
        margin-bottom: 20px;
    }

    .failure-container a {
        color: #28a745;
        text-decoration: none;
        font-weight: bold;
    }

    .failure-container a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
    <div class="failure-container">
        <h1>Incorrect Password</h1>
        <p>The passwords do not match. Please try again.</p>
        <p><a href="register.jsp">Go Back to Registration</a></p>
    </div>
</body>
</html>
