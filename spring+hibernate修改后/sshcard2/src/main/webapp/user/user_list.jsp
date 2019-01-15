<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path=request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:th="http://www.thymeleaf.org"
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>
          	  回收站
        </title>
        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/initPage.js"></script>
        <link href="../assets/css/bootstrap.css" rel="stylesheet">
        <link href="../assets/css/resource-search.css" rel="stylesheet">
        <script>
	        var _sum = 0;
	   		var _page = 0;
	   		window.onload = function() {
	   			_sum = ${sum};
	   			_page = ${page};
	   			var _content = document.getElementById("searchInput").value;
	   			pageUtil.initPage('page',{
		            totalCount: _sum,
		            curPage:_page,
		            defaultPageSize:10,
		            showCount:7,
		            isPN:true,
		            isFL:false,
		            jump:function(curPage,pageSize){
		            	if (_content=="") {
		            		window.location.href="../user/find?page="+curPage;
		            	} else {
		            		window.location.href="../user/find?content="+_content+"&page="+curPage;
		            	}
		            },
		        }); 
	   		}
	   		/* 全选 */
        	function selectAll() {
        		 var allCheckBoxs = document.getElementsByName("checkList");
        		 for (var i=0;i<allCheckBoxs.length ;i++){
        			allCheckBoxs[i].checked = true;
        		 }  
        	}
	   		/* 取消全选 */
        	function selectNone() {
	       		 var allCheckBoxs = document.getElementsByName("checkList");
	       		 for (var i=0;i<allCheckBoxs.length ;i++){
	       			allCheckBoxs[i].checked = false;
	       		 }
        	}
	   		/* 删除选中的内容 */
        	function deleteSelect() {
        		var allCheckBoxs = document.getElementsByName("checkList");
        		var value = new Array();
                for(var i = 0; i < allCheckBoxs.length; i++){
	                if(allCheckBoxs[i].checked)
	                value.push(allCheckBoxs[i].value);
                }
                if (value.length==0){
                    alert("请至少选择一条记录");
                    return false;
                } 
                var r=confirm("确认彻底删除这些用户?");
                if (r==true) {
                	window.location.href="deleteusers?ids="+encodeURI(JSON.stringify(value))
                }
        	}
        	function submitSearch() {
        		if (event.keyCode==13)  {
        			var str = document.getElementById("searchInput").value;
        			window.location.href="find?page=1&content="+str;
        		}
        	}
        	/* 单个删除内容 */
        	function deleteItem(id) {
        		var r=confirm("确认彻底删除此用户?");
                if (r==true) {
                	window.location.href="deleteuser?user.id="+id
                }
        	}
        	/* 跳转到首页 */
        	function jumpToIndex() {
        		window.location.href="../user/index.jsp";
        	}
        	/* 修改密码 */
        	function changePass(id) {
        		/* 记录修改的用户标记 */
        		document.getElementById("up_id").value = id;
        		/* 显示模态框 */
        		$('#changePass').modal('show')
        	}
      	</script>
    </head>
    <body>
        <div class="cover">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <h2>
                            用户管理
                        </h2>
                             <span class="search-sum-info">
                                共注册了
                                <strong class="re-num">
                                     ${sum}
                                </strong>
                               个用户
                            </span>
                            <div class="form-group">
                                <input type="text" class="form-control search" value="${content}" id="searchInput" onkeydown="submitSearch()" maxlength="42" placeholder="名片搜索"/>
                                <a href="javascript:;" class="btn-search">
                                    <span class="glyphicon glyphicon-search">
                                    </span>
                                </a>
                            </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="hot-search well text-center hidden-xs hidden-sm">
            <ul class="list-inline">
                <li>
                    <button class="btn btn-default" onclick="selectAll()">全选</button>
                </li>
                <li>
                    <button class="btn btn-default" onclick="selectNone()">取消全选</button>
                </li>
                <li>
                    <button class="btn btn-default" onclick="deleteSelect()">彻底删除所选</button>
                </li>
                <li>
                   <button class="btn btn-default" onclick="jumpToIndex()">返回首页</button>
                </li>
            </ul>
             <font color="red" style="font-size:14px;"><s:property value="msg"/></font>
        </div>
        <div class="search-content container">
            <table class="table table-striped">
			  <thead>
			    <tr>
			      	<th></th>
		            <th>编号</th>
		            <th>用户名</th>
		            <th>真实姓名</th>
		            <th>用户类型</th>
		            <th>操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<s:iterator var="user" value="userList" status="list">
				    <tr>
				      	<td><input type="checkbox" name="checkList" value="${user.id}" /></td>
				      	<td><s:property value="#user.id" /></td>
						<td><s:property value="#user.userName" /></td>
						<td><s:property value="#user.userRealName" /></td>
						<td><s:property value="#user.userType" /></td>
						<td>
							<a href="javascript:;" onclick="changePass(${user.id})">修改密码</a>
							<a href="javascript:;" onclick="deleteItem(${user.id})">删除</a>
						</td>
				    </tr>
			    </s:iterator>
			  </tbody>
			</table>
			<div class="page-container">
		        <div id='page'></div>
		    </div>
        </div>
		<div class="modal fade" id="changePass" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		    	<form role="form" action="changePass" method="post" class="registration-form">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
			            </div>
			            <div class="modal-body" style="padding: 20px;">
			            		<input id="up_id" name="user.id" class="sr-only"/>
                    			<div class="form-group">
		                        	<input type="password" name="old_password" required="true" placeholder="输入原密码" class="form-control">
		                        </div>  
		                        <div class="form-group">
		                        	<input type="password" name="user.userPassword" required="true" placeholder="输入新密码" class="form-control">
		                        </div>       
		                        <div class="form-group">
		                        	<input type="password" name="re_password" required="true" placeholder="重复密码" class="form-control">
		                        </div>                
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				                <button type="submit" class="btn btn-primary">确认更改</button>
				            </div>
			        </div>
			     </form>
		    </div>
		</div>
    </body>
</html>
