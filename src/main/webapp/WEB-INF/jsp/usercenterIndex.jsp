<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>用户中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/main.css" media="screen"/>
</head>
<style>
    ul {
        margin: 0;
        padding: 0;
    }
</style>
<body>
<div class="container" style="min-height: 100%;background: #fafafa" id="myapp">
    <div class="row">
    <div class="list-group">
        <a href="#" class="list-group-item active">
            个人中心
        </a>
        <%--<a href="/user/inner" class="list-group-item">--%>
            <%--<span class="glyphicon glyphicon glyphicon-user" aria-hidden="true"></span>--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>--%>
            <%--基本信息(车主/乘客切换)--%>
        <%--</a>--%>
        <%--<a href="/api/toIdentify" class="list-group-item">--%>

            <%--<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>实名认证--%>
        <%--</a>--%>
        <%--<a href="/v1/myPublishLineInfo" class="list-group-item">--%>

            <%--<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>我的发布--%>
        <%--</a>--%>
        <%--<a href="/template/noticeList.html" class="list-group-item">--%>

            <%--<span class="glyphicon glyphicon-bell" aria-hidden="true"></span>--%>
            <%--<span class="badge" aria-hidden="true" style="float: right;">{{message}}</span>我的通知--%>
        <%--</a>--%>
        <%--<a href="/user/logout" class="list-group-item">--%>

            <%--<span class="glyphicon glyphicon-off" aria-hidden="true"></span>--%>
            <%--<span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>退出--%>
        <%--</a>--%>
        <em>抱歉，此功能暂时关闭。</em>
    </div>
            <%--<input type="hidden" value="${user.userId}" name="userId">--%>

    </div>
    <footer class="skill-ftw">
        <ul class="fixed-skill-ftw">
            <li class="skill-ftw-li">
                <a href="/v1/toPublishlineInfoPage" >
                    <span class="skill-ftw-item jd-goods  ">发布行程</span>
                </a>
            </li>
            <li class="skill-ftw-li">
                <a href="/template/searchIndex.html" >
                    <span class="skill-ftw-item brand-goods ">查找</span>
                </a>
            </li>
            <li class="skill-ftw-li">
                <a href="/v1/myMenu">
                    <span class="skill-ftw-item choice-goods  active" >我的</span>
                </a>
            </li>
        </ul>
    </footer>
</div>
<script>
    var myapp = new Vue({
        el: '#myapp',
        data: {
            items: [
            ],
            message:""
        }
    })
</script>
<script src="/asert/js/bootstrap.js"></script>
<%--<jsp:include page="include/foot.jsp"></jsp:include>--%>
</body>
</html>
