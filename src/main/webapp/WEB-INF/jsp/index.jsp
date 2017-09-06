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
             <div class="form-group"><label for="start">出发地</label><input name="start" id="start" class="form-control"></div>
             <div class="form-group"><label for="end">目的地</label><input name="end" id="end" class="form-control"></div>
             <div class="form-group"> <label for="startTime">出发时间</label><input name="startTime" id="startTime" type="date" class="form-control"></div>
             <div class="form-group"><button class="btn btn-info"  onclick="searchLineInfo()">查询</button></div>
            </form>

        </div>
        <%--tab切换栏--%>
        <div class="identity ">
            <a href="#" onclick="changeIdentity(1)"><div class="col-md-6 text-center hover" id="tabdriver">司机</div></a>
            <a href="#" onclick="changeIdentity(0)"><div class="col-md-6 text-center" id="tabpassenger">乘客</div></a>
        </div>
            <div class="post" v-for="item in items">

                <div class="post-content">
                    <h3><strong>{{ item.start }}</strong>	&nbsp;</strong><strong class="glyphicon glyphicon-arrow-right" aria-hidden="true"></strong>	&nbsp;<strong>{{ item.end }}</strong>	&nbsp;
                        <span class="glyphicon glyphicon-earphone" aria-hidden="true">{{ item.user.mobile }}</span></h3>
                    <p>
                        人数:	&nbsp;<span class="">{{ item.personCount }}</span>		&nbsp;&nbsp;车牌号：{{ item.plateNumber }}
                        &nbsp;	&nbsp;
                        <span class="glyphicon glyphicon-user" aria-hidden="true">{{ item.user.userName }}</span>
                        &nbsp;	&nbsp;
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true">	{{ item.startTime }}</span>
                        <%--<span class="price">¥{{ item.price }}</span>--%>
                    </p>
                <p><span class="price">¥{{ item.price }}</span>
                    <span class="" v-if="item.isbargin === 0">不议价</span>
                    <span class="" v-if="item.isbargin === 1">可议价</span>
                </p>
                </div>
                <a v-bind:href="'/api/order/'+item.lid" class="discuss btn btn-default">马上预约</a>

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
