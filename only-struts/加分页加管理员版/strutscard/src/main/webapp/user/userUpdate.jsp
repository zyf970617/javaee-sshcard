<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>修改用户信息</title>
    <script>
        function showPass() {
            var txt = document.getElementById("show-msg").innerText;
            if (txt=="显示密码") {
                document.getElementById("show-msg").innerText = "隐藏密码";
                document.getElementById("userPass").type="text";
                document.getElementById("userPass").disabled=true;
            } else {
                document.getElementById("show-msg").innerText = "显示密码";
                document.getElementById("userPass").type="password";
                document.getElementById("userPass").disabled=false;
            }
        }
        window.onload = function () {
            var sex = document.getElementById("uType").innerText;
            if (sex=="超级管理员") {
                document.getElementById("male").checked=true;
            } else {
                document.getElementById("female").checked=true;
            }
        }
    </script>
</head>
<body>
<h2 id="body-title">修改用户信息</h2>
<form id="form1" action="../xsw/submitForUpdate" method="post">
    <span style="display: none;" id="uType">${update_user.userType}</span>
    <input type="text" name="user.userId" value="${update_user.userId}" style="display: none;"/>
    <label>账户名：</label><input type="text" name="user.userName" required="true" value="${update_user.userName}" maxlength="16"/><br/>
    <label>&nbsp;&nbsp;&nbsp;&nbsp;密码：</label><input type="password" id="userPass" name="user.userPassword" required="true" value="${update_user.userPassword}" maxlength="16"/> <a style="font-size: 12px;" href="javascript:;" onclick="showPass()"><span id="show-msg">显示密码</span></a><br/>
    <label>&nbsp;&nbsp;&nbsp;&nbsp;姓名：</label><input type="text" name="user.userRealName" value="${update_user.userRealName}" required="true" maxlength="50"/><br/>
    <label>&nbsp;&nbsp;&nbsp;&nbsp;类型：</label>超级管理员<input type="radio" name="user.userType" value="超级管理员" checked="checked" id="male"/> 二级管理员<input type="radio" name="user.userType" value="二级管理员" id="female"/><br/>
    <input type="submit" value="提交"/>
    <input type="reset" value="取消"/>
</form>
</body>
</html>
