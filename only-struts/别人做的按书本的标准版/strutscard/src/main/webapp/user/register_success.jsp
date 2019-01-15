<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path=request.getContextPath()+"/"; %>
  
<html>
<head>
<title>注册成功！</title>
</head>
<body>
<h3>恭喜，${user.userRealName},你成功注册了我们的管理系统！点此</h3>
<a href="<%=path%>user/login.jsp">登录</a>
</body>
</html>