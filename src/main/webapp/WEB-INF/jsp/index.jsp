<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app">

        <%--头部搜索栏--%>
        <div class="row">
            <form id="searchForm" class="form-inline" onsubmit="return false">
                <input name="type" value="1" hidden id="type">
            <label for="start">出发地</label><input name="start" id="start" class="form-control">
                <label for="end">目的地</label><input name="end" id="end" class="form-control">
                <label for="startTime">出发时间</label><input name="startTime" id="startTime" type="date" class="form-control">
                <button class="btn btn-info" onclick="searchLineInfo()">查询</button>
            </form>
            <a href="/v1/toPublishlineInfoPage">发布</a>
        </div>
        <%--tab切换栏--%>
        <div class="row identity">
            <a href="#" onclick="changeIdentity(1)"><span class="col-md-6 text-center hover" id="tabdriver">司机</span></a>
            <a href="#" onclick="changeIdentity(0)"><span class="col-md-6 text-center" id="tabpassenger">乘客</span></a>
        </div>

        <div class="post row" v-for="item in items">
            <%--<a href="#" class=""><img class="img-circle img-responsive" style="width: 100px;height: 100px;float: left"  src="${user.headImage}"></a>--%>
            <div class="post-content">
                <h3><strong class="address">{{ item.start }}</strong><small>至</small><strong class="address">{{ item.end }}</strong></h3>
                <p>
                    <em>出发时间：{{ item.startTime }}</em>

                    <em v-if="item.isbargin === 0">不接受议价
                    </em>
                    <em v-if="item.isbargin === 1">接受议价
                    </em>
                    <em>人数{{ item.personCount }}</em>
                    <em >电话：{{ item.user.mobile }}</em>
                <em>发布人：{{ item.user.userName }}</em>
                    <em v-if="item.type === 1">车牌号：{{ item.plateNumber }}</em>
                </p>
            </div>
                </span><em class="price">¥{{ item.price }}</em>
        </div>
</div>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            items: [
            ]
        }
    })
    $(document).ready(function(){
        var params = $("#searchForm").serialize();
        $.ajax({
            cache: true,
            type: "GET",
            url:"/v1/lineInfos",
            data : params,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                //var jsonData=JSON.parse(data);
                app.items=data;
            }
        });
    });
    function changeIdentity(type){
        if(type==0){
            $('#tabpassenger').addClass("hover");
            $('#tabdriver').removeClass("hover");
            $('#type').val(0);

        }else{
            $('#tabdriver').addClass("hover");
            $('#tabpassenger').removeClass("hover");
            $('#type').val(1);
        }
        searchLineInfo();
    }
    function searchLineInfo(){
        //ajax提交
        var params = $("#searchForm").serialize();
        debugger
        $.ajax({
            type : "GET",
            url : "/v1/lineInfos",
            data : params,
            success : function(data) {
                app.items=data;
            }
        });
    }
</script>
</body>
</html>
