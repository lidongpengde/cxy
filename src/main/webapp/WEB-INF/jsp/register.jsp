<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=23834821b1465df3fa84571571947619"></script>
    <script type="text/javascript" src="/asert/js/vue.js"></script>
    <script type="text/javascript" src="/asert/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/asert/js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="header-logo-invertocat" style="padding: 20px 20px">
            <svg aria-hidden="true" class="logo" height="48" version="1.1" viewBox="0 0 16 16" width="48"><path fill-rule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path></svg>
        </a>
    </div>
    <div class="collapse navbar-collapse" id="navigation">
        <ul class="nav navbar-nav">
            <li class="activeRouteClass ">
                <a href="pathFor newPosts">为您推荐</a>
            </li>
            <li class="activeRouteClass">
                <a href="{{pathFor 'bestPosts'}}">技术问答</a>
            </li>

            <li class="activeRouteClass ">
                <a href="/user/${user.userId}">我的</a>
            </li>
            <li class="dropdown">
                <a href="/user/logout">退出</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">

        </ul>
    </div>
</nav>
<div class="container" id="register">
    <div class="row">
        <div class="col-md-8 "><form action="savelogin">
            <div class="thumbnail">
            <div class="form-group"><label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" type="text" required></div>
            <div class="form-group"><label for="userPassword">密码：</label><input id="userPassword"class="form-control" name="userPassword" type="password" required></div>
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

            <input name="province" type="hidden" v-model="province">
            <input name="city" type="hidden" v-model="city">
            <input name="area" type="hidden" v-model="area">
            <div class="form-group">
                <label>
                    <input type="radio"name="userSex"value="1" checked> 男
                </label>
                <label>
                    <input type="radio" name="userSex" value="0"> 女
                </label>

            </div>
            <button type="submit" class="btn btn-primary  btn-block">成为会员</button>
            <div id="container"></div>
            </div>
        </form></div>
        <div class="col-md-4">
            <div class="thumbnail">
                <img src="/asert/img/registerzhenai.png" alt="程序缘">
                <div class="caption">
                    <h3>100亿程序员都在使用</h3>
                    <p>不要犹豫</p>
                </div>
            </div>
        </div>
    </div>


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
