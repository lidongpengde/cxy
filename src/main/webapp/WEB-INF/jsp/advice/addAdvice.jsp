<jsp:include page="../include/header.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>意见箱</title>
</head>
<body>
<div class="container" style="margin-top: 80px">
    <form onsubmit="return false" id="adviceForm">
    <div class="form-group">
        <input class="form-control" placeholder="标题" name="adviceTitle" id="adviceTitle">
    </div>
    <div class="form-group">
        <input class="form-control" placeholder="描述" name="adviceContent" id="adviceContent">
    </div>
    <div class="form-group">
        <button class="btn btn-danger" onclick="submitAdvice()">提交</button>
    </div>
    </form>
</div>
<script>
function submitAdvice() {
    var flag=false;
    var adviceTitle=$('#adviceTitle').val();
    var adviceContent=$('#adviceContent').val();
    if (!adviceTitle){
        alert("标题不可为空！")
    }
    if (!adviceContent){
        alert("内容不可为空！")
    }
    if (adviceTitle&&adviceContent){
        $.ajax({
            url:'/advice',
            type:'post',
            data:$('#adviceForm').serialize(),
            success:function (data) {
                if(data.code==200){
                    location.href="v1/toIndexPage";
                }
            },
            error:function (e) {
                alert("系统繁忙")
            }
        })
    }
}
</script>
</body>
</html>
