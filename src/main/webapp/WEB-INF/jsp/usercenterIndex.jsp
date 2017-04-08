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
            <a class="header-logo-invertocat" style="padding: 20px 20px">
                <svg aria-hidden="true" class="logo" height="48" version="1.1" viewBox="0 0 16 16" width="48"><path fill-rule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path></svg>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav">
                <li class="activeRouteClass ">
                    <a href="/user/main">为您推荐</a>
                </li>
                <li class="activeRouteClass">
                    <a href="#">技术问答</a>
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
        <a href="/message/list/${user.userId}" class="list-group-item"><span class="badge">${msgCount}</span>我的私信</a>
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
