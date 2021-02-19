package com.example.fastTask.servlet;


import com.example.fastTask.manager.UserManager;
import com.example.fastTask.model.User;
import com.example.fastTask.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(email, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (user.getUserType() == UserType.Manager) {
                resp.sendRedirect("/managerHome");
            } else {
                resp.sendRedirect("/userHome");
            }
        }
    }
}

