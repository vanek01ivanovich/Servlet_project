<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 22.04.2020
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/findRoute.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/user">HOME</a>

        <div class="userInfo">
            <fmt:message key="user.hi"/>
            ${user.getUserName()}
            <br>
            Your money
            ${user.getMoney()}
        </div>
        <form action="/logout">
            <button id="logout" type="submit" class="btn btn-outline-warning"><fmt:message key="logout"/></button>
        </form>
        <div class="dropdown">
            <button class="btn btn-outline-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <fmt:message key="languages"/>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="?lang=en"><fmt:message key="english"/></a>
                <a class="dropdown-item" href="?lang=ua"><fmt:message key="ukr.last.name"/></a>
            </div>
        </div>
    </nav>
</header>


<div class="container card text-center">
    <div class="row">
        <div class="col-sm ">
            <form method="post">
                <input type="hidden" name="page" value="1">
                <div class="form-row">
                    <div class="col">
                        <h3><fmt:message key="departure"/></h3>
                        <input name="stationFrom" type="text" class="form-control" placeholder="Departure">
                    </div>
                    <div class="col">
                        <h3><fmt:message key="arrival"/></h3>
                        <input name="stationTo" type="text" class="form-control" placeholder="Arrival">
                    </div>
                </div>

                <div class="form-row">
                    <div class="col">
                        <h3><fmt:message key="date"/></h3>
                        <input name="date" type="date" class="form-control" placeholder="Date" id="date">
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block"><fmt:message key="find.routes"/></button>

            </form>
        </div>
    </div>
    <c:if test="${emptyMessage == true}">
        <div class="alert alert-danger" role="alert">
            <fmt:message key="not.found"/>
        </div>
    </c:if>
</div>
<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>

</body>
</html>
