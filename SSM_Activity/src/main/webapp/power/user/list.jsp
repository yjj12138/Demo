<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pagetag" uri="http://java.sun.com/jsp/jstl/pagetag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../../Script/Common.js" type="text/javascript"></script>
    <script src="../../Script/Data.js" type="text/javascript"></script>
    <script>
		function del(){
			confirm("确定删除？");
		}
	</script>
    <style type="text/css">
        #daochu{ color:blue}
        #daochu:hover{ color:red; cursor: pointer}
        #delete{ color:blue}
        #delete:hover{ color:red; cursor: pointer}
        #addu{color: blue}
        #addu:hover{color: red; cursor: pointer}
        .tablelink:hover{cursor: pointer}
    </style>

    <script type="text/javascript">
        $(function () {
            $("[name='all']").click(function () {
                var t = $(this)[0].checked;
                var ids = $("[name='uid']");
                $(ids).each(function (i,n) {
                    n.checked = t;
                })
            })
        })

        $(function () {
            $("#daochu").click(function () {
                var uids = new Array();
                var ids = $("[name='uid']")
                $(ids).each(function (i,n) {
                    if(n.checked){
                        uids.push($(this).val());
                    }
                })
                if(uids.length == 0) {
                    alert("导出数据不能为空")
                }else {
                    $.ajax({
                        type: "POST",
                        url: "/daochuuser",
                        data:{"uids":uids},
                        traditional:true,
                        success:alert("导出成功")
                    })
                }

            })
        })
        
        $(function () {
            $("#delete").click(function () {
                var uids = new Array();
                var ids = $("[name='uid']")
                $(ids).each(function (i,n) {
                    if(n.checked){
                        uids.push($(this).val());
                    }
                })
                if(uids.length == 0) {
                    alert("删除数据不能为空")
                }else {
                    document.forms[0].submit();
                }
            });
        })

        $(function () {
            $(".tablelink").click(function () {
                var b = confirm("确定删除该用户?");
                if(b) {
                    var userid = $(this).attr("uid")
                    location.href="/power/user/deleteuser?userId=" + userid;
            }
            });
        })
    </script>
</head>
<body>


<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》人员管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
            <span style="text-decoration: none;" id="daochu" >【导出excel】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="text-decoration: none;" id="delete">【批量删除】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <a style="text-decoration: none;" href="/power/user/getroles" id="addu">【新增人员】</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>
 <div class="cztable" style="width: 100%;">
        <form action="/power/user/deleteusers" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px;" align="center">
                    
                    <th  width="8%">
						<input type="checkbox" name="all"/>
					</th>
					<th scope="col" width="15%">
                        序号
                    </th>
                    <th scope="col" width="15%">
                        账号
                    </th>
                    <th scope="col" width="15%">
                        姓名
                    </th>
                    <th scope="col" width="15%">
                        角色
                    </th>
                   
                    <th scope="col" >
                        操作
                    </th>
                </tr>
                
                <c:forEach items="${pageInfo.list}" var="ur" varStatus="sta">
                <tr align="center">
					<th><input type="checkbox" name="uid" value="${ur.userId}"/></th>
                    <td>
                        ${sta.count}
                    </td>
                    <td>
                      ${ur.userName}
                    </td>
                    <td>
                       <a href="info.jsp">${ur.userRealname}</a>
                    </td>
                    
                    <td>&nbsp;
                       ${ur.role.rolename}
                    </td>
                    
                    <td>&nbsp;
                        <a href="/power/user/selectusertb?userId=${ur.userId}">修改</a>
                        <a href="/power/user/selectoneusertb?userId=${ur.userId}">详情</a>
                        <a class="tablelink" uid="${ur.userId}"> 删除</a>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>
          <div class='MainStyle'>
            <div class=''>
                <pagetag:seperator url="/power/user/getusers" pageInfo="${pageInfo}"></pagetag:seperator>
            </div>
          </div>

        
        
      
    </div>
</body>
</html>