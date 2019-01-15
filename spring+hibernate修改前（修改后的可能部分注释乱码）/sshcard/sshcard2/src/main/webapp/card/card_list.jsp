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
          	  名片管理系统
        </title>
        <!-- 页面效果需要导入的bootstrap和jquery -->
        <script src="../assets/js/jquery-1.11.1.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <!-- 大头像的弹框插件 -->
        <script src="../assets/js/jquery-confirm.js"></script>
        <!-- 分页插件 -->
        <script src="../assets/js/initPage.js"></script>
        <link href="../assets/css/bootstrap.css" rel="stylesheet">
        <link href="../assets/css/resource-search.css" rel="stylesheet">
        <link href="../assets/css/jquery-confirm.css" rel="stylesheet">
        <script>
       		var _sum = 0;   /* 记录总数 */
       		var _page = 0;   /* 当前页码 */
       		/* 分页插件初始化 */
       		window.onload = function() {
       			_sum = ${sum};
       			_page = ${page};
       			var _content = document.getElementById("searchInput").value;  /* 取得搜索的内容 */
       			pageUtil.initPage('page',{
    	            totalCount: _sum,
    	            curPage:_page,
    	            defaultPageSize:10,
    	            showCount:7,
    	            isPN:true,
    	            isFL:false,
    	            jump:function(curPage,pageSize){
    	            	if (_content=="") {  /* 判断是否是搜索的情况 */
    	            		window.location.href="../card/find?page="+curPage+"&flag=0";
    	            	} else {
    	            		window.location.href="../card/find?content="+_content+"&page="+curPage+"&flag=0";
    	            	}
    	            },
    	        }); 
       		}
       		/* 预览操作 */
	        function getObjectURL(file) {    
	        	var url = null ; 
	        	if (window.createObjectURL!=undefined) { 
	        		url = window.createObjectURL(file) ;
	        	} else if (window.URL!=undefined) { 
	        		url = window.URL.createObjectURL(file) ;
	        	} else if (window.webkitURL!=undefined) {
	        		url = window.webkitURL.createObjectURL(file) ;
	        	}
	        	return url ;
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
        	/* 将选中的名片移动到回收站 */
        	function moveToTrash() {
        		var allCheckBoxs = document.getElementsByName("checkList");
        		var value = new Array();
        		/* 取得所有选中的id */
                for(var i = 0; i < allCheckBoxs.length; i++){
	                if(allCheckBoxs[i].checked)
	                value.push(allCheckBoxs[i].value);
                }
                if (value.length==0){
                    alert("请至少选择一条记录");
                    return false;
                } 
                /* 确认是否移动 */
                var r=confirm("确认将这些名片移动到回收站?");
                if (r==true) {
                	window.location.href="movecardsToTrash?ids="+JSON.stringify(value)+"&page="+_page+"&sum="+_sum;
                }
        	}
        	/* 导出excel */
        	function export_cards() {
        		window.location.href="../card/cardExcel";
        	}
        	/* 删除选中的记录 */
        	function deleteSelect() {
        		var allCheckBoxs = document.getElementsByName("checkList");
        		var value = new Array();
        		/* 取得所有选中的id */
                for(var i = 0; i < allCheckBoxs.length; i++){
	                if(allCheckBoxs[i].checked)
	                value.push(allCheckBoxs[i].value);
                }
                if (value.length==0){
                    alert("请至少选择一条记录");
                    return false;
                } 
                var r=confirm("确认彻底删除这些名片?");
                if (r==true) {
                	window.location.href="deletecards?ids="+JSON.stringify(value)
                }
        	}
        	/* 跳转到回收站 */
        	function jumpToTrash() {
        		window.location.href="../card/find2?flag=1&page=1"
        	}
        	/* 新增名片预览头像 */
        	function showPhoto(x) {
        		var y=document.getElementById(x);
        		var objUrl = getObjectURL(y.files[0]);
        		var http = y.value
       		 	var type = http.split(".");
        		var xsw_type = type[type.length-1];
        		/* 显示图片格式 */
        		if(xsw_type!="jpg"&&xsw_type!="png"&&xsw_type!="jpeg"&&xsw_type!="bmp"&&xsw_type!="gif"){
	       			y.value="";
	       			alert("图片格式必须是jpg、png、jpeg、bmp、gif中的一种")
	       		 }else{
	       			document.getElementById('myImage').src = objUrl;
	       		 }
        	}
        	/* 名片更新预览头像 */
        	function showPhoto1(x) {
        		var y=document.getElementById(x);
        		var objUrl = getObjectURL(y.files[0]);
        		var http = y.value
       		 	var type = http.split(".");
        		var xsw_type = type[type.length-1];
        		/* 限定文件格式 */
        		if(xsw_type!="jpg"&&xsw_type!="png"&&xsw_type!="jpeg"&&xsw_type!="bmp"&&xsw_type!="gif"){
	       			y.value="";
	       			alert("图片格式必须是jpg、png、jpeg、bmp、gif中的一种")
	       		 }else{
	       			document.getElementById('c_img').src = objUrl;  /* 重置src */
	       		 }
        	}
        	/* 显示大头像 */
        	function showImg(id) {
        		var url = "getUserImg?myCard.id="+id;
        		$.confirm({  /* 弹框插件 */
	    		    content: "<img src='"+url+"'>",
	    		    title: '头像',
	    		    confirmButton:'确定',
	    		    cancelButton:'',
	    		});
        	}
        	/* 跳转到首页 */
        	function jumpToIndex() {
        		window.location.href="../user/index.jsp";
        	}
        	/* 单个删除名片 */
        	function deleteItem(id) {
        		var r=confirm("确认彻底删除这张名片?");
                if (r==true) {
                	window.location.href="deletecard?myCard.id="+id
                }
        	}
        	/* 名片更新 */
        	function updateMsg(id,name,sex,department,mobile,email,phone,address) {
        		/* 将名片信息填充到输入框中 */
        		document.getElementById("c_id").value=id;
        		document.getElementById("c_name").value=name;
        		document.getElementById("c_part").value=department;
        		document.getElementById("c_mobile").value=mobile;
        		document.getElementById("c_email").value=email;
        		document.getElementById("c_phone").value=phone;
        		document.getElementById("c_address").value=address;
        		if (sex=="女") {
        			document.getElementById("c_female").checked=true;
        		}
        		/* 根据用户id取得用户头像 */
        		document.getElementById('c_img').src = "getUserImg?myCard.id="+id;
        		/* 显示模态框 */
        		$('#updateCard').modal('show')
        	}
        	/* 模糊搜索 */
        	function submitSearch() {
        		if (event.keyCode==13)  {  /* 回车事件监听 */
        			var content = document.getElementById("searchInput").value;  /* 取得搜索关键字 */
        			window.location.href="find?content="+content+"&flag=0&page=1";
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
                            J2EE名片管理系统
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
                   <button class="btn btn-default" onclick="moveToTrash()">将所选移到回收站</button>
                </li>
                <li>
                   <button class="btn btn-default" data-toggle="modal" data-target="#newCard">添加名片</button>
                </li>
                <li>
                    <button class="btn btn-default" data-toggle="modal" data-target="#myModal">导入名片</button>
                </li>
                <li>
                   <button class="btn btn-default" onclick="export_cards()">导出查询结果</button>
                </li>
                <li>
                   <button class="btn btn-default" onclick="jumpToTrash()">进入回收站</button>
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
							<a href="javascript:;" onclick="showImg(${card.id})">大头像</a>
							<a href="javascript:;" onclick="updateMsg(${card.id},'${card.name}','${card.sex}','${card.department}','${card.mobile}','${card.email}','${card.phone}','${card.address}')">修改</a>
							<a href="javascript:;" onclick="deleteItem(${card.id})">删除</a>
						</td>
				    </tr>
			    </s:iterator>
			  </tbody>
			</table>
			<!-- 分页插件 -->
			<div class="page-container">
		        <div id='page'></div>
		    </div>
        </div>
       	<!-- 批量导入名片模态框 -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		    	<form action="../card/excelCard" method="post" enctype="multipart/form-data">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">批量导入名片</h4>
			            </div>
			            <div class="modal-body" style="padding: 20px;">
			            	<h4>上传Excel文档</h4>
							<h5>注意，电子表只能为".xls"，并且表中从左到右，各字段信息依次为：</h5>
							<h5>姓名，性别，单位，手机，电话，电子邮箱，地址</h5>
							<input type="file" class="form-control" name="file"/>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			                <button type="submit" class="btn btn-primary">提交更改</button>
			            </div>
			        </div>
			     </form>
		    </div>
		</div>
		
		<!-- 新增名片模态框 -->
		<div class="modal fade" id="newCard" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		    	<form role="form" action="newcard" enctype="multipart/form-data"  method="post" class="registration-form">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">添加一张名片</h4>
			            </div>
			            <div class="modal-body" style="padding: 20px;">
                    			<div class="form-group">
		                        	<input type="text" name="myCard.name" required="true" placeholder="姓名" value="${myCard.name}" class="form-control">
		                        </div>
		                        <div class="form-group">
		                        	男:<input type="radio" style="width:30px;" name="myCard.sex" value="男" checked="checked" id="male"/> 
		                        	女:<input type="radio" style="width:30px;" name="myCard.sex" value="女" id="female"/>
		                        </div>     
		                        <div class="form-group">
		                        	<input type="text" name="myCard.department" required="true" placeholder="部门" value="${myCard.department}" class="form-control">
		                        </div>       
		                        <div class="form-group">
		                        	<input type="number" name="myCard.mobile" required="true" placeholder="电话" value="${myCard.mobile}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="number" name="myCard.phone" required="true" placeholder="手机" value="${myCard.phone}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="email" name="myCard.email" required="true" placeholder="电子邮箱" value="${myCard.email}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="text" name="myCard.address" required="true" placeholder="地址" value="${myCard.address}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        <img src="../assets/img/test.jpg" id="myImage" style="width:60px;height:60px;float:right;"/> 
		                        	<label style="margin-top:20px;" for="cardPhoto" class="btn btn-default">上传头像</label>
		                        	<input type="file" name="file" required="true" id="cardPhoto" class="sr-only" onchange="showPhoto(this.id)"/>        
		                        </div>                        
                            	<font color="red" style="font-size:14px;"><s:property value="msg"/></font><br>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				                <button type="submit" class="btn btn-primary">提交更改</button>
				            </div>
			        </div>
			     </form>
		    </div>
		</div>
		
		<!-- 名片信息更新模态框 -->
		<div class="modal fade" id="updateCard" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		    	<form role="form" action="updateCard" enctype="multipart/form-data"  method="post" class="registration-form">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">更新名片信息</h4>
			            </div>
			            <div class="modal-body" style="padding: 20px;">
			            		<input type="text" name="myCard.id" id="c_id" required="true" class="sr-only">
                    			<div class="form-group">
		                        	<input type="text" name="myCard.name" required="true" placeholder="姓名" id="c_name" value="${myCard.name}" class="form-control">
		                        </div>
		                        <div class="form-group">
		                        	男:<input type="radio" style="width:30px;" name="myCard.sex" value="男" checked="checked" id="c_male"/> 
		                        	女:<input type="radio" style="width:30px;" name="myCard.sex" value="女" id="c_female"/>
		                        </div>     
		                        <div class="form-group">
		                        	<input type="text" name="myCard.department" required="true" placeholder="部门" id="c_part" value="${myCard.department}" class="form-control">
		                        </div>       
		                        <div class="form-group">
		                        	<input type="number" name="myCard.mobile" required="true" placeholder="电话" id="c_mobile" value="${myCard.mobile}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="number" name="myCard.phone" required="true" placeholder="手机" id="c_phone" value="${myCard.phone}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="email" name="myCard.email" required="true" placeholder="电子邮箱" id="c_email" value="${myCard.email}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        	<input type="text" name="myCard.address" required="true" placeholder="地址" id="c_address" value="${myCard.address}" class="form-control">
		                        </div> 
		                        <div class="form-group">
		                        <img src="../assets/img/test.jpg" id="c_img" style="width:60px;height:60px;float:right;"/> 
		                        	<label style="margin-top:20px;" for="cardPhoto1" class="btn btn-default">上传头像</label>
		                        	<input type="file" name="file" id="cardPhoto1" class="sr-only" onchange="showPhoto1(this.id)"/>        
		                        </div>                        
                            	<font color="red" style="font-size:14px;"><s:property value="msg"/></font><br>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				                <button type="submit" class="btn btn-primary">提交更改</button>
				            </div>
			        </div>
			     </form>
		    </div>
		</div>
    </body>
</html>
