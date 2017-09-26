<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户基本信息完善</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" style="margin-top: 90px">
    <form action="/user/update" id="form" class="form-horizontal" onsubmit="return false">


            <div class="col-md-4">
                <img src="" id="previewPicture" hidden class="img-circle" height="200px" width="200px">
                <c:if test="${!empty userInfo.headImage}">
                <img class="img-circle"  src="/download/?filename=${userInfo.headImage}" height="200px" width="200px">
                </c:if>
            </div>

<div class="col-md-8">
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="nickname">昵称：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="nickname" name="nickname" placeholder="老王" value="${userInfo.nickName}">
            </div>
        </div>

        <div class="form-group ">
            <label class="col-sm-2 control-label" for="userMail">手机：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="userMail" name="userMail" value="${userInfo.mobile}">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="qq">用户名：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="qq" name="qq" value="${userInfo.userName}">
            </div>
        </div>
    <div class="form-group ">
        <label class="col-sm-2 control-label" for="qq">认证状态：</label>
        <div class="col-sm-10">
            <input class="form-control" type="text"  value="${userInfo.identifyStatus}">
        </div>
    </div>
    <div class="form-group ">
        <c:if test="${empty userInfo.headImage}">
                <input  name="file" type="file" onchange="submitIdentity()">
        </c:if>
    </div>


            <input type="hidden" value="${userInfo.id}" name="id">

            <input name="headImage" type="hidden" id="headImage">
<div class="form-group" style="margin-left: 20px">
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
            $('#file').fadeOut();
            $('#previewPicture').attr("src","/download/?filename="+data).fadeIn();
            $('#headImage').val(data);
        },
        error:function(e){
            alert("网络错误，请重试！！");
        }
    });}
function updateUserInfo() {
        $.ajax({
            url:'/user/update',
            data:$('#form').serialize(),
            type:'post',
            success:function (data) {
               alert("修改成功")
            }
        })

}
</script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
