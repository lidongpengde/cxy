<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
    <link href="/asert/css/stylesheet.css" rel="stylesheet" type="text/css">
</head>
<body style="background-color: #eee">
<%--<nav class="navbar navbar-default" role="navigation">--%>

    <%--<a class="header-logo-invertocat" style="padding: 8px 20px;float: left" >--%>
        <%--<svg aria-hidden="true" class="logo" height="48" version="1.1" viewBox="0 0 16 16" width="48"><path fill-rule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path></svg>--%>

    <%--</a>--%>
    <%--<h3 style="float: left">A ride whenever you need one</h3>--%>
    <%--<a href="/v1/toPublishlineInfoPage" style="float: right;margin: 20px" >发布</a>--%>
    <%--<a href="/v1/myPublishLineInfo" class="" style="float: right;margin: 20px">我的发布</a>--%>
    <%--<a href="/user/logout" class="" style="float: right;margin: 20px">退出</a>--%>
<%--</nav>--%>
<div id="navigation" class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand crawler-logo" href="/"><b class="app-name">A ride whenever you need one</b></a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav pull-right">
                <li data-home="" class="active"><a href="/v1/toPublishlineInfoPage">发布</a></li>
                <li data-services=""><a href="/v1/myPublishLineInfo">我的发布</a></li>
                <li data-orders=""><a href="/api/orders">我的订单</a></li>
                <li data-pricing=""><a href="/user/logout">退出</a></li>
                <%--<li data-support=""><a href="/Support">Support</a></li>--%>

                <%--<li data-register=""><a href="/Home/Register" class="btn btn-danger2" data-toggle="tooltip" data-placement="bottom" title="No Credit Card Required">TRY IT FOR FREE</a></li>--%>
                <%--<li data-login=""><a href="/Home/Login" class="btn btn-default">Login</a></li>--%>
            </ul>
        </div>
    </div>
</div>
<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
<script src="/asert/js/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
<script>
    var path='/cxy';
</script>
</body>
</html>
