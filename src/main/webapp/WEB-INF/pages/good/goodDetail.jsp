<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品详情</title>
    <style>
        p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div style="margin: 30px auto;">
        <p>商品名: ${good.name}</p>
        <p>库存: ${good.amount}</p>
        <p>单价（元）: ${good.price}</p>
        <p>商品描述: ${good.description}</p>
    </div>
<%--<table border="1px solid black" margin="20px auto">
    <tr>
        <td>商品名</td>
        <td>库存</td>
        <td>单价</td>
        <td>商品描述</td>
    </tr>

    <c:if test="${good ne null}">
        <tr>
            <td>${good.name}</td>
            <td>${good.amount}</td>
            <td>${good.price}</td>
            <td>${good.description}</td>
        </tr>
    </c:if>

</table>--%>
</body>
</html>
