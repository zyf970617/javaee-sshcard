<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>添加名片</title>
    <script>
        window.onload = function () {
            var sex = document.getElementById("sex").innerText;
            if (sex=="男"||sex=="女") {
                document.title = "修改名片";
                document.getElementById("body-title").innerText = "修改名片";
                document.getElementById("form1").action="../cards/submitForUpdate";
            }
            if (sex=="女") {
                document.getElementById("female").checked=true;
            } else {
                document.getElementById("male").checked=true;
            }
        }
    </script>
</head>
<body>
<h2 id="body-title">添加名片</h2>
<form id="form1" action="../cards/Inserts" method="post">
    <span style="display: none;" id="sex">${update_card.sex}</span>
    <input type="text" name="card.id" value="${update_card.id}" style="display: none;"/>
    <label>姓名：</label><input type="text" name="card.name" required="true" value="${update_card.name}" maxlength="16"/><br/>
    <label>性别：</label>男:<input type="radio" name="card.sex" value="男" checked="checked" id="male"/> 女:<input type="radio" name="card.sex" value="女" id="female"/><br/>
    <label>单位：</label><input type="text" name="card.department" value="${update_card.department}" required="true" maxlength="50"/><br/>
    <label>手机：</label><input type="number" name="card.mobile" value="${update_card.mobile}" required="true" maxlength="20"/><br/>
    <label>电话：</label><input type="number" name="card.phone" value="${update_card.phone}" required="true" maxlength="20"/><br/>
    <label>邮箱：</label><input type="email" name="card.email" value="${update_card.email}" required="true" maxlength="50"/><br/>
    <label>地址：</label><input type="text" name="card.address" value="${update_card.address}" required="true" maxlength="100"/><br/>
    <input type="submit" value="提交"/>
    <input type="reset" value="取消"/>
</form>
</body>
</html>
