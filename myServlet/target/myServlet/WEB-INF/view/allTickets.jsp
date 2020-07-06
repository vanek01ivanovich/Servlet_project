<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 08.05.2020
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="title.admin.look.all.tickets"/></title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/tables.css"%>
    <%@include file="/WEB-INF/css/allstaff.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/admin">Navbar</a>

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
<table class="table table-striped">
    <thead class="thead-dark">
        <tr>
            <th scope="col"><fmt:message key="first.name"/></th>
            <th scope="col"><fmt:message key="last.name"/></th>
            <th scope="col"><fmt:message key="departure"/></th>
            <th scope="col"><fmt:message key="arrival"/></th>
            <th scope="col"><fmt:message key="date.departure"/></th>
            <th scope="col"><fmt:message key="time.departure"/></th>
            <th scope="col"><fmt:message key="date.arrival"/></th>
            <th scope="col"><fmt:message key="time.arrival"/></th>
            <th scope="col"><fmt:message key="train"/></th>
            <th scope="col"><fmt:message key="price"/></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${allTickets}" var="tickets">
            <tr>
                <c:if test="${lang == 'en'}">
                    <th scope="row"> <c:out value="${tickets.getFirstName()}"/></th>
                    <th scope="row"> <c:out value="${tickets.getLastName()}"/></th>
                    <c:forEach items="${tickets.getDestinationProperties()}" var="dest">
                        <c:forEach items="${dest.getDestinations()}" var="station">
                            <th scope="row"><c:out value="${station.getDeparture()}"/></th>
                            <th scope="row"><c:out value="${station.getArrival()}"/></th>
                        </c:forEach>
                    </c:forEach>
                </c:if>

                <c:if test="${lang == 'ua'}">
                    <th scope="row"> <c:out value="${tickets.getFirstNameUkr()}"/></th>
                    <th scope="row"> <c:out value="${tickets.getLastNameUkr()}"/></th>
                    <c:forEach items="${tickets.getDestinationProperties()}" var="dest">
                        <c:forEach items="${dest.getDestinations()}" var="station">
                            <th scope="row"><c:out value="${station.getDepartureUA()}"/></th>
                            <th scope="row"><c:out value="${station.getArrivalUA()}"/></th>
                        </c:forEach>
                    </c:forEach>
                </c:if>

                <c:forEach items="${tickets.getDestinationProperties()}" var="dest">
                    <th scope="row"><c:out value="${dest.getDateDeparture()}"/></th>
                    <th scope="row"><c:out value="${dest.getTimeDeparture()}"/></th>
                    <th scope="row"><c:out value="${dest.getDateArrival()}"/></th>
                    <th scope="row"><c:out value="${dest.getTimeArrival()}"/></th>
                </c:forEach>

                <c:if test="${lang == 'en'}">
                    <c:forEach items="${tickets.getTrains()}" var="dest">
                        <th scope="row"><c:out value="${dest.getTrainName()}"/></th>
                    </c:forEach>
                </c:if>

                <c:if test="${lang == 'ua'}">
                    <c:forEach items="${tickets.getTrains()}" var="dest">
                        <th scope="row"><c:out value="${dest.getTrainNameUA()}"/></th>
                    </c:forEach>
                </c:if>


                <c:forEach items="${tickets.getDestinationProperties()}" var="dest">
                    <th scope="row"><c:out value="${dest.getPrice()}"/></th>
                </c:forEach>

            </tr>

        </c:forEach>
    </tbody>
</table>

<%--<c:if test="${pagination == true}">--%>
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
<%--</c:if>--%>



<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark"><fmt:message key="footer"/></div>
</footer>

</body>
</html>
