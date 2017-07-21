<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/stylesheet.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=23834821b1465df3fa84571571947619"></script>
    <script type="text/javascript" src="/asert/js/vue.js"></script>
    <script type="text/javascript" src="/asert/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/asert/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-8 ">
            <div class="aaa">
                <div class="login-tab login-tab-l">
                    <a href="javascript:void(0)" clstag="pageclick|keycount|201607144|1" onclick="registerPage()" id="registerPage" class="checked" style="outline: rgb(109, 109, 109) none 0px;"> 注册</a>
                </div>
                <div class="login-tab login-tab-r">
                    <a href="javascript:void(0)" clstag="pageclick|keycount|201607144|2" onclick="loginPage()" id="loginPage" style="outline: rgb(109, 109, 109) none 0px;" class="">账户登录</a>
                </div>
            </div>
            <form action="user/register">
            <div class="thumbnail" id="register">
             <div class="form-group"><label for="userName">手机：</label><input id="mobile" class="form-control" name="mobile" type="text" required></div>
            <div class="form-group"><label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" type="text" required></div>
            <div class="form-group"><label for="passWord">密码：</label><input id="passWord"class="form-control" name="passWord" type="password" required></div>
             <div class="form-group"><label for="confirmPassWord">确认密码：</label><input id="confirmPassWord"class="form-control" name="confirmPassWord" type="password" required></div>
            <div class="form-group"><label for="age">年龄：</label><input id="age" name="age" type="number"class="form-control"></div>
            <div class="form-group">
                <label>
                    <input type="radio"name="sex"value="1" checked> 男
                </label>
                <label>
                    <input type="radio" name="sex" value="0"> 女
                </label>
            </div>
             <div class="form-group"><label for="idCard">身份证号：</label><input id="idCard" name="idCard" type="number"class="form-control"></div>
            <button type="submit" class="btn btn-primary  btn-block">成为会员</button>
            </div>
        </form>
        <form action="/user/login" >
            <div class="thumbnail" id="login">
                <div class="form-group"><label for="userName">用户名：</label><input  class="form-control" name="userName" type="text" required></div>
                <div class="form-group"><label for="passWord">密码：</label><input class="form-control" name="passWord" type="password" required></div>
                <button type="submit" class="btn btn-primary  btn-block">登录</button>
            </div>
        </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#login").hide();
    });
function registerPage(){
$(this).addClass("checked");
    $("#register").show();
    $("#login").hide();
}
function loginPage(){
    $(this).addClass("checked");
    $("#register").hide();
    $("#login").show();
}
</script>
</body>
</html>
