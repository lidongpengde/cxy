<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <title>任我行顺风车网</title>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
<%--    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=23834821b1465df3fa84571571947619"></script>--%>
    <script src="/asert/js/mricode.pagination.js"></script>
    <script src="/asert/js/jquery.serializejson.js"></script>
    <link rel="stylesheet" type="text/css" href="/asert/css/main.css" media="screen"/>

    <script src="/asert/js/jquery.messager.js"></script>
</head>
<style>
    body{
        background-color: #315481;
        background-image: linear-gradient(to bottom, #315481, #918e82 100%);
        /*position: relative;*/
    }
    .glyphicon {
        color: #337ab7;
    }
    .container{
        max-width: 800px;
        background: #d2edf4;
        min-height: 100%;
        background-image: linear-gradient(to bottom, #d0edf5, #e1e5f0 100%);
    }
</style>
<body >
<div class="container" id="app" style="margin-top: 52px;padding-top: 15px;background: #fafafa;">

        <%--头部搜索栏--%>

            <form id="searchForm" class="form-inline" onsubmit="return false">
                <div class="row " style="margin-bottom: 15px;">
                <input name="type" value="1" hidden id="type">
             <div class="col-xs-6 col-md-3" style="margin-bottom: 15px;"><input name="start" id="start" class="form-control" placeholder="出发地"></div>
             <div class="col-xs-6 col-md-3" style="margin-bottom: 15px;"><input name="end" id="end" class="form-control" placeholder="目的地"></div>
             <div class="col-xs-6 col-md-4"><input name="startTime" id="startTime" type="date" class="form-control" placeholder="出发时间"></div>
                    <input name="startAdcode" id="startAdcode" hidden="hidden" >



             <div class="col-xs-2 col-md-2"><button class="btn btn-info"  onclick="searchLineInfo()">查询</button></div>
                </div>
            </form>

        <%--tab切换栏--%>
        <div class="identity ">
            <a href="/v1/toIndexPage" ><div class="col-md-6  col-xs-6 text-center " id="tabdriver">找车</div></a>
            <a href="/v1/searchMessenger" ><div class="col-md-6 col-xs-6 text-center hover" id="tabpassenger">找人</div></a>
        </div>
            <c:forEach var="item"   items="${result}"   varStatus="status">
            
            <div class="post">
                <div class="post-content">

                        <p ><span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color:#5cb85c ;margin-right: 6px"></span><span> ${ item.start }</span><span class="price">¥${ item.price }</span></p>
                     <p> <span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color: #f0ad4e;margin-right: 6px"></span>
                         <span>${ item.end }</span>
                        </p>
                    <p >
                    <ul class="inline" style="float: left;margin-bottom: 5px;margin-top: 5px">
                        <span  class="glyphicon glyphicon-shopping-cart" >${ item.personCount }人</span>
                        <span v-if="item.plateNumber " class="glyphicon glyphicon-adjust"></span>${ item.plateNumber }
                        <a v-bind:href="'tel:'+item.userMobile" class="hidden-xs"><span class="glyphicon glyphicon-phone" aria-hidden="true" ></span>${ item.userMobile } </a>
                        <span class="glyphicon glyphicon-user" aria-hidden="true" ></span>${ item.userNickname }
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true" ></span>${ item.startTime }
                    <span  >途径：</span>${ item.passThrough }
                    <%--<span class="bargin-label" v-if="item.isbargin === 0">不议价</span>--%>
                    <%--<span class="bargin-label" v-if="item.isbargin === 1">可议价</span>--%>
                </ul>
                    </p>
                <p>

                    <%--<span class="price">¥${ item.price }</span>--%>
                </p>
                </div>

                <div class="hidden-md hidden-lg col-xs-12">
                    <a href="tel:${item.userMobile}"  v-if="item.type==0" class="  btn-subscribe" style="margin-right: 30px">电话联系</a>
                </div>
            </div>
            </c:forEach>
            <div id="page" class="m-pagination" ></div>
            <footer class="skill-ftw">
                <ul class="fixed-skill-ftw">
                    <li class="skill-ftw-li">
                        <a href="/v1/toPublishlineInfoPage" >
                            <span class="skill-ftw-item jd-goods   ">发布行程</span>
                        </a>
                    </li>
                    <li class="skill-ftw-li">
                        <a href="/v1/toIndexPage">
                            <span class="skill-ftw-item brand-goods active">查找</span>
                        </a>
                    </li>
                    <li class="skill-ftw-li">
                        <a href="/v1/myMenu" >
                            <span class="skill-ftw-item choice-goods ">我的</span>
                        </a>
                    </li>
                </ul>
            </footer>
</div>
<script src="/asert/js/jquery.autocompleter.js"></script>
<script>

 /*   var app = new Vue({
        el: '#app',
        data: {
            items: [
            ]
        }
    })*/
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
                //app.items=data;
            }
        });
        //$("#page").pagination('remote');
    }
    $(document).ready(function(){
    });

    /**
     * 提示输入
     */
    $(function () {
        $('#start').autocompleter({
            cache: false,
            // marker for autocomplete matches
            highlightMatches: true,

            // object to local or url to remote search
            source: '/v2//HintInfo' ,

            template: '${ name }',
            // show hint
            hint: false,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,
        });
        $('#end').autocompleter({
            cache: false,
            // marker for autocomplete matches
            highlightMatches: true,

            // object to local or url to remote search
            source: '/v2//HintInfo' ,

            template: '${ name }',
            // show hint
            hint: false,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,
        });
    });
</script>
</body>
</html>
