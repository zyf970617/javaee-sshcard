<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>用户登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
    <form method="post" action="xsw/login">
        <h2>用户登录</h2>
        用户名：<input type="text" name="user.userName" /><span style="color:red;font-size: 13px;">${error_msg}</span><br/>
        &nbsp;&nbsp;&nbsp;&nbsp;密码：<input type="password" name="user.userPassword" /><br/>
        <input type="submit" value="登录"/>&nbsp;&nbsp;未注册者，请先注册，单击<a href="/register.jsp">注册</a>
    </form>
</body>
</html>
