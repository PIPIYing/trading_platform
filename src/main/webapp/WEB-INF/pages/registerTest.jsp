<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>二手交易平台 - 注册页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <script type="text/javascript" src="/trading_platform/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="/trading_platform/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/trading_platform/css/public.css" media="all">
</head>
<body>

<%--action是用来调用controller的控制器--%>
<form class="layui-form"  action="/register" method="post">
    <div class="layui-form-item logo-title">
        <h1>二手交易平台 - 注册页</h1>
        <div style="color: red;text-align: center;">${msg}</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-icon layui-icon-username"></label>
        <input type="text" name="userName" lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-item">
        <label class="layui-icon layui-icon-password"></label>
        <input type="password" name="password" lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input" />
    </div>
    <div class="layui-form-item">
        <label class="layui-icon layui-icon-username"></label>
        <select name="type" lay-verify="required">
            <option value="">请选择用户类型</option>
            <option value="1">客户</option>
        </select>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-btn layui-btn-normal layui-btn-fluid" lay-submit="" lay-filter="register">确认</button>
    </div>
</form>

<script src="/trading_platform/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script type="text/javascript">

    layui.use(['form'], function () {
        var form = layui.form;
        form.on('submit(register)', function (data) {
            data = data.field;
            if (data.userName == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (data.type == '') {
                layer.msg('类型不能为空');
                return false;
            }
        });
    });

</script>
</body>
</html>