<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 27.04.2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="title.destinations"/></title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/routes.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/user">Navbar</a>

        <div class="userInfo">
            <fmt:message key="user.hi"/>
            ${user.getUserName()}
            <br>
            <fmt:message key="money"/>
            ${user.getMoney()}
        </div>
        <div class="navigationBar">
            <form action="/findroute" id="buttonRoutes" >
                <button type="submit"><fmt:message key="find.another.routes"/></button>
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


    <div class="container" id="content">
        <table class="table">
            <tbody>
                    <c:forEach items="${listRoutes}" var="routes">
                        <tr>
                        <form action="/ticket">
                            <input type="hidden" name="idProperty" value="${routes.getIdProperty()}">
                            <th>
                                <c:if test="${lang == 'en'}">
                                    <c:forEach items="${routes.getDestinations()}" var="destination">
                                        <h3>${destination.getDeparture()}</h3>
                                        <br>
                                        <span><fmt:message key="departure"/></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${lang == 'ua'}">
                                    <c:forEach items="${routes.getDestinations()}" var="destination">
                                        <h3>${destination.getDepartureUA()}</h3>
                                        <br>
                                        <span><fmt:message key="departure"/></span>
                                    </c:forEach>
                                </c:if>
                                <br>
                                ${routes.getDateDeparture()}
                                <br>
                                ${routes.getTimeDeparture()}
                            </th>
                            <th>
                                <c:if test="${lang == 'en'}">
                                    <c:forEach items="${routes.getDestinations()}" var="destination">
                                        <h3>${destination.getArrival()}</h3>
                                        <br>
                                        <span><fmt:message key="arrival"/></span>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${lang == 'ua'}">
                                    <c:forEach items="${routes.getDestinations()}" var="destination">
                                        <h3>${destination.getArrivalUA()}</h3>
                                        <br>
                                        <span><fmt:message key="arrival"/></span>
                                    </c:forEach>
                                </c:if>
                                <br>
                                ${routes.getDateArrival()}
                                <br>
                                ${routes.getTimeArrival()}
                            </th>
                            <th>
                                <c:if test="${lang == 'en'}">
                                    <c:forEach items="${routes.getTrains()}" var="destination">
                                        <h3 class="secondary"><fmt:message key="train"/></h3>
                                        ${destination.getTrainName()}
                                    </c:forEach>
                                </c:if>
                                <c:if test="${lang == 'ua'}">
                                    <c:forEach items="${routes.getTrains()}" var="destination">
                                        <h3  class="secondary"><fmt:message key="train"/></h3>
                                        ${destination.getTrainNameUA()}
                                    </c:forEach>
                                </c:if>
                            </th>
                            <th>
                                <h3 class="secondary"><fmt:message key="price"/></h3>
                                ${routes.getPrice()} &#x20AC;
                            </th>


                            <th id="button" scope="col"><button type="submit" class="btn btn-outline-primary waves-effect" >Choose</button></th>
                        </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

<%--<form action="/routes">--%>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <li class="page-item">

                <a class="pag page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>

        </li>
        <c:forEach begin="0" end="${lengthPagination-1}" var="i">
            <li class="page-item"><a class="pag page-link" href="?page=${i+1}">${i+1}</a></li>
        </c:forEach>
        <li class="page-item">
            <a class="pag page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>







<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>

</body>
</html>

<%-- <c:forEach items="${listRoutes}" var="routes">
                    <form action="/ticket">
                    <tr>
                        <input type="hidden" name="idProperty" value="${routes.getIdProperty()}">
                        <c:if test="${lang == 'en'}">
                            <td>${routes.getStationDeparture()}</td>
                            <br>
                            <span>Departure</span>
                            <td>${routes.getStationArrival()}</td>
                        </c:if>
                        <c:if test="${lang == 'ua'}">
                            <td>${routes.getStationDepartureUA()}</td>
                            <td>${routes.getStationArrivalUA()}</td>
                        </c:if>
                        <td>

                                ${routes.getPrice()}
                        </td>
                        <td id="button" scope="col"><button type="submit" class="btn btn-outline-primary waves-effect" >Choose</button></td>
                    </tr>
                    </form>
                </c:forEach>--%>
