<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的订单</title>
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>

</head>
<body>
<jsp:include page="include/header.jsp"></jsp:include>

<div class="container">
<mark>展示单个订单信息</mark>
</div>
<script type="text/javascript">
    function realLogin(){
            $.ajax({
                cache: true,
                type: "POST",
                url:"/user/login",
                data:$('#loginForm').serialize(),// 你的formid
                async: false,
                error: function(request) {
                    alert("Connection error");
                },
                success: function(data) {
                    if (data.code==200){
                        location.href="/v1/toIndexPage";
                    }else{
                        alert(data.message);
                    }

                }
            });
    }

</script>
</body>
</html>
