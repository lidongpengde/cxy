<%--
  Created by IntelliJ IDEA.
  User: lidongpeng
  Date: 2017/9/20
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <script src="/asert/js/jquery.autocompleter.js"></script>
</head>
<style>
    .skill-ftw {
        width: 100%;
        max-width: 640px;
        height: 50px;
        position: relative;
    }
    .fixed-skill-ftw {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 50px;
        position: fixed;
        left: 0;
        bottom: 0;
        background: #fff;
        z-index: 999;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        overflow: hidden;
    }
    .skill-ftw-item {
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    }
    .skill-ftw-li {
        flex: 1;
        font-size: 12px;
        color: #2d2c37;
        text-align: center;
        float: left;
    }
</style>
<body>

<%--<footer>
            <div class="col-sm-12"><small>
                <span>Copyright © <a href="#">任我行顺风车网</a></span> |
                <span><a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备17060186号</a></span>|
                <span><a href="/advices" target="_blank" >意见箱</a></span></small>
                &lt;%&ndash;<span><a href="/template/joinus.html" target="_blank" >加入我们</a></span>&ndash;%&gt;
                &lt;%&ndash; <span><a href="/advices" target="_blank" >意见箱</a></span>&ndash;%&gt;
        </div>
</footer>--%>
<footer class="skill-ftw">
    <ul class="fixed-skill-ftw">
        <li class="skill-ftw-li">
            <a href="/v1/toPublishlineInfoPage" onclick="return seckillMPingJDSeckill(this.href)">
                <span class="skill-ftw-item jd-goods  active ">发布行程</span>
            </a>
        </li>
        <li class="skill-ftw-li">
            <a href="/v1/toIndexPage" onclick="return seckillMPingNewBrandSecKill(this.href)">
                <span class="skill-ftw-item brand-goods ">查找</span>
            </a>
        </li>
        <li class="skill-ftw-li">
            <a href="/seckill/seckillBrand" onclick="return seckillMPingBrandSecKill(this.href)">
                <span class="skill-ftw-item choice-goods ">我的</span>
            </a>
        </li>
    </ul>
</footer>
</body>
</html>
