<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 15.04.2020
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="data:,">
</head>
<body>
<h1>Hi Admin!</h1>
<a href="/logout">logout</a>




<form action="/admin/allUsers">
    <button type="submit">
        look all users
    </button>
</form>
<form action="/admin/allTickets">
    <button type="submit">
        look all tickets
    </button>
</form>

</body>
</html>
