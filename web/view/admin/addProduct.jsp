<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 20/04/2020
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<h1>Add product</h1>
    <hr>
    <c:if test="${requestScope['msg'] != null}">
        <h5>${requestScope['msg']}</h5>
    </c:if>
    <h5></h5>
<form method="post" action="/addProduct">
    <div class="form-group">
        <label for="inputName">Name</label>
        <input type="text" class="form-control" id="inputName" name="name" placeholder="Name" max>
    </div>
    <div class="form-group">
        <label for="inputPrice">Price</label>
        <input type="number" class="form-control" id="inputPrice" name="price" placeholder="Price">
    </div>
    <div class="form-group">
        <label for="inputImage">Image src</label>
        <input type="text" class="form-control" id="inputImage" name="image" placeholder="Image src">
    </div>
    <div class="form-group">
        <label for="inputDescription">Description</label>
        <input type="text" class="form-control" id="inputDescription" name="description" placeholder="Description" maxlength="1000">
    </div>
    <div class="form-group">
        <label for="inputCategory">Category</label>
        <select id="inputCategory" class="form-control" name="category_id">
            <c:forEach items="${requestScope['categoryList']}" var="category">
                <option selected value="${category.getId()}">${category.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label for="inputTag">Tag</label>
        <select id="inputTag" class="form-control" name="tag_id">
            <c:forEach items="${requestScope['tagList']}" var="tag">
                <option selected value="${tag.getId()}">${tag.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <button class="btn btn-primary" type="submit">Submit</button>

</form>
</div>

</body>
</html>
