<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任我行顺风车网</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
<style type="text/css">
/*    .copyright{
        position: inherit !important;
    }*/
</style>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="header " style="background-image: url(/asert/image/backgrond.jpg);">
    <div class="container" >
        <div class="row">
            <div class="hidden-xs col-md-8">
                <h2 style="color: #fff">“任我行顺风车网，和志同道合的人一起出行...”</h2>
            </div>
            <div class="col-xs-12 col-md-4" >
                    <div class="shadow">
                        <form action="/user/register" id="registerForm" name="registerForm" onsubmit="return false">

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
                                <div class="form-group">
                                    <label for="userName">手机：</label><input id="mobile" class="form-control" name="mobile"  maxlength="11" onblur="vailPhone()">
                                </div>
                                <div class="form-group">
                                    <label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" placeholder="zhangsan" maxlength="20" type="text" onblur="validateUserName()"required onkeydown="if(event.keyCode==32) return false">
                                </div>
                                <div class="form-group"><label for="passWord">密码：</label><input id="passWord"class="form-control" name="passWord"  maxlength="10" type="password" required onkeydown="if(event.keyCode==32) return false"></div>
                                <div class="form-group"><label for="confirmPassWord">确认密码：</label><input id="confirmPassWord"class="form-control" maxlength="10"   name="confirmPassWord" type="password" required onkeydown="if(event.keyCode==32) return false"></div>
                                <%--<div class="form-group"><label for="age">年龄：</label><input id="age" name="age" maxlength="2" type="number"class="form-control" required></div>--%>
                                <div class="form-group">
                                    <label>
                                        <input type="radio"name="sex"value="1" checked> 男
                                    </label>
                                    <label>
                                        <input type="radio" name="sex" value="0"> 女
                                    </label>
                                </div>
                                <div class="form-group"><label for="nickName">昵称：</label><input id="nickName" name="nickName" maxlength="18" placeholder="张三" class="form-control" required></div>
                                <div class="form-group"><a  class="btn btn-primary  btn-block" onclick="registerUser()">成为会员</a></div>
                                <br>
                            </div>

                        </form>
                        <form action="user/login" name="loginForm" id="loginForm" onsubmit="return false">
                            <div class="" id="login">
                                <div class="form-group">
                                    <label >用户名：</label>
                                    <input  class="form-control" name="userName"  type="text" required placeholder="用户名/手机号">
                                </div>
                                <div class="form-group">
                                    <label >密码：</label>
                                    <input class="form-control" name="passWord" type="password" required placeholder="密码">
                                </div>
                                <div class="form-group"> <input type="checkbox" name="remindMe" >一周免登录</div>
                                <div class="form-group"> <button  class="btn btn-primary  btn-block" onclick="realLogin()">登录</button></div>

                            </div>
                        </form>
                </div>

            </div>
        </div>

    </div>

    </div>
<div class="container">
    <div style="float: left" class="col-xs-12 col-md-6">
        <h5 style="text-align: center">微信公众号</h5>
        <img style="margin-right: auto;margin-left: auto" src="/asert/image/gongzhonghao.png" width="280" height="280" class="img-responsive">
    </div>
 <%--   <div class="col-xs-12 col-md-6">
        <h5 style="text-align: center">手机客户端</h5>
        <img style="margin-right: auto;margin-left: auto" src="/asert/image/app.png" class="img-responsive" width="280" height="280">
    </div>--%>
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
        var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(phone == ''){
            message = "手机号码不能为空！";
        }else if(phone.length !=11){
            message = "请输入有效的手机号码！";
        }else if(!myreg.test(phone)){
            message = "请输入有效的手机号码！";
        }else{
            flag = true;
        }
        $.ajax({
            url:'/user/validateExists/'+phone,
            type:'post',
            async:false,
            success:function(data){
                if (data=='true'){
                    message="手机号已存在，建议换一个！";
                    $('#errorMsg').fadeIn();
                    flag=false;
                }else{

                }
            }
        });
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
    function validateUserName(){
        var flag=true;
        var userName=$('#userName').val();
        if (userName){
            $.ajax({
                url:'/user/validateExists/'+userName,
                type:'post',
                async:false,
                success:function(data){
                    if (data=='true'){
                        $('#errorMsg').text("用户名已存在，建议换一个！");
                        $('#errorMsg').fadeIn();
                        flag=false;
                    }else{
                        $('#errorMsg').fadeOut();
                    }
                }
            });
            return flag;
        }else{
            return false;
        }

    }
    function registerUser(){
        debugger
       var mobileFlag= vailPhone();
       var passwordFlag=validatePassword();
       var userNameFlag=validateUserName();
       if (mobileFlag && passwordFlag&&userNameFlag){
           $.ajax({
               cache: true,
               type: "POST",
               url:"/user/register",
               data:$('#registerForm').serialize(),// 你的formid
               async: false,
               error: function(request) {
                   alert("Connection error");
               },
               success: function(data) {
                   if (data.code==200){
                       location.href="/v1/toIndexPage";
                   }else{
                       alert(data.message);
                   }

               }
           });
       }
    }
    function realLogin(){
            $.ajax({
                cache: true,
                type: "POST",
                url:"/user/login",
                data:$('#loginForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    if (data.code==200){
                        if (data.refer){
                            location.href=data.refer;
                        }else{
                            location.href="/v1/toIndexPage";
                        }
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
