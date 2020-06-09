<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 15.04.2020
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/user.css"%>
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
                <a class="dropdown-item" href="?lang=ua"><fmt:message key="ukrainian"/></a>
            </div>
        </div>
    </nav>
</header>

<input type="hidden" value="${alert}" id="button">

<form action="/findroute">
    <button type="submit" class="btn btn-warning">Find Route</button>
</form>

<form action="/user" method="post">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <div class="form-group">
                <label for="cardNumber">Card Number</label>
                <input required type="number" name="cardNumber" class="form-control" id="cardNumber" placeholder="Enter your card number">
                <small id="emailHelp" class="form-text text-muted">We'll never share your card number with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="amountMoney">Amount</label>
                <input required type="number" name="money"  id="amountMoney" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" placeholder="Enter your amount">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">TOP-UP</button>
        </div>
    </div>
</form>



<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>

<script>
    var al = document.getElementById("button").value;

    if (al === "1"){
        swal({
            icon:"success",
            text:"You have filled up your card successfully!"
        });
    }

    if (al === "0"){
        swal({
            icon:"error",
            text:"Some problems with filling up!"
        });
    }
</script>

</body>
</html>
