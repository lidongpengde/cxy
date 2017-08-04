<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <title>展示我的订单列表</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body >
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="app">
    <c:forEach var="myOrder"   items="${orders}"   varStatus="status">
    <div class="col-sm-6 col-sm-offset-2">
        <div class="product-card">
            <a class="img-link" target="_blank" href="https://learn.vegetablegardeninglife.com/ebook-companion-planting-grow-better-vegetables"><img class="img-responsive" src="https://www.withcoach.com/assets/marketing/product-cards/teach-live-convert-deec0b7f5597d3e14da7af1cc5000b088ff05721495e3aae651ba67efcbd4b8c.png" alt="Companion planting">
            </a><div class="product-info">
            <h4 class="product-title h-bold clearfix">
                <div class="pull-left">
                    <a target="_blank" href="https://learn.vegetablegardeninglife.com/ebook-companion-planting-grow-better-vegetables">${myOrder.lineInfoStart} - ${myOrder.lineInfoEnd}</a>
                </div>
                <div class="pull-right text-primary text-right">¥${myOrder.lineInfoPrice}</div>
            </h4>
            <p class="product-author">
                <b>发布人：</b><strong><a target="_blank" href="">${myOrder.publisherName}</a> <em>${myOrder.publisherMobile}</em></strong><br>
                <b>预约人：</b><strong><a target="_blank" href="">${myOrder.subscriberName}</a><em>${myOrder.subscriberMobile}</em></strong>
            </p>
            <p class="product-desc"><fmt:formatDate  pattern="yyyy:mm:dd HH:mm:ss" value="${myOrder.createTime}"/></p>
            <c:choose>
                <c:when test="${myOrder.orderStatus=='0'}">
                    <mark id="waitensure">待确认</mark>  <a class="btn btn-default" href="#" onclick="ensureOrder('${myOrder.orderId}')">确认订单</a>
                </c:when>
                <c:when test="${myOrder.orderStatus=='1'}">
                    <mark id="ensured">已确认</mark> <a class="btn btn-default" href="#" onclick="endOrder('${myOrder.orderId}')">结束订单</a>
                </c:when>
                <c:when test="${myOrder.orderStatus=='2'}">
                    <mark id="finished">已完成</mark>
                </c:when>
            </c:choose>
        </div>
        </div>
    </div>
    </c:forEach>
</div>
<script>

    $(document).ready(function(){

    });
    function changeIdentity(type){
        if(type==0){
            $('#tabpassenger').addClass("hover");
            $('#tabdriver').removeClass("hover");
            $('#type').val(0);

        }else{
            $('#tabdriver').addClass("hover");
            $('#tabpassenger').removeClass("hover");
            $('#type').val(1);
        }
        searchLineInfo();
    }
    function endOrder(orderId){
        $.ajax({
            cache: true,
            type: "PATCH",
            url:"/api/order/"+orderId,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                if (data.code==200){
                    $("#ensured").text("已完成");
                    $(this).hide();
                }else{
                    alert(data);
                }
            }
        });
    }
    function ensureOrder(orderId){
        $.ajax({
            cache: true,
            type: "PUT",
            url:"/api/order/"+orderId,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                if (data.code==200){
                    location.reload();
                }else{
                    alert(data);
                }

            }
        });
    }
</script>
</body>
</html>
