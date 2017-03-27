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
<div class="">
  <form id="new_session" action="/user/login" accept-charset="UTF-8" method="post">
    <div class="form-group" style="position: relative;">
      <input placeholder="手机号或邮箱" type="text" name="userName" id="" class="form-control" style="padding: 4px 12px 4px 35px;">
      <i class="glyphicon glyphicon-user iconpass" aria-hidden="true"></i>
    </div>
    <div class="form-group" style="position: relative;">
      <input placeholder="密码" type="password" name="userPassword" id="userPassword" class="form-control" style="padding: 4px 12px 4px 35px;">
      <i class="glyphicon glyphicon-lock iconpass" aria-hidden="true"></i>
    </div>
    <div class="form-group" style="float: left;">
      <input type="checkbox" value="true" checked="checked" name="session[remember_me]" id="session_remember_me"><span>记住我</span>
      <a id="js-sign-up-btn" href="/user/toregister" class="text-center">没有账号？马上注册</a>
    </div>
    <button class="btn btn-primary" type="submit" style="width: 100%">登录</button>
    
</form>
</div>
    </div>
  </div>
</body>
</html>