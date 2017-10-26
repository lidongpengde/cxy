<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<!DOCTYPE html>
<html xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
    <title>任我行顺风车网</title>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
    <script src="/asert/js/mricode.pagination.js"></script>
</head>
<body>
<div class="container" id="app" style="margin-top: 90px">
	<div v-for="todo in todos" class="thumbnail" >
            <img  v-if="showPositive" name="upload" v-bind:src="'/download/?filename='+todo.positive" v-on:click="reverseMessage" class="img-rounded" />
            <img  v-if="showNegative" name="upload" v-bind:src="'/download/?filename='+todo.negative" v-on:click="reverseMessage" class="img-rounded" />
            <h3 >{{todo.realName}}</h3>
            <p><tel>手机：{{todo.user.mobile}}</tel><small>提交时间：{{todo.createTime}}</small></p>
            <a v-bind:href="'/user/'+todo.userId"  class="btn btn-default">通过</a>
	</div>

</div>
<div class="container">
    <div id="page" class="m-pagination" ></div>
</div>

<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
            todos: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ],
            showPositive:true,
            showNegative:false
        },
        methods: {
            reverseMessage: function () {
                app.showPositive=!app.showPositive;
                app.showNegative=!app.showNegative
            }
    }});
    $("#page").pagination({
        pageSize: 10,
        remote: {
            url: '/api/Identifys',
            success: function (data) {
                app.todos=data.list;
            },
            totalName:'total'
        }
    });
</script>

</body>
</html>