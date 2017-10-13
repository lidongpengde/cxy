<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
    <script src="/asert/js/mricode.pagination.js"></script>
    <script src="/asert/js/jquery.serializejson.js"></script>
    <script src="/asert/js/jquery.messager.js"></script>
</head>
<body >

<div class="container" id="app" style="margin-top: 90px">

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
            <a href="#" onclick="changeIdentity(1)"><div class="col-md-6 text-center hover" id="tabdriver">人找车</div></a>
            <a href="#" onclick="changeIdentity(0)"><div class="col-md-6 text-center" id="tabpassenger">车找人</div></a>
        </div>
            <div class="post" v-for="item in items">

                <div class="post-content">
                    <a v-bind:href="'/api/mySubscibe/'+item.lid">
                    <h3><strong>{{ item.start }}</strong>	&nbsp;</strong><strong class="glyphicon glyphicon-arrow-right" aria-hidden="true"></strong>	&nbsp;<strong>{{ item.end }}</strong>	&nbsp;
                        <span class="glyphicon glyphicon-earphone" aria-hidden="true">{{ item.user.mobile }}</span></h3></a>
                    <p>
                        人数:	&nbsp;<span class="">{{ item.personCount }}</span>		&nbsp;&nbsp;<span v-if="item.plateNumber ">车牌号：{{ item.plateNumber }}</span>
                        &nbsp;	&nbsp;
                        <span class="glyphicon glyphicon-user" aria-hidden="true">{{ item.user.nickName }}</span>
                        &nbsp;	&nbsp;
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true">	{{ item.startTime }}</span>
                        <%--<span class="price">¥{{ item.price }}</span>--%>
                    </p>
                <p><span class="price">¥{{ item.price }}</span>
                    <span class="bargin-label" v-if="item.isbargin === 0">不议价</span>
                    <span class="bargin-label" v-if="item.isbargin === 1">可议价</span>
                </p>
                </div>
                <a v-bind:href="'/api/toSubscibe/'+item.lid"  v-if="item.type==1" class="discuss btn btn-default">马上预约</a>
                <%--<span class="price discuss">¥{{ item.price }}</span>--%>
            </div>
            <div id="page" class="m-pagination" ></div>
</div>

<script>
    var app = new Vue({
        el: '#app',
        data: {
            items: [
            ]
        }
    })
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
      /*  //ajax提交
        var params = $("#searchForm").serialize();
        $.ajax({
            type : "GET",
            url : "/v1/lineInfos",
            data : params,
            success : function(data) {
                app.items=data.list;
            }
        });*/
        $("#page").pagination('remote');
    }
    $("#page").pagination({
        pageSize: 10,
        remote: {
            url: '/v1/lineInfos',
            pageParams: function(data){
                var params = $("#searchForm").serializeJSON();
                return {
                    pageIndex:data.pageIndex,
                    pageSize:data.pageSize,
                    type:params.type,
                    start:params.start,
                    end:params.end,
                    startTime:params.startTime
                };
            },
            success: function (data) {
                app.items=data.list;
            },
            totalName:'total'
        }
    });
    $(document).ready(function(){
        $.ajax({
            type:'post',
            url:'/v1/getMsgByUser',
            success:function (data){
               $('#userMsg').html(data);  //我的发布下面的span标签
               // $.messager.anim('fade',1000);//messager消息提示
               // $.messager.show(0,data);
            },
            error:function (e) {
                alert("系统繁忙"+e);
            }
        })
    })
</script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
