<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 16.02.2021
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/loginServlet" method="post">
    <label for="email"> email: </label><br>
    <input type="text" id="email" name="email"><br>
    <label for="password"> password</label><br>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>
