<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 18/04/2020
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../../css/account.css" rel="stylesheet">

<%--    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>--%>
<%--    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>--%>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row ">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4>${requestScope.user.getName()}</h4>
                        <small><cite title="San Francisco, USA">${requestScope.user.getAddress()}<i class="glyphicon glyphicon-map-marker">
                        </i></cite></small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>${requestScope.user.getEmail()}
                            <br />
                            <i class="glyphicon glyphicon-phone"></i>${requestScope.user.getPhone_number()}
                            <br />
                            <i class="glyphicon glyphicon-warning-sign"></i>${requestScope.user.getPassword()}
                        </p>
                        <!-- Split button -->
                        <div class="row justify-content-around">
                            <a type="button" class="btn btn-primary" href="/account?action=change">
                                Change password</a>
                            <a type="button" class="btn btn-primary" href="/account?action=logout">
                                Sign out</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>