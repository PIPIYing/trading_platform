<%--
  Created by IntelliJ IDEA.
  User: YURI
  Date: 2021/7/20
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>order</title>
</head>
<body>
    <table border="1px solid black">
        <tr>
            <td>订单编号</td>
            <td>用户名</td>
            <td>商品名</td>
            <td>单价</td>
            <td>总价格</td>
            <td>电话</td>
            <td>地址</td>
            <td>备注</td>
            <td>创建时间</td>
        </tr>

        <c:if test="${orders ne null}">
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.username}</td>
                    <td>${order.password}</td>
                    <td>${order.amount}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.phone}</td>
                    <td>${order.address}</td>
                    <td>${order.remark}</td>
                    <td>${order.createTime}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
