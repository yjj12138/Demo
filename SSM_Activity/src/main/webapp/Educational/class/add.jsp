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
                <span style="float:left">当前位置是：教务中心-》班级管理-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/Educational/class/addclass" method="post">
	<table border="1" width="100%" class="table_a">
                
                <tr>
                    <td  width="120px;">班级编号：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="classnum" value="201602B" />
                    </td>
                </tr>
               
               <tr>
                    <td>学院<span style="color:red">*</span>：</td>
                    <td>
                        <select id="academy" name="deptid">
                        	<option value="-1">--请选择--</option>
                            <c:forEach items="${list}" var="d">
                                <option value="${d.departid}">${d.departname}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>专业<span style="color:red">*</span>：</td>
                    <td>
                        <select id="major" name="majorid">
                        	<option value="-1">--请选择--</option>
                        </select>
                    </td>
                </tr>
               
				<tr>
                    <td>班主任：<span style="color:red">*</span>：</td>
                    <td>
						<select id="teacher" name="teacher">
						   <option value="-1">--请选择--</option>
						</select>
					</td>
                </tr>
                <tr>
                    <td>班级名称：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classname" value="2016春篮球" /></td>
                </tr>
				<tr>
                    <td>人数：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="peoplecount" value="" /></td>
                </tr>
				 <tr>
                    <td>开班时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classbegin" value="2013-10-10" /></td>
                </tr>
                <tr>
                    <td>毕业时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classend" value="2013-10-10" /></td>
                </tr>
				<tr>
                    <td>QQ：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="classqq" value="" /></td>
                </tr>
					<tr>
                    <td>宣传语：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="tagline" value="" /></td>
                </tr>
			
			


                <tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea name="classcontent">一个新开辟领域的探讨，探讨摸索</textarea>
                    </td>
                </tr>
				

				
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="保存">
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
                            $("#major").append(new Option("--请选择--",-1))
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
                    $.getJSON("/getTeacher","majorid=" + majorid,function (rs) {
                        $("#teacher").empty();
                        $(rs).each(function (i,n) {
                            $("#teacher").append(new Option(n.userRealname,n.userId + "-" + n.userRealname))
                        })
                    })
                }
            })
        })
    </script>
</body>
</html>
