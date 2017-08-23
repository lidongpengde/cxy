<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
	<title>login</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap-datetimepicker.min.css" media="screen"/>


</head>

<body>
<div class="container">
    <div class="main">
      

<h4 class="title">
  <div class="normal-title">
    <p class="lead text-left"><strong>发布需求</strong> </p>
  </div>
</h4>
<div id="app">

  <div ><transition name="fade">
    <p class="errors">{{error}}</p>
  </transition></div>
  <form id="message" onsubmit="return false">
      <div class="alert-danger form-group" id="errormsg"></div>
    <div class="form-group">
      <label>
        <input type="radio"name="type"value="1" checked v-on:click="selectType(1)"> 司机
      </label>
      <label>
        <input type="radio" name="type" value="0" v-on:click="selectType(0)"> 乘客
      </label>
    </div>
    <div class="form-group" >
      <input placeholder="出发地" type="text" name="start" id="start" class="form-control"  onblur="checkValid(this)"></div>
    <div class="form-group" >
      <input placeholder="目的地" type="text" name="end" id="end" class="form-control" onblur="checkValid(this)"></div>
        <div class="form-group" >
      <input placeholder="价格" type="" name="price" id="price" class="form-control" onblur="checkValid(this)" ></div>
            <div class="form-group" >
                <label>
                  <input type="radio"name="isbargin"value="1" checked > 接受议价
                </label>
                <label>
                  <input type="radio" name="isbargin" value="0" > 不接受
                </label>
              </div>
    <div class="form-group" >
      <input placeholder="人数" type="number" max="3" min="1" name="personCount" id="personCount" class="form-control" onblur="checkValid(this)"></div>
              <div class="form-group" >
      <input placeholder="车牌号" type="text" name="plateNumber" id="plateNumber" class="form-control"  onblur="checkValid(this)"></div>
      <div class="form-group">
          <div class="input-group date form_datetime col-md-5"  data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
              <input class="form-control" size="16" type="text" value="" name="startTime" id="startTime" readonly placeholder="出发时间" onblur="checkValid(this)">
              <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
              <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
          </div>
          <input type="hidden" id="dtp_input1" style="margin-top: 350px" value="" />
      </div>
      <br/>
      <div class="form-group">
    <button class="btn btn-primary" onclick="submitline()" style="width: 100%">提交</button>
          </div>
</form>
</div>
    </div>
  </div>
<script src="/asert/js/bootstrap-datetimepicker.js"></script>
<script src="/asert/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>

    var app=new Vue({

        el: '#app',
        data: {
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
    $( document ).ready(function() {
        console.log( "ready!" );
    });

    var flag=true;
    var validatebeforeSubmit=function () {
        var start=$('#start').val();
        var end=$('#end').val();
        var price=$('#price').val();
        var personCount=$('#personCount').val();
        var plateNumber=$('#plateNumber').val();
        var startTime=$('#startTime').val()
        if (start && end &&price &&personCount&&plateNumber&&startTime){
            flag=true;
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
            success : function(msg) {
                location.href="toIndexPage";
            }
        });

    }
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
     function checkValid(form) {
        var inputVal=form.value;
         if (inputVal){
             $("#errormsg").fadeOut();
             return true;
         }else{
             $("#errormsg").text("所填信息不能为空");
             $("#errormsg").fadeIn();
             return false;
         }

     }
</script>
</body>
</html>