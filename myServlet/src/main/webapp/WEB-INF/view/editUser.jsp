<%--
  Created by IntelliJ IDEA.
  User: Ваня
  Date: 09.05.2020
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <title><fmt:message key="title.update.user"/></title>
    <link rel="icon" href="data:,">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<style>
    <%@include file="/WEB-INF/css/headerFooter.css"%>
    <%@include file="/WEB-INF/css/registration.css"%>
    <%@include file="/WEB-INF/css/editUser.css"%>
</style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="/login">HOME</a>
        <div class="navigationBar">
            <div class="dropdown">
                <button class="btn btn-outline-success dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="languages"/>
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="?lang=en">English</a>
                    <a class="dropdown-item" href="?lang=ua">Ukrainian</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<input type="hidden" value="${alert}" id="button">
<form  method="post">
    <div class="container card text-center">
        <div class="card-header">
            <h5>Edit user</h5>
        </div>
        <div class="row">
            <div class="col-sm ">
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">User Name</span>
                            </div>
                            <input value="${user.getUserName()}" name="userName" type="text" class="form-control" id="validationServerUsername" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:if test="${validUserName == false}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="regex.userName.error"/>
                            </div>
                        </c:if>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">First Name</span>
                            </div>
                            <input name="firstName" value="${user.getFirstName()}" type="text" class="form-control" id="validationServerUsername" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:if test="${validFirstName == false}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="regex.firstName.error"/>
                            </div>
                        </c:if>


                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Last Name</span>
                            </div>
                            <input name="lastName" value="${user.getLastName()}" type="text" class="form-control" id="validationServerUsername" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:if test="${validLastName == false}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="regex.lastName.error"/>
                            </div>
                        </c:if>


                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">FirstUkr Name</span>
                            </div>
                            <input name="ukrFirstName" value="${user.getFirstNameUkr()}" type="text" class="form-control" id="validationServerUsername" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:if test="${validFirstNameUkr == false}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="regex.firstNameUkr.error"/>
                            </div>
                        </c:if>



                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">LastUkr Name</span>
                            </div>
                            <input name="ukrLastName" value="${user.getLastNameUkr()}" type="text" class="form-control" id="validationServerUsername" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:if test="${validLastNameUkr == false}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="regex.lastNameUkr.error"/>
                            </div>
                        </c:if>

                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Role</label>
                            </div>
                            <select name="role" class="custom-select" id="inputGroupSelect01">

                                <c:if test="${user.getRole() == 'ROLE_USER'}">
                                    <option value="ROLE_USER" selected>USER</option>
                                    <option value="ROLE_ADMIN">ADMIN</option>
                                </c:if>
                                <c:if test="${user.getRole() == 'ROLE_ADMIN'}">
                                    <option value="ROLE_ADMIN" selected>ADMIN</option>
                                    <option value="ROLE_USER">USER</option>
                                </c:if>
                            </select>
                        </div>



                    </div>

                    <div class="card-footer text-muted">
                        <button type="submit" class="btn btn-outline-primary waves-effect" >submit</button>

                    </div>
            </div>
        </div>
    </div>

</form>

<form action="/admin/allUsers">
    <button type="submit">back</button>
</form>

<footer class="text-white bg-dark">
    <div id="footer" class="card-footer text-muted text-white bg-dark">© 2020 Copyright:All rights reserved</div>
</footer>
</body>
<script>
    var al = document.getElementById("button").value;


    if (al === "0"){
        swal({
            icon:"error",
            text:"Some problems with edit user"
        });
    }
</script>
</html>
