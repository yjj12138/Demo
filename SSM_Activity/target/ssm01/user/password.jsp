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
    
    <script type="text/javascript">
        function submitMail() {
            document.forms[0].submit();
        }
    </script>
</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：个人中心-》密码修改</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/updatepass" method="post">
        <input type="hidden" value="${sessionScope.usertb.userId}" name="userId"/>
    <table width="100%" cellpadding="0" cellspacing="0">
        <tr>
            <td align="right" width="80">原密码：</td>
            <td><input type="password" name="f_goods_name" value="${sessionScope.usertb.userPs}" /></td>
        </tr>
        <tr>
            <td align="right" width="90">新密码：</td>
            <td><input type="password" name="userPs" /></td>
        </tr>
        <tr>
            <td align="right" width="90">密码确认：</td>
            <td><input type="password" name="newpass" /></td>
        </tr>
       
        <tr align="center">
            <td colspan="5" height="40">
                <div align="center">
                    
                    <input type="button" id="button2" value="确认" onclick="submitMail()"/>
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
