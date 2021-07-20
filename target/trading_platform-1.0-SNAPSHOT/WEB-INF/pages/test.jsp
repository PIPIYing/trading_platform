<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>读取admin表</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <td>id</td>
        <td>用户名</td>
        <td>密码</td>
        <td>管理员类型</td>
    </tr>

    <c:if test="${admins ne null}">
        <c:forEach items="${admins}" var="admin">
            <tr>
                <td>${admin.id}</td>
                <td>${admin.username}</td>
                <td>${admin.password}</td>
                <td>${admin.adminType}</td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>