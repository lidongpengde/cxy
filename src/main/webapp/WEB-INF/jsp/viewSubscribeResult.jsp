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
    <title>点滴出行</title>
</head>
<body style="background-color: #fff">
<div class="container" >
<div class=" effect" >

    <div class="post-content">
        <h3><strong>${ lineinfo.start }</strong>	&nbsp;</strong><strong class="glyphicon glyphicon-arrow-right" aria-hidden="true"></strong>	&nbsp;<strong>${ lineinfo.end }</strong>	&nbsp;
            <span class="glyphicon glyphicon-earphone" aria-hidden="true">${ lineinfo.user.mobile }</span></h3>
        <p>
            人数:	&nbsp;<span class="">${ lineinfo.personCount }</span>		&nbsp;&nbsp;<span v-if="item.plateNumber ">车牌号：${ lineinfo.plateNumber }</span>
            &nbsp;	&nbsp;
            <span class="glyphicon glyphicon-user" aria-hidden="true">${ lineinfo.user.nickName }</span>
            &nbsp;	&nbsp;
            <span class="glyphicon glyphicon-calendar" aria-hidden="true">	${ lineinfo.startTime }</span>
            <%--<span class="price">¥{{ item.price }}</span>--%>
        </p>
    </div>
    <span class="price discuss">¥${ lineinfo.price }</span>
</div>













<div>
    <c:forEach var="subscribe" items="${subscribeList}" varStatus="status">
        <div class="tiptitle">
            <h3>${subscribe.personName} <small><span class="label  label-danger">${subscribe.personCount}人</span></small></h3>
            <hgroup>
            <p>联系电话：${subscribe.personMobile}</p>
            <p><article>描述：${subscribe.description}</article></p>
            </hgroup>
        </div>
    </c:forEach>

</div>
</div>
</body>
</html>
