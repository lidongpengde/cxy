<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/asert/css/style.css" />
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container">

    <c:forEach var="message" items="${msgList}" varStatus="status">
        <blockquote>
            <p class="text-left">${message.senderName}<small><fmt:formatDate value="${message.sendTime}" type="date" pattern="yyyy/MM/dd:HH:mm:ss"/></small></p>
        <div class="well well-sm">${message.content}</div>
        </blockquote>
    </c:forEach>
</div>

<script src="/asert/js/jquery-3.1.1.min.js"></script>
<script src="/asert/js/bootstrap.js"></script>
<script>
    function sendMessage() {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/message/addMessage",
            data:$('#message').serialize(),// 你的formid
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                alert(data.message)
            }
        });
    }

</script>
</body>
</html>
