<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <link rel="shortcut icon" href="/asert/image/smalllogo.jpg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
    <link href="/asert/css/stylesheet.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
</head>
<body>
<%--<style>
    @font-face
    {
        font-family: myFirstFont;
        src: url('/asert/fonts/huawenxingkai.ttf');
    }
    .navbar-brand{
        font-family: myFirstFont;
    }
</style>--%>
<nav class="navbar navbar-default navbar-fixed-top"><div class="container">
    <div class="navbar-header">
        <a href="/" class="navbar-brand" style="color: #00A87E ;font-size: 30px;font-family:  STXingkai">
            <strong>任我行</strong><strong style="color: #0086BF">顺风车</strong><strong style="color: #F53F5B">网</strong>
            <%--<img src="/asert/image/logo.jpg" width="300" height=50>--%>
        </a>
        <a data-toggle="collapse" data-target="#NavbarCollapse" class="navbar-toggle collapsed col-md-2" href="#" aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span></a>
    </div>
    <c:choose><c:when test="${sessionScope.const_user== null}">
        <div id="NavbarCollapse" class="navbar-collapse collapse" aria-expanded="false" style="height: 227px;">
            <ul class="nav navbar-nav navbar-right">
                <li data-pricing=""><a href="/" class="menu-main"><span>绿色出行，任我行</span></a></li>
            </ul>
        </div>
    </c:when>
        <c:otherwise>
            <nav id="NavbarCollapse" class="navbar-collapse collapse" aria-expanded="true" style="height: 227px;">
                <ul class="nav navbar-nav navbar-right">
                    <li data-services="" class="menu-main has-sub-menu" onmouseover="viewDetaiMenu(1)" onmouseout="viewDetaiMenu(0)">
                        <a class="menu-child" href="/user/inner/${ sessionScope.const_user.id}" style="color: #FF8400;">
                            <span>${ sessionScope.const_user.nickName}</span></span>
                        </a>
                    </li>
                    <li data-home="" >
                        <a href="/v1/toPublishlineInfoPage" class="menu-main"><span>发布</span></a></li>
                    <li data-pricing=""><a href="/main.html" class="menu-main"><span>首页</span></a></li>
                    <li data-pricing=""><a href="/v1/toIndexPage" class="menu-main"><span>发现更多</span></a></li>
                    <li data-pricing=""><a href="/v1/myMenu" class="menu-main"><span>我的</span></a></li>
                </ul>
            </nav>
        </c:otherwise>
    </c:choose>
</div>
</nav>
<%--<div id="navigation" class="navbar navbar-inverse navbar-fixed-top " >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand crawler-logo" href="/"><img src="https://d1ovtcjitiy70m.cloudfront.net/vi-1/images/blablacar-ridesharing-logo.svg" alt="BlaBlaCar" width="126" height="21"></a>
        </div>
        <div class="navbar-collapse collapse ">
            <ul class="nav navbar-nav pull-right">
                <li data-home="" ><a href="/v1/toPublishlineInfoPage" class="menu-main"><span>发布</span></a></li>

                <li id="我的" class="menu-main has-sub-menu" data-letters="Solutions" onmouseover="viewDetaiMenu(1)" onmouseout="viewDetaiMenu(0)">
                    <a id="Solutions" href="" class="menu-main" data-letters="Solutions" >
                        <span>我的</span>
                        <ul class="sub-menu-secondary">
                            <li data-services=""><a class="menu-child" href="/v1/myPublishLineInfo">我的发布</a></li>
                            <li data-orders=""><a class="menu-child" href="/api/orders">我的订单</a></li>
                        </ul>
                    </a>
                </li>

                <li data-orders=""><a href="/api/toIdentify" class="menu-main"><span>实名认证</span></a></li>
                <li data-pricing=""><a href="/user/logout" class="menu-main"><span>退出</span></a></li>
            </ul>
        </div>
</div>--%>
<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
<script src="/asert/js/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
<script>
    var path='/cxy';

    function viewDetaiMenu(o) {
        if (o){
            $('.sub-menu-secondary').attr('style','display:block;');
        }else{

            $('.sub-menu-secondary').attr('style','display:none;');
        }

    }
</script>
</body>
</html>
