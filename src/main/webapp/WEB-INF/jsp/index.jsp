<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <a href="/user/logout">退出</a>
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
            <b><span class="label label-warning">${user.age}岁</span></b>
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
    <div><a href="#" class="discuss btn btn-default" data-toggle="modal" data-target="#myModal${status.count}">沟通一下</a></div>
    <!-- Modal -->
    <div class="modal fade" id="myModal${status.count}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <form action="" method="post" id="message">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">${user.userName}</h4>
                </div>
                <div class="modal-body">
                    <textarea class="form-control" rows="3" name="content"></textarea>
                </div>
                <input type="hidden" name="receiverId" value="${user.userId}">
                <input type="hidden" name="receiverName" value="${user.userName}">
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="sendMessage()">发送</button>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
<%--<c:if test="${status.count%2=='1'}">
    <div class=" col-md-2" style="background-color: #eee;border: #ce4844;border: 1px solid #eee;"></div>
</c:if>--%>
    </c:forEach>
</div>

<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
<script>
    function sendMessage() {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/message/addMessage",
            data:$('#message').serialize(),// 你的formid
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                alert("seccess");
            }
        });
    }

</script>
</body>
</html>
