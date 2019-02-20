<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Registration</title>
</head>

<body>

<div class="container" style="width: 256px">
    <c:set var="loginError" value="${errors.getFieldError('login')}"/>
    <c:set var="passwordError" value="${errors.getFieldError('password')}"/>
    <c:set var="firstNameError" value="${errors.getFieldError('firstName')}"/>
    <c:set var="lastNameError" value="${errors.getFieldError('lastName')}"/>
    <c:set var="patronymicError" value="${errors.getFieldError('patronymic')}"/>

    <h1 align="center">Registration</h1>
    <br>

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/doRegister" method="post">

        <fieldset>
            <legend>Mandatory</legend>

            <%-- Login --%>
            <div class="form-group">
                <input type="text" class="form-control" name="login" placeholder="Login"
                <c:if test="${user ne null}">value="${user.login}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${loginError eq null}">
                       title="Login"
                       data-content="Should contain 3 - 45 characters in English or numbers."
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Login"
                       data-content="${loginError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

            <%-- Password --%>
            <div class="form-group">
                <input type="password" class="form-control" name="password" placeholder="Password"
                <%--<c:if test="${user ne null}">value="${user.password}"</c:if>--%>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${passwordError eq null}">
                       title="Password"
                       data-content="Should contain 5 - 45 characters in English, numbers or punctuation"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Password"
                       data-content="${passwordError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

        </fieldset>

        <fieldset>
            <legend>Optional</legend>

            <%-- First Name --%>
            <div class="form-group">
                <input type="text" class="form-control" name="firstName" placeholder="First Name"
                       <c:if test="${user ne null}">value="${user.firstName}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${firstNameError eq null}">
                       data-content="First Name"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in First Name"
                       data-content="${firstNameError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

            <%-- Last Name --%>
            <div class="form-group">
                <input type="text" class="form-control" name="lastName" placeholder="Last Name"
                       <c:if test="${user ne null}">value="${user.lastName}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${lastNameError eq null}">
                       data-content="Last Name"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Last Name"
                       data-content="${lastNameError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

            <%-- Patronymic --%>
            <div class="form-group">
                <input type="text" class="form-control" name="patronymic" placeholder="Patronymic"
                       <c:if test="${user ne null}">value="${user.patronymic}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${patronymicError eq null}">
                       data-content="Patronymic Name"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Patronymic"
                       data-content="${patronymicError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>
        </fieldset>

        <%-- Buttons --%>
        <div class="form-group" align="center">
            <input type="submit" class="btn btn-primary" value="Ok" style="width: 96px">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <a href="/" class="btn btn-primary" style="width: 96px">Cancel</a>
        </div>
    </form>

    <%--<div class="form-horizontal" align="right">
        <form action="/" method="post">
            <input type="submit" class="btn btn-primary" value="Cancel" style="width: 96px">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>--%>

    <c:remove var="login"/>
    <c:remove var="password"/>
    <c:remove var="firstNameError"/>
    <c:remove var="lastNameError"/>
    <c:remove var="patronymicError"/>

</div>
<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>
</body>
</html>