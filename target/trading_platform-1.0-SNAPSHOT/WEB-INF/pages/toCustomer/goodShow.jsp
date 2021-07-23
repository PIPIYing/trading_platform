<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>>七号商城<</title>
    <link rel="stylesheet" href="/trading_platform/lib/layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo"> > 七号商城 < </div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img"> <span id="nameTag"></span>
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a id="correct">修改密码</a>
                    </dd>
                    <dd>
                        <a id="buyRecord">购买记录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="/toLogin">登出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item">
                    <a href="/goodShow">商品展示</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/noticeShow">公告展示</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">

            <div class="demoTable">
                <div class="layui-form-item layui-form">
                    商品名：
                    <div class="layui-inline">
                        <input class="layui-input" name="name" id="name" autocomplete="off">
                    </div>
                    <button class="layui-btn" data-type="reload">搜索</button>
                </div>
            </div>


            <!--表单，查询出的数据在这里显示-->
            <table id="userTable" lay-filter="userTable"></table>

            <script type="text/html" id="userTableBar">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail">详情</a>
                <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="buy">购买</a>
           </script>


        </div>
    </div>
</div>
<script src="/trading_platform/lib/layui/layui.js"></script>

<script>
    layui.use(['table'], function () {
        var table = layui.table;

        /*表格获取数据+渲染*/
        table.render({
            elem: '#userTable',
            url: '/goodAll',
            tool: '#userTableBar',
            defaultToolbar: [],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'name', width: 150, title: '商品名'},
                /*{field: 'amount', width: 80, title: 'amount'},*/
                {field: 'price', width: 80, title: 'price'},
                /*{field: 'description', width: 150, title: 'description'},*/
                {title: '操作', minWidth: 50, toolbar: '#userTableBar', align: "center"}
            ]],
            limits: [5, 10, 15, 20, 25, 50, 100],
            limit: 10,  <!--默认显示10条-->
            page: true,
            id: 'userReload'
        });

        /*搜索事件*/
        var $ = layui.$, active = {
            reload: function () {
                console.log("点击搜索按钮");
                var name = $('#name').val();
                console.log("名: " + name);
                //执行重载
                table.reload('userReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    , where: {                  //请求参数
                        name: name
                    },
                    url: "/goodSearch",   //请求路径
                    method: "POST"
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        /*tool栏监听事件*/
        table.on('tool(userTable)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {  // 监听详情操作
                var index = layer.open({
                    title: '详情信息',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/goodDetail?id=' + data.id
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'buy') {
                var index = layer.open({
                    title: '填写订单',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/orderDetail?id='+data.id
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });
    })

    layui.use('layer', function(){
        var $ = layui.jquery,
            layer = layui.layer;

        $(document).on('click', '#correct', function() {
            var index = layer.open({
                title: '修改密码信息',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['60%', '60%'],
                content: '/userPwdUpdate'
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        });

        $(document).on('click', '#buyRecord', function() {
            var index = layer.open({
                title: '查看购买记录',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['60%', '60%'],
                content: '/buyRecord'
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        });
    })

    window.onload = function() {
        console.log("进入主页");
        console.log(sessionStorage.getItem("userId"))
        console.log(sessionStorage.getItem("userName"))

        /*sessionStorage.setItem("userId",GetQueryString('id'));
        sessionStorage.setItem("userName",GetQueryString('name'));
        var username = sessionStorage.getItem("userName");
        var usernameDom = document.getElementById("nameTag");
        usernameDom.innerText = username;*/

        if(!sessionStorage.getItem("userId")) {
            sessionStorage.setItem("userId",GetQueryString('id'));
        }
        if(!sessionStorage.getItem("userName")) {
            sessionStorage.setItem("userName",GetQueryString('name'));
            console.log(sessionStorage.setItem("userName",GetQueryString('name')));
        }
        var username = sessionStorage.getItem("userName");
        var usernameDom = document.getElementById("nameTag");
        usernameDom.innerText = username;
        function GetQueryString(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        }
    }
</script>
</body>

</html>
