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
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.1&key=23834821b1465df3fa84571571947619&plugin=AMap.Autocomplete"></script>

</head>
<style>
    .amap-sug-result{
        position: absolute;
    }
</style>
<body>
<div class="container" style="margin-top: 70px">
    <div class="main">
      

<%--<h4 class="title">--%>
  <%--<div class="normal-title">--%>
    <%--<p class="lead text-left"><strong>发布需求</strong> </p>--%>
  <%--</div>--%>
<%--</h4>--%>
<div id="app">

  <form id="message" onsubmit="return false">
      <div class="errors" ><span id="errormsg"></span></div>
    <div class="form-group">
      <label>
        <input type="radio"name="type"value="1" checked v-on:click="selectType(1)"> 我是司机
      </label>
      <label>
        <input type="radio" name="type" value="0" v-on:click="selectType(0)"> 我是乘客
      </label>
    </div>
    <div class="form-group" >
      <input placeholder="出发地" type="text" name="start" id="start"  class="form-control"  onblur="checkValid(this)" value="${alterLine.start}"></div>
    <div class="form-group" >
      <input placeholder="目的地" type="text" name="end" id="end" class="form-control" onblur="checkValid(this)" value="${alterLine.end}"></div>
    <%--<div class="form-group" >
      <input placeholder="价格" type="number" name="price" id="price" class="form-control" onblur="checkValid(this)" >
    </div>--%>
      <div class="input-group form-group">
          <span class="input-group-addon">¥</span>
          <input placeholder="价格" type="number" name="price" id="price" min="0"  class="form-control" onblur="checkValid(this)" value="${alterLine.price}" >
          <span class="input-group-addon">.00</span>
      </div>
            <div class="form-group" >
                <label>
                  <input type="radio"name="isbargin"value="1" checked > 接受议价
                </label>
                <label>
                  <input type="radio" name="isbargin" value="0" > 不接受
                </label>
              </div>
    <div class="form-group" >
        <select name="personCount" id="personCount" class="form-control" value="${alterLine.personCount}">
            <option disabled="disabled" selected value="0">请选择人数</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
      </div>
              <div class="form-group" >
      <input placeholder="车牌号" type="text" name="plateNumber" id="plateNumber" class="form-control"  value="${alterLine.plateNumber}"></div>
      <div class="form-group">
          <input type="datetime-local" name="startTime" id="startTime" class="form-control" title="出发时间" step="1" >
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
  </div>
<%--<script src="/asert/js/bootstrap-datetimepicker.js"></script>
<script src="/asert/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<script>

    var app=new Vue({

        el: '#app',
        data: {
            options:'',
            error: ''
        },
        methods: {
            submit: function(event) {
                debugger
                //ajax提交

                    var params = $("#message").serialize();
                    $.ajax( {
                        type : "POST",
                        url : "/v1/lineInfos",
                        data : params,
                        success : function(msg) {
                            alert("success: " + msg);
                        }
                    });

            },
            selectType:function(message){
                debugger
                var type=message;
                console.log(type);
                if(type==1){
                    $('#plateNumber').show();
                }else{
                    $('#plateNumber').hide();
                }
            }
        }

    });
    var flag=true;
    var validatebeforeSubmit=function () {
        var start=$('#start').val();
        var end=$('#end').val();
        var price=$('#price').val();
        var personCount=$('#personCount').val();
        var plateNumber=$('#plateNumber').val();
        var startTime=$('#startTime').val()
        if (start && end &&price &&personCount&&startTime){
            flag=true;
            $("#errormsg").fadeOut();
        }else{
            $("#errormsg").text("所填信息不完整");
            $("#errormsg").fadeIn();
            flag=false;
        }
    }
     function submitline(){
        validatebeforeSubmit();
        if(!flag){
            return false;
        }
        //ajax提交
        var params = $("#message").serialize();
        $.ajax({
            type : "POST",
            url : "/v1/lineInfo",
            data : params,
            success : function(data) {
                if(data.code==200){
                    location.href="toIndexPage";
                }else{
                    alert(data.message)
                }

            }
        });

    }
     function checkValid(form) {
        var inputVal=form.value;
         if (inputVal){
             $("#errormsg").fadeOut();
             return true;
         }else{
             var  errormsg=$("#errormsg");
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
    function startselect (e) {
        debugger
        $('#startAdcode').val(e.poi.adcode);
        $('#startLongitude').val(e.poi.location.lng);
        $('#startLatitude').val(e.poi.location.lat);
    }

    var endauto = new AMap.Autocomplete({
        input: "end"
    });
    AMap.event.addListener(endauto, "select", endselect);//注册监听，当选中某条记录时会触发
    function endselect (e) {
        $('#endAdcode').val(e.poi.adcode);
        $('#endLongitude').val(e.poi.location.lng);
        $('#endLatitude').val(e.poi.location.lat);
    }
    /**
     * 提示输入
     */
    $(function () {
/*        $('#start').autocompleter({
            cache: false,
            // marker for autocomplete matches
            highlightMatches: true,

            // object to local or url to remote search
            source: '/v2//HintInfo' ,

           /!* template: '<img src="{{ id }}" alt="Image for autocompleter list item" /> {{ label }}{{ cityName }} {{ name }}',*!/
            template: '{{ cityName }} {{ name }}<em>{{ id }}</em>',
            // show hint
            hint: false,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,

            callback: function (value, index, selected) {
                if (selected) {
                    $('#startAdcode').val(selected.adCode);
                    $('#startLongitude').val(selected.longitude);
                    $('#startLatitude').val(selected.latitude);
                }
            }
        });*/
        /*$('#end').autocompleter({
            cache: false,
            // marker for autocomplete matches
            highlightMatches: true,

            // object to local or url to remote search
            source: '/v2//HintInfo' ,

            template: '{{ cityName }} {{ name }}',
            // show hint
            hint: false,

            // abort source if empty field
            empty: false,

            // max results
            limit: 5,
            callback: function (value, index, selected) {
                if (selected) {
                    $('#endAdcode').val(selected.adCode);
                    $('#endLongitude').val(selected.longitude);
                    $('#endLatitude').val(selected.latitude);
                }
            }
        });*/
        //var selval = ${alterLine.personCount};
        //var typeval = ${alterLine.type};
        //var isbarginval = ${alterLine.isbargin};
        $('#personCount').val(${alterLine.personCount});
        $("input[name='type'][value=${alterLine.type}]").attr("checked",true);
        $("input[name='isbargin'][value=${alterLine.isbargin}]").attr("checked",true);
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
    var startTime=document.getElementById('startTime');
    var time = new Date().Format("yyyy-MM-ddTHH:mm:ss");
    startTime.value=time;
</script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>