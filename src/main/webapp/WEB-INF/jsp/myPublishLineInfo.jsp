<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <title>任我行顺风车网</title>
</head>
<body >
<div class="container" id="app" style="margin-top: 55px">
    <div class="table-responsive">
    <table class="table table-hover ">
        <thead>
        <tr>
            <th>出发地</th>
            <th>目的地</th>
            <th>价格</th>
           <%-- <th>出发时间</th>
            <th>人数</th>
            <th>手机号</th>
            <th>车牌号</th>
            <th>是否议价</th>--%>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item"   items="${mylist.list}"   varStatus="status">
            <tr>
                <td><a href="/api/mySubscibe/${item.lid}">${ item.start }</a></td>
                <td>${ item.end }</td>
                <td>¥${ item.price }</td>
               <%-- <td>${ item.startTime }</td>--%>
              <%--  <td>${ item.personCount }</td>
                <td>${ item.userMobile }</td>
                <td>${ item.plateNumber }</td>--%>
               <%-- <td>
                    <c:choose>
                        <c:when test="${item.isbargin== '0'}">
                            否
                        </c:when>
                        <c:otherwise>
                            是
                        </c:otherwise>
                    </c:choose>
                </td>--%>
                <td class="form-inline" >
                        <a href="#" onclick="cancelPublish('${item.lid}')" class="btn btn-danger btn-xs " style="margin: 3px 0">取消</a>

                        <a href="/v1/toPublishlineInfoPage?lid=${item.lid}" class="btn btn-warning btn-xs "style="margin: 3px 0">编辑</a>
                </td>
                <td></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    <button class="btn btn-danger" onClick="javascript :history.back(-1);">返回</button>
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
