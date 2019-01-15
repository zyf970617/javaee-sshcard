<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>导入名片</title>
</head>
<body>
<h3>上传Excel文档</h3>
<h4>注意，电子表只能为".xls"，并且表中从左到右，各字段信息依次为：</h4>
<h5>姓名，性别，单位，手机，电话，电子邮箱，地址</h5>
<form action="../upload/cardExcel" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
