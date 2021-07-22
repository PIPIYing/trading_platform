<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>添加商品</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/trading_platform/lib/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="/trading_platform/css/public.css" media="all">
    <style>
        body {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<div class="layui-form layuimini-form">
    <div class="layui-form-item">
        <label class="layui-form-label required">商品名</label>
        <div class="layui-input-block">
            <input type="text" name="name" autocomplete="off" class="layui-input" lay-verify="required" lay-reqtext="商品名不能为空">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">库存</label>
        <div class="layui-input-block">
            <input type="text" name="amount" class="layui-input" lay-verify="required" lay-reqtext="库存不能为空">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">单价</label>
        <div class="layui-input-block">
            <input type="text" name="price" class="layui-input" lay-verify="required" lay-reqtext="单价不能为空">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label required">商品描述</label>
        <div class="layui-input-block">
            <input type="text" name="description" class="layui-input" lay-verify="required" lay-reqtext="商品描述不能为空">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block required">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认添加</button>
        </div>
    </div>
</div>
<script src="/trading_platform/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听增加提交
        form.on('submit(saveBtn)', function (data) {
            var datas = data.field;//form单中的数据信息
            console.log(datas);
            //向后台发送数据提交添加
            $.ajax({
                url: "goodAddSumbit",
                type: "POST",
                data: datas,
                success:function(result){
                    if(result.code==0){//如果成功
                        layer.msg('添加成功',{
                            icon:6,
                            time:500
                        },function(){
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                         layer.msg("添加失败");
                    }
                }
            })
            return false;
        });

        //监听上传提交
        /*form.on('submit(uploadBtn)', function (data) {
            var datas = data.field; //form单中的数据信息
            console.log(datas);
            //向后台发送数据提交添加
            $.ajax({
                url: "upload",
                type: "POST",
                contentType: "multipart/form-data",
                data: datas,
                success:function(result){
                    console.log(result);
                    /!*if(result.code==0){//如果成功
                        layer.msg('添加成功',{
                            icon:6,
                            time:500
                        },function(){

                        })
                    }else{
                        layer.msg("添加失败");
                    }*!/
                }
            })
            return false;
        });*/
    });
</script>
</body>
</html>
