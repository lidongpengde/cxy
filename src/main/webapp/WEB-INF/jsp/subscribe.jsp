<%--
  Created by IntelliJ IDEA.
  User: lidongpeng
  Date: 2017/9/18
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>提交预约</title>
</head>
<body style="background-color: #fff">
<div class="container" style="margin-top: 90px">
<form action="/api/subscibe" method="post" id="subForm">
<input name="lineinfoId" id="lineInfoId" type="hidden"   value="${lineInfoId}">
    <div class="form-group"><label>人数</label>
        <select type="number"  class="form-control" name="personCount" id="personCount">
            <option disabled="disabled" selected value="0">请选择出行人数</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
    </div>
    <div class="form-group"><label>联系方式</label><input type="phone" class="form-control" name="personMobile" id="personMobile"></div>
    <div class="form-group"><label>描述</label><input type="text" class="form-control" name="description" id="description"></div>
    <div class="form-group"><button type="submit" class="btn btn-danger" onclick="submitSubscribe()">提交</button></div>
</form>
</div>
<script>
    function vailPhone(mobile){
        var flag = false;
        var message = "";
        var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
        if(mobile == ''){
            message = "手机号码不能为空！";
        }else if(mobile.length !=11){
            message = "请输入有效的手机号码！";
        }else if(!myreg.test(mobile)){
            message = "请输入有效的手机号码！";
        }else{
            flag = true;
        }
        if(!flag){
            $('#errorMsg').text(message);
        }else{
            $('#errorMsg').text('');
        }
        return flag;
    }
    function submitSubscribe() {
       var personCount= $('#personCount').val();
       var personMobile=$('#personMobile').val();
       var description=$('#description').val();
       if(!personCount){
           alert("请选择出行人数")
           return;
       }
        if(!vailPhone(personMobile)){
            alert("请输入正确的手机，方便司机联系您")
            return;
        }
        if(!description){
            alert("说点什么吧")
            return;
        }
        $("subForm").submit(function(e){
            alert("Submitted");
        });
    }
</script>
</body>
</html>
