<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>go366.club</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="header" style="background-image: url(http://static.bootcss.com/expo/img/d/dd/2de797545de56274f03a5920eb3a1.jpg);">
    <div class="container">
        <div class="row">
            <div class="col-xs-7">
                <h2 style="color: #fff">“顺路接一单，油费全省了，小金库也富裕了”</h2>
            </div>
            <div class="col-xs-5" style="z-index: 2">
                    <div class="shadow">
                        <form action="user/register" id="registerForm" name="registerForm" onsubmit="return false">

                            <div class="aaa">
                                <div class="login-tab login-tab-l">
                                    <a href="#"  onclick="registerPage()" id="registerPage" class="checked" > 注册</a>
                                </div>
                                <div class="login-tab login-tab-r">
                                    <a href="#"  onclick="loginPage()" id="loginPage"  class="">账户登录</a>
                                </div>
                            </div>
                            <div class=" " id="register" >
                                <div><strong id="errorMsg"  style="color: red;margin-left: 18px;"></strong></div>
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
                                <div class="form-group"><label for="nickName">昵称：</label><input id="nickName" name="nickName" maxlength="18" class="form-control"></div>
                                <div class="form-group"><button  class="btn btn-primary  btn-block" onclick="registerUser()">成为会员</button></div>
                                <br>
                            </div>

                        </form>
                        <form action="user/login" name="loginForm" id="loginForm" onsubmit="return false">
                            <div class="" id="login">
                                <div class="form-group"><label >用户名：</label><input  class="form-control" name="userName"  type="text" required></div>
                                <div class="form-group"><label >密码：</label><input class="form-control" name="passWord" type="password" required></div>
                                <div class="form-group"> <input type="checkbox" name="remind" >一周免登录</div>
                                <div class="form-group"> <button  class="btn btn-primary  btn-block" onclick="realLogin()">登录</button></div>

                            </div>
                        </form>
                </div>

            </div>
        </div>

    </div>
    </div>
<div id="statisics">
    <div class="titleAll color-white">
        <h3>Community</h3>
    </div>
    <div id="all-object-list-statisics">
        <div class="object-list-statisics wow fadeInRightBig" data-wow-delay=".2s" style="visibility: visible; animation-delay: 0.2s;"><i class="icon"></i>
            <span id="number-packages" class="number-statisics">12707</span>
            <span class="description">每日信息发布</span>
        </div>
        <div class="object-list-statisics wow fadeInRightBig" data-wow-delay=".4s" style="visibility: visible; animation-delay: 0.4s;"><i class="icon"></i>
            <span id="number-service" class="number-statisics">226</span>
            <span class="description">已注册司机</span>
        </div>
        <div class="object-list-statisics wow fadeInRightBig" data-wow-delay=".6s" style="visibility: visible; animation-delay: 0.6s;"><i class="icon"></i>
            <span id="number-question" class="number-statisics">26598</span>
            <span class="description">已注册会员</span>
        </div>
        <div class="object-list-statisics width-65 wow fadeInRightBig" data-wow-delay=".8s" style="visibility: visible; animation-delay: 0.8s;"><i class="icon"></i>
            <span id="number-uniqueinstall" class="number-statisics">508K</span>
            <span class="description">UNIQUE<br> INSTALLS</span>
        </div>
        <div class="object-list-statisics width-65 wow fadeInRightBig" data-wow-delay="1s" style="visibility: visible; animation-delay: 1s;"><i class="icon"></i>
            <span id="number-event" class="number-statisics">16</span>
            <span class="description">UPCOMING<br> EVENTS</span>
        </div>
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
               url:"${pageContext.request.contextPath}/user/register",
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
    function realLogin(){
            $.ajax({
                cache: true,
                type: "POST",
                url:"${pageContext.request.contextPath}/user/login",
                data:$('#loginForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    if (data.code==200){
                        location.href="${pageContext.request.contextPath}/v1/toIndexPage";
                    }else{
                        alert(data.message);
                    }

                }
            });
    }

</script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>

</html>
