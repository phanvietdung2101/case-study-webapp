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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="../../script/change-password.js"></script>
</head>
<body>
<div class="container">
    <div class="row justify-content-center">
        <h1>Change Password</h1>
    </div>
    <div class="row justify-content-center">
        <div class="col-sm-6 col-sm-offset-3">
            <p class="text-center">Use the form below to change your password. Your password cannot be the same as your username.</p>
            <form class="form" method="post" id="/account?action=change">
                <div class="form-group">
                    <input type="password" class="input-lg form-control" name="oldPassword" id="password1" placeholder="Old Password" autocomplete="off">
                </div>
                <div class="form-group">
                    <input type="password" class="input-lg form-control" name="newPassword" id="password2" placeholder="New Password" autocomplete="off">
                </div>
                <div class="row justify-content-around">
                    <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing Password..." value="Change Password">
                    <a href="/index" class="col-xs-12 btn btn-secondary btn-load btn-lg">Back to Homepage</a>
                </div>
            </form>
        </div><!--/col-sm-6-->
    </div><!--/row-->
</div>

</body>
</html>
