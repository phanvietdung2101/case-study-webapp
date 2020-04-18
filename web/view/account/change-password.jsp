<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 18/04/2020
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="../../script/change-password.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h1>Change Password</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <p class="text-center">Use the form below to change your password. Your password cannot be the same as your username.</p>
            <form method="post" id="/account?action=change">
                <div class="row">
                    <input type="password" class="input-lg form-control" name="oldPassword" id="password1" placeholder="Old Password" autocomplete="off">
                    <input type="password" class="input-lg form-control" name="newPassword" id="password2" placeholder="New Password" autocomplete="off">
                    <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing Password..." value="Change Password">
                </div>
            </form>
        </div><!--/col-sm-6-->
    </div><!--/row-->
</div>

</body>
</html>
