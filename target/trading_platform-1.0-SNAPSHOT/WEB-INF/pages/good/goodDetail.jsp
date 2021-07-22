<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>goodDetail</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>amount</td>
        <td>price</td>
        <td>description</td>
    </tr>

    <c:if test="${good ne null}">
        <tr>
            <td>${good.id}</td>
            <td>${good.name}</td>
            <td>${good.amount}</td>
            <td>${good.price}</td>
            <td>${good.description}</td>
        </tr>
    </c:if>

</table>
</body>
</html>
