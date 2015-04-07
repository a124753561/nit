<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.nit.util.SpringUtils"%>
<%
	String base = request.getContextPath();
	ApplicationContext applicationContext = SpringUtils.getApplicationContext();
	if (applicationContext != null) {
		response.sendRedirect("login.jsp");
	} else {
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>提示信息 - Powered By rsico</title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="author" content="rsico Team" />
<meta name="copyright" content="rsico" />
<link href="<%=base%>/resources/admin/css/common.css" rel="stylesheet"
	type="text/css" />
<link href="<%=base%>/resources/admin/css/login.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<fieldset>
		<legend>系统出现异常</legend>
		<p>
			请检查rsico程序是否已正确安装 [<a href="<%=base%>/install/">点击此处进行安装</a>]
		</p>
		<p>
			<strong>提示: rsico安装完成后必须重新启动WEB服务器</strong>
		</p>
	</fieldset>
</body>
</html>
<%
	}
%>