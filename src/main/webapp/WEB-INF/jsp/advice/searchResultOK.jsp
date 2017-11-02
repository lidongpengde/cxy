<!doctype html>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>blablacar</title>
    <link rel="stylesheet" href="/asert/css/main1119.css"/>
    <script src="/asert/js/vue.js"></script>
    <script src="/asert/js/jquery-3.1.1.min.js"></script>
<body>
<div id="app">
    <div id="banner-home">
    <div class="action-banner wow fadeInUp" style="visibility: visible; animation-name: fadeInUp;">
        <a id="installnow" href="/v1/toPublishlineInfoPage" class="btn-custom hvr-rectangle-out-bg">发布行程</a>
        <a id="more-features" href="/tutorials" class="btn-custom hvr-rectangle-out"> tutorial</a>
    </div>
    </div>
<div class="post" v-for="item in items">

    <div class="post-content">
            <h3><strong>{{ item.start }}</strong></strong><span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span><strong>{{ item.end }}</strong>
            </h3>
        <p>
        <span class="price">¥{{ item.price }}</span>
        <span class="glyphicon glyphicon-calendar" aria-hidden="true">	{{ item.startTime }}</span>
        </p>
        <p>
            <a v-bind:href="'tel:'+item.userMobile"  v-if="item.type==1" class="discuss btn action-banner">电话联系</a>
            <a v-bind:href="'/api/toSubscibe/'+item.lid"  v-if="item.type==1" class="discuss btn contact-person">预约</a>
        </p>
    </div>
</div>
</div>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            items: [
            ]
        }
    });
    var params={
        startAdcode:localStorage.getItem("startlocationadcode"),
        endtAdcode:localStorage.getItem("detanationadcode")
    };
    $(document).ready(function () {
        $.ajax({
            type : "GET",
            url : "/location/lineInfo",
            data : params,
            success : function(data) {
                debugger
                app.items=data.buessObj;
            }
        });
    });
    console.log(localStorage.getItem("startlocationname"));
    console.log(localStorage.getItem("startlocationadcode"));
    console.log(localStorage.getItem("detanationname"));
    console.log(localStorage.getItem("detanationadcode"));
</script>
</body>
</html>