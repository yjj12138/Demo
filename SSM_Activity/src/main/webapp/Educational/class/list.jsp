<%@ page import="com.util.PageUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pagetag" uri="http://java.sun.com/jsp/jstl/pagetag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
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
		confirm("确认删除？");
	}

</script>
    <style type="text/css">
        #daochu{ color:blue}
        #daochu:hover{ color:red; cursor: pointer}
        .tablelink:hover{cursor: pointer}
    </style>
<script type="text/javascript">
    $(function () {
        $("[name='all']").click(function () {
            var t = $(this)[0].checked;
            var ids = $("[name='cid']");
            $(ids).each(function (i,n) {
                n.checked = t;
            })
        })
    })

    $(function () {
        $("#daochu").click(function () {
            var cids = new Array();
            var ids = $("[name='cid']")
            $(ids).each(function (i,n) {
                if(n.checked){
                    cids.push($(this).val());
                }
            })
            if(cids.length == 0) {
                alert("导出数据不能为空")
            }else {
                $.ajax({
                    type: "POST",
                    url: "/daochu",
                    data:{"cids":cids},
                    traditional:true,
                    success:alert("导出成功")
                })
            }

        })
    })
</script>


</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》班级管理</span> <span
			style="float: right; margin-right: 8px; font-weight: lighter">
            <span  id="daochu" style="text-decoration: blink"  >【导出excel】</span>&nbsp;&nbsp;
            <a style="text-decoration: blink" href="/Educational/class/getDepart">【新增班级】&emsp;&emsp;&emsp;&emsp;</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/class/list" method="post">
					<li>
                        <label>院系名称:</label>
                        <input name="dname" type="text" class="scinput1" value="${dname}"/>&nbsp;&nbsp;
						<label>班级名称:</label>
                        <input name="cname" type="text" class="scinput1" value="${cname}"/>&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>
            </ul>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <tr style="font-weight: bold;">
                		<th  width="8%">
						<input type="checkbox" name="all"/>
						</th>
                        <th >院系</th>
						<th width="">班级编号</th>
						<th width="">班级名称</th>
                        <th width="15%">班主任老师</th>						
                        <th width="15%">人数</th>
						<th width="15%">班级状态</th>
						<th width="20%">操作</th>  
                </tr>
                 <c:forEach items="${pageInfo.list}" var="cla">
                     <tr id="product1">
                         <td  width="8%" align="center">
                             <input type="checkbox" name="cid" value="${cla.classid}"/>
                         </td>
                         <td align="center">${cla.department.departname}</td>
                         <td align="center">${cla.classnum}</td>
                         <td align="center">${cla.classname}</td>
                         <td align="center">${cla.classteacher}</td>
                         <td align="center">${cla.peoplecount}</td>
                         <td align="center">${cla.classstate}</td>
                         <td align="center">
                             <a href="/Educational/class/detail?classid=${cla.classid}">详情</a>
                             <a href="/Educational/class/updateready?classid=${cla.classid}">修改</a>
                             <a class="tablelink" cid="${cla.classid}">删除</a>
                         </td>
                     </tr>
                 </c:forEach>


                    <tr>
                        <td colspan="20" style="text-align: center;">						
						<%--<a style="text-decoration: none;" href="/Educational/class/list?cname=${classname}&dname=${deptname}">
                            首页
                        </a>
                        <a style="text-decoration: none;" href="/Educational/class/list?index=${pageInfo.pageNum - 1 <= 1?1:pageInfo.pageNum - 1}&cname=${classname}&dname=${deptname}">
                            上一页
                        </a>
                        <c:forEach begin="1" end="${pageInfo.pages}" var="i">
                            <a style="text-decoration: none;" href="/Educational/class/list?index=${i}&cname=${classname}&dname=${deptname}">
                                    ${i}
                            </a>
                        </c:forEach>
                        <a style="text-decoration: none;" href="/Educational/class/list?index=${pageInfo.pageNum + 1 >= pageInfo.pages?pageInfo.pages:pageInfo.pageNum + 1}&cname=${classname}&dname=${deptname}">
                            下一页
                        </a>
                        <a style="text-decoration: none;" href="/Educational/class/list?index=${pageInfo.pages}&cname=${classname}&dname=${deptname}">
                            尾页
                        </a>
                            共${pageInfo.total}条
                            每页显示<%=PageUtil.PAGESIZE%>条
                            ${pageInfo.pageNum}/${pageInfo.pages}--%>
                            <pagetag:seperator url="/Educational/class/list?cname=${cname}&dname=${dname}" pageInfo="${pageInfo}"></pagetag:seperator>
                        </td>
                    </tr>
                </tbody>
</table>
	</div>

	</div>
	</div>

	</div>

<script type="text/javascript">
    $(function () {
        $(".tablelink").click(function () {
            var b = confirm("确定删除该班级?");
            if(b) {
                var classid = $(this).attr("cid")
                location.href="/Educational/class/deleteclass?classid=" + classid;
            }
        });
    })
</script>
</body>
</html>
