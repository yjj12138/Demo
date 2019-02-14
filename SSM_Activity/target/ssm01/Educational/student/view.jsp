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
                <span style="float:left">当前位置是：教务中心-》学生管理-》详细</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="../../right.jsp" method="post">
	<table border="1" width="100%" class="table_a">
                
                <tr  width="120px;">
                    <td width="10%">姓名<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" readonly name="f_goods_image" value="${stu.stuname}" readonly/>
					</td>
                </tr>
                <tr>
                    <td>性别<span style="color:red">*</span>：</td>
                    <td>
                        <input type="radio" ${stu.stusex==1?"checked":""} value="1" readonly/>男 <input type="radio" ${stu.stusex==1?"":"checked"} value="2"/>女
                    </td>
                </tr>

				<tr>
                    <td>EMAIL：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.email}" readonly/>
                    </td>                
                </tr>

				<tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.phone}" readonly/>
                    </td>                
                </tr>

				<tr>
                    <td>户口所在地：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.registered}"  readonly/>
                    </td>                
                </tr>

				<tr>
                    <td>住址：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.address}" readonly/>
                    </td>                
                </tr>
				<tr>
                    <td>政治面貌：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.politics}" readonly/>
                    </td>                
                </tr>
				<tr>
                    <td>身份证号：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.cardid}" readonly/>
                    </td>                
                </tr>
				
				<tr>
                    <td>专业：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="${stu.major.majorname}" readonly/>
                    </td>                
                </tr>
					
				</tr>
					<tr>
                    <td>登记时间：</td>
                    <td>
                        <input type="text" name="f_goods_image" value="<fmt:formatDate value="${stu.regdate}" pattern="yyyy/MM/dd"></fmt:formatDate> " readonly />
                    </td>                
                </tr>
				<tr>
                    <td>简介<span style="color:red">*</span>：</td>
                    <td>
                        <textarea>${stu.stucontent}</textarea>
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
