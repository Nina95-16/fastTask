package com.example.fastTask.servlet;


import com.example.fastTask.manager.TaskManager;
import com.example.fastTask.model.Task;
import com.example.fastTask.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userHome")
public class UserHomeServlet extends HttpServlet {
    TaskManager taskmanager = new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Task> allTasksByUser = taskmanager.getAllTasksByUser(user.getId());
        req.setAttribute("mytasks",allTasksByUser);
        req.getRequestDispatcher("/userHome.jsp").forward(req,resp);
    }
}
