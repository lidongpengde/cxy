<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
</head>
<body style="background-color: #eee">
<%--<jsp:include page="include/header.jsp"></jsp:include>--%>
<div class="container" id="app">

    <%--头部搜索栏--%>
<div class="row">出发地<input name="start"> 目的地<input name="end">  <button>查询</button><a href="/v1/toPublishlineInfoPage">发布</a></div>
        <%--tab切换栏--%>
        <div class="row">
            <span>司机</span>
            <span>乘客</span>
        </div>
        <%--内容信息--%>
<div class="post row" v-for="item in items">
    {{ item.message }}
</div>
</div>
<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
<script src="/asert/js/vue.js"></script>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            items: []
        }
    })
    $(document).ready(function(){
        $.ajax({
            cache: true,
            type: "GET",
            url:"/v1/lineInfos",
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                debugger
                app.data=data;
            }
        });
    });


</script>
</body>
</html>
