<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path=request.getContextPath()+"/"; %>
   

<html>
<head>
<title>登录成功！</title>
</head>
<body>
欢迎你，${user.userRealName}，你登录成功！！<br>
欢迎进入名片管理系统，请点击
<a href="<%=path%>card/find">名片管理系统</a>
</body>
</html>