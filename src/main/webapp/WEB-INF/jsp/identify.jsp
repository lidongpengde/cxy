<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app">
    <form action="/api/Identify"  method="post" id="form">
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
    <div class="form-group" >
            <input type="file" value="上传认真信息" name="file" id="file" onchange="submitIdentity()">
    </div>
        <div>
            <input name="path" hidden id="path">

            <img src="/" id="previewPicture">
        </div>
        <button type="submit" >submit</button>
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
