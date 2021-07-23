<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2021/7/23
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>>七号商城<</title>
    <link rel="stylesheet" href="/trading_platform/lib/layui/css/layui.css">
</head>
<body class="layui-layout-body">
    <div class="layui-body">
        <!--表单，查询出的数据在这里显示-->
        <table id="userTable" lay-filter="userTable"></table>
    </div>
<script src="/trading_platform/lib/layui/layui.js"></script>

<script>
    layui.use(['table'], function () {
        var table = layui.table;
        var userId = sessionStorage.getItem("userId");

        /*表格获取数据+渲染*/
        table.render({
            elem: '#userTable',
            url: '/buyRecordGet?userId='+userId,
            tool: '#userTableBar',
            defaultToolbar: [],
            cols: [[
                {field: 'userName', width: 100, title: '收件人'},
                {field: 'goodName', width: 150, title: '商品名'},
                {field: 'totalPrice', width: 80, title: '总价'},
                {field: 'createTime', width: 150, title: '下单时间'}
            ]],
            limits: [5, 10, 15, 20, 25, 50, 100],
            limit: 10,  <!--默认显示10条-->
            page: true
        });
    })
</script>
</body>
</html>
