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
