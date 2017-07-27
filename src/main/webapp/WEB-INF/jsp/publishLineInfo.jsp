<%@ taglib prefix="v-on" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>login</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
</head>
<jsp:include page="include/header.jsp"></jsp:include>
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
    <div class="form-group">
      <label>
        <input type="radio"name="type"value="1" checked v-on:click="selectType(1)"> 司机
      </label>
      <label>
        <input type="radio" name="type" value="0" v-on:click="selectType(0)"> 乘客
      </label>
    </div>
    <div class="form-group" >
      <input placeholder="出发地" type="text" name="start" id="start" class="form-control" required></div>
    <div class="form-group" >
      <input placeholder="目的地" type="text" name="end" id="end" class="form-control" required></div>
      <div class="form-group" >
      <input placeholder="出发时间" type="date" name="startTime" id="startTime" class="form-control" required></div>
        <div class="form-group" >
      <input placeholder="价格" type="" name="price" id="price" class="form-control"required ></div>
            <div class="form-group" >
                <label>
                  <input type="radio"name="isbargin"value="1" checked > 接受议价
                </label>
                <label>
                  <input type="radio" name="isbargin" value="0" > 不接受
                </label>
              </div>
    <div class="form-group" >
      <input placeholder="人数" type="number" maxlength="1" name="personCount" id="personCount" class="form-control" required></div>
              <div class="form-group" >
      <input placeholder="车牌号" type="number" name="plateNumber" id="plateNumber" class="form-control"  ></div>
    <button class="btn btn-primary" onclick="submitline()" style="width: 100%">提交</button>
</form>
</div>
    </div>
  </div>

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
     function submitline(){
        //ajax提交
        var params = $("#message").serialize();
         debugger
        $.ajax({
            type : "POST",
            url : "/v1/lineInfo",
            data : params,
            success : function(msg) {
                location.href="toIndexPage";
            }
        });

    }
</script>
</body>
</html>