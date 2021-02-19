<%@ page import="com.example.fastTask.model.Task" %>
<%@ page import="com.example.fastTask.model.User" %>
<%@ page import="java.util.List" %>
Created by IntelliJ IDEA.
  User: Admin
  Date: 16.02.2021
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager Home</title>
</head>
<body>
<% List<Task> myTasks = (List<Task>) request.getAttribute("myTasks");
    List<Task> allTasks = (List<Task>) request.getAttribute("allTasks");
    List<User> allUsers = (List<User>) request.getAttribute("allUsers");%>
<h3> My Tasks</h3>
<% if (myTasks != null && !myTasks.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>status</td>
        <td>createdDate</td>
        <td>deadline</td>
    </tr>
    </thead>
    <% for (Task myTask : myTasks) { %>
    <tr>
        <td><%= myTask.getId()%>
        </td>
        <td><%= myTask.getTitle()%>
        </td>
        <td><%= myTask.getDescription()%>
        </td>
        <td><%= myTask.getTaskStatus()%>
        </td>
        <td><%= myTask.getCreatedDate()%>
        </td>
        <td><%= myTask.getDeadline()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
<hr>
<h3> All Tasks</h3>
<% if (allTasks != null && !allTasks.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>status</td>
        <td>createdDate</td>
        <td>deadline</td>
        <td>User name/surname</td>
    </tr>
    </thead>
        <% for (Task task : allTasks) { %>
    <tr>
        <td><%= task.getId()%>
        </td>
        <td><%= task.getTitle()%>
        </td>
        <td><%= task.getDescription()%>
        </td>
        <td><%= task.getTaskStatus()%>
        </td>
        <td><%= task.getCreatedDate()%>
        </td>
        <td><%= task.getDeadline()%>
        </td>
        <td><%= task.getUser().getName()%> <%= task.getUser().getSurname()%>
        </td>
    </tr>
        <%
            }
        }
    %>
    <hr>
    <h3> Users</h3>
        <% if (allUsers != null && !allUsers.isEmpty()) {
%>
    <table>
        <thead>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>surname</td>
            <td>email</td>
        </tr>
        </thead>
        <% for (User users : allUsers) { %>
        <tr>
            <td><%= users.getId()%>
            </td>
            <td><%= users.getName()%>
            </td>
            <td><%= users.getSurname()%>
            </td>
            <td><%= users.getEmail()%>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
