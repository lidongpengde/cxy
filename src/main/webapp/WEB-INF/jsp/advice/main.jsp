<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>blablacar</title>
    <link rel="stylesheet" href="/asert/css/main1119.css"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.1&key=23834821b1465df3fa84571571947619&plugin=AMap.Autocomplete"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<body>

<div id='container'></div>
<div id="banner-home">
    <div class="action-banner wow fadeInUp" style="visibility: visible; animation-name: fadeInUp;">
        <a id="installnow" href="/v1/toPublishlineInfoPage" class="btn-custom hvr-rectangle-out"> publish</a>
    </div>
</div>
<div id="myPageTop">
    <div class="start">
        <label>您当前位置</label>
        <input type="text" name="startlocationname" id="startlocationname">
        <input type="text" name="startlocationadcode" id="startlocationadcode">
    </div>
    <div>
       <label>请输入目的地：</label>
       <input id="detanationname"/>
       <input type="text" name="detanationadcode" id="detanationadcode">
    </div>
    <div>
        <div class="action-banner wow fadeInUp" style="visibility: visible; animation-name: fadeInUp;">
            <a id="" href="/v1/toPublishlineInfoPage" class="btn-custom hvr-rectangle-out-bg">searchcar</a>
            <a id="more-features" href="/tutorials" class="btn-custom hvr-rectangle-out"> searchperson</a>
        </div>
    </div>
</div>
<div id="tip"></div>
<script type="text/javascript">
/************************************
由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
***************************************/
    var map, geolocation;
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
        var str=['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        if(data.accuracy){
             str.push('精度：' + data.accuracy + ' 米');
        }//如为IP精确定位结果则没有精度信息
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        /*document.getElementById('tip').innerHTML = str.join('<br>');*/
        document.getElementById("startlocationname").value=data.formattedAddress;
        document.getElementById("startlocationadcode").value=data.addressComponent.adcode;
/*        $('#startlocationname').setValue(data.formattedAddress);
        $('#startlocationadcode').setValue(data.addressComponent.adcode);*/
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
    }
    //输入提示
    var auto = new AMap.Autocomplete({
        input: "detanationname"
    });
      AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
    	document.getElementById('detanationname').value = e.poi.name;
    	document.getElementById('detanationadcode').value = e.poi.adcode;
        console.log(e.poi.adcode);
        console.log(e.poi.name);  //关键字查询查询
    }
    function goSearchResult() {
        var startlocationname=document.getElementById('startlocationname').value;
        var startlocationadcode=document.getElementById('startlocationadcode').value;
        var detanationname=document.getElementById('detanationname').value;
        var detanationadcode=document.getElementById('detanationadcode').value;
        localStorage.setItem("startlocationname", startlocationname);
        localStorage.setItem("startlocationadcode", startlocationadcode);
        localStorage.setItem("detanationname", detanationname);
        localStorage.setItem("detanationadcode", detanationadcode);
        location.href="searchResultOK.html"
    }
</script>
</body>
</html>