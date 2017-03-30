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
<div class="container">

    <c:forEach var="user" items="${userlist}" varStatus="status">


<div class="post row">
    <a href="#" class=""><img class="img-circle img-responsive" style="width: 100px;height: 100px;float: left"  src="${user.headImage}"></a>
    <div class="post-content">
        <h3><a href="{{url}}"></a><span class="label label-danger">${user.job}</span></h3>
        <p>
                ${user.age}岁,
            <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>${user.city}
                    <c:choose>
                        <c:when test="${user.userSex=='0'}">
                            <span class="label label-default">女</span>
                        </c:when>
                        <c:otherwise>
                            <span class="label label-default">男</span>
                        </c:otherwise>
                    </c:choose>
        </p>
    </div>
    <div><a href="{{pathFor 'postPage'}}" class="discuss btn btn-default">沟通一下</a></div>

</div>
<%--<c:if test="${status.count%2=='1'}">
    <div class=" col-md-2" style="background-color: #eee;border: #ce4844;border: 1px solid #eee;"></div>
</c:if>--%>
    </c:forEach>
</div>

<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
