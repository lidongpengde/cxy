<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lidongpeng
  Date: 2017/9/18
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html>
<head>
    <title>任我行顺风车网</title>
</head>
<body style="background-color: #fff">
<div class="container" style="margin-top: 90px">
<div class=" effect" >

    <div class="post-content">

            <p ><span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color:#5cb85c ;margin-right: 6px"></span><span>${ lineinfo.start } </span><span class="price">¥${lineinfo.price }</span></p>
            <p> <span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color: #f0ad4e;margin-right: 6px"></span>
                <span>${ lineinfo.end } </span>
            </p>
        <p>
            剩余座位:	&nbsp;<span class="">${ lineinfo.personCount }</span>		&nbsp;&nbsp;<span v-if="item.plateNumber ">车牌号：${ lineinfo.plateNumber }</span>
            &nbsp;	&nbsp;
            <span class="glyphicon glyphicon-user" aria-hidden="true">${ lineinfo.userNickname }</span>
            &nbsp;	&nbsp;
            <span class="glyphicon glyphicon-calendar" aria-hidden="true">${ lineinfo.startTime }</span>
        </p>

    </div>

</div>
    <ul class="tab-navs clearfix">
        <li class="tab-nav active"><a class="anchor" href="#" >行程预约信息</a></li>
    </ul>
<div>
    <c:forEach var="subscribe" items="${subscribeList}" varStatus="status">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">预约人：${subscribe.personName} <small>人数：<span class="label">${subscribe.personCount}人</span></small></h3>
            </div>
            <div class="panel-body">
                <p>联系电话：${subscribe.personMobile}</p>
                <p><article>描述：${subscribe.description}</article></p>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty subscribeList}">
        <em>目前还没有人预约。。。</em>
    </c:if>
</div>
</div>
<jsp:include page="include/foot.jsp"></jsp:include>
</body>
</html>
