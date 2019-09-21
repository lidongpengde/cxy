<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<jsp:include page="include/header.jsp"></jsp:include>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <title>任我行顺风车网</title>
</head>
<body>
<div class="container" id="app" style="margin-top: 55px">
    <h3>我的发布</h3>

        <div class="list-group" id="headapp" style="font-size: 13px">
            <div class="list-unstyled">
                <c:forEach var="item" items="${mylist.list}" varStatus="status">
                    <div class="post">
                        <a href="http://www.rwxing.cn/api/mySubscibe/${item.lid}">
                        <%--<a href="/api/mySubscibe/${item.lid}">${ item.start }</a>--%>
                            <%--${ item.end }--%>
                        <p ><span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color:#5cb85c ;margin-right: 6px"></span><span> ${ item.start }</span>
                            <button type="button" class="close"  onclick="cancelPublish('${item.lid}')" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </p>

                        <p> <span class="glyphicon glyphicon-map-marker" aria-hidden="true" style="color: #f0ad4e;margin-right: 6px"></span>
                            <span>${ item.end }</span>
                        </p>
                        ¥${ item.price }

                        <%--<a href="#" onclick="cancelPublish('${item.lid}')" class="btn btn-danger btn-xs "--%>
                           <%--style="margin: 3px 0">取消</a>--%>
                         <p style="float: right;font-size: 24px" >
                        <input id="ss${item.lid}" value="http://www.rwxing.cn/api/mySubscibe/${item.lid}" hidden >
                        <a href="#" class="">

                            <span class="glyphicon glyphicon-share-alt code-btn" id="code-btn${item.lid}" aria-hidden="true" data-clipboard-text="http://www.rwxing.cn/api/mySubscibe/${item.lid}" >
                            </span>
                        </a>
                         </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
    </div>
    <button class="btn btn-danger" onClick="javascript :history.back(-1);">返回</button>
</div>
<script src="https://cdn.bootcss.com/clipboard.js/2.0.4/clipboard.js"></script>
<script>
    $(".code-btn").click(function(e){
        var id  = e.target.id;
        //实例化clipboard
        $("#ss"+id).show();
        var clipboard = new ClipboardJS('#'+id);
        clipboard.on("success", function(e){
            $(".alertMsg").html("复制成功！");
            $(".modal-bg").css("display", "block");
            alert("链接已复制成功，可到微信中粘贴分享朋友查看");
            $("#ss"+id).hide()
            e.clearSelection();
        });
        clipboard.on("error", function(e){
            $(".alertMsg").html("请选择“拷贝”进行复制!");
            $(".modal-bg").css("display", "block");
        });
    });

</script>

<script>

    function cancelPublish(lid) {
        $.ajax({
            type: "post",
            url: "/v1/lineInfo/" + lid,
            success: function (data) {
                alert(data.message);
                location.reload();
            }
        });
    }
    function updateLineInfo(lid) {
        //ajax提交
        debugger
        $.ajax({
            type: "post",
            url: "/v1/lineInfo/" + lid,
            success: function (data) {
                alert(data.message);
                location.reload();
            }
        });
    }
    function alterInfo(lid) {
        $.ajax({
            type: "post",
            url: "/v1/toPublishlineInfoPage?lid=" + lid,
            success: function () {

            }
        })
    }
</script>
</body>
<jsp:include page="include/foot.jsp"></jsp:include>
</html>
