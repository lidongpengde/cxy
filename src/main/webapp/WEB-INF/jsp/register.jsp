<%--
  Created by IntelliJ IDEA.
  User: lidp
  Date: 2017/3/19
  Time: 下午8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=23834821b1465df3fa84571571947619"></script>
    <script type="text/javascript" src="/asert/js/vue.js"></script>
</head>
<body>
<div>

    <form action="savelogin">
        <div class="container" id="register">
            <h2>会员注册中心</h2>
            <div class="form-group"><label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" type="text"></div>
            <div class="form-group"><label for="userPassword">密码：</label><input id="userPassword"class="form-control" name="userPassword" type="password"></div>
            <div class="form-group"><label for="job">工作：</label>
                <select class="form-control"id="job" name="job">
                    <option>java工程师</option>
                    <option>android</option>
                    <option>ios</option>
                    <option>node.js</option>
                    <option>hadoop</option>
                </select>
            </div>
            <div class="form-group"><label for="age">年龄：</label><input id="age" name="age" type="number"class="form-control"></div>

            <input name="province" type="text" v-model="province">
            <input name="city" type="text" v-model="city">
             <input name="area" type="text" v-model="area">
            <div class="form-group">
                <label>
                    <input type="radio" name="userSex" value="0"> 女
                </label>
                <label>
                    <input type="radio"name="userSex"value="1"> 男
                </label>
            </div>
        <button type="submit" class="btn btn-default">成为会员</button>
        </div>
    </form>
</div>
<script type="text/javascript">
    var register = new Vue({
        el: '#register',
        data: {
            province: '',
            city:'',
            area:''
        }
    })
    /***************************************
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
        debugger
        register.province=data.addressComponent.province;
        if(data.addressComponent.city)
            register.city=data.addressComponent.city;
        else
            register.city=data.addressComponent.province;
        register.area=data.addressComponent.district;
        var alladdress=data.formattedAddress;
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
    }
</script>
</body>
</html>
