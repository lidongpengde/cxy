<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <link rel="shortcut icon" href="/asert/image/smalllogo.jpg" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
    <link href="/asert/css/stylesheet.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
</head>
<body>
<style>
    .navbar{
        background: #d2edf4;
        background-image: linear-gradient(to bottom, #d0edf5, #e1e5f0 100%);
    }
</style>
<nav class="navbar navbar-default navbar-fixed-top"><div class="container" id="headapp">
    <div class="navbar-header">
        <a href="/" class="navbar-brand">
            <img src="/asert/image/newlogo.png" style="margin-top: -5px" width="200" height="30" class="img-responsive" >
        </a>
        <a  class="navbar-toggle collapsed col-md-2" href="javascript:history.back(-1)">
            <%--<span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>--%>
            <span class="glyphicon glyphicon-arrow-left" style="font-size: 16px;color: #888;"></span>
        </a>
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
                        <a href="/v1/toPublishlineInfoPage" class="menu-main"><span>发布行程</span></a></li>
                    <%--<li data-pricing=""><a href="/main.html" class="menu-main"><span>首页</span></a></li>--%>
                    <li data-pricing=""><a href="/v1/toIndexPage" class="menu-main"><span>查找</span></a></li>
                    <li data-pricing=""><a href="/v1/myMenu" class="menu-main"><span>我的<span class="badge ">{{message}}</span></span></a></li>
                </ul>
            </nav>
        </c:otherwise>
    </c:choose>
</div>
</nav>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.js"></script>
<script src="https://cdn.bootcss.com/vue/2.5.3/vue.js"></script>
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
    var headapp = new Vue({
        el: '#headapp',
        data: {
            items: [
            ],
            message:""
        }
    })
    $(document).ready(function(){
        var  classes = "LineInfo";
        var  htmltext="" ;
        $.ajax({
            type:'post',
            url:'/notice/getMsgByUser?classes='+classes,
            success:function (data){
                if (data){
                    headapp.message=data.length;
                }
            },
            error:function (e) {
                console.log("系统繁忙"+e);
            }
        })
    })
</script>
</body>
</html>
