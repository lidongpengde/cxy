最近做项目，好多页面需要传递值。现在我用了以下的方法。感觉很好用。
1、利用页面链接传递，用？号链接，不过这样传递中文会麻烦些，数字挺方便的。
页面一：
<script> 
function to(){ 
var getval =document.getElementById("cc").value; 
window.location.href="b.html?id="5555"; 
} 
</script> 
页面二：
取值
var thisURL = document.URL; 
var getval =thisURL.split('?')[1]; 
var showval= getval.split("=")[1]; 
showval==5555；//这个就是取出来的值

2、利用localstarage和sesionstorage 存取值；
setItem存储value
用途：将value存储到key字段
用法：.setItem( key, value)
代码示例：
sessionStorage.setItem("key", "value"); localStorage.setItem("site", "js8.in");

getItem获取value
用途：获取指定key本地存储的值
用法：.getItem(key)
代码示例：

var value = sessionStorage.getItem("key"); var site = localStorage.getItem("site");
localstarage和sesionstorage 只能存储字符串类型值，对象类型的值可以先进行转化
例如：

var obj = { name:'Jim' };
var str = JSON.stringify(obj); 
//存入 
sessionStorage.obj = str; 
//读取 
str = sessionStorage.obj; 
//重新转换为对象 
obj = JSON.parse(str);


