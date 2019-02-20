<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Login</title>
</head>

<body>
<div class="container" style="width: 256px">
    <h1 align="center">uContact</h1>
    <br>

    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="${loginUrl}" method="post">

        <fieldset>
            <legend>Please login or register</legend>

            <div class="form-group">
                <input type="text" class="form-control" name="j_login" placeholder="Login"></div>

            <div class="form-group">
                <input type="password" class="form-control" name="j_password" placeholder="Password"></div>
        </fieldset>

        <div class="form-group" align="center">
            <input type="submit" class="btn btn-primary" value="Login" style="width: 96px">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <a href="/register" class="btn btn-primary" style="width: 96px">Register</a>
        </div>

        <c:if test="${param.error != null}">
            <p align="center">Invalid username and password.</p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p align="center">You have been logged out.</p>
        </c:if>

    </form>

</div>

<%--<c:url value="/j_spring_security_check" var="loginUrl"/>
<form action="${loginUrl}" method="post">
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <button type="submit" class="btn">Log in</button>
</form>--%>

</body>
</html>