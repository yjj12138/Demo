<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../Script/Common.js" type="text/javascript"></script>
    <script src="../Script/Data.js" type="text/javascript"></script>
    

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：书籍管理-》资料上传</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/book/addziliao" method="post" enctype="multipart/form-data">
	<table border="1" width="100%" class="table_a">
                <tr>
                    <td>资料名称 ：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="informationname" value="3432545" />
                    </td>
                </tr>
               
                <tr>
                    <td>资料类型 ：<span style="color:red">*</span>：</td>
                    <td>
					<select name="typeid">
                        <c:forEach items="${flist}" var="f">
                            <option value="${f.infoid}">${f.infotype}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td>更新时间：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="date" value="2014-04-11 " /></td>
                </tr>
                <tr>
                    <td>上传人：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="uploader" value="王二麻子" /></td>
                </tr>
				<tr>
                    <td>上传：<span style="color:red">*</span>：</td>
                    <td>
                        <input type="file" name="myfile" value=""/>
                    </td> 
                </tr>
				

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="提交"> <input type="submit" value="返回" onclick="history.back();">
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
