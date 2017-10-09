<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app" style="margin-top: 90px">
    <form action="/api/Identify"  method="post" id="form">
        <div class="form-group"><label for="realName">真实姓名：</label><input id="realName" class="form-control" name="realName"></div>
        <div class="form-group"><label for="idCardNumber">身份证号：</label><input id="idCardNumber" class="form-control" name="idCardNumber"   ></div>
        <div class="form-group"><label for="positive">正面：</label><input id="positive" class="form-control" name="positive"></div>
        <div class="form-group"><label for="negative">反面：</label><input id="negative" class="form-control" name="negative" ></div>
        <div class="form-group">
            <div class="form-inline">
        <div class="bordered" style="float: left">
            <img src="/" id="previewpositive" class="img-responsive"  width="200px" height="125px">
                <input type="file"  name="file" id="positivefile" onchange="submitIdentity('positivefile','previewpositive','positive')">
        </div>
            <div class=" col-md-4" style="border-right: 1px solid #dddddd"></div>
        <div class="bordered " style="float: right">
            <img src="/" id="previewnegative" class="img-responsive" width="200px" height="125px">
            <input type="file"  name="file1" id="negativefile" onchange="submitIdentity('negativefile','previewnegative','negative')">
        </div>
            </div>
        </div>
        <div class="form-group" style="margin-top: 30px">
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
</body>
</html>
