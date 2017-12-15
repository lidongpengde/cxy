<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="GB2312">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
    <title>任我行顺风车网</title>
</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app" style="margin-top: 55px;min-height: 100%">
    <form action="/api/Identify"  method="post" id="identifyForm" onsubmit="return false">
        <div class="form-group row"><label for="realName">真实姓名：</label><input id="realName" class="form-control" maxlength="10" name="realName" required></div>
        <div class="form-group row"><label for="idCardNumber">身份证号：</label><input id="idCardNumber" class="form-control" maxlength="20" name="idCardNumber"  required ></div>
        <div class="form-group"><%--<label for="positive">正面：</label>--%><input id="positive" type="hidden" class="form-control" name="positive" ></div>
        <div class="form-group"><%--<label for="negative">反面：</label>--%><input id="negative" type="hidden" class="form-control" name="negative" ></div>
        <input hidden="hidden" name="age" id="age">
        <div class="form-group row">
        <div class="bordered col-xs-5 col-md-4" style="float: left;padding-left: 0px;padding-right: 0px">
            <img src="/asert/image/positive.jpg" id="previewpositive" class="img-responsive" height="180" width="385">
            <a href="javascript:;" class="a-upload">
                <input type="file"  name="file" id="positivefile" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity('positivefile','previewpositive','positive')">上传身份证正面
            </a>

        </div>

        <div class="bordered col-xs-5 col-md-4" style="float: right;padding-left: 0px;padding-right: 0px">
            <img src="/asert/image/negetive.jpg" id="previewnegative"   height="180" width="385" class="img-responsive">
            <a href="javascript:;" class="a-upload">
                <input type="file"  name="file1" id="negativefile" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity('negativefile','previewnegative','negative')">上传身份证反面
            </a>

        </div>
        </div>
        <div class="form-group row" style="padding-top: 10px;float: left">
        <button type="submit" class="btn btn-danger" onclick="submitIdentityForm()">提交认证</button>
        </div>
    </form>
</div>
<script src="/asert/js/common/validate.js"></script>
<script>
    function submitIdentity(upload,preview,col) {
        debugger
        var pic = $('#'+upload)[0].files[0];
        var fd = new FormData();
        fd.append('file', pic);
      //  var form = new FormData(document.getElementById("form"));
        $.ajax({
            url:"/upload.do",
            type:"post",
            data:fd,
            cache: false,
            processData: false,
            contentType: false,
            success:function(data){
                if(data.success){
                $('#file').fadeOut();
                $('#'+preview).attr("src",data.imagePath).fadeIn();
                $('#'+col).val(data.imagePath);
                }else{
                    alert(data.message);
                }
            },
            error:function(e){
                alert("网络错误，请重试！！");
            }
        });
    }
function submitIdentityForm() {
       var idCardNumber= $('#idCardNumber').val();
        var validateResult=IdentityCodeValid(idCardNumber);
        if (validateResult){
            $.ajax({
                cache: true,
                type: "POST",
                url:"/api/Identify",
                data:$('#identifyForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    debugger
                    if (data.code==200){
                        alert(data.message);
                        location.href="/v1/toIndexPage";
                    }else{
                        alert(data.message);
                    }

                }
            });
        }else{

        }
    
}
</script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
