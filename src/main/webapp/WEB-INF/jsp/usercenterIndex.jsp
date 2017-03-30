<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/style.css" rel="stylesheet" type="text/css">


    <script src="/asert/js/jquery-3.1.1.min.js"></script>

    <script src="/asert/js/ng-file-upload-shim.js"></script>
    <script src="/asert/js/ng-file-upload.js"></script>
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
            <a class="navbar-brand" href="/user/main">Microscope</a>
        </div>
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav">
                <li class="activeRouteClass ">
                    <a href="/user/main">为您推荐</a>
                </li>
                <li class="activeRouteClass">
                    <a href="{{pathFor 'bestPosts'}}">技术问答</a>
                </li>

                <li class="activeRouteClass ">
                    <a href="/user/${user.userId}">我的</a>
                </li>
                <li class="dropdown">
                    <a href="/user/logout">退出</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

            </ul>
        </div>
    </nav>
<div class="container">
    <div class="row">
    <div class="list-group">
        <a href="#" class="list-group-item active">
            个人中心
        </a>
        <a href="#" class="list-group-item"><span class="badge">14</span>我的私信</a>
        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>基本信息</a>
        <a href="/user/toUpdatePage/${user.userId}" class="list-group-item"><span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>设置基本信息</a>
    </div>
            <input type="hidden" value="${user.userId}" name="userId">

    </div>
</div>
<script>

</script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
