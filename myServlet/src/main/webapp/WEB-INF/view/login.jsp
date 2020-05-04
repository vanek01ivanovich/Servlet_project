<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 15.04.2020
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="data:,">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/login.css"%>
</style>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/login">Navbar</a>
        <a href="?lang=en">EN</a>
        <a href="?lang=ua">UA</a>
    </nav>

</header>
<div class="container card text-center ">

    <div class="card-header bg-warning">
        <h2>
            <fmt:message key="login.initial"/>
        </h2>
    </div>

    <div class="card-body">



        <form action="/login" method="post">
            <label>UserName:
                <input type="text" name="userName" placeholder="Username" class="form-control">
           </label>
            <br><br>
            <label >Password:
                <input type="password" class="form-control" placeholder="Password" name="password">
            </label>

            <br><br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
        </form>
        <form action="/registration">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Registration</button>
        </form>
    </div>
</div>
<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark">© 2020 Copyright:All rights reserved</div>
</footer>
</body>
</html>
