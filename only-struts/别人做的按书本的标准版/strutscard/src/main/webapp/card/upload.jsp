<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传记录页面</title>
</head>
<body>
   上传Excel文件，其电子表
   <s:form action="/card/upload" method="post" enctype="multipart/form-data">
        <s:file name="file" label="提交文件"></s:file><br><br>
       <s:submit value="提交"></s:submit>
   </s:form>
</body>
</html>