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
        <div class="form-group" >
        <div class="btn-group" data-toggle="buttons">
            <label class="btn btn-primary active">
                <input type="radio" name="idCard" id="idCard" autocomplete="off" checked> 身份证
            </label>
            <label class="btn btn-primary">
                <input type="radio" name="graduation" id="graduation" autocomplete="off"> 驾照
            </label>
            <label class="btn btn-primary">
                <input type="radio" name="license" id="license" autocomplete="off"> 毕业证
            </label>
        </div>
        </div>
    <div class="form-group" >
            <input type="file"  name="file" id="file" onchange="submitIdentity()">
    </div>
        <div class="form-group">
            <input name="path" hidden id="path">

            <img src="/" id="previewPicture" class="img-responsive">
        </div>
        <div class="form-group" >
        <button type="submit" class="btn btn-danger">提交认证</button>
        </div>
    </form>
</div>
<script>
/*    $(document).ready(function(){
        $("form").submit(function(){
            alert("提交");
        });
    });*/
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
                $('#path').val(data);
            },
            error:function(e){
                alert("网络错误，请重试！！");
            }
        });
    }

</script>
</body>
</html>
