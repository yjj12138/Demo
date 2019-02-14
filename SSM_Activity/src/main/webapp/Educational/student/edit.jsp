<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
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
    

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》学生管理-》修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/Educational/student/updatestudent" method="post">
	<table border="1" width="100%" class="table_a">
        <input type="hidden" name="studentid" value="${stu.studentid}"/>
                <tr  width="120px;">
                    <td width="10%">学号：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="studentno" value="${stu.studentno}" />
					</td>
                </tr>

                <tr  width="120px;">
                    <td>姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text"  name="stuname" value="${stu.stuname}" />
					</td>
                </tr>
                 <tr>
                    <td>学院<span style="color:red">*</span>：</td>
                    <td>
                        <select name="deptid" id="academy">
                            <c:forEach items="${dlist}" var="dpart">
                        	<option ${stu.deptid==dpart.departid?"selected":""} value="${dpart.departid}">${dpart.departname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>专业<span style="color:red">*</span>：</td>
                    <td>
                        <select name="majorid" id="major">
                            <c:forEach items="${mlist}" var="m">
                                <c:if test="${stu.majorid==m.majorid}">
                        	        <option value="${m.majorid}">${m.majorname}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>班级<span style="color:red">*</span>：</td>
                    <td>
                        <select id="class" name="classid">
                            <c:forEach items="${classes}" var="c">
                                <c:if test="${stu.classid==c.classid}">
                                    <option value="${c.classid}">${c.classname}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" name="stusex" ${stu.stusex==1?"ckecked":""} value="1" />男 <input type="radio" name="stusex" ${stu.stusex==1?"":"ckecked"} value="2"/>女
                    </td>
                </tr>


				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="email" value="${stu.email}" />
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="phone" value="${stu.phone}" />
                    </td>                
                </tr>

				<tr>
                    <td>户口所在地：</td>
                    <td>
                        <input type="text" name="registered" value="${stu.registered}" />
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="address" value="${stu.address}" />
                    </td>                
                </tr>
				<tr>
                    <td>政治面貌：</td>
                    <td>
                        <input type="text" name="politics" value="${stu.politics}" />
                    </td>                
                </tr>
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="cardid" value="${stu.cardid}" />
                    </td>                
                </tr>
				
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="stucontent">${stu.stucontent}</textarea>
                    </td>
                </tr>
				<tr>
					<td colspan=2 align="center">
						<input type="submit" value="修改" />
					</td> 
				</tr>
			</table>
	</form>
</div>

            </div>
        </div>
    </div>

        <script type="text/javascript">
            $(function () {
                $("#academy").change(function () {
                    var departid = $(this).val();
                    if(departid == -1){
                        alert("请选择学院")
                    }else{
                        $.ajax({
                            type: "POST",
                            url: "/getMajor",
                            data: "departid=" + departid,
                            success: function(rs){
                                $("#major").empty();
                                $(rs).each(function(i,n){
                                    $("#major").append(new Option(n.majorname,n.majorid));
                                });
                            }
                        });
                    }
                });
            });

            $(function () {
                $("#major").change(function () {
                    var majorid = $(this).val();
                    if(majorid == -1) {
                        alert("请选择专业");
                    }else {
                        $.getJSON("/getClasses","majorid=" + majorid,function (rs) {
                            $("#class").empty();
                            $(rs).each(function (i,n) {
                                $("#class").append(new Option(n.classname,n.classid));
                            })
                        })
                    }
                })
            })

        </script>
</body>
</html>
