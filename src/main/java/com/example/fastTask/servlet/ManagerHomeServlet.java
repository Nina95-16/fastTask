package com.example.fastTask.servlet;


import com.example.fastTask.manager.TaskManager;
import com.example.fastTask.manager.UserManager;
import com.example.fastTask.model.Task;
import com.example.fastTask.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/managerHome")
public class ManagerHomeServlet extends HttpServlet {
    private UserManager userManager = new UserManager();
    private TaskManager taskManager = new TaskManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     User user = (User) req.getSession().getAttribute("user");
        List<Task> allTasksByUser = taskManager.getAllTasksByUser(user.getId());
        List<Task> allTasks = taskManager.getAllTasks();
        List<User> allUsers = userManager.getAllUsers();
        req.setAttribute("myTasks",allTasksByUser);
        req.setAttribute("alltasks",allTasks);
        req.setAttribute("allUsers",allUsers);
        req.getRequestDispatcher("/managerHome.jsp").forward(req,resp);
    }
}
