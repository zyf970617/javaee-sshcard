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
        <title>名片管理系统</title>
        <link rel="stylesheet" href="../assets/css/bootstrap.css">
		<link rel="stylesheet" href="../assets/css/form-elements.css">
        <link rel="stylesheet" href="../assets/css/style.css">
    </head>
    <body>
        <div class="top-content">
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12 text">
                            <h1>J2EE课程设计之名片管理系统</h1>
                            <div class="description">
                            	<p>
	                            	请选择您要进行的操作（本系统使用前端页面并非原创，来自于Bootstrap框架的模板）
                            	</p>
                            </div>
                            <div class="top-big-link">
                            	<a class="btn btn-link-2" href="checkIn?type=1">用户管理子系统</a>
                            	<a class="btn btn-link-2" href="checkIn?type=2">名片管理子系统</a>
                            	<a class="btn btn-link-2" href="login.jsp">用户登录/注册</a>
                            </div>
                            <br/>
                            <font color="red" style="font-size:20px;"><s:property value="msg"/></font><br>  
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