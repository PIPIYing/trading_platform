<%--
  Created by IntelliJ IDEA.
  User: 12419
  Date: 2021/7/22
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
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
        <div class="layui-logo"> > 七号商城后台管理 < </div>
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
                    <a href="/userIndex">用户管理</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/goodIndex">商品管理</a>
                </li>
                <%--<li class="layui-nav-item">
                    <a href="">公告展示</a>
                </li>
                <li class="layui-nav-item">
                    <a href="">商品展示</a>
                </li>
                <li class="layui-nav-item">
                    <a href="">购物车</a>
                </li>
                <li class="layui-nav-item">
                    <a href="">支付订单</a>
                </li>--%>
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

            <script type="text/html" id="userToolbar">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                    <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
                </div>
            </script>

            <!--表单，查询出的数据在这里显示-->
            <table id="userTable" lay-filter="userTable"></table>

            <script type="text/html" id="userTableBar">
                <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">修改库存</a>
                <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
                <%--<a class="layui-btn layui-btn-xs layui-btn-danger data-count-edit" lay-event="detail">详情</a>--%>
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
            toolbar: '#userToolbar',
            tool: '#userTableBar',
            defaultToolbar: [],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: 'ID', sort: true},
                {field: 'name', width: 150, title: '商品名'},
                {field: 'amount', width: 80, title: 'amount'},
                {field: 'price', width: 80, title: 'price'},
                {field: 'description', width: 150, title: 'description'},
                {title: '操作', minWidth: 50, toolbar: '#userTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
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

        /*toolbar栏监听事件*/
        table.on('toolbar(userTable)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加商品',
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/goodAdd',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if(obj.event === 'delete') {  //监听删除操作
                var checkStatus=table.checkStatus(obj.config.id);
                var data=checkStatus.data;
                if(data.length==0){//如果没有选中信息
                    layer.msg("请选择要删除的记录信息");
                }else{
                    //获取记录信息的id集合,拼接的ids
                    var ids=getCheackId(data);
                    layer.confirm('确定是否删除', function (index) {
                        //调用删除功能
                        deleteInfoByIds(ids,index);
                        layer.close(index);
                    });
                }
            }
        })

        /*tool栏监听事件*/
        table.on('tool(userTable)', function (obj) {
            var data=obj.data;
            if (obj.event === 'edit') {  // 监听修改操作
                var index = layer.open({
                    title: '修改库存信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/goodUpdate?id='+data.id
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                layer.confirm('确定是否删除', function (index) {
                    //调用删除功能
                    deleteInfoByIds(data.id,index);
                    layer.close(index);
                });
            } else if (obj.event === 'detail') {  // 监听详情操作
                var index = layer.open({
                    title: '详情信息',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['60%', '60%'],
                    content: '/goodDetail?id='+data.id
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                console.log("点击详情按钮")
            }
        });

        /*删除用户*/
        function deleteInfoByIds(ids ,index){
            //向后台发送请求
            $.ajax({
                url: "/goodDelete",
                type: "POST",
                data: {ids: ids},
                success: function (result) {
                    if (result.code == 0) {//如果成功
                        layer.msg('删除成功', {
                            icon: 6,
                            time: 500
                        }, function () {
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        });
                    } else {
                        layer.msg("删除失败");
                    }
                }
            })
        };

        /*获取id组*/
        function getCheackId(data){
            var arr=new Array();
            for(var i=0;i<data.length;i++){
                arr.push(data[i].id);
            }
            //拼接id,变成一个字符串
            return arr.join(",");
        };
    })

    window.onload = function() {
        var username = sessionStorage.getItem("userName");
        var usernameDom = document.getElementById("nameTag");
        usernameDom.innerText = username;
    }
</script>
</body>

</html>
