<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Contacts</title>
</head>
<body>
<div class="container">
    <h1 align="center">Contact List</h1>
    <br>
    <!--<div class="table-responsive">-->
    <table class="table table-striped table-responsive">
        <thead align="center">
        <tr>
            <!-- Photo -->
            <!--<td><b></b></td>-->

            <!-- First Name -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByFirstName" method="post">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by First Name">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- Last Name -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByLastName" method="post">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by Last Name">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- Patronymic -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByPatronymic" method="post">
                    <input type="text" class="form-control" name="patronymic" placeholder="Patronymic"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by Patronymic">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- Cell Phone -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByCellPhone" method="post">
                    <input type="text" class="form-control" name="cellPhone" placeholder="Cell Phone"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by Cell Phone">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- Home Phone style="width: 128px;"-->
            <td><b>
                <form class="form-inline" role="form" action="/searchByHomePhone" method="post">
                    <input type="text" class="form-control" name="homePhone" placeholder="Home Phone"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by Home Phone">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- Address -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByAddress" method="post">
                    <input type="text" class="form-control" name="address" placeholder="Address"
                           style="width: 112px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by Address">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <!-- E-mail -->
            <td><b>
                <form class="form-inline" role="form" action="/searchByEmail" method="post">
                    <input type="text" class="form-control" name="email" placeholder="E-mail" style="width: 128px"
                           data-toggle="popover" data-trigger="focus" data-placement="top"
                           data-content="Search by E-mail">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </b></td>

            <td colspan="2">
                <form class="form-inline" role="form" action="/" method="post">
                    <%--<input type="submit" class="btn btn-primary" value="Log out"/>--%>
                    <button type="submit" class="btn btn-default btn-sm" style="padding-top: 8px; padding-bottom: 8px">
                        <span class="glyphicon glyphicon-refresh"></span> Refresh
                    </button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </td>

 <%--           <form class="form-inline" align="center" action="${logoutUrl}" method="post"
                  style="padding-left: 10px;">
                <input type="submit" class="btn btn-primary" value="Log out"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>--%>

        </tr>
        </thead>

        <c:forEach items="${contacts}" var="contact">
            <tr>
                    <%--<!--<td><img height="40" width="40" src="/image/${contact.photo.id}"/></td>-->--%>
                <td>${contact.firstName}</td>
                <td>${contact.lastName}</td>
                <td>${contact.patronymic}</td>
                <td>${contact.cellPhone}</td>
                <td>${contact.homePhone}</td>
                <td width="20px">${contact.address}</td>
                <td>${contact.email}</td>
                    <%--<!--<td><a href="/delete?id=${contact.id}">Delete</a></td>-->--%>
                <c:url var="deleteContact" value="/deleteContact"/>
                <c:url var="editContact" value="/editContact"/>
                <td>
                    <form class="form-inline" role="form" action="${editContact}" method="post">
                        <input type="submit" class="btn btn-default" value="&#9998;" title="Edit">
                        <input type="hidden" name="contactId" value="${contact.contactId}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>

                <td>
                    <form class="form-inline" role="form" action="${deleteContact}" method="post">
                        <input type="submit" class="btn btn-default" value="&#x2702;" title="Delete">
                        <input type="hidden" name="contactId" value="${contact.contactId}"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table align="center">
        <tr>
            <td>
                <form class="form-inline" align="center" action="/addContact" method="post">
                    <input type="submit" class="btn btn-primary" value="Add new">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <!--<a href="/j_spring_security_logout" class="btn btn-primary">Cancel</a>-->
                    <%--<a href="/logout" class="btn btn-primary">Logout</a>--%>
                </form>
            </td>
            <td>
                <c:url var="logoutUrl" value="/logout"/>
                <form class="form-inline" align="center" action="${logoutUrl}" method="post"
                      style="padding-left: 10px;">
                    <input type="submit" class="btn btn-primary" value="Log out"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </td>
        </tr>
    </table>
</div>

<script>
    $(document).ready(function () {
        $('[data-toggle="popover"]').popover();
    });
</script>
</body>
</html>