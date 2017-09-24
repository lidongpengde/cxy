<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app" style="margin-top: 90px">

    <%--头部搜索栏--%>
    <%--<div class="row">--%>
        <%--<form id="searchForm" class="form-inline" onsubmit="return false">--%>
            <%--<input name="type" value="1" hidden id="type">--%>
            <%--<label for="start">出发地</label><input name="start" id="start" class="form-control">--%>
            <%--<label for="end">目的地</label><input name="end" id="end" class="form-control">--%>
            <%--<button class="btn btn-info" onclick="searchLineInfo()">查询</button>--%>
        <%--</form>--%>
        <%--<a href="/v1/toPublishlineInfoPage">发布</a>--%>
    <%--</div>--%>
        <c:forEach var="item"   items="${mylist.list}"   varStatus="status">
            <div class="post row" v-for="item in items">
                    <%--<a href="#" class=""><img class="img-circle img-responsive" style="width: 100px;height: 100px;float: left"  src="${user.headImage}"></a>--%>
                <div class="post-content">
                    <h3><strong class="address">${ item.start }</strong><small>至</small><strong class="address">${ item.end }</strong></span><em class="text-center">¥${ item.price }</em></h3>
                    <p>
                        <em>出发时间：${ item.startTime }</em>

                        <em v-if="item.isbargin === 0">不接受议价
                        </em>
                        <em v-if="item.isbargin === 1">接受议价
                        </em>
                        <em>人数：${ item.personCount }</em>
                        <em >电话：${ item.user.mobile }</em>
                        <em>发布人：${ item.user.userName }</em>
                        <em v-if="item.type === 1">车牌号：${ item.plateNumber }</em>
                    </p>
                </div>
                <button class="discuss btn btn-default" onclick="cancelPublish('${item.lid}')">取消发布</button>
            </div>
        </c:forEach>
</div>
<script>

    function cancelPublish(lid){
debugger
        updateLineInfo(lid);

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
</script>
</body>
</html>
