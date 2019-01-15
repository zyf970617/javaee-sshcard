<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
    <title>名片管理</title>
    <style type="text/css">
        .main {
            text-align: center;
            margin-top: 20px;
        }
        .page-container {
            margin-top: 16px;
        }
    </style>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/initPage.js"></script>
    <script type="text/javascript">
        function jumpTo(s) {
            if ($(".card-type").text()=="search") {
                window.location.href="../cards/careList?start="+s+"&type=search&key="+$(".search-r").val();
            } else {
                window.location.href="../cards/careList?start="+s;
            }
        }
        function deleteById(id) {
            var r=confirm("确认删除该名片?");
            if (r==true) {
                window.location.href="../cards/deleteById?flag=0&start=1&id="+id;
            }
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
            $(".move-to-trash").click(function () {
                var arr = [];
                $('.checkId:checked').each(function(){
                    arr.push($(this).val());
                });
                if (arr.length==0){
                    alert("请至少选择一条记录");
                    return false;
                }
                httpRequest("/cards/batchMove",{"ids":JSON.stringify(arr),"flag":"0"},"post",function (data) {
                    if (data=="ok") {
                        window.location.href = "../cards/careList?start=1";
                    }
                })
            });
            $(".delete-ture").click(function () {
                var arr = [];
                $('.checkId:checked').each(function(){
                    arr.push($(this).val());
                });
                if (arr.length==0){
                    alert("请至少选择一条记录");
                    return false;
                }
                var r=confirm("确认删除这些名片?");
                if (r==true) {
                    httpRequest("/cards/batchDelete",{"ids":JSON.stringify(arr)},"post",function (data) {
                        if (data=="ok") {
                            window.location.href = "../cards/careList?start=1";
                        }
                    })
                }
            })
            $(".jump-tp-trash").click(function () {
                window.location.href="../cards/trashList?start=1";
            });
            
            $(".add-new-card").click(function () {
                window.location.href="../card/insert.jsp";
            });

            $(".import-cards").click(function () {
                window.location.href="../card/upload.jsp";
            })

            $(".export-cards").click(function () {
                window.location.href="../download/cardExcel";
            })

            $(".jump-tp-index").click(function () {
                window.location.href="../index.jsp";
            })
            
            $(".search-submit").click(function () {
                window.location.href="../cards/careList?start=1&type=search&key="+$(".search-r").val();
            })
        })
    </script>
</head>
<body>
<div class="main">
    <h3>名片浏览与查询</h3>
    <span>名片搜索 </span><input type="text" class="search-r" value="${card_key}"/><button class="search-submit">查询</button> <br/><br/>
    <button class="select-all">全选</button>
    <button class="select-none">取消全选</button>
    <button class="delete-ture">彻底删除所选</button>
    <button class="move-to-trash">将所选移到回收站</button>
    <button class="add-new-card">添加名片</button>
    <button class="import-cards">导入名片</button>
    <button class="export-cards">导出查询结果</button>
    <button class="jump-tp-trash">进入回收站</button>
    <button class="jump-tp-index">返回首页</button>
    <table width="70%" border="0" cellpadding="3" cellspacing="1" align="center">
        <tr bgcolor="#8899cc">
            <td></td>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>单位</td>
            <td>手机</td>
            <td>电话</td>
            <td>Email</td>
            <td>通讯地址</td>
            <td>操作</td>
        </tr>
        <s:iterator value="#session.card_list" id="card">
        <tr>
            <td><input type="checkbox" value="${card.id}" class="checkId"/></td>
            <td><s:property value="id"/></td>
            <td><s:property value="name"/></td>
            <td><s:property value="sex"/></td>
            <td><s:property value="department"/></td>
            <td><s:property value="mobile"/></td>
            <td><s:property value="phone"/></td>
            <td><s:property value="email"/></td>
            <td><s:property value="address"/></td>
            <td><a href="javascript:;" onclick="deleteById(${card.id})">删除</a>
                <a href="../cards/updateMsg?id=${card.id}">修改</a></td>
        <tr>
        </s:iterator>
    </table>
    <div class="page-container">
        <div id='page'></div>
    </div>
    <span style="display: none;" class="card-type">${card_type}</span>
</div>
</body>
</html>
