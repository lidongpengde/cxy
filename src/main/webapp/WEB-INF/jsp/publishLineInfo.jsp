<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
    <title>任我行顺风车网</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <%-- <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap-datetimepicker.min.css" media="screen"/>--%>
    <link rel="stylesheet" type="text/css" href="/asert/css/main.css" media="screen"/>
    <script src="/asert/js/jquery.autocompleter.js"></script>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.4.1&key=23834821b1465df3fa84571571947619&plugin=AMap.Autocomplete"></script>

</head>
<style>
    .amap-sug-result {
        position: absolute;
    }

    body {
        background-color: #315481;
        background-image: linear-gradient(to bottom, #315481, #918e82 100%);
        /*position: relative;*/
    }

    .container {
/*        padding-top: 10px;*/
        max-width: 600px;
        margin: 0 auto;
        min-height: 100%;
        /*background: #d2edf4;*/
        /*background-image: linear-gradient(to bottom, #d0edf5, #e1e5f0 100%);*/
    }

    @media (min-width: 600px) {
        .hello {
            min-height: 600px !important;
            width: 500px;
        }
    }
</style>
<style type="text/css">
    ul,
    li {
        list-style: none;
    }

    a {
        text-decoration: none;
    }

    a:hover {
        cursor: pointer;
        text-decoration: none;
    }

    a:link {
        text-decoration: none;
    }

    img {
        vertical-align: middle;
    }

    .btn-numbox {
        overflow: hidden;
        margin-top: 20px;
    }

    .btn-numbox li {
        float: left;
    }

    .btn-numbox li .number {
        display: inline-block;
        /*font-size: 12px;
        color: #808080;*/
        vertical-align: sub;
    }

    .btn-numbox .count {
        overflow: hidden;
        margin: 0 16px 0 16px;
    }

    .btn-numbox .count .num-jian,
    .input-num,
    .num-jia {
        display: inline-block;
        width: 28px;
        height: 28px;
       /* line-height: 28px;*/
        text-align: center;
        font-size: 18px;
        color: #999;
        cursor: pointer;
        border: 1px solid #e6e6e6;
    }


    .btn-numbox .count .input-num {
        width: 58px;
        height: 28px;
        color: #333;
        border-left: 0;
        border-right: 0;
    }
</style>
<body>
<div class="container hello" style="margin-top: 55px;background: #fafafa;">
    <div class="main">
        <div id="app">

            <form id="message" onsubmit="return false">
                <div class="errors"><span id="errormsg"></span></div>
                <div class="form-group">
                    <label>
                        <input type="radio" name="type" value="1" checked v-on:click="selectType(1)"> 我是司机
                    </label>
                    <label>
                        <input type="radio" name="type" value="0" v-on:click="selectType(0)"> 我是乘客
                    </label>
                </div>
                <div class="form-group">
                    <input placeholder="出发地" type="text" name="start" id="start" class="form-control"
                           onblur="checkValid(this)" value="${alterLine.start}"></div>
                <div class="form-group">
                    <input placeholder="目的地" type="text" name="end" id="end" class="form-control"
                           onblur="checkValid(this)" value="${alterLine.end}"></div>
                <%--<div class="form-group" >
                  <input placeholder="价格" type="number" name="price" id="price" class="form-control" onblur="checkValid(this)" >
                </div>--%>
                <div class="input-group form-group">
                    <span class="input-group-addon">¥</span>
                    <input placeholder="价格" type="number" name="price" id="price" min="0" class="form-control"
                           onblur="checkValid(this)" value="${alterLine.price}">
                    <span class="input-group-addon">/人</span>
                </div>
                <div class="form-group">
                    <label>
                        <input type="radio" name="isbargin" value="1" checked> 接受议价
                    </label>
                    <label>
                        <input type="radio" name="isbargin" value="0"> 不接受
                    </label>
                </div>
                <div class="form-group">
                    <ul class="btn-numbox">
                        <li><span class="number">人数</span></li>
                        <li>
                            <ul class="count">
                                <li><span id="num-jian" class="num-jian">-</span></li>
                                <li><input type="text" class="input-num" id="input-num" name="personCount" max="4"
                                           min="1" value="1"/></li>
                                <li><span id="num-jia" class="num-jia">+</span></li>
                            </ul>
                        </li>
                        　　　
                    </ul>
                </div>
                <div class="form-group">
                    <input placeholder="车牌号:后四位即可" type="text" name="plateNumber" id="plateNumber" class="form-control"
                           value="${alterLine.plateNumber}"></div>
                <div class="form-group">
                    <input type="datetime-local" name="startTime" id="startTime" class="form-control" title="出发时间"
                           step="1">
                </div>
                <br/>
                <div class="form-group">
                    <button class="btn btn-primary" onclick="submitline()" style="width: 100%">提交</button>
                </div>
                <input name="startAdcode" id="startAdcode" hidden="hidden" value="${alterLine.startAdcode}">
                <input name="startLongitude" id="startLongitude" hidden="hidden" value="${alterLine.startLongitude}">
                <input name="startLatitude" id="startLatitude" hidden="hidden" value="${alterLine.startLatitude}">
                <input name="endAdcode" id="endAdcode" hidden="hidden" value="${alterLine.endAdcode}">
                <input name="endLongitude" id="endLongitude" hidden="hidden" value="${alterLine.endLongitude}">
                <input name="endLatitude" id="endLatitude" hidden="hidden" value="${alterLine.endLatitude}">
                <input name="userNickname" id="userNickname" hidden="hidden" value="${alterLine.userNickname}">
                <input name="userMobile" id="userMobile" hidden="hidden" value="${alterLine.userMobile}">
                <input name="status" id="status" hidden="hidden" value="${alterLine.status}">
                <input name="lid" id="lid" hidden="hidden" value="${alterLine.lid}">
            </form>
        </div>
    </div>
    <footer class="skill-ftw">
        <ul class="fixed-skill-ftw">
            <li class="skill-ftw-li">
                <a href="/v1/toPublishlineInfoPage" onclick="return seckillMPingJDSeckill(this.href)">
                    <span class="skill-ftw-item jd-goods  active ">发布行程</span>
                </a>
            </li>
            <li class="skill-ftw-li">
                <a href="/template/searchIndex.html" onclick="return seckillMPingNewBrandSecKill(this.href)">
                    <span class="skill-ftw-item brand-goods ">查找</span>
                </a>
            </li>
            <li class="skill-ftw-li">
                <a href="/v1/myMenu" onclick="return seckillMPingBrandSecKill(this.href)">
                    <span class="/v1/myMenu">我的</span>
                </a>
            </li>
        </ul>
    </footer>
</div>
<%--<script src="/asert/js/bootstrap-datetimepicker.js"></script>
<script src="/asert/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<script>
    var map, geolocation;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
        resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function () {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition: 'RB'
        });
        map.addControl(geolocation);
        geolocation.getCurrentPosition();
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
    });
    //解析定位结果
    function onComplete(data) {
        var str = ['定位成功'];
        /*document.getElementById('tip').innerHTML = str.join('<br>');*/
        var start=$('#start').val();
        if (!start){
            document.getElementById("start").value = data.formattedAddress;
        }
        document.getElementById("startAdcode").value = data.addressComponent.adcode;
    }
    var app = new Vue({

        el: '#app',
        data: {
            options: '',
            error: ''
        },
        methods: {
            submit: function (event) {
                //ajax提交

                var params = $("#message").serialize();
                $.ajax({
                    type: "POST",
                    url: "/v1/lineInfos",
                    data: params,
                    success: function (msg) {
                        alert("success: " + msg);
                    }
                });

            },
            selectType: function (message) {
                var type = message;
                console.log(type);
                if (type == 1) {
                    $('#plateNumber').show();
                } else {
                    $('#plateNumber').hide();
                }
            }
        }

    });
    var flag = true;
    var validatebeforeSubmit = function () {
        var start = $('#start').val();
        var end = $('#end').val();
        var price = $('#price').val();
        var personCount = $('#input-num').val();
        var plateNumber = $('#plateNumber').val();
        var startTime = $('#startTime').val()
        if (start && end && price && personCount && startTime) {
            flag = true;
            $("#errormsg").fadeOut();
        } else {
            $("#errormsg").text("所填信息不完整");
            $("#errormsg").fadeIn();
            flag = false;
        }
    }
    function submitline() {
        validatebeforeSubmit();
        if (!flag) {
            return false;
        }
        //ajax提交
        var params = $("#message").serialize();
        $.ajax({
            type: "POST",
            url: "/v1/lineInfo",
            data: params,
            success: function (data) {
                if (data.code == 200) {
                    location.href = "/template/searchIndex.html";
                } else {
                    alert(data.message)
                }

            }
        });

    }
    function checkValid(form) {
        var inputVal = form.value;
        if (inputVal) {
            $("#errormsg").fadeOut();
            return true;
        } else {
            var errormsg = $("#errormsg");
            $("#errormsg").text("所填信息不能为空");
            $("#errormsg").fadeIn().focus(errormsg);
            return false;
        }

    }
    /**
     * 提示输入
     */
    var startauto = new AMap.Autocomplete({
        input: "start"
    });
    AMap.event.addListener(startauto, "select", startselect);//注册监听，当选中某条记录时会触发
    function startselect(e) {
        debugger
        $('#startAdcode').val(e.poi.adcode);
        $('#startLongitude').val(e.poi.location.lng);
        $('#startLatitude').val(e.poi.location.lat);
    }

    var endauto = new AMap.Autocomplete({
        input: "end"
    });
    AMap.event.addListener(endauto, "select", endselect);//注册监听，当选中某条记录时会触发
    function endselect(e) {
        $('#endAdcode').val(e.poi.adcode);
        $('#endLongitude').val(e.poi.location.lng);
        $('#endLatitude').val(e.poi.location.lat);
    }
    /**
     * 提示输入
     */
    $(function () {
    });
    //时间选择框设置为当前时间
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            /*     "q+": Math.floor((this.getMonth() + 3) / 3), //季度
             "S": this.getMilliseconds() //毫秒*/
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    var startTime = document.getElementById('startTime');
    var time = new Date().Format("yyyy-MM-ddTHH:mm:ss");
    startTime.value = time;
    /**
     * 加减控件
     * @type {Element}
     */

    var num_jia = document.getElementById("num-jia");
    var num_jian = document.getElementById("num-jian");
    var input_num = document.getElementById("input-num");
    num_jia.onclick = function () {
        if (input_num.value >= 4) {
            input_num.value = 4;
        } else {
            input_num.value = parseInt(input_num.value) + 1;
        }

    }
    num_jian.onclick = function () {
        if (input_num.value <= 0) {
            input_num.value = 0;
        } else {
            input_num.value = parseInt(input_num.value) - 1;
        }
    }
</script>
<%--<jsp:include page="include/foot.jsp"></jsp:include>--%>
</body>
</html>