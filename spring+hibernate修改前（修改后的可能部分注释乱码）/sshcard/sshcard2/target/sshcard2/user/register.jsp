<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>注册</title>
        <link rel="stylesheet" href="../assets/css/bootstrap.css">
		<link rel="stylesheet" href="../assets/css/form-elements.css">
        <link rel="stylesheet" href="../assets/css/style.css">
    </head>
    <body>
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6 text">
                            <h1>J2EE课程设计之名片管理系统</h1>
                            <div class="description">
                            	<p>
	                            	请再右侧完善你的个人信息，提交之后即可注册（本系统使用前端页面并非原创，来自于Bootstrap框架的模板）
                            	</p>
                            </div>
                            <div class="top-big-link">
                            	<a class="btn btn-link-1" href="login.jsp">已有账号</a>
                            	<a class="btn btn-link-2" href="javascript:history.back(-1)">返回上一页</a>
                            </div>
                        </div>
                        <div class="col-sm-6 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>立即注册</h3>
                            		<p>请填写以下信息完成注册</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-pencil"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="register" method="post" class="registration-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="userName">用户名</label>
			                        	<input type="text" name="user.userName" placeholder="用户名" value="${user.userName}" class="form-first-name form-control" id="userName">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="userPassWord">密码</label>
			                        	<input type="password" name="user.userPassword" placeholder="密码" value="${user.userPassword}" class="form-last-name form-control" id="userPassWord">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="Re_Passwordl">确认密码</label>
			                        	<input type="password" name="re_password" placeholder="确认密码" value="${re_password}" class="form-email form-control" id="Re_Password">
			                        </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="form-realName">真实姓名</label>
                                        <input type="text" name="user.userRealName" placeholder="真实姓名" value="${user.userRealName}" class="form-email form-control" id="form-realName">
                                    </div>
                                    <div class="form-group">
                                        <label class="sr-only" for="veriCode">验证码</label>
                                        <input type="text" style="width:80%;display: inline;" name="veriCode" placeholder="验证码" class="form-email form-control" id="veriCode"/>
                                        <img src="../user/randCode" alt="网络错误" onclick="this.src='../user/randCode?t='+Math.random()" style="width:18%;height:47px;"/>
                                    </div>
									<s:fielderror cssStyle="color:red;font-size:14px;list-style-type: none;padding:0;margin:0;"></s:fielderror>                                     
                                    <font color="red" style="font-size:14px;"><s:property value="msg"/></font><br>                                   
			                        <button type="submit" class="btn">确认提交</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/jquery.backstretch.min.js"></script>
        <script src="../assets/js/retina-1.1.0.min.js"></script>
        <script src="../assets/js/scripts.js"></script>

    </body>

</html>