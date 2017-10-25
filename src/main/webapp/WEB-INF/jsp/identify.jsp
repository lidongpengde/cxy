<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="GB2312">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app" style="margin-top: 90px">
    <form action="/api/Identify"  method="post" id="form">
        <div class="form-group"><label for="realName">真实姓名真是的：</label><input id="realName" class="form-control" name="realName" required></div>
        <div class="form-group"><label for="idCardNumber">身份证号：</label><input id="idCardNumber" class="form-control" name="idCardNumber"  required ></div>
        <div class="form-group"><%--<label for="positive">正面：</label>--%><input id="positive" type="hidden" class="form-control" name="positive" ></div>
        <div class="form-group"><%--<label for="negative">反面：</label>--%><input id="negative" type="hidden" class="form-control" name="negative" ></div>
        <div class="form-group">
        <div class="bordered col-xs-5 col-md-6" style="float: left">
            <img src="/asert/image/positive.jpg" id="previewpositive" class="img-responsive" height="297px">
            <a href="javascript:;" class="a-upload">
                <input type="file"  name="file" id="positivefile" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity('positivefile','previewpositive','positive')">上传身份证正面
            </a>

        </div>

        <div class="bordered col-xs-5 col-md-6" style="float: right">
            <img src="/asert/image/negetive.jpg" id="previewnegative" class="img-responsive"  height="297px">
            <a href="javascript:;" class="a-upload">
                <input type="file"  name="file1" id="negativefile" accept = "image/jpg,image/jpeg,image/png" onchange="submitIdentity('negativefile','previewnegative','negative')">上传身份证反面
            </a>

        </div>
        </div>
        <div class="form-group" style="padding-top: 30px;float: left">
        <button type="submit" class="btn btn-danger">提交认证</button>
        </div>
    </form>
</div>
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
                $('#file').fadeOut();
                $('#'+preview).attr("src","/download/?filename="+data).fadeIn();
                $('#'+col).val(data);
            },
            error:function(e){
                alert("网络错误，请重试！！");
            }
        });
    }

</script>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
