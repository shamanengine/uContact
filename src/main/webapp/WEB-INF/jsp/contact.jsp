<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>New Contact</title>
</head>

<body>
<div class="container" style="width: 256px">
    <h1 align="center">New Contact</h1>
    <br>

    <%--<c:set var="firstNameError" scope="request" value="${errors.getFieldError('firstName')}"/>
    <c:set var="lastNameError" scope="request" value="${errors.getFieldError('lastName')}"/>
    <c:set var="patronymicError" scope="request" value="${errors.getFieldError('patronymic')}"/>
    <c:set var="cellPhoneError" scope="request" value="${errors.getFieldError('cellPhone')}"/>
    <c:set var="homePhoneError" scope="request" value="${errors.getFieldError('homePhone')}"/>
    <c:set var="addressError" scope="request" value="${errors.getFieldError('address')}"/>
    <c:set var="emailError" scope="request" value="${errors.getFieldError('email')}"/>--%>

    <c:set var="firstNameError" value="${errors.getFieldError('firstName')}"/>
    <c:set var="lastNameError" value="${errors.getFieldError('lastName')}"/>
    <c:set var="patronymicError" value="${errors.getFieldError('patronymic')}"/>
    <c:set var="cellPhoneError" value="${errors.getFieldError('cellPhone')}"/>
    <c:set var="homePhoneError" value="${errors.getFieldError('homePhone')}"/>
    <c:set var="addressError" value="${errors.getFieldError('address')}"/>
    <c:set var="emailError" value="${errors.getFieldError('email')}"/>

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/doAddContact" method="post">

        <fieldset>
            <legend>Mandatory</legend>

            <%-- First Name --%>
            <div class="form-group">
                <input type="text" class="form-control" name="firstName" placeholder="First Name"
                       <c:if test="${contact ne null}">value="${contact.firstName}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${firstNameError eq null}">
                       title="First Name"
                       data-content="4-45 characters"
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
                       <c:if test="${contact ne null}">value="${contact.lastName}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${lastNameError eq null}">
                       title="Last Name"
                       data-content="4-45 characters"
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
                       <c:if test="${contact ne null}">value="${contact.patronymic}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${patronymicError eq null}">
                       title="Patronymic"
                       data-content="4-45 characters"
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

            <%-- Cell Phone --%>
            <div class="form-group">
                <input type="text" class="form-control" name="cellPhone" placeholder="Cell Phone"
                       <c:if test="${contact ne null}">value="${contact.cellPhone}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${cellPhoneError eq null}">
                       title="Cell Phone"
                       data-content="International format"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Cell Phone"
                       data-content="${cellPhoneError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
                <%--data-toggle="popover" data-trigger="focus" data-placement="left"
                data-content="Valid formats e.x.
                +38 0yy xxx-xx-xx, +380yyxxxxxxx, +38(0yy)xxxxxxx, +380yyxxx-xx-xx, 0yyxxx-xx-xx.">--%>
            </div>
        </fieldset>

        <fieldset>
            <legend>Optional</legend>

            <%-- Home Phone --%>
            <div class="form-group">
                <input type="text" class="form-control" name="homePhone" placeholder="Home Phone"
                       <c:if test="${contact ne null}">value="${contact.homePhone}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${homePhoneError eq null}">
                       data-content="Home Phone"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Home Phone"
                       data-content="${homePhoneError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

            <%-- Address --%>
            <div class="form-group">
                <input type="text" class="form-control" name="address" placeholder="Address"
                       <c:if test="${contact ne null}">value="${contact.address}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${addressError eq null}">
                       data-content="Address"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in Address"
                       data-content="${addressError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

            <%-- E-mail --%>
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="E-mail"
                       <c:if test="${contact ne null}">value="${contact.email}"</c:if>
                       data-toggle="popover" data-trigger="focus"
                <c:choose>
                <c:when test="${emailError eq null}">
                       data-content="E-mail"
                       data-placement="left"
                </c:when>
                <c:otherwise>
                       title="Error in E-mail"
                       data-content="${emailError.getDefaultMessage()}"
                       data-placement="right" style="border-color: red"
                </c:otherwise>
                </c:choose>
                >
            </div>

        </fieldset>

        <div class="form-group form-horizontal" align="center">
            <input type="submit" class="btn btn-primary" value="Ok" style="width: 96px">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="${contact.contactId}" value="${contact.contactId}"/>
            <a href="/" class="btn btn-primary" style="width: 96px">Cancel</a>
        </div>

    </form>

    <c:remove var="firstNameError"/>
    <c:remove var="lastNameError"/>
    <c:remove var="patronymicError"/>
    <c:remove var="cellPhoneError"/>
    <c:remove var="homePhoneError"/>
    <c:remove var="addressError"/>
    <c:remove var="emailError"/>

</div>

<%--<c:remove var="firstNameError" scope="request"/>
<c:remove var="lastNameError" scope="request"/>
<c:remove var="patronymicError" scope="request"/>
<c:remove var="cellPhoneError" scope="request"/>
<c:remove var="homePhoneError" scope="request"/>
<c:remove var="addressError" scope="request"/>
<c:remove var="emailError" scope="request"/>--%>



<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>

</body>

</html>