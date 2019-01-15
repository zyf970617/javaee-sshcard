<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>用户注册</title>
    <script src="js/jquery.js"></script>
    <script>
        $(document).ready(function () {
            $(".submit-register").click(function () {
                var name = $(":input[name='userName']").val();
                var pass = $(":input[name='userPassword']").val()
                var repeat = $(":input[name='repeat']").val();
                var realName = $(":input[name='userRealName']").val();
                if (name==""||pass==""||repeat==""||realName=="") {
                    alert("请填写完整的信息");
                } else if (pass.length<6) {
                    alert("密码长度不能小于6位")
                } else if (pass!=repeat) {
                    alert("两次密码不一致");
                } else {
                    $.ajax({
                        url:"xsw/registerUser",type:"post",
                        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                        data:{"user.userName":name,
                            "user.userPassword":pass,
                            "user.userRealName":realName},
                        success: function (data) {
                            if (data=="ok") {
                                alert("注册成功，即将跳转到登录页");
                                setTimeout("window.location.href='login.jsp'", 1000);
                            } else {
                                alert("注册失败，请重试");
                            }
                        } ,error: function () {
                            alert("注册失败，请重试");
                        }
                    });
                }

            });
        })
    </script>
</head>
<body>
<h1>欢迎注册我们的系统，请认真填写你的信息</h1>
<form method="post" action="*">
    &nbsp;&nbsp;&nbsp;&nbsp;账户名：<input type="text" name="userName" required="true" maxlength="16"/><br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码：<input type="password" name="userPassword" required="true" maxlength="16"/><br/>
    重复密码：<input type="password" name="repeat" required="true" maxlength="16"/><br/>
    真实姓名：<input type="text" name="userRealName" required="true" maxlength="16"/><br/>
    <input type="button" value="提交" class="submit-register"> <input type="reset" value="重置"/>
</form>
</body>
</html>
