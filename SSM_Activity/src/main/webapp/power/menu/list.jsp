<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="pagetag" uri="http://java.sun.com/jsp/jstl/pagetag" %>
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
    <script>
	function del(){
		confirm("确认删除？");
	}

</script>
    <style type="text/css">
        #daochu{color: blue}
        #daochu:hover{color: red; cursor: pointer;}
        #delete{color: blue}
        #delete:hover{color: red; cursor: pointer;}
        #addmenu{color: blue}
        #addmenu:hover{color: red; cursor: pointer;}
        .tablelink:hover{cursor: pointer}
    </style>

    <script type="text/javascript">
        $(function () {
            $("[name='all']").click(function () {
                var t = $(this)[0].checked;
                var ids = $("[name='mid']");
                $(ids).each(function (i,n) {
                    n.checked = t;
                })
            })
        })

    $(function () {
        $("#delete").click(function () {
            var mids = new Array();
            var ids = $("[name='mid']")
            $(ids).each(function (i,n) {
                if(n.checked){
                    mids.push($(this).val());
                }
            })
            if(mids.length == 0) {
                alert("删除数据不能为空")
            }else {
                document.forms[0].action="/power/menu/deletemenus"
                document.forms[0].submit();
            }
        });
    })

    $(function () {
        $("#daochu").click(function () {
            var mids = new Array();
            var ids = $("[name='mid']")
            $(ids).each(function (i,n) {
                if(n.checked){
                    mids.push($(this).val());
                }
            })
            if(mids.length == 0) {
                alert("导出数据不能为空")
            }else {
                document.forms[0].action="/power/menu/daochumenus"
                document.forms[0].submit();
            }
        });
    })

    $(function() {
        $("#addmenu").click(function() {
           document.forms[0].action="/power/menu/selectmenuname";
           document.forms[0].submit();
        })
    })

    </script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span> <span style="float:left">当前位置是：权限管理-》菜单管理</span> <span
			style="float:right;margin-right: 8px;font-weight: bold">
			<span style="text-decoration: none;" id="daochu">【导出excel】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="text-decoration: none;" id="delete">【批量删除】</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span style="text-decoration: none;" id="addmenu">【新增菜单】</span>&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>

 <div class="cztable">
        <form method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
                <tr style="height: 25px" align="center">
                	<th><input type="checkbox" name="all"/></th>
                    <th scope="col">
                        序号
                    </th>
                    
                    <th scope="col">
                        菜单名称
                    </th>
                    <th scope="col">
                        UTL
                    </th>
                    <th scope="col">
                        状态
                    </th>
                    <th scope="col">
                        操作
                    </th>
                </tr>
                
               
                <c:forEach items="${pageInfo.list}" varStatus="sta" var="m">
                <tr align="center">
                    <th><input type="checkbox" name="mid" value="${m.menuid}-${m.upmenuid}"/></th>
                    <td>
                        ${sta.count}
                    </td>
                    <td>
                       ${m.menuname}
                    </td>
                    <td>
                       <a href="">${m.menupath}</a>
                    </td>
                    
                    <td>&nbsp;
                        ${m.menustate==1?"启用":"禁用"}
                    </td>
                    
                    <td>&nbsp;
                        <a href="/power/menu/details?menuid=${m.menuid}&upmenuid=${m.upmenuid}">详情</a>
                        <a href="/power/menu/updatemenuready?menuid=${m.menuid}">修改</a>
						<a class="tablelink" mid="${m.menuid}" upmid="${m.upmenuid}"> 删除</a>
                    </td>
                </tr>
                </c:forEach>

            </tbody>
        </table>
        </form>
          <div class='MainStyle'>
                <div class=''>
                    <pagetag:seperator url="/power/menu/getmenus" pageInfo="${pageInfo}"></pagetag:seperator>
                </div>
          </div>
    </div>

    <script type="text/javascript">
        $(function() {
          $("#psize").change(function() {
              var size = $(this).val();
            location.href="/power/menu/getmenus?size=" + size;
          })
        })

    $(function () {
        $(".tablelink").click(function () {
            var b = confirm("确定删除该用户?");
             if(b) {
                var menuid = $(this).attr("mid");
                var upmenuid = $(this).attr("upmid");
                location.href="/power/menu/deletemenubymenuid?menuid=" + menuid + "&upmenuid=" + upmenuid;
             }
        });
    })
    </script>
</body>
</html>