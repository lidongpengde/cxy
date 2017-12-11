<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" style="margin-top: 70px" id="myapp">
    <div class="row">
    <div class="list-group">
        <a href="#" class="list-group-item active">
            个人中心
        </a>
        <a href="/user/inner/${ sessionScope.const_user.id}" class="list-group-item">
            <span class="glyphicon glyphicon glyphicon-user" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>
            基本信息
        </a>
        <a href="/api/toIdentify" class="list-group-item">

            <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>实名认证
        </a>
        <a href="/v1/myPublishLineInfo" class="list-group-item">

            <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>我的发布
        </a>
        <a href="/template/noticeList.html" class="list-group-item">

            <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>
            <span class="badge" aria-hidden="true" style="float: right;">{{message}}</span>我的通知
        </a>
        <a href="/user/logout" class="list-group-item">

            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true" style="float: right;"></span>退出
        </a>
    </div>
            <input type="hidden" value="${user.userId}" name="userId">

    </div>
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
    $(document).ready(function(){
        var  classes = "LineInfo";
        var  htmltext="" ;
        $.ajax({
            type:'post',
            url:'/notice/getMsgByUser?classes='+classes,
            success:function (data){
                myapp.message=data.length;
            },
            error:function (e) {
                alert("系统繁忙"+e);
            }
        })
    })
</script>
<script src="/asert/js/bootstrap.js"></script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
