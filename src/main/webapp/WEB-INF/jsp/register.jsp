<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册中心</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/stylesheet.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-8 ">
            <div class="aaa">
                <div class="login-tab login-tab-l">
                    <a href="#"  onclick="registerPage()" id="registerPage" class="checked" > 注册</a>
                </div>
                <div class="login-tab login-tab-r">
                    <a href="#"  onclick="loginPage()" id="loginPage"  class="">账户登录</a>
                </div>
            </div>
            <form action="user/register" id="registerForm" name="registerForm" onsubmit="return false">
            <div class="thumbnail" id="register">
                <div>
                <strong id="errorMsg"  class="text-danger text-center"></strong></div>
             <div class="form-group"><label for="userName">手机：</label><input id="mobile" class="form-control" name="mobile"   onblur="vailPhone()"></div>
            <div class="form-group"><label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" type="text" required></div>
            <div class="form-group"><label for="passWord">密码：</label><input id="passWord"class="form-control" name="passWord" type="password" required></div>
             <div class="form-group"><label for="confirmPassWord">确认密码：</label><input id="confirmPassWord"class="form-control" name="confirmPassWord" type="password" required></div>
            <div class="form-group"><label for="age">年龄：</label><input id="age" name="age" maxlength="2" type="number"class="form-control"></div>
            <div class="form-group">
                <label>
                    <input type="radio"name="sex"value="1" checked> 男
                </label>
                <label>
                    <input type="radio" name="sex" value="0"> 女
                </label>
            </div>
             <div class="form-group"><label for="idCard">身份证号：</label><input id="idCard" name="idCard" type="number" minlength="15" maxlength="18" class="form-control"></div>
            <button  class="btn btn-primary  btn-block" onclick="registerUser()">成为会员</button>
            </div>
        </form>
        <form action="/user/login" name="loginForm">
            <div class="thumbnail" id="login">
                <div class="form-group"><label >用户名：</label><input  class="form-control" name="userName"  type="text" required></div>
                <%--这里把密码type改为password  console就会报错，不知道咋回事--%>
                <div class="form-group"><label >密码：</label><input class="form-control" name="passWord" type="password" required></div>
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
    $('#registerPage').addClass("checked");
    $('#loginPage').removeClass("checked");

    $("#register").show();
    $("#login").hide();
}
function loginPage(){
    $('#registerPage').removeClass("checked");
    $('#loginPage').addClass("checked");
    $("#register").hide();
    $("#login").show();
}
//表单验证
    //验证手机号
    function vailPhone(){
        var phone = $("#mobile").val();
        var flag = false;
        var message = "";
        var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        debugger
        if(phone == ''){
            message = "手机号码不能为空！";
        }else if(phone.length !=11){
            message = "请输入有效的手机号码！";
        }else if(!myreg.test(phone)){
            message = "请输入有效的手机号码！";
        }else{
            flag = true;
        }
        if(!flag){
            $('#errorMsg').text(message);
        }else{
            $('#errorMsg').text('');
        }
        return flag;
    }
    function validatePassword(){

       var passWord= $('#passWord').val();
        var confirmPassWord= $('#confirmPassWord').val();
        if (!passWord==confirmPassWord){
            $('#errorMsg').text("两次密码输入不一致！");
        }
        return passWord==confirmPassWord;
    }
    function registerUser(){
       var mobileFlag= vailPhone();
       var passwordFlag=validatePassword();
       if (mobileFlag && passwordFlag){
           $.ajax({
               cache: true,
               type: "POST",
               url:"user/register",
               data:$('#registerForm').serialize(),// 你的formid
               async: false,
               error: function(request) {
                   alert("Connection error");
               },
               success: function(data) {
                   $("#loginPage").click();
               }
           });
       }else{
           alert("您填写信息有误！")
       }
    }

</script>
</body>
</html>
