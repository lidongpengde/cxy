<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>济宁拼车网</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
<style type="text/css">
    body{
        background-color: #315481;
        background-image: linear-gradient(to bottom, #315481, #918e82 100%);
        /*position: relative;*/
    }
    .glyphicon {
        color: #337ab7;
    }
    .container{
        position: relative;
        max-width: 450px !important;
    }
    @media (min-width: 600px) {
        .hello {
            min-height: 600px !important;
        }
    }
    .btn-primary-meteor {
        display: inline-block;
        width: 80%;
        background-color: #de4f4f;
        margin-top: 10px;
        margin-bottom: 15px;
        color: #fff;
        border: none;
        height: 42px;
        text-align: center;
        line-height: 42px;
        font-size: 17px;
        text-transform: uppercase;
    }
</style>
</head>

<body>
<jsp:include page="include/header.jsp"></jsp:include>
    <div class="container hello" style="margin-top: 55px;background-color: #fafafa;background-image: none" >
        <div class="row">
            <div class="" >
                    <div class="shadow">
                        <form action="#" id="registerForm" name="registerForm" onsubmit="return false">

                            <div class="aaa">
                                <div class="login-tab login-tab-l">
                                    <a href="#"  onclick="registerPage()" id="registerPage" class="" > 注册</a>
                                </div>
                                <div class="login-tab login-tab-r">
                                    <a href="#"  onclick="loginPage()" id="loginPage"  class="checked">账户登录</a>
                                </div>
                            </div>
                            <div class=" " id="register" >
                                <div><strong id="errorMsg"  style="color: red;margin-left: 18px;"></strong></div>
                                <div class="form-group">
                                    <label for="userName">手机：</label><input id="mobile" class="form-control" name="mobile"  maxlength="11" onblur="vailPhone()">
                                    <span class="error-account" id="error-mobile"></span>
                                </div>
                                <div class="form-group">
                                    <label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" placeholder="zhangsan" maxlength="20" type="text" onblur="validateUserName()"required onkeydown="if(event.keyCode==32) return false">
                                    <span class="error-account" id="error-userName"></span>
                                </div>
                                <div class="form-group"><label for="passWord">密码：</label><input id="passWord"class="form-control" name="passWord"  maxlength="10" type="password" required onkeydown="if(event.keyCode==32) return false"></div>
                                <div class="form-group">
                                    <label for="confirmPassWord">确认密码：</label><input id="confirmPassWord"class="form-control" maxlength="10"   name="confirmPassWord" type="password" onblur="validatePassword()" required onkeydown="if(event.keyCode==32) return false">
                                    <span class="error-account" id="error-passWord"></span>
                                </div>
                                <div class="form-group"><a  class="btn btn-primary  btn-block" id="adduserbtn" onclick="registerUser()">成为会员</a></div>
                                <br>
                            </div>

                        </form>
                        <form action="user/login" name="loginForm" id="loginForm" onsubmit="return false">
                            <div class="" id="login">
                                <div class="form-group">
                                    <label >手机号：</label>
                                    <input  class="form-control" name="userName"  type="text" required placeholder="手机号">
                                </div>
                                <div class="form-group">
                                    <label >密码：</label>
                                    <input class="form-control" name="passWord" type="password" required placeholder="密码">
                                </div>
                                <div class="form-group">
                                    <label >选择角色：</label>
                                    <select class="form-control"  name="remindMe">
                                    <option value="1">我是司机</option>
                                    <option value="0">我是乘客</option>
                                </select>
                                </div>
                                <div class="form-group"> <button  class="btn btn-primary  btn-block"  onclick="realLogin()">登录</button></div>

                            </div>
                        </form>
                        <div style="text-align: center">
                            <a href="javascript:alert('请到公众号留言，我们在后台收到会回复您！')"> 忘记密码? </a>
                            <br>
                            <a class="btn-primary-meteor" href="/template/text.html">不登录直接发布拼车</a>
                        </div>
                    </div>

            </div>
        </div>

    </div>

    </div>
<script type="text/javascript">
    $(document).ready(function(){
        $("#register").hide();
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
        var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(16[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
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
            $('#error-mobile').text(message);
            return;
        }
        $.ajax({
            url:'/user/validateExists/'+phone,
            type:'post',
            async:false,
            success:function(data){
                if (data=='true'){
                    message="手机号已存在，建议换一个！";
                    $('#error-mobile').fadeIn();
                    flag=false;
                }else{

                }
            }
        });
        if(!flag){
            $('#error-mobile').text(message);
        }else{
            $('#error-mobile').text('');
        }
        return flag;
    }
    function validatePassword(){

       var passWord= $('#passWord').val();
        var confirmPassWord= $('#confirmPassWord').val();
        if (!passWord){
            $('#error-passWord').text("请设置登录密码");
            return false;
        }
        if (passWord!=confirmPassWord){
            $('#error-passWord').text("两次密码输入不一致！");
        }else{
            $('#error-passWord').text("");
        }
        return passWord==confirmPassWord;
    }
    function validateUserName(){
        var flag=true;
        var userName=$('#userName').val();
        if (!userName){
            $('#error-userName').text("请输入用户名");
            return;
        }
        if (userName){
            $.ajax({
                url:'/user/validateExists/'+userName,
                type:'post',
                async:false,
                success:function(data){
                    if (data=='true'){
                        $('#error-userName').text("用户名已存在，建议换一个！");
                        $('#error-userName').fadeIn();
                        flag=false;
                    }else{
                        $('#error-userName').fadeOut();
                    }
                }
            });
            return flag;
        }else{
            return false;
        }

    }
    function registerUser(){
       var mobileFlag= vailPhone();
        if (!mobileFlag){
            return false;
        }
       var passwordFlag=validatePassword();
        if (!passwordFlag){
            return false;
        }
       var userNameFlag=validateUserName();
        if (!userNameFlag){
            return false;
        }
       if (mobileFlag && passwordFlag&&userNameFlag){

           $("#adduserbtn").attr('disabled',true);
           $.ajax({
               cache: true,
               type: "POST",
               url:"/user/register",
               data:$('#registerForm').serialize(),// 你的formid
               async: false,
               error: function(request) {
                   alert("Connection error");
                   $("#adduserbtn").attr('disabled',false);
               },
               success: function(data) {
                   $("#adduserbtn").attr('disabled',false);
                   if (data.code==200){
                       alert("注册成功，现在可以去登录。");
                       loginPage();
                   }else{
                       alert(data.message);
                   }

               }
           });
       }
       return false;
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
                            location.href="/v1/toPublishlineInfoPage";
                        }
                    }else{
                        alert(data.message);
                    }

                }
            });
    }
</script>
<%--<jsp:include page="include/foot.jsp"></jsp:include>--%>
</body>

</html>
