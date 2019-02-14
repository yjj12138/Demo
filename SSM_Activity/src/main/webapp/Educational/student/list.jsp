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
		confirm("确认操作？");
	}
</script>



</head>
<body>
	
	<div class="div_head" style="width: 100%;text-align:center;">
		<span>
                <span style="float: left;">当前位置是：教务中心-》学生管理</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="/Educational/student/getDepart">【新增学生】</a>&emsp;&emsp;&emsp;&emsp;
                </span>
            </span>
	</div>

	<div class="cztable">
		<div>
				  <form action="/Educational/student/list" method="post">
                    学生名称: 
					<input type="text" name="sname" value="${sname}"/>
                     学生学号: 
					<input type="text" name="sno" value="${sno}"/>
					性别: 
					<select name="sgender" id="sel">
							<option value="">--请选择--</option>
							<option value="1" />男
							<option value="2" />女
						</select>
					<input type="submit" value="查询" />

                </form>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
                        <th >学号</th>
						<th width="">姓名</th>
						<th width="">性别</th>
                        <th width="15%">联系电话</th>						
                        <th width="15%">专业</th>
						<th width="15%">登记时间</th>
						<th>操作</th>
                    </tr>
				<c:forEach items="${pageInfo.list}" var="stu">
					<tr id="product1">
						<td align="center">${stu.studentno}</td>
						<td align="center">${stu.stuname}</td>
						<td align="center">${stu.stusex==1?"男":"女"}</td>
						<td align="center">${stu.phone}</td>
						<td align="center">${stu.major.majorname}</td>
						<td align="center">
							<fmt:formatDate value="${stu.regdate}" pattern="yyyy-MM-dd"></fmt:formatDate>
						</td>
						<td align="center">
							<a href="/Educational/student/updatestudentready?studentid=${stu.studentid}">修改</a>
							<c:if test="${stu.stustate=='正常'}">
								<a href="/Educational/student/exitstudent?studentid=${stu.studentid}" class="tablelink">退学</a>
							</c:if>
							<c:if test="${stu.stustate=='退学'}">
								<a href="/Educational/student/inputstudent?studentid=${stu.studentid}" class="tablelink">入学</a>
							</c:if>
							<a href="/Educational/student/detailstudent?studentid=${stu.studentid}">详细</a>
						</td>
					</tr>
				</c:forEach>


                    <tr>
                        <td colspan="20" style="text-align: center;">
							<pagetag:seperator url="/Educational/student/list?sname=${sname}&sno=${sno}&sgender=${sgender}" pageInfo="${pageInfo}"></pagetag:seperator>
                        </td>
                    </tr>
                </tbody>
            </table>
	</div>

	</div>
	</div>

	</div>
</body>
</html>
