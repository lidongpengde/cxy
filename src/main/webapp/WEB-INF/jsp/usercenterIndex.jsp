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
<jsp:include page="include/header.jsp"></jsp:include>
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
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
