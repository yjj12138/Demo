<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script>
	function del(){
		confirm("确认删除？");
	}

</script>
</head>
<body>

   

<div class="div_head" style="width: 100%;text-align:center;">
		<span>
            <span style="float:left">当前位置是：流程管理-》部署管理</span> <span style="float:right;margin-right: 8px;font-weight: bold">
		</span>
		</span>
	</div>

<div class="morebt">
 
</div>





 <div class="cztable">

        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tbody>
            <tr style="height: 25px" align="center">
                <th colspan="4">部署信息管理列表</th>
            </tr>

            <tr style="height: 25px" align="center">
                    <th scope="col">ID</th>
                    <th scope="col">流程名称</th>
                    <th scope="col">发布时间</th>
                    <th scope="col">操作</th>
                </tr>
            <c:forEach items="${deploy}" var="dp">
                <tr align="center">
                    <td>${dp.id}</td>
                    <td>${dp.name}</td>
                    <td>
                        <fmt:formatDate value="${dp.deploymentTime}" pattern="yyyy/MM/dd"></fmt:formatDate>
                    </td>
                    <td>
                        <a href="/bushu/deletedeploy?id=${dp.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <hr>
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tbody>
         <tr style="height: 25px" align="center">
             <th colspan="8">流程定义信息列表</th>
         </tr>

         <tr style="height: 25px" align="center">
             <th scope="col">ID</th>
             <th scope="col">名称</th>
             <th scope="col">流程定义的key</th>
             <th scope="col">流程定义的版本</th>
             <th scope="col">流程定义的规则文件名</th>
             <th scope="col">流程定义的规则图片名</th>
             <th scope="col">部署ID</th>
             <th scope="col">操作</th>
         </tr>
         <c:forEach items="${processDef}" var="pd">
         <tr style="height: 25px" align="center">
             <th scope="col">${pd.id}</th>
             <th scope="col">${pd.name}</th>
             <th scope="col">${pd.key}</th>
             <th scope="col">${pd.version}</th>
             <th scope="col">${pd.resourceName}</th>
             <th scope="col">${pd.diagramResourceName}</th>
             <th scope="col">${pd.deploymentId}</th>
             <th scope="col">
                 <a href="/bushu/lookprocesspicture?deploymentId=${pd.deploymentId}&resourceName=${pd.diagramResourceName}">查看流程图</a>
             </th>
         </tr>
         </c:forEach>
         </tbody>
     </table>
     <hr>
     <form action="/bushu/addprocess" method="post" enctype="multipart/form-data">
     <table>
         <tr>
             <th colspan="2">部署流程定义</th>
         </tr>
         <tr>
             <th>流程名称:</th>
             <th><input type="text" name="processname"/> </th>
         </tr>
         <tr>
             <th>流程文件:</th>
             <th><input type="file" name="processfile"/> </th>
         </tr>
         <tr>
             <th colspan="2">
                 <input type="submit" value="上传流程">
             </th>
         </tr>
     </table>
     </form>
    </div>

    </div>
</body>
</html>