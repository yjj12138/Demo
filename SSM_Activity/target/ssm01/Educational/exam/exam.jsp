<%@ page import="com.util.PageUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pagetag" uri="http://java.sun.com/jsp/jstl/pagetag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script src="../../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../../Script/Common.js" type="text/javascript"></script>
<script src="../../Script/Data.js" type="text/javascript"></script>

<script>
	function del(){
		confirm("确认删除？");
	}

</script>

	<style type="text/css">
		.tablelink:hover{cursor: pointer}
	</style>
</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：教务中心-》考试</span> <span
			style="float:right;margin-right: 8px;font-weight: bold"> <a
				style="text-decoration: none" href="/Educational/exam/getDepart">【新增考试】</a>
		</span>
		</span>
	</div>

	<div class="cztable">
		<div>
			
			<ul class="seachform1">
				<form action="/Educational/exam/exam" method="post">
					<li>
						<label>考试科目</label>
						<input name="ename" type="text" class="scinput1" value="${ename}"/>&nbsp;&nbsp;
						<input  type="submit" class="scbtn" value="查询"/>&nbsp;
					</li>
						
				</form>

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
					<th scope="col">编号</th>
					<th scope="col">考试编号</th>
					<th scope="col">考试科目</th>
					<th scope="col">考试时间</th>
					<th scope="col">考试班级</th>
					<th scope="col">考试状态</th>
					<th scope="col">操作</th>
				</tr>
			<c:forEach items="${pageInfo.list}" var="e">
				<tr align="center">
					<td>${e.examid}</td>
					<td>${e.examnum}</td>
					<td>${e.examsubject}</td>
					<td>
						<fmt:formatDate value="${e.examtime}" pattern="yyyy-MM-dd"></fmt:formatDate>
					</td>
					<td>${e.classes.classname}</td>
					<td>${e.examstate}</td>
					<td>
						<a href="/Educational/exam/examdetail?examid=${e.examid}">详细</a>
						<c:if test="${e.examstate=='准备中'}">
							<a href="/Educational/exam/updateexamready?examid=${e.examid}">修改</a>
							<a class="tablelink" eid="${e.examid}">删除</a>
						</c:if>
						<c:if test="${e.examstate=='已结束'}">
							<a href="/Educational/exam/getStudentScore?examid=${e.examid}">考试成绩</a>
							<a href="/Educational/exam/readdexamready?examid=${e.examid}&classname=${e.classes.classname}">组织补考</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>


				 <tr>
                        <td colspan="20" style="text-align: center;">
							<%--<a style="text-decoration: none;" href="/Educational/exam/exam?&ename=${ename}">
                            	首页
							</a>
							<a style="text-decoration: none;" href="/Educational/exam/exam?index=${pageInfo.pageNum - 1 <= 1?1:pageInfo.pageNum - 1}&ename=${ename}">
							上一页
							</a>
							<c:forEach begin="1" end="${pageInfo.pages}" var="e">
								<a style="text-decoration: none;" href="/Educational/exam/exam?index=${e}&ename=${ename}">
										${e}
								</a>
							</c:forEach>

							<a style="text-decoration: none;" href="/Educational/exam/exam?index=${pageInfo.pageNum + 1 >= pageInfo.pages?pageInfo.pages:pageInfo.pageNum + 1}&ename=${ename}">
							下一页
							</a>
							<a style="text-decoration: none;" href="/Educational/exam/exam?index=${pageInfo.pages}&ename=${ename}">
							尾页
							</a>
							共${pageInfo.total}条
							每页显示<%=PageUtil.PAGESIZE%>条
							${pageInfo.pageNum}/${pageInfo.pages}--%>
                            <pagetag:seperator url="/Educational/exam/exam?ename=${ename}" pageInfo="${pageInfo}"></pagetag:seperator>
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
            var b = confirm("确定删除该场考试?");
            if(b) {
                var examid = $(this).attr("eid")
                location.href="/Educational/exam/deleteexam?examid=" + examid;
            }
        });
    })
</script>
</body>
</html>
