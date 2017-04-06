<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>login</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
	<link rel="stylesheet" type="text/css" href="/asert/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="/asert/css/style.css">
</head>
<body class="">
<div class="sign">
    <div class="logo"><a href="/"><img src="http://cdn-qn0.jianshu.io/assets/web/logo-58fd04f6f0de908401aa561cda6a0688.png" alt="Logo"></a></div>
    <div class="main">
      

<h4 class="title">
  <div class="normal-title">
    <p class="lead text-left"><strong>请登录</strong> </p>
  </div>
</h4>
<div id="app">

  <div ><transition name="fade">
    <p class="errors">{{error}}</p>
  </transition></div>
  <form id="message">
    <div class="form-group" style="position: relative;">
      <input placeholder="手机号或邮箱" type="text" name="userName" id="" class="form-control" style="padding: 4px 12px 4px 35px;">
      <i class="glyphicon glyphicon-user iconpass" aria-hidden="true"></i>
    </div>
    <button class="btn btn-primary" type="submit" style="width: 100%">登录</button>
    
</form>
</div>
    </div>
  </div>
<script src="/asert/js/vue.js"></script>
<script src="https://cdn.jsdelivr.net/vue.resource/1.2.1/vue-resource.min.js"></script>
<script>
    var app=new Vue({

        el: '#app',
        data: {
            error: ''
        },
        methods: {
            sendmessage: function(event) {
                var formData = new FormData(event.target);
                this.$http.post('/user/login', formData).then((response) => {
                    if(response.body.code==200){
                        debugger
                        location.href="/user/main";
                    }
                    this.error=response.body.message;
                });

            }
        }

    })
</script>
</body>
</html>