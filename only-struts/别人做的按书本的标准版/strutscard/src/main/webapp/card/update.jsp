<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改名片</title>
</head>
<body>
<h2>修改名片信息</h2>
<s:form action="/card/update" method="post">
	<s:hidden name="card.id"></s:hidden>
	<s:textfield label="姓名" name="card.name"></s:textfield>
	<s:radio label="性别" list="#{'男':'男','女':'女'}"
					name = "card.sex" value="男"></s:radio>
	<s:textfield label="单位" name="card.department"></s:textfield>
	<s:textfield label="手机" name="card.mobile"></s:textfield>
	<s:textfield label="电话" name="card.phone"></s:textfield>
	<s:textfield label="Email" name="card.email"></s:textfield>
	<s:textfield label="地址" name="card.address"></s:textfield>
	<table>
	<tr>
		<td><s:submit value="修改" theme="simple" /></td>
		<td><s:reset value="取消" theme="simple" /></td>
	</tr>
	</table>
</s:form>
</body>
</html>