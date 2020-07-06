<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 05.05.2020
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <link rel="icon" href="data:,">
    <title><fmt:message key="title.ticket"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>
<style>
    <%@include file="/WEB-INF/css/ticket.css"%>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/user">HOME</a>

        <div class="userInfo">
            <fmt:message key="user.hi"/>
            ${user.getUserName()}
            <br>
            <fmt:message key="money"/>
            ${user.getMoney()}
        </div>
        <div class="navigationBar">
            <form action="/logout" style="margin-right:10px">
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
        </div>
    </nav>
</header>
<input type="hidden" value="${alert}" id="button">
<form action="/ticket" method="post">
<div class="container card text-center">
    <div class="card-header">
        <fmt:message key="ticket"/>
    </div>
    <div class="card-body">

            <input type="hidden" name="idProperty" value="${ticket.getIdProperty()}">
            <div class="info">
                <span><fmt:message key="first.name"/></span>
                <c:if test="${lang == 'en'}">
                    ${user.getFirstName()}
                </c:if>
                <c:if test="${lang == 'ua'}">
                    ${user.getFirstNameUkr()}
                </c:if>
            </div>

            <div class="info">
                <span><fmt:message key="last.name"/></span>
                <c:if test="${lang == 'en'}">
                    ${user.getLastName()}
                </c:if>
                <c:if test="${lang == 'ua'}">
                    ${user.getLastNameUkr()}
                </c:if>
            </div>

            <div class="info">
                <span><fmt:message key="departure"/></span>
                <c:if test="${lang == 'en'}">
                    <c:forEach items="${ticket.getDestinations()}" var="tick">
                        <c:out value="${tick.getDeparture()}"/>
                    </c:forEach>

                </c:if>
                <c:if test="${lang == 'ua'}">
                    <c:forEach items="${ticket.getDestinations()}" var="tick">
                        <c:out value="${tick.getDepartureUA()}"/>
                    </c:forEach>
                </c:if>
            </div>

            <div class="info">
                <span><fmt:message key="arrival"/></span>
                <c:if test="${lang == 'en'}">
                    <c:forEach items="${ticket.getDestinations()}" var="tick">
                        <c:out value="${tick.getArrival()}"/>
                    </c:forEach>

                </c:if>
                <c:if test="${lang == 'ua'}">
                    <c:forEach items="${ticket.getDestinations()}" var="tick">
                        <c:out value="${tick.getArrivalUA()}"/>
                    </c:forEach>
                </c:if>
            </div>

            <div class="info">
                <span><fmt:message key="date"/>/<fmt:message key="time.departure"/>e</span>
                <c:out value="${ticket.getDateDeparture()}"/>
                /
                <c:out value="${ticket.getTimeDeparture()}"/>
            </div>

            <div class="info">
                <span><fmt:message key="date"/>/<fmt:message key="time.arrival"/></span>
                <c:out value="${ticket.getDateArrival()}"/>
                /
                <c:out value="${ticket.getTimeArrival()}"/>
            </div>

            <div class="info">
                <span><fmt:message key="train"/></span>
                <c:if test="${lang == 'en'}">
                    <c:forEach items="${ticket.getTrains()}" var="train">
                        <c:out value="${train.getTrainName()}"/>
                    </c:forEach>

                </c:if>
                <c:if test="${lang == 'ua'}">
                    <c:forEach items="${ticket.getTrains()}" var="train">
                        <c:out value="${train.getTrainNameUA()}"/>
                    </c:forEach>
                </c:if>

            </div>

            <div class="info">
                <span><fmt:message key="price"/></span>
                <c:out value="${ticket.getPrice()}"/>
            </div>

    </div>

    <div class="card-footer text-muted">
        <button type="submit" class="btn btn-outline-primary waves-effect" ><fmt:message key="choose"/></button>

    </div>

</div>
</form>
<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>

<fmt:message key="not.enough.money" var="errorAlert"/>
<script>
    var al = document.getElementById("button").value;

    if (al === "0"){
        swal({
            icon:"error",
            text:"${errorAlert}"
        });
    }
    if (al === "1"){

        swal({
            icon:"success",
            text:"Success",
        });

        window.location = '/findroute';


    }

</script>
</body>
</html>
