<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户基本信息完善</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
</head>
<style>
    .max{width:100%;height:auto;}
    .min{width:100px;height:100px;}
    body{
        background-color: #315481;
        background-image: linear-gradient(to bottom, #315481, #918e82 100%);
        position: relative;
    }
    </style>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" style="margin-top: 55px;background: #fafafa">
    <form action="/user/update" id="form" class="form-horizontal" onsubmit="return false">


            <div class="col-md-4">
                <c:if test="${empty userInfo.headImage}">
                <div class="form-group ">

                        <img src="http://gfs14.gomein.net.cn/T1YvKTBsDv1RCvBVdK.png" id="previewPicture"  class="img-circle min" >
                </div>
                    <div class="form-group ">
                        <a href="javascript:;" class="a-upload">
                            <input  name="file" type="file" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity()">上传头像
                        </a>
                </div>
                    </c:if>

                <c:if test="${!empty userInfo.headImage}">
                <div class="form-group ">
                <img class="img-circle min"  src="${userInfo.headImage}" id="previewPicture" >
                </div>
                    <div class="form-group ">
                    <a href="javascript:;" class="a-upload">
                        <input  name="file" type="file" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity()">更改头像
                    </a>
                </div>
                </c:if>

            <%--    <div class="form-group ">
                    <c:if test="${empty userInfo.headImage}">
                        <a href="javascript:;" class="a-upload">
                            <input  name="file" type="file" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity()">上传头像
                        </a>
                    </c:if>
                </div>--%>
            </div>
            <div class="col-md-8">
                <div class="form-group ">
                    <label class="control-label" >用户名：</label>
                    <div class="">
                        <label class="form-control" >${userInfo.userName}</label>
                    </div>

                </div>
                <div class="form-group"><label for="age">年龄：</label><input id="age" name="age" maxlength="2" type="number"class="form-control" value="${userInfo.age}"></div>
                    <%--<div class="form-group">
                        <label>
                            <input type="radio"name="sex"value="1" checked> 男
                        </label>
                        <label>
                            <input type="radio" name="sex" value="0"> 女
                        </label>
                    </div>--%>
                <div class="form-group">
                    <label for="nickName">昵称：</label>
                    <input id="nickName" name="nickName" maxlength="18" placeholder="张三" class="form-control" value="${userInfo.nickName}">
                </div>
                    <div class="form-group ">

                        <div class="">
                            <label class="control-label" >手机：</label>
                            <label>${userInfo.mobile}</label>
                        </div>
                    </div>
                <div class="form-group ">

                    <c:if test="${not empty userInfo.email}">
                        <div class="">
                            <label class="control-label" >邮箱：</label>
                            <label  >${userInfo.email}</label>
                        </div>
                    </c:if>
                    <c:if test="${empty userInfo.email}">
                        <a href="/template/bindEmail.html">绑定邮箱</a>
                    </c:if>
                </div>

                <div class="form-group ">
                    <label class="control-label" >认证状态：</label>
                    <label class=" control-label" >
                        <c:if test="${userInfo.identifyStatus=='1'}">
                            <span style="color: #008200;">已认证</span>
                        </c:if>
                        <c:if test="${userInfo.identifyStatus=='0'}">
                            <a href="/api/toIdentify">未认证</a>
                        </c:if>
                        <c:if test="${userInfo.identifyStatus=='3'}">
                            <span href="/api/toIdentify">认证中</span>
                        </c:if>
                    </label>
                </div>

                        <input type="hidden" value="${userInfo.id}" name="id">

                        <input name="headImage" type="hidden" id="headImage">
            <div class="form-group">
                <button class="btn btn-danger" onclick="updateUserInfo()">保存</button>
            </div>
            </div>
    </form>
</div>
<script>
    function submitIdentity() {
    var form = new FormData(document.getElementById("form"));
    $.ajax({
        url:"/upload.do",
        type:"post",
        data:form,
        cache: false,
        processData: false,
        contentType: false,
        success:function(data){
            debugger
            $('#file').fadeOut();
            $('#previewPicture').attr("src",data.imagePath).fadeIn();
            $('#headImage').val(data.imagePath);
        },
        error:function(e){
            alert("网络错误，请重试！！");
        }
    });}
function updateUserInfo() {
        var age=$('#age').val();
        var nickName=$('#nickName').val();
        if (age && nickName){
            return false;
        }
        $.ajax({
            url:'/user/update',
            data:$('#form').serialize(),
            type:'post',
            success:function (data) {

               alert(data.message)
            }
        })

}
    $('#previewPicture').click(function(){
        $(this).toggleClass('min');
        $(this).toggleClass('max');
    });
</script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
