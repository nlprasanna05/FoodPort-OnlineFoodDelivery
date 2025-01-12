package com.tap.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Get the current session (if it exists) and invalidate it to log the user out
        HttpSession session = req.getSession(false); // false indicates not to create a new session if one doesn't exist
        if (session != null) {
            session.invalidate(); // Invalidate the session to clear session attributes
        }
        
        // Redirect to the home page after logging out
        resp.sendRedirect("home.jsp");
    }
}
