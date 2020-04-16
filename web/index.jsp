<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 15/04/2020
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="./css/style.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Homepage</title>
  </head>
  <body>
  <div class="container" style = "height: auto">
  <%@include file="part/header.jsp"%>
  <div id="content" class="row">
  <c:forEach items="${requestScope['productList']}" var="product">
    <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
      <div class="my-list">
        <img src="${product.getImage()}" alt="img" class="img-product" />
        <h3>${product.getName()}</h3>
        <h5>${product.getPrice()} VND</h5>
        <div id="function">
          <a class="btn btn-primary" href="#" role="button">Add to card</a>
          <a class="btn btn-primary" href="/index?action=view&id=${product.getId()}" role="button">Detail</a>
        </div>
      </div>
    </div>
  </c:forEach>
  </div>
  </div>
  <footer class="card card-default">
    <div class="card-heading text-center">
      <p>Yez &copy; 2020</p>
    </div>
  </footer>
  </div>
  </body>
</html>
