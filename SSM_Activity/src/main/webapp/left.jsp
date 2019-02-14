<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />
        <script language=javascript>
            function expand(el)
            {
                childobj = document.getElementById("child" + el);

                if (childobj.style.display == 'none')
                {
                    childobj.style.display = 'block';
                }
                else
                {
                    childobj.style.display = 'none';
                }
                return;
            }
        </script>
    </head>
    <body background=img/menu_bg.jpg >
        <table height="100%" cellspacing=0 cellpadding=0 width=170   background=./img/menu_bg.jpg border=0>
            <tr>
                <td valign=top align=middle>
                    <table cellspacing=0 cellpadding=0 width="100%" border=0>
						<tr>
                            <td height=10></td>
						</tr>
					</table>
					<!--一级菜单-->
					<c:forEach items="${usertb.role.menu}" var="one">
                    <table cellspacing=0 cellpadding=0 width=150 border=0>
                        <tr height=22>
                            <td style="padding-left: 30px" background=./img/menu_bt.jpg>
							   <a class=menuparent onclick=expand(${one.menuid})  href="javascript:void(0);">${one.menuname}</a>
							 </td>
						</tr>
                        <tr height=4>
                            <td></td>
						</tr>
					</table>
					<!--二级菜单-->
                    <table id="child${one.menuid}" style="display: none" cellspacing=0 cellpadding=0  width=150 border=0>
                        <c:forEach items="${one.second}" var="sec">
						<tr height=20>
                            <td align=middle width=30>
								<img height=9 src="./img/menu_icon.gif" width=9>
							</td>
                            <td>
								<a class=menuchild href="${sec.menupath}" target="right">${sec.menuname}</a>
							</td>
						</tr>
                        </c:forEach>
                        <tr height=4>
                            <td colspan=2></td>
						</tr>
					</table>
					</c:forEach>
				</td>
                <td width=1 bgcolor=#d1e6f7></td>
            </tr>
        </table>
    </body>
</html>