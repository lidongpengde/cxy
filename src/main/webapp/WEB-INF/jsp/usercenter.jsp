<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户基本信息完善</title>
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
            <a class="header-logo-invertocat" style="padding: 20px 20px">
                <svg aria-hidden="true" class="logo" height="48" version="1.1" viewBox="0 0 16 16" width="48"><path fill-rule="evenodd" d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.013 8.013 0 0 0 16 8c0-4.42-3.58-8-8-8z"></path></svg>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav">
                <li class="activeRouteClass ">
                    <a href="/user/main">为您推荐</a>
                </li>
                <li class="activeRouteClass">
                    <a href="#">技术问答</a>
                </li>

                <li class="activeRouteClass ">
                    <a href="/user/${user.userId}">我的</a>
                </li>
                <li class="dropdown">
                    <a href="/user/logout">退出</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

            </ul>
        </div>
    </nav>
<div class="container">
    <div class="row">
    <form action="/user/update" ng-app="fileUpload" ng-controller="MyCtrl" name="form" class="form-horizontal">
<div class="col-md-5">
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="hometownProvince">家乡所在省：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="hometownProvince" name="hometownProvince" placeholder="Large input">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="hometownCity">家乡所在市：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="hometownCity" name="hometownCity" placeholder="Small input">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="hometownArea">家乡所在区</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="hometownArea" name="hometownArea" placeholder="Large input">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="nickname">昵称：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="nickname" name="nickname" placeholder="老王">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="education">学历：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="education" name="education" placeholder="Large input">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="income">收入：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="income" name="income" placeholder="10">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="userTel">电话：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="userTel" name="userTel" placeholder="13565266598">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="hobby">爱好：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="hobby" name="hobby" placeholder="lol">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="birthday">生日：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="birthday" name="birthday" placeholder="Small input">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="height">身高：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="height" name="height" placeholder="身高">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="weight">体重：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="weight" name="weight" placeholder="体重">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="userMail">邮箱：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="userMail" name="userMail" placeholder="215@qq.com">
            </div>
        </div>
        <div class="form-group ">
            <label class="col-sm-2 control-label" for="qq">qq：</label>
            <div class="col-sm-10">
                <input class="form-control" type="text" id="qq" name="qq" placeholder="">
            </div>
        </div>

            <input type="hidden" value="${user.userId}" name="userId">

            <input name="headImage" type="hidden" id="headImageId">

</div>
        <div class="col-md-5">
        <c:if test="${empty user.headImage}">
            <div class="btn-upload " ngf-select="upload($file)"><span class="glyphicon glyphicon-upload" aria-hidden="true"></span>上传图片</div>
        </c:if>
            <div class="col-md-5"> <img src="${user.headImage}" id="headImage" class="img-circle img-responsive"></div>
            <button type="submit" class="btn btn-default" style="width: 100%">立即认证</button>
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
