<%--
  Created by IntelliJ IDEA.
  User: 12419
  Date: 2021/7/22
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>order表单</title>
    <link rel="stylesheet" href="/trading_platform/lib/layui/css/layui.css" media="all">
    <script type="text/javascript" src="/trading_platform/lib/layui/layui.js"></script>
</head>

<body>
    <input name="id" type="hidden" value="${good.id}">
    <input name="name" type="hidden" value="${good.name}">
    <input name="price" type="hidden" value="${good.price}">

    <div class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label required">收件人姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="userName" required lay-verify="required" placeholder="请输入收件人姓名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label required">商品名框</label>
            <div class="layui-input-inline">
                <input type="text" name="goodName" required lay-verify="required" disabled value="${good.name}" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">购买数量</label>
            <div class="layui-input-inline">
                <select name="amount" lay-verify="required">
                    <option value=""></option>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                </select>
            </div>
            <div class="layui-form-mid layui-word-aux">限购15件</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">收件人电话</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" required  lay-verify="required" placeholder="请输入收件人电话" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label required">收件人地址</label>
            <div class="layui-input-inline">
                <input type="text" name="address" required  lay-verify="required" placeholder="请输入收件地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label required">备注</label>
            <div class="layui-input-inline">
                <textarea name="remark" placeholder="请输入备注信息" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" class="layui-btn" lay-submit lay-filter="saveBtn">立即提交</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
    </div>

<script>

    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer,
            $ = layui.$;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            console.log("点击提交按钮");
            var datas = data.field;
            var userId = sessionStorage.getItem("userId");
            var totalPrice = parseInt(datas.amount) * parseInt(datas.price);
            var createTime = new Date().Format("yyyy-MM-dd HH:mm:ss");
            $.ajax({
                url:"/buy",  /*提交url*/
                type:"POST",
                data: {
                    userId: userId,
                    goodId: datas.id,
                    userName: datas.userName,
                    goodName: datas.goodName,
                    amount: datas.amount,
                    totalPrice: totalPrice,
                    phone: datas.phone,
                    address: datas.address,
                    remark: datas.remark,
                    createTime: createTime
                },
                success:function(result){
                    if(result.code==0){//如果成功
                        layer.msg("购买成功",{
                            icon:6,
                            time:500
                        },function(){
                            parent.window.location.reload();
                            var iframeIndex = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(iframeIndex);
                        })
                    }else{
                        layer.msg(result.msg);
                    }
                }
            })
            return false;
        });

    })

    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

</script>
</body>
</html>