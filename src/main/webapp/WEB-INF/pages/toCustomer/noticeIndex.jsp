<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <%--<dd>
                        <a href="">基本资料</a>
                    </dd>--%>
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

        <div class="demoTable" style="margin-top: 20px;margin-left: 20px;">
            <div class="layui-form-item layui-form">
                公告id：
                <div class="layui-inline">
                    <input class="layui-input" name="name" id="name" autocomplete="off">
                </div>
                <button class="layui-btn" data-type="reload">搜索</button>
            </div>
        </div>

        <div style="padding: 15px;">

            <table id="userTable" lay-filter="userTable"></table>

        </div>
    </div>
</div>
<script src="/trading_platform/lib/layui/layui.js"></script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#userTable',
            url: '/noticeAll',//数据接口
            toolbar: '#userToolbar',
            tool: '#userTableBar',
            defaultToolbar: [],
            page: true,//开启分页
            cols: [[ //表头
                {type: "checkbox", width: 50},
                {field: 'id', title: '公告编号', width:150, sort: true}
                /*,{field: 'user_id', title: '用户id', width:150, sort: true}*/
                ,{field: 'authorName', title: '发布者', width:150}
                ,{field: 'title', title: '标题', width:150}
                ,{field: 'content', title: '内容', width:150}
                ,{field: 'newestTime', title: '最新修改时间', width:200}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,  <!--默认显示15条-->
            id:'userReload'
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
                        id: name
                    },
                    url: "/searchNotice",   //请求路径
                    method: "POST"
                }, 'data');
            }
        };
        $('.demoTable .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });

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
        var username = sessionStorage.getItem("userName");
        var usernameDom = document.getElementById("nameTag");
        usernameDom.innerText = username;
    }
</script>
</body>

</html>
