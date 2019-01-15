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
	        var _sum = 0;   /* 名片的记录总数 */
	   		var _page = 0;  /* 分页的当前页码 */
	   		/* 初始化分页插件 */
	   		window.onload = function() {
	   			_sum = ${sum};
	   			_page = ${page};
	   			/* 取得搜索框中的值 */
	   			var _content = document.getElementById("searchInput").value;
	   			pageUtil.initPage('page',{
		            totalCount: _sum,
		            curPage:_page,
		            defaultPageSize:10,
		            showCount:7,
		            isPN:true,
		            isFL:false,
		            jump:function(curPage,pageSize){
		            	if (_content=="") {  /* 根据是否是搜索决定跳转路径 */
		            		window.location.href="../card/find2?page="+curPage+"&flag=1";
		            	} else {
		            		window.location.href="../card/find2?content="+_content+"&page="+curPage+"&flag=1";
		            	}
		            },
		        }); 
	   		}
	   		/* 全部选中 */
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
	   		/* 删除选中的名片 */
        	function deleteSelect() {
        		var allCheckBoxs = document.getElementsByName("checkList");
        		var value = new Array();
        		/* 取得选中的id值 */
                for(var i = 0; i < allCheckBoxs.length; i++){
	                if(allCheckBoxs[i].checked)
	                value.push(allCheckBoxs[i].value);
                }
                if (value.length==0){
                    alert("请至少选择一条记录");
                    return false;
                } 
                /* 确认是否删除 */
                var r=confirm("确认彻底删除这些名片?");
                if (r==true) {
                	window.location.href="deletecards2?ids="+JSON.stringify(value)
                }
        	}
	   		/* 将选中的内容移出回收站 */
        	function moveFromTrash() {
        		var allCheckBoxs = document.getElementsByName("checkList");
        		var value = new Array();
        		/* 取得选中的id值 */
                for(var i = 0; i < allCheckBoxs.length; i++){
	                if(allCheckBoxs[i].checked)
	                value.push(allCheckBoxs[i].value);
                }
                if (value.length==0){
                    alert("请至少选择一条记录");
                    return false;
                } 
                /* 确认是否移动 */
                var r=confirm("确认还原将这些名片?");
                if (r==true) {
                	window.location.href="movecardsFromTrash?ids="+JSON.stringify(value)
                }
        	}
	   		/* 跳转到名片管理系统 */
        	function jumpFromTrash() {
        		window.location.href="../card/find?flag=0&page=1"
        	}
	   		/* 搜索名片 */
        	function submitSearch() {
        		if (event.keyCode==13)  {
        			var str = document.getElementById("searchInput").value;
        			window.location.href="find2?flag=1&page=1&content="+str;
        		}
        	}
	   		/* 删除单个名片 */
        	function deleteItem(id) {
        		var r=confirm("确认彻底删除这张名片?");
                if (r==true) {
                	window.location.href="deletecard2?myCard.id="+id
                }
        	}
      	</script>
    </head>
    <body>
        <div class="cover">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <h2>
                            名片回收站
                        </h2>
                             <span class="search-sum-info">
                                共收录了
                                <strong class="re-num">
                                     ${sum}
                                </strong>
                               张名片
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
                   <button class="btn btn-default" onclick="moveFromTrash()">将所选移出化回收站</button>
                </li>
                <li>
                   <button class="btn btn-default" onclick="jumpFromTrash()">进入名片管理</button>
                </li>
            </ul>
        </div>
        <div class="search-content container">
            <table class="table table-striped">
			  <thead>
			    <tr>
			      	<th></th>
		            <th>编号</th>
		            <th>头像</th>
		            <th>姓名</th>
		            <th>性别</th>
		            <th>单位</th>
		            <th>手机</th>
		            <th>电话</th>
		            <th>Email</th>
		            <th>通讯地址</th>
		            <th>操作</th>
			    </tr>
			  </thead>
			  <tbody>
			  	<s:iterator var="card" value="listCard" status="list">
				    <tr>
				      	<td><input type="checkbox" name="checkList" value="${card.id}" /></td>
				      	<td><s:property value="#card.id" /></td>
				      	<td><img src="getUserImg?myCard.id=${card.id}" style="width:24px;height:24px;"></td>
						<td><s:property value="#card.name" /></td>
						<td><s:property value="#card.sex" /></td>
						<td><s:property value="#card.department" /></td>
						<td><s:property value="#card.mobile" /></td>
						<td><s:property value="#card.phone" /></td>
						<td><s:property value="#card.email" /></td>
						<td><s:property value="#card.address" /></td>
						<td>
							<a href="javascript:;" onclick="deleteItem(${card.id})">删除</a>
						</td>
				    </tr>
			    </s:iterator>
			  </tbody>
			</table>
			<div class="page-container">
		        <div id='page'></div>
		    </div>
        </div>
    </body>
</html>
