<%--
  Created by IntelliJ IDEA.
  User: lidp
  Date: 2017/3/19
  Time: 下午8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>

    <form action="savelogin">
        <div class="container">
            <h2>会员注册中心</h2>
            <div class="form-group"><label for="userName">用户名：</label><input id="userName" class="form-control" name="userName" type="text"></div>
            <div class="form-group"><label for="userPassword">密码：</label><input id="userPassword"class="form-control" name="userPassword" type="password"></div>
            <div class="form-group"><label for="job">工作：</label>
                <select class="form-control"id="job" name="job">
                    <option>java</option>
                    <option>android</option>
                    <option>ios</option>
                    <option>node.js</option>
                    <option>hadoop</option>
                </select>
            </div>
            <div class="form-group"><label for="age">年龄：</label><input id="age" name="age" type="number"class="form-control"></div>
           <%-- <div class="col-md-4">家乡所在省：<input name="hometownProvince" type="text"></div>
            <div class="col-md-4">家乡所在市：<input name="hometownCity" type="text"></div>
            <div class="col-md-4">家乡所在区：<input name="hometownArea" type="text"></div>
            <div class="col-md-4">昵称：<input name="nickname" type="text"></div>--%>
            <%--<div class="col-md-4">所在省：<input name="province" type="text"></div>
            <div class="col-md-4">所在市：<input name="city" type="text"></div>
            <div class="col-md-4">所在区：<input name="area" type="text"></div>--%>
            <%--<div class="col-md-4">学历：<input name="education" type="text"></div>
            <div class="col-md-4">收入：<input name="income" type="text"></div>
            <div class="col-md-4">手机：<input name="userTel" type="text"></div>
            <div class="col-md-4">爱好：<input name="hobby" type="text"></div>--%>
            <div class="form-group">
                <label>
                    <input type="radio" name="userSex" value="0"> 女
                </label>
                <label>
                    <input type="radio"name="userSex"value="1"> 男
                </label>
            </div>
        <button type="submit" class="btn btn-default">成为会员</button>
        </div>
    </form>
</div>
</body>
</html>
