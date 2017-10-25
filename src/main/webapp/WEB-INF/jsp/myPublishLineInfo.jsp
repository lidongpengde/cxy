<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <title>任我行-我的发布</title>
</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app" style="margin-top: 90px">
    <div class="table-responsive">
    <table class="table table-hover ">
        <thead>
        <tr>
            <th>出发地</th>
            <th>目的地</th>
            <th>价格</th>
            <th>出发时间</th>
            <th>人数</th>
            <th>手机号</th>
            <th>车牌号</th>
            <th>是否议价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item"   items="${mylist.list}"   varStatus="status">
            <tr>
                <td><a href="/api/mySubscibe/${item.lid}">${ item.start }</a></td>
                <td>${ item.end }</td>
                <td>¥${ item.price }</td>
                <td>${ item.startTime }</td>
                <td>${ item.personCount }</td>
                <td>${ item.userMobile }</td>
                <td>${ item.plateNumber }</td>
                <td>
                    <c:choose>
                        <c:when test="${item.isbargin== '0'}">
                            不接受议价
                        </c:when>
                        <c:otherwise>
                            接受议价
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${item.status=='1'}">
                        <a href="#" onclick="cancelPublish('${item.lid}')">取消发布</a>
                    </c:if>
                    <c:if test="${item.status=='0'}">
                        <a href="/v1/toPublishlineInfoPage?lid=${item.lid}" >再次编辑</a>
                    </c:if>
                </td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>
<script>

    function cancelPublish(lid){
        $.ajax({
            type : "post",
            url : "/v1/lineInfo/"+lid,
            success : function(data) {
                alert(data.message);
                location.reload();
            }
        });
    }
    function updateLineInfo(lid){
        //ajax提交
        debugger
        $.ajax({
            type : "post",
            url : "/v1/lineInfo/"+lid,
            success : function(data) {
               alert(data.message);
                location.reload();
            }
        });
    }
    function alterInfo(lid){
        $.ajax({
            type:"post",
            url:"/v1/toPublishlineInfoPage?lid="+lid,
            success:function () {

            }
        })
    }
</script>
</body>
</html>
