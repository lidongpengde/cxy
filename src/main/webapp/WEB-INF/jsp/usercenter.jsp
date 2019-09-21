<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户基本信息</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
</head>
<style>
label ,span{
    align-items: center;
    padding: 0 20px;
    height: 40px;
    overflow: hidden;
    display: inline-block;
    font-size: 16px;
    line-height: 32px;
    color: #8590A6;
    text-align: center;
    cursor: pointer;
    background: none;
    border-radius: 3px;
}
</style>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" style="margin-top: 55px;padding-top: 25px">
    <h3 class="text-center">基本信息</h3>
<div class="">
    <div>
        <label class="text-left">手机号：</label>
        <span class="text-right">${userInfo.mobile}</span>
    </div>
    <div>
        <label class="text-left">用户名:</label>
        <span class="text-right">${userInfo.userName}</span>
    </div>
    <div>
        <label class="text-left">当前身份</label>
        <c:if test="${sessionScope.user_type== '1'}">
            <span class="text-right">司机</span>
        </c:if>
        <c:if test="${sessionScope.user_type== '0'}">
            <span class="text-right">乘客</span>
        </c:if>
        <button class="btn btn-default btn-sm" onclick="changeType()">
        <span class="glyphicon glyphicon-pencil" aria-hidden="true" style="height: 6px"></span>切换身份
        </button>
    </div>
</div>

</div>
<script>
    function submitIdentity() {
        var form = new FormData(document.getElementById("form"));
        $.ajax({
            url: "/upload.do",
            type: "post",
            data: form,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                debugger
                $('#file').fadeOut();
                $('#previewPicture').attr("src", data.imagePath).fadeIn();
                $('#headImage').val(data.imagePath);
            },
            error: function (e) {
                alert("网络错误，请重试！！");
            }
        });
    }
    function updateUserInfo() {
        var age = $('#age').val();
        var nickName = $('#nickName').val();
        if (age && nickName) {
            return false;
        }
        $.ajax({
            url: '/user/update',
            data: $('#form').serialize(),
            type: 'post',
            success: function (data) {

                alert(data.message)
            }
        })

    }
    $('#previewPicture').click(function () {
        $(this).toggleClass('min');
        $(this).toggleClass('max');
    });

    function changeType() {
        $.ajax({
            url: '/user/changeUserType',
            type: 'post',
            success: function (data) {
                alert("😊"+data.message)
                location.reload();
            }
        })

    }
</script>
<script src="/asert/js/bootstrap.js"></script>
</body>
<jsp:include page="include/foot.jsp"></jsp:include>
</html>
