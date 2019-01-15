<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<html>
<head>
<title>登录界面</title>
</head>
<body>
<s:fielderror cssStyle="color:red"></s:fielderror>
<font color="red"><s:property value="msg"/></font><br>

<form action="<%=basePath%>user/login" method="post">
<table>

<tr><th colspan="2">用户登录</th></tr>
<tr><td align="right">用户名：</td>
<td><input type="text" name="user.userName" value="${user.userName}"/></td>
</tr>
<tr><td align="right">密码：</td>
<td><input type="password" name="user.userPassword"/></td>
</tr>
<tr><td align="left"><input type="submit" value="登录"/></td>
<td>未注册者，请先注册，单击
<a href="<%=path%>/user/register.jsp">注册</a></td></tr>
</table>

</form>
</body>
</html>

