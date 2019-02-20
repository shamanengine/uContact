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

    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/doAddContact" method="post">

        <!--        <div class="form-group">
                    <label class="control-label col-sm-2" for="firstName">First Name*:</label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control" id="firstName" name="firstName"
                               placeholder="Mandatory, minimum 4 symbols"></div>
                </div>-->

        <fieldset>
            <legend>Mandatory</legend>
            <div class="form-group">
                <input type="text" class="form-control" name="firstName" title="First Name" placeholder="First Name"
                       data-toggle="popover" data-trigger="focus" data-placement="left"
                       data-content="Should contain at least 4 characters"></div>

            <div class="form-group">
                <input type="text" class="form-control" name="lastName" title="Last Name" placeholder="Last Name"
                       data-toggle="popover" data-trigger="focus" data-placement="left"
                       data-content="Should contain at least 4 characters"></div>

            <div class="form-group">
                <input type="text" class="form-control" name="patronymic" title="Patronymic" placeholder="Patronymic"
                       data-toggle="popover" data-trigger="focus" data-placement="left"
                       data-content="Should contain at least 4 characters"></div>

            <div class="form-group">
                <input type="text" class="form-control" name="cellPhone" title="Cell Phone" placeholder="Cell Phone"
                       data-toggle="popover" data-trigger="focus" data-placement="left"
                       data-content="Valid format, e.x. +38 044 xxx-xx-xx"></div>
        </fieldset>

        <fieldset>
            <legend>Optional</legend>
            <div class="form-group"><input type="text" class="form-control" name="homePhone"
                                           placeholder="Home Phone"></div>

            <div class="form-group"><input type="text" class="form-control" name="address"
                                           placeholder="Address">
            </div>

            <div class="form-group"><input type="email" class="form-control" name="email"
                                           placeholder="E-mail">
            </div>

            <!--            <div class="form-group"><label class="btn btn-default">Add Photo:
                            <input type="file" name="photo" class="btn btn-default" style="display: none;"></label></div>-->
        </fieldset>

        <div class="form-group" align="center"><input type="submit" class="btn btn-primary" value="Ok"
                                                      style="width: 96px">
            <a href="/" class="btn btn-primary" style="width: 96px">Cancel</a>
        </div>

    </form>

    <script>
        $(document).ready(function () {
            $('[data-toggle="popover"]').popover();
        });
    </script>
</div>
</body>
</html>