<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <span style="float:left">当前位置是：教务中心-》班级审核-》详细</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
</div>
</div>
<div class="cztable">
    <form action="../../right.jsp" method="post">
        <table border="1" width="100%" class="table_a">
            <tr>
                <td width="120px;">院系：<span style="color:red">*</span>：</td>
                <td><input type="text" name="f_goods_name" value="${classes.department.departname}" /></td>
            </tr>
            <tr>
                <td>班级编号：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="f_goods_name" value="${classes.classnum}" readonly />
                </td>
            </tr>

            <tr>
                <td>班级名称：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="f_goods_price" value="${classes.classname}" readonly/></td>
            </tr>
            <tr>
                <td>人数：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="f_goods_price" value="${classes.peoplecount}" readonly/></td>
            </tr>

            <tr>
                <td>开班时间：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="f_goods_price" value="<fmt:formatDate value="${classes.classbegin}" pattern="yyyy/MM/dd"></fmt:formatDate>" readonly/></td>
            </tr>
            <tr>
                <td>毕业时间：<span style="color:red">*</span>：</td>
                <td>
                    <input type="text" name="f_goods_price" value="<fmt:formatDate value="${classes.classend}" pattern="yyyy/MM/dd"></fmt:formatDate>" readonly/></td>
            </tr>
            <tr>
                <td>状态<span style="color:red">*</span>：</td>
                <td>
                    <select>
                        <option>${classes.classstate}</option>
                    </select>
                </td>
            </tr>


            <tr>
                <td>简介<span style="color:red">*</span>：</td>
                <td>
                    <textarea>${classes.classcontent}</textarea>
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
