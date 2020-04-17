<%--
  Created by IntelliJ IDEA.
  User: msi
  Date: 16/04/2020
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="./css/product-detail.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <title>Product detail</title>
</head>
<body>
<c:set var="product" scope="request" value="${product}"></c:set>
<div class="container">
    <div class="card">
        <div class="row">
            <aside class="col-sm-5 border-right">
                <article class="gallery-wrap" style=>
                    <div class="img-big-wrap">
                        <div> <a href="#"><img src="${product.getImage()}" id="product-img"></a></div>
                    </div> <!-- slider-product.// -->
<%--                    <div class="img-small-wrap">--%>
<%--                        <div class="item-gallery">  </div>--%>
<%--                    </div> <!-- slider-nav.// -->--%>
                </article> <!-- gallery-wrap .end// -->
            </aside>
            <aside class="col-sm-7">
                <article class="card-body p-5">
                    <h3 class="title mb-3">${product.getName()}</h3>

                    <p class="price-detail-wrap">
	<span class="price h3 text-warning">
		<span class="currency">VNĐ </span><span class="num">${product.getPrice()}</span>
	</span>
                        <span>/per 1</span>
                    </p> <!-- price-detail-wrap .// -->
                    <dl class="item-property">
                        <dt>Description</dt>
                        <dd><p>${product.getDescription()}</p></dd>
                    </dl>
                    <dl class="param param-feature">
                        <dt>Model#</dt>
                        <dd>12345611</dd>
                    </dl>  <!-- item-property-hor .// -->
                    <hr>
                    <div class="row">
                        <div class="col-sm-5">
                            <dl class="param param-inline">
                                <dt>Quantity: </dt>
                                <dd>
                                    <select class="form-control form-control-sm" style="width:70px;">
                                        <option> 1 </option>
                                        <option> 2 </option>
                                        <option> 3 </option>
                                    </select>
                                </dd>
                            </dl>  <!-- item-property .// -->
                        </div> <!-- col.// -->
                    </div> <!-- row.// -->
                    <hr>
                    <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                    <a href="#" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                </article> <!-- card-body.// -->
            </aside> <!-- col.// -->
        </div> <!-- row.// -->
    </div> <!-- card.// -->


</div>
</body>
</html>
