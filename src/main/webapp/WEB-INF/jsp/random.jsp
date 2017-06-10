<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>您的要求</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
    <link href="/asert/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/asert/css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=23834821b1465df3fa84571571947619&plugin=AMap.DistrictSearch"></script>
    <script type="text/javascript" src="/asert/js/vue.js"></script>
    <script type="text/javascript" src="/asert/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/asert/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>
<div class="container" id="register">
    <div class="row">
        <div class="col-md-8 ">
            <form action="saveEager">
            <div class="thumbnail">
            <div class="form-group"><label for="job">工作：</label>
                <select class="form-control"id="job" name="job">
                    <option>java工程师</option>
                    <option>android</option>
                    <option>ios</option>
                    <option>node.js</option>
                    <option>hadoop</option>
                </select>
            </div>
            <div class="form-group"><label for="minAge">最小年龄：</label><input id="minAge" name="minAge" type="number"class="form-control"></div>
                <div class="form-group"><label for="maxAge">最大年龄：</label><input id="maxAge" name="maxAge" type="number"class="form-control"></div>
                <div class="form-group"><label for="height">身高：</label><input id="height" name="maxAge" type="number"class="form-control"></div>
                <div class="form-group"><label for="salary">年薪：</label><input id="salary" name="maxAge" type="number"class="form-control"></div>
                <div class="form-group"><label for="salary">爱好：</label><input id="hobby" name="maxAge" type="text"class="form-control"></div>
                <div class="form-group"><label for="salary">所在城市：</label>
                    <select id="location" name="location" class="form-control">
                        <option></option>
                    </select>
                </div>
                <div id="tip" class="form-group">
                    省：<select id='province' style="width:100px" onchange='search(this)'></select>
                    市：<select id='city' style="width:100px" onchange='search(this)'></select>
                    区：<select id='district' style="width:100px" onchange='search(this)'></select>
                    街道：<select id='street' style="width:100px" onchange= 'setCenter(this)'></select>
                </div>
            <button type="submit" class="btn btn-primary  btn-block">立即参与</button>
            <div id="container"></div>
            </div>
        </form></div>

    </div>


</div>
<script type="text/javascript">
    var map, district, polygons = [], citycode;
    var citySelect = document.getElementById('city');
    var districtSelect = document.getElementById('district');
    var areaSelect = document.getElementById('street');
    //行政区划查询
    var opts = {
        subdistrict: 1,   //返回下一级行政区
        level: 'city',
        showbiz:false  //查询行政级别为 市
    };
    district = new AMap.DistrictSearch(opts);//注意：需要使用插件同步下发功能才能这样直接使用
    district.search('中国', function(status, result) {
        if(status=='complete'){
            getData(result.districtList[0]);
        }
    });
    function getData(data) {

        var subList = data.districtList;
        var level = data.level;

        //清空下一级别的下拉列表
        if (level === 'province') {
            nextLevel = 'city';
            citySelect.innerHTML = '';
            districtSelect.innerHTML = '';
            areaSelect.innerHTML = '';
        } else if (level === 'city') {
            nextLevel = 'district';
            districtSelect.innerHTML = '';
            areaSelect.innerHTML = '';
        } else if (level === 'district') {
            nextLevel = 'street';
            areaSelect.innerHTML = '';
        }
        if (subList) {
            var contentSub =new Option('--请选择--');
            for (var i = 0, l = subList.length; i < l; i++) {
                var name = subList[i].name;
                var levelSub = subList[i].level;
                var cityCode = subList[i].citycode;
                if(i==0){
                    document.querySelector('#' + levelSub).add(contentSub);
                }
                contentSub=new Option(name);
                contentSub.setAttribute("value", levelSub);
                contentSub.center = subList[i].center;
                contentSub.adcode = subList[i].adcode;
                document.querySelector('#' + levelSub).add(contentSub);
            }
        }

    }
    function search(obj) {
        var option = obj[obj.options.selectedIndex];
        var keyword = option.text; //关键字
        var adcode = option.adcode;
        district.setLevel(option.value); //行政区级别
        district.setExtensions('all');
        //行政区查询
        //按照adcode进行查询可以保证数据返回的唯一性
        district.search(adcode, function(status, result) {
            if(status === 'complete'){
                getData(result.districtList[0]);
            }
        });
    }
    function setCenter(obj){
        map.setCenter(obj[obj.options.selectedIndex].center)
    }
</script>
<script type="text/javascript" src="http://webapi.amap.com/demos/js/liteToolbar.js"></script>

</body>
</html>
