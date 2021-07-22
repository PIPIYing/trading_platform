<%--
  Created by IntelliJ IDEA.
  User: 12419
  Date: 2021/7/22
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <script src="/trading_platform/lib/layui/layui.js"></script>

    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/trading_platform/lib/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/trading_platform/lib/layui/style/admin.css" media="all">
    <link rel="stylesheet" href="/trading_platform/lib/layui/style/login.css" media="all">
</head>
<body>

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>二手商品交易平台</h2>
            <p> 7号</p>
            <div style="color: red;text-align: center;margin-top: 20px;">${msg}</div>
        </div>
        <form class="layui-form"  action="/login" method="post">
            <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                    <input type="text" name="userName" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                    <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
                </div>

                <div class="layui-form-item">
                    <select name="type" lay-verify="required">
                        <option value="">请选择用户类型</option>
                        <option value="0">管理员</option>
                        <option value="1">客户</option>
                    </select>
                </div>


                <%--<div class="layui-form-item" style="margin-bottom: 20px;">
                    <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
                    <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
                </div>--%>
                <div class="layui-form-item">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-login-submit">登录</button>
                </div>

                <div class="layui-trans layui-form-item layadmin-user-login-other">
                    <a href="/toRegister" class="layadmin-user-jump-change layadmin-link">用户注册</a>
                </div>

            </div>
        </form>

    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>© All Rights Reserved for seven</p>
    </div>

</div>

<script src="/trading_platform/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">

    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;
        form.on('submit(LAY-user-login-submit)', function (data) {
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