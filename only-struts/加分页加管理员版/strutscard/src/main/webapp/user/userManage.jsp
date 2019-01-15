<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>用户管理</title>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/initPage.js"></script>
    <script type="text/javascript" >
        function jumpTo(s) {
            if ($(".card-type").text()=="search") {
                window.location.href="../xsw/userList?start="+s+"&type=search&key="+$(".search-r").val();
            } else {
                window.location.href="../xsw/userList?start="+s;
            }
        }
        function deleteUser(userName) {
            var r=confirm("确认删除这条用户信息?");
            if (r==true) {
                httpRequest("/xsw/deleteUserById", {"user.userName": userName}, "post", function (data) {
                    if (data == "ok") {
                        window.location.href = "../user/userUpdate";
                    } else {
                        alert("您没有删除的权限");
                    }
                })
            }
        }
        function changeUser(uId) {
            httpRequest("/xsw/changeUserMsg", {"user.userName": uId}, "post", function (data) {
                if (data == "no_auth") {
                    alert("您没有删除的权限");
                } else {
                    window.location.href = "../user/userUpdate.jsp";
                }
            })
        }
        $(document).ready(function () {
            pageUtil.initPage('page',{
                totalCount:<%=session.getAttribute("page_sum") %>,
                curPage:<%=session.getAttribute("page_start") %>,
                defaultPageSize:15,
                showCount:7,
                isPN:true,
                isFL:false,
                jump:function(curPage,pageSize){
                    setTimeout("jumpTo(curPage)",100);
                },
            });
            $(".select-all").click(function () {
                $('.checkId').prop('checked', true);
            });
            $(".select-none").click(function () {
                $('.checkId').prop('checked', false);
            });
            $(".search-submit").click(function () {
                window.location.href="../xsw/userList?start=1&type=search&key="+$(".search-r").val();
            });

            $(".jump-tp-index").click(function () {
                window.location.href="../index.jsp";
            })

            $(".delete-ture").click(function () {
                var arr = [];
                $('.checkId:checked').each(function(){
                    arr.push($(this).val());
                });
                if (arr.length==0){
                    alert("请至少选择一条记录");
                    return false;
                }
                var r=confirm("确认删除这些用户信息?");
                if (r==true) {
                    httpRequest("/xsw/batchDelete",{"ids":JSON.stringify(arr)},"post",function (data) {
                        if (data=="ok") {
                            window.location.href = "../xsw/userList?start=1";
                        } else {
                            alert("您没有删除的权限");
                        }
                    })
                }
            })
        })
    </script>
    <style type="text/css">
        .main {
            text-align: center;
            margin-top: 20px;
        }
        .page-container {
            margin-top: 16px;
        }
    </style>
</head>
<body>
<div class="main">
    <h3>用户信息管理</h3>
    <span>用户搜索 </span><input type="text" class="search-r" value="${card_key}"/><button class="search-submit">查询</button><br/><br/>
    <button class="select-all">全选</button>
    <button class="select-none">取消全选</button>
    <button class="delete-ture">删除所选</button>
    <button class="jump-tp-index">返回首页</button>
    <table width="70%" border="0" cellpadding="3" cellspacing="1" align="center">
        <tr bgcolor="#8899cc">
            <td></td>
            <td>编号</td>
            <td>姓名</td>
            <td>真实姓名</td>
            <td>类型</td>
            <td>操作</td>
        </tr>
        <s:iterator value="#session.user_list" id="user">
        <tr>
            <td><input type="checkbox" value="${user.userId}" class="checkId"/></td>
            <td><s:property value="userId"/></td>
            <td><s:property value="userName"/></td>
            <td><s:property value="userRealName"/></td>
            <td><s:property value="userType"/></td>
            <td><a href="javascript:;" onclick="deleteUser(${user.userId})">删除</a>
                <a href="javascript:;" onclick="changeUser('${user.userName}')">修改信息</a></td>
        <tr>
            </s:iterator>
    </table>
    <div class="page-container">
        <div id='page'></div>
    </div>
    <span style="display: none;" class="user_name">${user_name}</span>
</div>
</body>
</html>
