<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 08.05.2020
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="title.admin.look.all.users"/></title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">

</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/allstaff.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/admin">HOME</a>

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
        <th scope="col"><fmt:message key="user.name"/></th>
        <th scope="col"><fmt:message key="first.name"/></th>
        <th scope="col"><fmt:message key="last.name"/></th>
        <th scope="col" ><fmt:message key="role"/></th>
        <th scope="col"></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>

        <c:forEach items="${allUsers}" var="users">
            <tr>
                <form action="/admin/allUsers/editUser">
                    <input type="hidden" name="userId" value="${users.getId()}">
                    <th scope="row"> <c:out value="${users.getUserName()}"/></th>
                    <c:if test="${lang == 'en'}">
                        <th scope="row"> <c:out value="${users.getFirstName()}"/></th>
                        <th scope="row"> <c:out value="${users.getLastName()}"/></th>
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        <th scope="row"> <c:out value="${users.getFirstNameUkr()}"/></th>
                        <th scope="row"> <c:out value="${users.getLastNameUkr()}"/></th>
                    </c:if>

                    <th scope="row"> <c:out value="${users.getRole()}"/></th>
                    <th><button type="submit">edit</button></th>
                </form>
                <form action="/admin/allUsers" method="post">
                    <input type="hidden" name="userId" value="${users.getId()}">
                    <th scope="row"><button type="submit">delete</button></th>
                </form>

            </tr>
        </c:forEach>
    </tbody>
</table>

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
