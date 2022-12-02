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
    <title>管理系统后台</title>
</head>
<script type="text/javascript" src="<%=basePath%>/js/texiao.js"></script>
<frameset rows="48px,*">
    <frame src="<%=basePath%>/header.jsp" name='headerr' framespacing="0"
           frameborder="0" border="0" scrolling="No" noresize="noresize">
    <frameset cols="480px,*">
        <frame  src="<%=basePath%>/artists/artistnavigation.jsp" framespacing="0" frameborder="0"
                border="0" scrolling="No" noresize="noresize">
        <frameset >
            <frame src="<%=basePath%>/artists/artistIndex.jsp" name='main' framespacing="0"
                   frameborder="0" border="0" scrolling="Yes" noresize="noresize">

        </frameset>
    </frameset>
</frameset>
</html>