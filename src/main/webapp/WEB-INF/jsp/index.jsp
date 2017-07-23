<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
    <script src="/asert/js/vue.js"></script>
</head>
<body >
<%--<jsp:include page="include/header.jsp"></jsp:include>--%>
<div class="container" id="app">

    <%--头部搜索栏--%>
<div class="row">出发地<input name="start"> 目的地<input name="end">  <button>查询</button><a href="/v1/toPublishlineInfoPage">发布</a></div>
        <%--tab切换栏--%>
        <div class="row">
            <span>司机</span>
            <span>乘客</span>
        </div>

        <div class="post row" v-for="item in items">
            <%--<a href="#" class=""><img class="img-circle img-responsive" style="width: 100px;height: 100px;float: left"  src="${user.headImage}"></a>--%>
            <div class="post-content">
                <h3>{{ item.start }}至{{ item.end }}</h3>
                <p>
                    <b><span class="label label-warning">出发时间：{{ item.startTime }}</span></b>
                    </span>价格：{{ item.price }}
                    是否接受议价<span>{{ item.isbargin }}</span>
                    人数<span>{{ item.personCount }}</span>
                </p>
            </div>
                <div><a href="#" class="discuss btn btn-default" data-toggle="modal" data-target="#myModal${status.count}">沟通</a></div>
        </div>
</div>
<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            items: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ]
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
                //var jsonData=JSON.parse(data);
                app.items=data;
            }
        });
    });


</script>
</body>
</html>
