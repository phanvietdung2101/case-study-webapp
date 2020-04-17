<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 16/04/2020
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <link href="./css/login.css" rel="stylesheet" >
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
<%--        <div class="fadeIn first">--%>
<%--            <img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon" alt="User Icon" />--%>
<%--        </div>--%>

        <!-- Login Form -->
        <form method="post">
            <input type="text" id="login" class="fadeIn second" name="email" placeholder="email">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a><br>
            <a class="underlineHover" href="#">Register User</a>
        </div>


    </div>
</div>

</body>
</html>
