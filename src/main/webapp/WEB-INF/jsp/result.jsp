<%--
  Created by IntelliJ IDEA.
  User: lidongpeng
  Date: 2017/9/1
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>任我行顺风车网</title>
</head>
<body>
<div class="container" style="margin-top: 90px">
    <h3>${result}</h3>
    <p>点击<a href="#" onClick="javascript :history.back(-1);">返回</a></p>
</div>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
