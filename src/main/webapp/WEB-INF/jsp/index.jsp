<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
</head>
<body>
<div class="container">
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="{{pathFor 'home'}}">Microscope</a>
    </div>
    <div class="collapse navbar-collapse" id="navigation">
        <ul class="nav navbar-nav">
            <li class="activeRouteClass ">
                <a href="pathFor newPosts">为您推荐</a>
            </li>
            <li class="activeRouteClass">
                <a href="{{pathFor 'bestPosts'}}">技术问答</a>
            </li>

            <li class="activeRouteClass ">
                <a href="{{pathFor 'postSubmit'}}">我的</a>
            </li>
            <li class="dropdown">
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

        </ul>
    </div>
</nav>
    <c:forEach var="user" items="${userlist}">


<div class="post">
    <a href="#" class="upvote"><img class="img-rounded img-responsive" src="/asert/img/Koala.jpg"></a>
    <div class="post-content">
        <h3><a href="{{url}}">${user.userName}</a><span>${user.job}</span></h3>
        <p>
            ${user.income},
                ${user.age},
            <a href="{{pathFor 'postPage'}}">${user.hometownProvince}</a>
            <a href="{{pathFor 'postEdit'}}">${user.hobby}</a>
        </p>
    </div>
    <a href="{{pathFor 'postPage'}}" class="discuss btn btn-default">Discuss</a>
</div>
    </c:forEach>
</div>

<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.min.js"></script>
</body>
</html>
