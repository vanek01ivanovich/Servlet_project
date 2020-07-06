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
    <title>title.login</title>
    <link rel="icon" href="data:,">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>
<style>
    <%@include file="/WEB-INF/css/login.css"%>

</style>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/login">Navbar</a>

        <div class="navigationBar">
            <div class="dropdown">
                <button class="btn btn-outline-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="languages"/>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="?lang=en"><fmt:message key="english"/></a>
                    <a class="dropdown-item" href="?lang=ua"><fmt:message key="ukrainian"/></a>
                </div>
            </div>
        </div>
    </nav>

</header>

<div class="container card text-center ">
    <c:if test="${errorLogin==true}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="error"/>
        </div>
    </c:if>
    <c:if test="${logoutMessage==true}">
        <div class="alert alert-info" role="alert">
            <fmt:message key="logout"/>
        </div>
    </c:if>
    <div class="card-header bg-warning">
        <h2>
            <fmt:message key="login.initial"/>
        </h2>
    </div>

    <div class="card-body">



        <form action="/login" method="post">
            <label>
                <fmt:message key="user.name"/>
                <input type="text" name="userName" placeholder="Username" class="form-control">
           </label>
            <br><br>
            <label >
                <fmt:message key="password"/>
                <input type="password" class="form-control" placeholder="Password" name="password">
            </label>

            <br><br>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login"/></button>
        </form>
        <form action="/registration">
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="registration"/></button>
        </form>
    </div>
</div>
<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>
</body>
</html>
