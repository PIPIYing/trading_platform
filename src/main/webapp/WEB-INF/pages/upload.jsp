<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h2>文件上传</h2>
<form action="/upload" method="post" enctype="multipart/form-data">
    <p>请选择文件：<input type="file" name="file"></p>
    <p><input type="submit" value="上传"></p>
</form>
</body>
</html>