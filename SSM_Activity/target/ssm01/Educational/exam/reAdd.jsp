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
    <script src="../Script/Data.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        function submitMail() {
            var mtitle = "联系方式有修改";
            var html = "<div style='padding:10px;'><div style='width:65px; height:120px; float:left;'>修改的地方：</div><div style='width:250px; height:120px; float:left;'><textarea id='objeCont' name='objeCont' style='width:250px; height:105px;'></textarea></div></div>";

            var submit = function (v, h, f) {
                if (f.objeCont == '' || f.objeCont.length > 80) {
                    $.jBox.tip("请您输入有修改的地方，且不超过80个字！", 'error', { focusId: "objeCont" }); // 关闭设置 objeCont 为焦点
                    return false;
                }

                StudentCompain.insertCompain('', mtitle, 5, f.objeCont, function (data) {
                    var obj = $.parseJSON(data);
                    var resultObj = false;
                    if (obj.ok) {
                        $.jBox.tip("成功提交联系方式的修改邮件！");
                    }
                });
            };

            $.jBox(html, { title: "联系方式修改的邮件", submit: submit });
        }
    </script>
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：教务中心-》考试-》新增</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="exam.jsp">【返回】</a>
                </span>
            </span>
        </div>
</div>
<div class="cztable">
	<form action="/Educational/exam/readdexam" method="post">
    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right">考试编号：</td>
            <td>
				<input type="text" value="${exam.examnum}" />
			</td>
        </tr>

        <tr>
            <td align="right">补考考试编号：</td>
            <td>
				<input type="text" name="examnum"/>
			</td>
        </tr>

        <tr>
            <td align="right">考试科目：</td>
            <td>
				<input type="text" value="${exam.examsubject}" name="examsubject"/>
			</td>
        </tr>

		<tr>
            <td align="right">考试时间：</td>
            <td>
				<input type="text" value="2018/6/6" name="examtime"/>
			</td>
        </tr>

		<tr>
            <td align="right">考试班级：</td>
            <td>
				<select name="deptid">
                    <c:forEach items="${list}" var="aa">
                        <c:if test="${exam.deptid==aa.departid}">
                            <option value="${exam.deptid}">${aa.departname}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <select name="majorid">
                        	<c:forEach items="${major}" var="bb">
                                <c:if test="${exam.majorid==bb.majorid}">
                                    <option value="${exam.majorid}">${bb.majorname}</option>
                                </c:if>
                            </c:forEach>
                </select>
                <select name="classid">
                    <option value="${exam.classid}">${classname}</option>
       			</select>        
			</td>
        </tr>

		<tr>
            <td align="right">考试人数：</td>
            <td>
				<input type="text" name="examcount" value="13"/>
			</td>
        </tr>

		<tr>
            <td align="right">考试状态：</td>
            <td>
				<input type="text" name="examstate" value="准备中"/>
			</td>
        </tr>
        
        <tr align="center">
            <td colspan="5" height="40">
                <div align="center">
                    
                    <input type="submit" id="button2" value="添加"/>
                </div>
            </td>
        </tr>
	
    </table>
	</form>
</div>

            </div>
        </div>
        
    </div>
</body>
</html>
