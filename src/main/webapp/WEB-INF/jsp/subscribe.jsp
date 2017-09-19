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
<div class="container">
<form action="/api/subscibe" method="post">
<input name="lineinfoId" id="lineInfoId" type="hidden"   value="${lineInfoId}">
    <div class="form-group"><label>人数</label>
        <select type="number"  class="form-control" name="personCount">
            <option disabled="disabled" selected value="0">请选择出行人数</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
    </div>
    <div class="form-group"><label>联系方式</label><input type="phone" class="form-control" name="personMobile"></div>
    <div class="form-group"><label>描述</label><input type="text" class="form-control" name="description"></div>
    <div class="form-group"><button type="submit" class="btn btn-danger">提交</button></div>
</form>
</div>
</body>
</html>
