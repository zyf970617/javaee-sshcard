<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%String path=request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>登录</title>
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
	                            	请输入你的个人信息并进行登录
                            	</p>
                            </div>
                            <div class="top-big-link">
                            	<a class="btn btn-link-1" href="register.jsp">还没有账号</a>
                            	<a class="btn btn-link-2" href="javascript:history.back(-1)">返回上一页</a>
                            </div>
                        </div>
                        <div class="col-sm-6 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>立即登录</h3>
                            		<p>请填写以下信息完成登录</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-pencil"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="login" method="post" class="registration-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="userName">用户名</label>
			                        	<input type="text" name="user.userName" placeholder="用户名" value="${user.userName}" class="form-first-name form-control" id="userName">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="userPassWord">密码</label>
			                        	<input type="password" name="user.userPassword" placeholder="密码" value="${user.userPassword}" class="form-last-name form-control" id="userPassWord">
			                        </div>                                    
                                    <font color="red" style="font-size:14px;"><s:property value="msg"/></font><br>                                   
			                        <button type="submit" class="btn">登录</button>
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