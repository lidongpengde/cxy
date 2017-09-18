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
<body>
<div class="container">
<form action="/api/subscibe" method="post">
<input name="lineinfoId" id="lineInfoId" type="hidden"   value="${lineInfoId}">
    <div class="form-group"><label>人数</label><input type="number" name="personCount"></div>
    <div class="form-group"><label>联系方式</label><input type="phone" name="personMobile"></div>
    <div class="form-group"><label>描述</label><input type="text" name="description"></div>
    <div class="form-group"><button type="submit" >提交</button></div>
</form>
</div>
</body>
</html>
