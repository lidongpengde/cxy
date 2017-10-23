<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=23834821b1465df3fa84571571947619"></script>
    <script src="/asert/js/mricode.pagination.js"></script>
    <script src="/asert/js/jquery.serializejson.js"></script>
    <link rel="stylesheet" type="text/css" href="/asert/css/main.css" media="screen"/>

    <script src="/asert/js/jquery.messager.js"></script>
</head>
<body >
<div class="container" id="app" style="margin-top: 90px">

        <%--头部搜索栏--%>

            <form id="searchForm" class="form-inline" onsubmit="return false">
                <div class="row">
                <input name="type" value="1" hidden id="type">
             <div class="col-xs-3"><input name="start" id="start" class="form-control" placeholder="出发地"></div>
             <div class="col-xs-3"><input name="end" id="end" class="form-control" placeholder="目的地"></div>
             <div class="col-xs-6"><input name="startTime" id="startTime" type="date" class="form-control" placeholder="出发时间"></div>
                    <input name="startAdcode" id="startAdcode" hidden="hidden" >

                </div>
                <br>
                <div class="row">
             <div class="col-xs-2"><button class="btn btn-info"  onclick="searchLineInfo()">查询</button></div>
                </div>
            </form>

        <%--tab切换栏--%>
        <div class="identity ">
            <a href="#" onclick="changeIdentity(1)"><div class="col-md-6 text-center hover" id="tabdriver">找车</div></a>
            <a href="#" onclick="changeIdentity(0)"><div class="col-md-6 text-center" id="tabpassenger">找人</div></a>
        </div>
            <div class="post" v-for="item in items">

                <div class="post-content">
                    <a v-bind:href="'/api/mySubscibe/'+item.lid">
                    <h4><strong>{{ item.start }}</strong></strong><strong class="glyphicon glyphicon-arrow-right" aria-hidden="true"></strong><strong>{{ item.end }}</strong>
                        </h4></a>
                    <p>
                    <ul class="inline"></ul>
                        人数:<span class="">{{ item.personCount }}</span><span v-if="item.plateNumber ">车牌号：{{ item.plateNumber }}</span>
                        <a v-bind:href="'tel:'+item.userMobile">{{ item.userMobile }}</a>
                        <span class="glyphicon glyphicon-user" aria-hidden="true">{{ item.userNickname }}</span>
                        <span class="glyphicon glyphicon-calendar" aria-hidden="true">	{{ item.startTime }}</span>
                    </p>
                <p><span class="price">¥{{ item.price }}</span>
                    <span class="bargin-label" v-if="item.isbargin === 0">不议价</span>
                    <span class="bargin-label" v-if="item.isbargin === 1">可议价</span>

                </p>
                </div>
                <a v-bind:href="'/api/toSubscibe/'+item.lid"  v-if="item.type==1" class="discuss btn btn-danger">马上预约</a>
                <%--<span class="price discuss">¥{{ item.price }}</span>--%>
            </div>
            <div id="page" class="m-pagination" ></div>
</div>

<script>
    /***************************************
     由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
     ***************************************/
    var map, geolocation;
    var adCode
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition:'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
    //解析定位结果
    function onComplete(data) {
        adCode=data.addressComponent.adcode;
        console.log(adCode);
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
                        startTime:params.startTime,
                        startAdcode:adCode
                    };
                },
                success: function (data) {
                    app.items=data.list;
                },
                totalName:'total'
            }
        });
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
    }




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

            template: '{{ name }}',
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

            template: '{{ name }}',
            // show hint
            hint: false,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,
        });
    });
    $(document).ready(function(){
        var  classes = "LineInfo";
        var  htmltext="" ;
        $.ajax({
            type:'post',
            url:'/notice/getMsgByUser?classes='+classes,
            success:function (data){
                var dataitem = eval(data);
                jQuery.each(dataitem,function(rec){
                    htmltext = "<a class=\"menu-child\" href=\"/api/mySubscibe/"+this.businessid+"?id="+this.id+"\">"+this.messages+"</a><br>"+htmltext;
                });
                if(htmltext!=""){
                    // $('#userMsg').html(htmltext);  //我的发布下面的span标签
                    $.messager.lays(500, 200);
                    $.messager.anim('fade',2000);//messager消息提示
                    $.messager.show(0,htmltext);
                }
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
