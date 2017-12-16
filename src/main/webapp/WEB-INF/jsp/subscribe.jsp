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
    <title>任我行顺风车网</title>
</head>
<body style="background-color: #fff">
<div class="container" style="margin-top: 55px">
<form action="/api/subscibe" method="post" id="subForm" onsubmit="return submitSubscribe()">
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
    <div class="form-group"><label>联系方式</label><input type="phone" class="form-control" maxlength="20" name="personMobile" id="personMobile"></div>
    <div class="form-group"><label>描述</label><input type="text" class="form-control" maxlength="30" name="description" id="description"></div>
    <div class="form-group"><button  class="btn btn-danger">提交</button></div>
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
       var flag=true;
       if(!personCount){
           flag=false;
           alert("请选择出行人数");
           return false;
       }
        if(!vailPhone(personMobile)){
           flag=false;
            alert("请输入正确的手机，方便司机联系您")
            return false;
        }
        if(!description){
            flag=false;
            alert("说点什么吧")
            return false;
        }
        debugger
        if (flag){
            $("subForm").submit(function(e){
                alert("Submitted");
            });
        }

    }
</script>
</body>
</html>
