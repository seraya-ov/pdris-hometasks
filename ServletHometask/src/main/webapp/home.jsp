<%@page import="objects.User" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home Page</title>
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Hello, <%=user.getUsername() %></h3>
<br>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
