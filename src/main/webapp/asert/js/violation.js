/**
 * Created by lidp on 2018/11/6.
 */
var url = "https://www.autohome.com.cn/violation";
//var url = "";
var provinceid = 110000;
var cityid = 110100;
var elen = 99;
var vlen = 0;
var platenum = "";
var egnum = "";
var vnum = "";
var carid = 0;
var cityname = "";
var provinceName = "";
var btn = {
    "selectBtn": $("#selectBtn"),
    "loadingBtn": $("#loadingBtn")
}
//var citylistjson = $.parseJSON('{"340000":[["合肥",6,6,340100],["芜湖",6,6,340200],["蚌埠",6,6,340300],["淮南",6,6,340400],["马鞍山",6,6,340500],["淮北",6,6,340600],["铜陵",6,6,340700],["安庆",6,6,340800],["黄山",6,6,341000],["滁州",6,6,341100],["阜阳",6,6,341200],["宿州",6,6,341300],["巢湖",6,6,341400],["六安",6,6,341500],["亳州",6,6,341600],["池州",6,6,341700],["宣城",6,6,341800]],"110000":[["北京",99,0,110100]],"500000":[["重庆",0,6,500100]],"350000":[["福州",0,6,350100],["厦门",0,6,350200],["莆田",0,6,350300],["三明",0,6,350400],["泉州",0,6,350500],["漳州",0,6,350600],["南平",0,6,350700],["龙岩",0,6,350800],["宁德",0,6,350900]],"440000":[["广州",4,6,440100],["韶关",6,6,440200],["深圳",99,99,440300],["珠海",99,99,440400],["汕头",6,6,440500],["佛山",6,6,440600],["江门",6,6,440700],["湛江",6,6,440800],["茂名",6,6,440900],["肇庆",6,6,441200],["惠州",4,6,441300],["梅州",6,6,441400],["汕尾",6,6,441500],["河源",6,6,441600],["阳江",6,6,441700],["清远",6,6,441800],["东莞",6,6,441900],["中山",4,6,442000],["潮州",4,6,445100],["揭阳",6,6,445200],["云浮",6,6,445300]],"450000":[["南宁",6,6,450100],["柳州",6,0,450200],["桂林",6,0,450300],["梧州",6,0,450400],["北海",6,0,450500],["防城港",6,0,450600],["钦州",6,0,450700],["贵港",6,0,450800],["玉林",6,0,450900],["百色",6,0,451000],["贺州",6,0,451100],["河池",6,0,451200],["来宾",6,0,451300],["崇左",6,0,451400]],"520000":[["贵阳",6,0,520100],["六盘水",6,0,520200],["遵义",6,0,520300],["安顺",6,0,520400],["铜仁",6,0,522200],["黔西南",6,0,522300],["毕节",6,0,522400],["黔东南",6,0,522600],["黔南",6,0,522700]],"620000":[["兰州",0,99,620100],["嘉峪关",0,99,620200],["金昌",0,99,620300],["白银",0,99,620400],["天水",0,99,620500],["武威",0,99,620600],["张掖",0,99,620700],["平凉",0,99,620800],["酒泉",0,99,620900],["庆阳",0,99,621000],["定西",0,99,621100],["陇南",0,99,621200],["临夏",0,99,622900],["甘南",0,99,623000]],"460000":[["海口",0,4,460100],["三亚",0,4,460200],["五指山",0,4,461100],["琼海",0,4,461200],["儋州",0,4,461300],["文昌",0,4,461400],["万宁",0,4,461500],["东方",0,4,461600],["定安",0,4,461700],["屯昌",0,4,461800],["澄迈",0,4,461900],["临高",0,4,462000],["白沙",0,4,462100],["昌江",0,4,462200],["乐东",0,4,462300],["陵水",0,4,462400],["保亭",0,4,462500],["琼中",0,4,462600]],"410000":[["郑州",0,99,410100],["开封",99,6,410200],["洛阳",99,6,410300],["平顶山",99,6,410400],["安阳",99,6,410500],["鹤壁",99,6,410600],["新乡",99,6,410700],["焦作",99,6,410800],["濮阳",99,6,410900],["许昌",99,6,411000],["漯河",99,6,411100],["三门峡",99,6,411200],["南阳",99,6,411300],["商丘",99,6,411400],["信阳",99,6,411500],["周口",99,6,411600],["驻马店",99,6,411700],["济源",99,6,419000]],"420000":[["武汉",0,6,420100],["黄石",0,6,420200],["十堰",0,6,420300],["宜昌",0,6,420500],["襄阳",0,6,420600],["鄂州",0,6,420700],["荆门",0,6,420800],["孝感",0,6,420900],["荆州",0,6,421000],["黄冈",0,6,421100],["咸宁",0,6,421200],["随州",0,6,421300],["恩施",0,6,422800],["仙桃",0,6,423100],["潜江",0,6,423200],["天门",0,6,423300],["神农架",0,6,423400]],"430000":[["长沙",6,6,430100],["株洲",6,6,430200],["湘潭",6,6,430300],["衡阳",6,6,430400],["邵阳",6,6,430500],["岳阳",6,6,430600],["常德",6,6,430700],["张家界",6,6,430800],["益阳",0,6,430900],["郴州",6,6,431000],["永州",6,6,431100],["怀化",6,6,431200],["娄底",6,6,431300],["湘西",6,6,433100]],"130000":[["石家庄",0,4,130100],["唐山",0,4,130200],["秦皇岛",0,4,130300],["邯郸",0,4,130400],["邢台",0,4,130500],["保定",0,4,130600],["张家口",0,4,130700],["承德",0,4,130800],["沧州",0,4,130900],["廊坊",0,4,131000],["衡水",0,4,131100]],"230000":[["哈尔滨",0,99,230100],["齐齐哈尔",0,99,230200],["鸡西",0,99,230300],["鹤岗",0,99,230400],["双鸭山",0,99,230500],["大庆",0,99,230600],["伊春",0,99,230700],["佳木斯",0,99,230800],["七台河",0,99,230900],["牡丹江",0,99,231000],["黑河",0,99,231100],["绥化",0,99,231200],["大兴安岭",0,99,232700]],"320000":[["南京",6,0,320100],["无锡",6,0,320200],["徐州",6,0,320300],["常州",6,0,320400],["苏州",0,99,320500],["南通",6,0,320600],["连云港",6,0,320700],["淮安",6,0,320800],["盐城",6,0,320900],["扬州",0,6,321000],["镇江",0,4,321100],["泰州",6,0,321200],["宿迁",6,0,321300]],"360000":[["南昌",6,6,360100],["景德镇",6,6,360200],["萍乡",6,6,360300],["九江",6,6,360400],["新余",6,6,360500],["鹰潭",6,6,360600],["赣州",6,6,360700],["吉安",6,6,360800],["宜春",6,6,360900],["抚州",6,6,361000],["上饶",6,6,361100]],"220000":[["长春",0,4,220100],["吉林",0,4,220200],["四平",0,4,220300],["辽源",0,4,220400],["通化",0,4,220500],["白山",0,4,220600],["松原",0,4,220700],["白城",0,4,220800],["延边",0,4,222400]],"210000":[["沈阳",0,4,210100],["大连",0,4,210200],["鞍山",0,4,210300],["抚顺",6,6,210400],["本溪",0,4,210500],["丹东",0,4,210600],["锦州",0,6,210700],["营口",0,99,210800],["阜新",99,0,210900],["辽阳",0,4,211000],["盘锦",0,4,211100],["铁岭",0,0,211200],["朝阳",0,0,211300],["葫芦岛",0,99,211400]],"150000":[["呼和浩特",6,6,150100],["包头",0,6,150200],["乌海",6,6,150300],["赤峰",6,6,150400],["通辽",6,6,150500],["鄂尔多斯",6,6,150600],["呼伦贝尔",6,6,150700],["巴彦淖尔",0,6,150800],["乌兰察布",6,6,150900],["兴安盟",6,6,152200],["锡林郭勒盟",6,6,152500],["阿拉善盟",6,6,152900]],"640000":[["银川",4,6,640100],["石嘴山",4,6,640200],["吴忠",4,6,640300],["固原",4,6,640400],["中卫",4,6,640500]],"630000":[["西宁",0,99,630100],["海东",0,99,632100],["海北",0,99,632200],["黄南",0,99,632300],["海南",0,99,632500],["果洛",0,99,632600],["玉树",0,99,632700],["海西",0,99,632800]],"610000":[["西安",99,0,610100],["铜川",99,99,610200],["宝鸡",99,99,610300],["咸阳",99,99,610400],["渭南",99,99,610500],["延安",99,99,610600],["汉中",99,99,610700],["榆林",99,99,610800],["安康",99,99,610900],["商洛",99,99,611000]],"510000":[["成都",0,8,510100],["自贡",0,6,510300],["攀枝花",0,6,510400],["泸州",0,6,510500],["德阳",0,6,510600],["绵阳",0,6,510700],["广元",0,6,510800],["遂宁",0,6,510900],["内江",0,6,511000],["乐山",0,6,511100],["南充",0,6,511300],["眉山",0,6,511400],["宜宾",0,6,511500],["广安",0,6,511600],["达州",0,6,511700],["雅安",0,6,511800],["巴中",0,6,511900],["资阳",0,6,512000],["阿坝",0,6,513200],["甘孜",0,6,513300],["凉山",0,6,513400]],"310000":[["上海",99,0,310100]],"140000":[["太原",0,6,140100],["大同",0,6,140200],["阳泉",0,6,140300],["长治",0,6,140400],["晋城",0,6,140500],["朔州",0,6,140600],["晋中",0,6,140700],["运城",0,6,140800],["忻州",0,6,140900],["临汾",0,6,141000],["吕梁",0,6,141100]],"370000":[["济南",0,6,370100],["青岛",0,6,370200],["淄博",0,6,370300],["枣庄",0,6,370400],["东营",0,6,370500],["烟台",0,6,370600],["潍坊",0,17,370700],["济宁",0,6,370800],["泰安",0,6,370900],["威海",0,6,371000],["日照",0,6,371100],["莱芜",0,6,371200],["临沂",0,6,371300],["德州",0,6,371400],["聊城",0,6,371500],["滨州",0,6,371600],["菏泽",0,6,371700]],"120000":[["天津",6,0,120100]],"650000":[["乌鲁木齐",0,4,650100],["克拉玛依",0,4,650200],["吐鲁番",0,4,652100],["哈密",0,4,652200],["昌吉",0,4,652300],["博尔塔拉",0,4,652700],["巴音郭楞",0,4,652800],["阿克苏",0,4,652900],["克孜勒苏",0,4,653000],["喀什",0,4,653100],["和田",0,4,653200],["伊犁",0,4,654000],["塔城",0,4,654200],["阿勒泰",0,4,654300],["奎屯",0,4,654500],["石河子",0,4,655100]],"540000":[],"530000":[["昆明",99,99,530100],["曲靖",99,99,530300],["玉溪",99,99,530400],["保山",99,99,530500],["昭通",99,99,530600],["丽江",99,99,530700],["普洱",99,99,530800],["临沧",99,99,530900],["楚雄",99,99,532300],["红河",99,99,532500],["文山",99,99,532600],["西双版纳",99,99,532800],["大理",99,99,532900],["德宏",99,99,533100],["怒江",99,99,533300],["迪庆",99,99,533400]],"330000":[["杭州",0,6,330100],["宁波",0,6,330200],["温州",0,6,330300],["嘉兴",99,99,330400],["湖州",0,6,330500],["绍兴",6,6,330600],["金华",0,3,330700],["衢州",0,6,330800],["舟山",0,6,330900],["台州",0,6,331000],["丽水",0,6,331100]]}');

jQuery(function ($) {

    //绑定事件
    var carinfo = Cookie.read('wwwselcarinfo');
    //$("#engineNumber").val("1111");
    if (carinfo != "") {
        $("#saveCar").attr("checked", "checked");
        var acookie = Cookie.read("wwwselcarinfo").split("|");
        if (acookie[0] != 'undefined')
            cityid = acookie[0];
        getCityByCityId(cityid, $("#citylist"));
        $("#cityname").css('color', '#666');
        $("#provinceName").css('color', '#666');
        if (acookie[1] != 'undefined')
            platenum = decodeURIComponent(acookie[1]);
        if (acookie[2] != 'undefined')
            $("#engineNumber").val(acookie[2]);
        if (acookie[3] != 'undefined')
            $("#vehicleIdNumber").val(acookie[3]);
        if (platenum.length > 0) {
            $("#firstNum").text(decodeURIComponent(platenum).substring(0, 1));
            $("#platenum").val(decodeURIComponent(platenum).substring(1));
            $("#firstNum").css('color', '#666');
            $("#platenum").css('color', '#666');
        }


    }
    $("#selFirstNum dd").click(function () {
        $("#firstNum").text($(this).text().substring(0, 1));
    });

    $("#provinceList dd").click(function () {
        provinceid = $(this).attr("provinceid");
        //加载城市列表
        //        $("#cityname").text("选择城市");
        getCity(provinceid, $("#citylist"));
        //变换首字
        $("#firstNum").text(getFristZM(provinceid));
    });

    $("#btn_select").click(function () {
        platenum = $("#firstNum").text() + $("#platenum").val().replace(/(^\s*)|(\s*$)/g, '');
        egnum = $("#engineNumber").val().replace(/(^\s*)|(\s*$)/g, '');
        vnum = $("#vehicleIdNumber").val().replace(/(^\s*)|(\s*$)/g, '');
        $("#vcityid").val(cityid);
        $("#vplatenum").val(platenum);
        $("#vengineNumber").val(egnum);
        $("#vvehicleIdNumber").val(vnum);

        clearError();

        if (validInput() == false) {
            return;
        }
        else {
            $("#carcode").val('');
            loadingShow();
            saveInfo();
            getViolationList(0);
        }

    })
    $("#recodebtn").click(function () {

        getViolationList(1);
    })
    $("#codebtn").click(function () {
        //GetViolationListByCarCode

        $(".layer").hide();
        saveInfo();
        if ($("#isiplimit").val() == 0)
            getViolationListByCarCode();
        else
            getViolationList(0);
    });
    $(".close-btn").click(function () {
        $(".layer").hide();
        $("#codeerror").hide();
        loadingHide();
    })
    $('#platenum').keyup(function () {
        if (/[a-z]/.test($(this).val()))
            $(this).val($(this).val().toUpperCase());
        if ($(this).val().length > 6)
            $(this).val($(this).val().substr(0, 6));
    });
    $('#engineNumber').keyup(function () {
        if (/[a-z]/.test($(this).val()))
            $(this).val($(this).val().toUpperCase());
    });
    $('#vehicleIdNumber').keyup(function () {
        if (/[a-z]/.test($(this).val()))
            $(this).val($(this).val().toUpperCase());
    });
});
function clearError() {
    $("#GlobalMessage").hide();
    $("#verrmsg").hide();
    $("#eerrmsg").hide();
}
function validInput() {
    var validstate = true;
    if (egnum.indexOf("发动机") > 0) {
        $("#eerrmsg").html("请输入完整发动机号");
        $("#eerrmsg").show();
        validstate = false;

    }
    if (vnum.indexOf("车架号") > 0) {
        $("#verrmsg").html("请输入完整车架号");
        $("#verrmsg").show();
        validstate = false;

    }
    if (platenum.length == "1") {
        globalError('请输入车牌号');
        validstate = false;
    }
    if (elen != egnum.length) {
        if ((elen != 99 && elen != 0 && egnum.length == 0) || (elen == 99 && egnum.length == 0)) {
            $("#eerrmsg").html("请输入完整发动机号");
            $("#eerrmsg").show();
            validstate = false;
        }
    }
    if (vlen != vnum.length) {
        if ((vlen != 99 && vlen != 0 && vnum.length == 0) || (vlen == 99 && vnum.length == 0)) {
            $("#verrmsg").html("请输入完整车架号");
            $("#verrmsg").show();
            validstate = false;
        }
    }
    if ($("#cityname").text() == "选择城市") {
        globalError('请选择城市!');
        validstate = false;
    }

    return validstate;

}
//获取城市
function getCity(id, obj,tcityid) {
    cities = citylistjson[id];
    var str = '<dl>';
    for(var city in cities){
        str += '<dd onclick="saveCity(' + cities[city][3] + ',\'' + cities[city][0] + '\',\'' + cities[city][1] + '\',\'' + cities[city][2] + '\')"><a href="javascript:void(0)">' + cities[city][0] + '</a></dd>';

        if (city == 0 || (tcityid != 'undefined' && tcityid == cities[city][3])) {
            elen = cities[city][1];
            vlen = cities[city][2];
            cityname = cities[city][0];
            cityid = cities[city][3];
            $("#cityname").text(cityname);
            changeInput();
        }
    }
    str += '</dl>';

    obj.html(str);

}
function saveCity(scityid, cityname, elength, vlength) {
    cityid = scityid;
    elen = elength;
    vlen = vlength;
    $("#cityname").html(cityname);
    changeInput();
}
function changeInput() {
    hideError();
    if (elen == 99) {
        $("#engineNumberdiv").show();
        $("#engineNumber").attr("placeholder", "请输入全部发动机号");
    }
    else if (elen == 0) {
        $("#engineNumberdiv").hide();
        $("#engineNumber").val("");
    }
    else {
        $("#engineNumberdiv").show();
        $("#engineNumber").attr("placeholder", "请输入" + elen + "位发动机号");
    }
    if (vlen == 99) {
        $("#vehicleIdNumberdiv").show();
        $("#vehicleIdNumber").attr("placeholder", "请输入全部车架号");
    }
    else if (vlen == 0) {
        $("#vehicleIdNumberdiv").hide();
        $("#vehicleIdNumber").val("");
    }
    else {
        $("#vehicleIdNumberdiv").show();
        $("#vehicleIdNumber").attr("placeholder", "请输入" + vlen + "位车架号");
    }
}
function getFristZM(cityid) {
    var c = cityid.substring(0, 2);
    switch (c) {
        case '11':
            return '京';
        case '31':
            return '沪';
        case '33':
            return '浙';
        case '32':
            return '苏';
        case '44':
            return '粤';
        case '37':
            return '鲁';
        case '14':
            return '晋';
        case '13':
            return '冀';
        case '51':
            return '川';
        case '41':
            return '豫';
        case '50':
            return '渝';
        case '21':
            return '辽';
        case '22':
            return '吉';
        case '23':
            return '黑';
        case '34':
            return '皖';
        case '42':
            return '鄂';
        case '43':
            return '湘';
        case '36':
            return '赣';
        case '35':
            return '闽';
        case '61':
            return '陕';
        case '62':
            return '甘';
        case '64':
            return '宁';
        case '15':
            return '蒙';
        case '12':
            return '津';
        case '52':
            return '贵';
        case '53':
            return '云';
        case '45':
            return '桂';
        case '46':
            return '琼';
        case '63':
            return '青';
        case '65':
            return '新';
        case '54':
            return '藏';
        default:
            return '京'

    }
}


function getViolationList(authcodetype) {

//    $.post(url+'/violation/GetViolationTotal',
//                { cityid:
//                cityid, platenum: platenum, egnum: egnum, vnum: vnum
//                },
//                function (data) {
//                    jsonData(data);

    //                });
    platenum = encodeURIComponent(decodeURIComponent(platenum).replace(/(^\s*)|(\s*$)/g, ''));
    $.ajax({
        url:url+'/violation/GetViolationTotal',
        type: 'POST',
        data: { cityid: cityid, platenum: platenum, egnum: egnum, vnum: vnum, authcode: $("#carcode").val(), authcodetype: authcodetype },
        dataType: 'json',
        timeout: 30000,
        error: function(){globalError('服务器开小差了，请稍后重试!');},
        success: function(data){jsonData(data);}
    });

}
function getViolationListByCarCode() {

    $.post(url+'/Violation/GetViolationListByCarCode',
        { cityid:
        cityid, carid: carid, carcode: $("#carcode").val()
        },
        function (data) {
            jsonData(data);

        });
}

function jsonData(data) {
    //
    //    $("#loaddiv").hide();
    //    $("#loaddiv").attr("style", "display:none");

    if (typeof (data) != 'undefined' || data.length > 0) {
        if (data.Data == "") {

        }
        if (typeof (data.Data) === 'string')
            data.Data = $.parseJSON(data.Data);


        //验证
        if (validResult(data.Data) == false)
            return;

    }
}
function saveInfo() {
    if ($("#saveCar").prop("checked") == true) {
        var selcarinfo = cityid + "|" + encodeURIComponent(decodeURIComponent(platenum).replace(/(^\s*)|(\s*$)/g, '')) + "|" + egnum.replace(/(^\s*)|(\s*$)/g, '') + "|" + vnum.replace(/(^\s*)|(\s*$)/g, '');
        Cookie.write('wwwselcarinfo', selcarinfo, 30);
    }
    else {
        Cookie.remove('wwwselcarinfo');
    }
}

function validResult(data) {
    if (data.returncode != 0) {

        if (data.returncode == -1) {
            //服务器错误
            globalError('服务器开小差了，请稍后重试!');
        }
        else if (data.returncode == -50) {
            //验证码
            //$("#carcode").val("");
            //$("#errorTipDiv").hide();
            carid = data.result.carid;
            $("#loading").hide();
            $(".layer").show();
            $("#codeimg").attr("src", "data:image/gif;base64," + data.result.citys[0].authimage);

            $("#isiplimit").val(0);
        } else if (data.returncode == -54) {
            //验证码
            //$("#carcode").val("");
            //$("#errorTipDiv").hide();
            carid = data.result.carid;
            $("#loading").hide();
            $(".layer").show();
            $("#codeimg").attr("src", "data:image/gif;base64," + data.result.citys[0].authimage);

            $("#isiplimit").val(1);
        }
        else if (data.returncode == -56) {
            //验证码
            //$("#carcode").val("");
            //$("#errorTipDiv").hide();
            //carid = data.result.carid;
            $("#loading").hide();
            $(".layer").show();
            $("#codeerror").show();

            $("#isiplimit").val(1);
        }
        else if (data.returncode < -80) {
            globalError(data.message);
        }
        else {
            globalError('服务器开小差了，请稍后重试!');
        }
        return false;
    }
    else {
        if (typeof (data.result) != 'undefined' && data.result != null && data.result.items[0].citys[0].authimage != "") {
            $("#codeerror").show();
            $("#loading").hide();
            $("#carcode").val("");
            $(".layer").show();
            $("#codeimg").attr("src", "data:image/gif;base64," + data.result.items[0].citys[0].authimage);
            return false;
        }
        else {
            //alert(encodeURIComponent);
            //location.href = '/violation/violationList?cityid=' + cityid + '&platenum=' + encodeURIComponent(platenum) + '&engineNumber=' + egnum + '&vehicleIdNumber=' + vnum;
            $("#violationlist").submit();
        }
    }

}

function getCityByCityId(cityid, obj) {
    var cprovinceId = cityid.substring(0, 2) + '0000';
    getCity(cprovinceId, obj,cityid);
    provinceName = $("dd[provinceid='" + cprovinceId + "']").text();
    $("#provinceName").text(provinceName);
    changeInput();
}
function globalError(c) {
    $("#GlobalMessage").html(c);
    $("#GlobalMessage").show();
    loadingHide();
}
function loadingHide() {
    $("#selectBtn").show();
    $("#loadingBtn").hide();
    $("#loading").hide();
}
function loadingShow() {
    $("#loading").show();
    $("#selectBtn").hide();
    $("#loadingBtn").show();
}
function hideError() {

    $("#eerrmsg").hide();
    $("#verrmsg").hide();
    $("#GlobalMessage").hide();
}