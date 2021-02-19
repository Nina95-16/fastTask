<%@ page import="com.example.fastTask.model.Task" %>
<%@ page import="java.util.List" %>
Created by IntelliJ IDEA.
User: Admin
Date: 16.02.2021
Time: 19:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserHome</title>
</head>
<body>
<% List<Task> myTasks = (List<Task>) request.getAttribute("myTasks");%>
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

</body>
</html>
