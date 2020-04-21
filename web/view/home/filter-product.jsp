
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style>
        .card img{
            max-height: 250px;
        }
        .card {
            margin-top: 10px;
            padding: 10px;
        }
    </style>
    <meta charset="UTF-8">
    <title>HomePage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="./css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/index">Simple Ecommerce</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/index">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="#">Categories <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Product</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/order">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/account">Account</a>
                </li>
            </ul>

            <form class="form-inline my-2 my-lg-0" method="get" action="/index">
                <div class="input-group input-group-sm">
                    <input type="hidden" value="search" name="action">
                    <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="string" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
                <%--                Cart --%>
                <a class="btn btn-success btn-sm ml-3" href="/order">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">3</span>
                </a>
                <div class="dropdown">
                    <button class="btn btn-success btn-sm ml-3" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Account
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <%--                        <%--%>
                        <%--                            if(session.getAttribute("username") != null){--%>
                        <%--                        %>--%>
                        <%--                        <a class="dropdown-item" >Hello, <%=session.getAttribute("username") %></a>--%>
                        <%--                        <%} %>--%>
                        <c:if test="${sessionScope['user'] != null}">
                            <a class="dropdown-item">
                                Hello, <c:out value="${sessionScope['user'].getName()}"></c:out>
                            </a>
                            <a class="dropdown-item" href="/account">Account detail</a>
                            <a class="dropdown-item" href="/account?action=change">Change password</a>
                            <a class="dropdown-item" href="/account?action=logout">Log out</a>
                        </c:if>
                        <c:if test="${sessionScope['user'] == null}">
                            <a class="dropdown-item" href="/login">Login</a>
                            <a class="dropdown-item" href="/login?action=register_user">Register user</a>
                        </c:if>

                    </div>
                </div>
            </form>

        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">E-COMMERCE CATEGORY</h1>
        <p class="lead text-muted mb-0">Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un peintre anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte...</p>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/index">Home</a></li>
                    <c:if test="${param.category != null}">
                        <li class="breadcrumb-item"><a href="/index?category=${param.category}">${param.category}</a></li>
                    </c:if>
                    <c:if test="${param.tag != null}">
                        <li class="breadcrumb-item active" aria-current="page">${param.tag}</li>
                    </c:if>
                </ol>
            </nav>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-12 col-sm-3">
            <%--            List category--%>
            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Category</div>
                <ul class="list-group category_block">
                    <c:choose>
                        <c:when test="${param.category != null}">
                            <li class="list-group-item"><a href="/index?category=${param.category}">${param.category}</a></li>
                        </c:when>
                        <c:when test="${param.category == null}">
                            <c:forEach items="${requestScope['categoryList']}" var="category">
                                <li class="list-group-item"><a href="/index?category=${category.getName()}&tag=${param.tag}">${category.getName()}</a></li>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </ul>
            </div>

            <div class="card bg-light mb-3">
                <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Brand </div>
                <ul class="list-group category_block">
                    <c:choose>
                        <c:when test="${param.tag != null }">
                            <li class="list-group-item"><a href="/index?tag=${param.tag}">${param.tag}</a></li>
                        </c:when>
                        <c:when test="${param.tag == null }">
                            <c:forEach items="${requestScope['tagList']}" var="tag">
                                <li class="list-group-item"><a href="/index?tag=${tag.getName()}&category=${param.category}">${tag.getName()}</a></li>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </ul>
            </div>
        </div>
        <div class="col">
            <div class="row">
                <!--                List -->
                <c:forEach items="${requestScope['productList']}" var="product">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <img class="card-img-top" src="${product.getImage()}" alt="Card image cap">
                            <div class="card-body">
                                <h4 class="card-title"><a href="/index?action=view&id=${product.getId()}" title="View Product">${product.getName()}</a></h4>
                                <div class="row">
                                    <div class="col">
                                        <p class="btn btn-danger btn-block">${product.getPrice()}</p>
                                    </div>
                                    <div class="col">
                                        <a href="/index?action=add_to_cart&product_id=${product.getId()}" class="btn btn-success btn-block">Add</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-12">
                    <nav aria-label="...">
                        <ul class="pagination">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">Previous</a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item active">
                                <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- Footer -->
<footer class="text-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-lg-4 col-xl-3">
                <h5>About</h5>
                <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                <p class="mb-0">
                    Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
                </p>
            </div>

            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                <h5>Informations</h5>
                <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                <ul class="list-unstyled">
                    <li><a href="">Link 1</a></li>
                    <li><a href="">Link 2</a></li>
                    <li><a href="">Link 3</a></li>
                    <li><a href="">Link 4</a></li>
                </ul>
            </div>

            <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                <h5>Others links</h5>
                <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                <ul class="list-unstyled">
                    <li><a href="">Link 1</a></li>
                    <li><a href="">Link 2</a></li>
                    <li><a href="">Link 3</a></li>
                    <li><a href="">Link 4</a></li>
                </ul>
            </div>

            <div class="col-md-4 col-lg-3 col-xl-3">
                <h5>Contact</h5>
                <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                <ul class="list-unstyled">
                    <li><i class="fa fa-home mr-2"></i> My company</li>
                    <li><i class="fa fa-envelope mr-2"></i> email@example.com</li>
                    <li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
                    <li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
                </ul>
            </div>
            <div class="col-12 copyright mt-3">
                <p class="float-left">
                    <a href="#">Back to top</a>
                </p>
                <p>yez</p>
            </div>
        </div>
    </div>
</footer>

</body>
</html>

