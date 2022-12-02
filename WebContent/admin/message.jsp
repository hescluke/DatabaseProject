<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			 ;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>正在跳转...</title>
</head><script type="text/javascript" src="<%=basePath%>/js/texiao.js"></script>
<body style="background-color: rgb(240, 243, 239);">
	&nbsp;&nbsp;&nbsp;&nbsp;${message}
	<h7>1秒后<a
		href="<%=basePath%>/admin/manager.jsp">跳转</a> </h7>
	<%
		response.setHeader("refresh", "1;URL=/admin/manager.jsp");
	%>
</body>
</html>