<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pagetag" uri="http://java.sun.com/jsp/jstl/pagetag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
    <script src="../Script/Common.js" type="text/javascript"></script>
    <style type="text/css">
        .ma{color: blue}
        .ma:hover{color: red;cursor: pointer}
    </style>
    <script type="text/javascript">
        function searchData(ctype, type) {
            if (type == "t") {
                var c = $("#cValue").val();
                window.location = 'StudentMaterial.aspx?ctype=' + ctype + "&cid=" + c;
            } else if (type == "c") {
                var t = $("#tValue").val();
                window.location = 'StudentMaterial.aspx?cid=' + ctype + "&ctype=" + t;
            }
        }
        //查询的填充
        $().ready(function () {
            var ctype = Request.QueryString("ctype");
            var cid = Request.QueryString("cid");
            if (ctype != null && ctype != "") {
                $("#tValue").val(ctype);
                $("#" + ctype).addClass("hover");
            } else {
                $("#tAll").addClass("hover");
            }
            if (cid != null && cid != "") {
                $("#cValue").val(cid);
                $("#" + cid).addClass("hover");
            } else {
                $("#cAll").addClass("hover");
            }
        });

        $(function () {
            $("#tAll").click(function () {
                location.href="/book/ziliaolistsecond"
            });
            $("#1").click(function () {
                location.href="/book/mohusecond?tid=1";
            });
            $("#2").click(function () {
                location.href="/book/mohusecond?tid=2";
            });
            $("#3").click(function () {
                location.href="/book/mohusecond?tid=3";
            });
            $("#4").click(function () {
                location.href="/book/mohusecond?tid=4";
            });
        })
    </script>
</head>
<body>

	<div class="div_head" style="width: 100%;text-align:center;">
            <span>
                <span style="float:left">当前位置是：学习中心-》资料中心</span>
                <span style="float:right;margin-right: 8px;font-weight: bold">
                    <a style="text-decoration: none" href="../right.jsp">【返回】</a>
                </span>
            </span>
        </div>  


    <div class="feilei">
        <a href="#"><strong>资料中心</strong></a></div>
    <input type="hidden" id="cValue" value="" />
    <input type="hidden" id="tValue" value="" />
    <div class="fllist">
        <form action="/book/ziliaolistsecond" method="post">
        <ul>
           	<li>
            <strong>
            标题:&nbsp;&nbsp;
            </strong>
            <input type="text" name="title"/>&nbsp;&nbsp;<input type="submit" value="搜索" />
            </li>
            <li><strong>类型：</strong><a id="tAll" class="ma">全部</a>

                <a id="1" class="ma">复习资料</a>

                <a id="2" class="ma">练习题</a>

                <a id="3" class="ma">内部资料</a>

                <a id="4" class="ma">真题</a>

            </li>
        </ul>
        </form>
        <div class="cztable">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="text-align:center;">
                <tr>
                <th width="6%">格式</th>
                    <th style="padding-left: 20px;">
                        资料名称
                    </th>
                    <th style="width: 15%; text-align: center;">
                        资料类型
                    </th>
                    <th style="width: 15%; text-align: center;">
                        更新时间
                    </th>
                    <th style="width: 15%; text-align: center;">
                        操作
                    </th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="bk">
                <tr>
                    <td>
                        <c:if test="${bk.filetype=='doc'}">
                            <img src='../Images/FileIco/doc.gif' />
                        </c:if>
                        <c:if test="${bk.filetype!='doc'}">
                            <img src='../Images/FileIco/default.jpg' />
                        </c:if>
                    </td>
                    <td class="xxleft">
                        ${bk.informationname}
                    </td>
                    <td>
                        ${bk.infotype.infotype}
                    </td>
                    <td>
                        <fmt:formatDate value="${bk.date}" pattern="yyyy-MM-dd"></fmt:formatDate>
                    </td>
                    <td>
                        <a href="/book/download?file=${bk.url}" target="_blank">
                            <img src="../Images/down.gif" alt="点击下载" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div class='MainStyle'>
                <div class=''>
                    <pagetag:seperator url="/book/ziliaolistsecond?title=${t}" pageInfo="${pageInfo}"></pagetag:seperator>
                </div>
            </div>

            </div>
        </div>
        
    </div>

    <script type="text/javascript">
        $(function() {
            $("#psize").change(function() {
                var size = $(this).val();
                location.href="/book/ziliaolist?title=${t}&size=" + size;
            })
        })
    </script>
</body>
</html>
