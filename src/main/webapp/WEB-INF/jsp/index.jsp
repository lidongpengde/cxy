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
                <a href="/user/${user.userId}">我的</a>
            </li>
            <li class="dropdown">
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

        </ul>
    </div>
</nav>
    <c:forEach var="user" items="${userlist}" varStatus="status">


<div class="post col-md-5">
    <a href="#" class=""><img class="img-rounded img-responsive"  src="${user.headImage}"></a>
    <div class="post-content">
        <h3><a href="{{url}}">${user.userName}</a><span>${user.job}</span></h3>
        <p>
            ${user.income},
                ${user.age},
            <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>${user.hometownProvince}
                爱好:<span>${user.hobby}</span>
        </p>
    </div>
    <div><a href="{{pathFor 'postPage'}}" class="discuss btn btn-default">沟通一下</a></div>

</div>
<c:if test="${status.count%2=='1'}">
    <div class=" col-md-2" style="background-color: #eee;border: #ce4844;border: 1px solid #eee;"></div>
</c:if>
    </c:forEach>
</div>

<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
