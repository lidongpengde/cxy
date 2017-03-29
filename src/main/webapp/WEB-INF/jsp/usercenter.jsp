<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/style.css" rel="stylesheet" type="text/css">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>


    <script src="/asert/js/jquery-3.1.1.min.js"></script>

    <script src="/asert/js/ng-file-upload-shim.js"></script>
    <script src="/asert/js/ng-file-upload.js"></script>
</head>
<body>
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navigation">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/user/main">Microscope</a>
        </div>
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav">
                <li class="activeRouteClass ">
                    <a href="/user/main">为您推荐</a>
                </li>
                <li class="activeRouteClass">
                    <a href="{{pathFor 'bestPosts'}}">技术问答</a>
                </li>

                <li class="activeRouteClass ">
                    <a href="/user/${user.userId}">我的</a>
                </li>
                <li class="dropdown">
                    <a href="/user/logout／${user.userId}">退出</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

            </ul>
        </div>
    </nav>
<div class="container">
    <div class="row">
    <form action="/user/update" ng-app="fileUpload" ng-controller="MyCtrl" name="form">
<div class="col-xs-6">
            <h2>用户会员中心</h2>
            <input type="hidden" value="${user.userId}" name="userId">
            <div class="form-group"><label >用户名：${user.userName}</label></div>
            <div class="form-group"><label>工作：${user.job}</label>

            </div>
            <div class="form-group"><label >年龄：</label>${user.age}</div>
         <%--   <div class="form-group">家乡所在省：<input name="hometownProvince" type="text"></div>
            <div class="form-group">家乡所在市：<input name="hometownCity" type="text"></div>
            <div class="form-group">家乡所在区：<input name="hometownArea" type="text"></div>--%>
            <div class="form-group">昵称：<input name="nickname" type="text"></div>
            <div class="form-group">学历：<input name="education" type="text"></div>
            <div class="form-group">收入：<input name="income" type="number"></div>
            <div class="form-group">手机：<input name="userTel" type="phone"></div>
            <div class="form-group">爱好：<input name="hobby" type="text"></div>
            <div class="form-group"><label >性别：</label>${user.userSex}</div>

            <div> <img src="${user.headImage}" id="headImage"></div>
            <input name="headImage" type="hidden" id="headImageId">
            <button type="submit" class="btn btn-default">立即认证</button>
</div>
        <div class="col-xs-5">
        <c:if test="${empty user.headImage}">
            <div class="btn-upload " ngf-select="upload($file)"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span>上传图片</div>
        </c:if>
        </div>
    </form>
    </div>
</div>
<script>
    //inject directives and services.
    var app = angular.module('fileUpload', ['ngFileUpload']);

    app.controller('MyCtrl', ['$scope', 'Upload', function ($scope, Upload) {
        // upload on file select or drop
        $scope.upload = function (file) {
            Upload.upload({
                url: '/upload.do',
                data: {file: file, 'username': $scope.username}
            }).then(function (resp) {
                debugger
                $('#headImage').attr("src",resp.data.fileurl);
                $('#headImageId').val(resp.data.fileurl);
                console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
            }, function (resp) {
                console.log('Error status: ' + resp.status);
            }, function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);
            });
        };
    }]);
</script>
<script src="/asert/js/bootstrap.js"></script>
</body>
</html>
