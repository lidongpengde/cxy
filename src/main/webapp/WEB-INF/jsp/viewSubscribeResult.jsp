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
<script type="text/javascript"
        src="https://webapi.amap.com/maps?v=1.4.12&key=23834821b1465df3fa84571571947619&plugin=AMap.Driving"></script>
<html>
<head>
    <title>济宁拼车网</title>
</head>
<style>
    .smallButton {
        border: 2px solid #777;
        display: inline-block;
        padding: 3px 3px;
        font-size: 28px;
        color: #777 !important;
        text-align: center;
        cursor: pointer;
        z-index: 10000;
        border-radius: 50%;
        background-color: #fff;
        outline: none;
        box-shadow: 0 0 10px rgba(0, 0, 0, .15);
        font-weight: 700;
        line-height: normal;
    }

    span {
        font-size: 90%;
    }

    .code-btn {
        font-size: 28px;
    }
</style>
<body>
<div class="container" style="margin-top: 55px">
    <div style="font-size: 13px">
        <div class="text-center">
            <h4 class="text-center">请直接与我电话联系</h4>
            <p>
                <span>出发 ：</span><span class="glyphicon glyphicon-map-marker" aria-hidden="true"
                                       style="color:#5cb85c ;margin-right: 6px"></span><span> ${ lineinfo.start }</span>
            </p>
            <p>
                <span>终点 ：</span>
                <span class="glyphicon glyphicon-map-marker" aria-hidden="true"
                      style="color: #f0ad4e;margin-right: 6px"></span>
                <span>${ lineinfo.end }</span>
            </p>
            <div class="form-inline">
                <span>价格 ：</span>
                <span>¥${lineinfo.price }/人</span>
            </div>
            <br>
            <p>
            <p>
                <c:if test="${ lineinfo.type=='1' }">
                    <span class="ensurebtn" v-if="item.type==1" style="padding: 0 2px;margin-left: 3px;">车主  </span>
                </c:if>
                <c:if test="${ lineinfo.type=='0' }">
                    <span class="ensurebtn" v-if="item.type==0" style="padding: 0 2px;margin-left: 3px;">乘客  </span>
                </c:if>

                <c:if test="${ lineinfo.type=='1' }">
                    <span class="ensurebtn" v-if="item.type==1">空余${ lineinfo.personCount }座 </span>
                </c:if>
                <c:if test="${ lineinfo.type=='0' }">
                    <span class="ensurebtn" v-if="item.type==0">${ lineinfo.personCount }人</span>
                </c:if>
            </p>
            <p>
                <span>出发时间 ：</span>
                <span class="ensurebtn" aria-hidden="true"> ${ lineinfo.startTime } </span>
            </p>

            <p>
                <span>手机号 ：</span>
                <span class="" aria-hidden="true"> ${ lineinfo.userMobile } </span>
            </p>
            <div>
                <a href="tel:${ lineinfo.userMobile }" class="smallButton"><strong><span
                        class="glyphicon glyphicon-earphone"></span></strong></a>

                <a href="#" class="smallButton" style="margin-left: 16px">
                            <span class="glyphicon glyphicon-share-alt code-btn" id="code-btn${lineinfo.lid}"
                                  aria-hidden="true"
                                  data-clipboard-text="http://www.rwxing.cn/api/mySubscibe/${lineinfo.lid}">
                            </span>

                </a>
                <a href="#"
                   onclick="showMap(${ lineinfo.startLongitude },${ lineinfo.startLatitude },${ lineinfo.endLongitude },${ lineinfo.endLatitude })"
                   style="margin-left: 16px" class="smallButton">
                    <span class="glyphicon glyphicon-globe code-btn" id="code-btn${lineinfo.lid}">
                            </span></a>
            </div>
        </div>
        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="exampleModalLabel">路线规划</h4>
                    </div>
                    <div class="modal-body" id="container" style="height: 350px">
                        <form>
                            <div class="form-group">
                                <label class="control-label">地图加载中。。。</label>

                            </div>
                            <div class="form-group">
                                <label class="control-label">Message:</label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.bootcss.com/clipboard.js/2.0.4/clipboard.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.js"></script>
        <script>
            $(".code-btn").click(function (e) {
                var id = e.target.id;
                //实例化clipboard
                $("#ss" + id).show();
                var clipboard = new ClipboardJS('#' + id);
                clipboard.on("success", function (e) {
                    $(".alertMsg").html("复制成功！");
                    $(".modal-bg").css("display", "block");
                    alert("链接已复制成功，可到微信中粘贴分享朋友查看");
                    $("#ss" + id).hide()
                    e.clearSelection();
                });
                clipboard.on("error", function (e) {
                    $(".alertMsg").html("请选择“拷贝”进行复制!");
                    $(".modal-bg").css("display", "block");
                });
            });

            function showMap(a, b, c, d) {
                if (!a || !b || !c || !d) {
                    alert("位置信息不完整，无法规划路线");
                    return;
                }

                //基本地图加载
                var map = new AMap.Map("container", {
                    resizeEnable: true,
                    center: [a, b],//地图中心点
                    zoom: 9 //地图显示的缩放级别
                });
                //构造路线导航类
                var driving = new AMap.Driving({
                    map: map
                });
                // 根据起终点经纬度规划驾车导航路线
                driving.search(new AMap.LngLat(a, b), new AMap.LngLat(c, d), function (status, result) {
                    // result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
                    if (status === 'complete') {
                        //log.success('绘制驾车路线完成')
                    } else {
                        //log.error('获取驾车数据失败：' + result)
                    }
                });
                $('#modal').modal('toggle')
            }

        </script>
        <jsp:include page="include/foot.jsp"></jsp:include>
    </div>
    <div class="text-center">
        <h4>扫码关注拼车网公众号</h4>
        <img src="/asert/image/gongzhonghao.png" width="150px" height="150px">
    </div>
</div>
</body>
</html>
