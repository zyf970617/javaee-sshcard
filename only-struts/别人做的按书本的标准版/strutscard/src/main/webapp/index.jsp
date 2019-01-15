<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String path=request.getContextPath()+"/"; %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主界面</title>
</head>
<body>
    <h1>名片管理系统</h1>
    <a href="<%=path %>user/login.jsp">用户管理子系统</a><br><br>
    <a href="<%=path %>card/find">名片管理子系统</a>
</body>
</html>