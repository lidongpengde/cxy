<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="include/header.jsp"></jsp:include>
<!DOCTYPE html>
<html xmlns:v-on="http://www.w3.org/1999/xhtml" xmlns:v-bind="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
    <link href="/asert/css/jquery.pagination.css" rel="stylesheet" />
    <script src="/asert/js/mricode.pagination.js"></script>
</head>
<body>
<div class="container" id="app">

  
<div class="row" >
  <div class="col-md-4">

    <div class="input-group">

      <input type="text" class="form-control" id="title" placeholder="输入标题">
      <span class="input-group-btn">
        <button class="btn btn-default btn-color" type="button" v-on:click="search(this)">查询</button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->


		<div v-for="todo in todos" class="thumbnail" style="min-height: 120px">
            <img style="float: left;min-height: 100px" name="upload" v-bind:src="''" class="img-rounded" />
		<h3 style="float: left;margin-left: 10px">{{todo.theme}} <small>被捐助人：{{todo.personName}}（收款账号：{{todo.alipay}}）---{{todo.createTime}}</small></h3>
            <a href="#" v-on:click="del(todo.infoId)" style="float: right;margin: 40px" class="btn btn-default">删除</a>
            <a v-bind:href="'updateInformation?mid='+todo.infoId"  style="float: right;margin: 40px" class="btn btn-default">修改</a>
		<h5 style="float:left;margin-left: -150px;
    margin-top: 70px; ">{{todo.content}}</h5>
	</div>



</div>
<div id="page" class="m-pagination" style="margin-left: 80px"></div>
<script type="text/javascript">
    var app = new Vue({
        el: '#app',
        data: {
            todos: [
                { text: '学习 JavaScript' },
                { text: '学习 Vue' },
                { text: '整个牛项目' }
            ]
        },
        // 在 `methods` 对象中定义方法
        methods: {
            del: function (uid) {

                $.get( "deleteInformation?mid="+uid, function( data ) {
                    location.reload();
                });
            },
            search:function(hhh){

                var title=$('#title').val();
                search(title);
            }
        }
    })
   $(document).ready(function(){
        $.get( "/api/Identifys", function( data ) {
            data= JSON.parse(data);
            app.todos=data;
        });
    });
   function  search(title){
       debugger
       $.get( "Identifys?information.theme="+title, function( data ) {
           data= JSON.parse(data);
           app.todos=data.list;
       });
   }
/*    $("#page").pagination({
        pageSize: 10,
        remote: {
            url: 'queryInformationList',
            success: function (data) {
                debugger
                app.todos=data.list;
            },
            totalName:'total'
        }
    });*/
</script>

</body>
</html>