<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                ;
    %>
    <title>导航条</title>
</head>
<body>
<div style="margin-top: 100px; margin-left: 90px">
    <p>
        <a href="<%=basePath%>/artists/artistPassword.jsp"
           style="color: black; font-weight: bold; text-decoration: none"
           target="main"> <img src="<%=basePath%>/img/icon-12.png"
                               style="width: 24px" />&nbsp;&nbsp;修改密码
        </a>
    </p>
    <p>
        <a href="<%=basePath%>/artist/ArtistServlet?op=findArtistSong&a_n=${sessionScope.artist.artist_name}"
           style="color: black; font-weight: bold; text-decoration: none"
           target="main"><img src="<%=basePath%>/img/icon-13.png"
                              style="width: 24px" />&nbsp;&nbsp;我的歌曲</a>
    </p>
    <p>
        <a href="<%=basePath%>/admin/ManagerServlet?op=addSongUI"
           style="color: black; font-weight: bold; text-decoration: none"
           target="main"><img src="<%=basePath%>/img/icon-4.png"
                              style="width: 24px" />&nbsp;&nbsp;发布单曲</a>
    </p>
    <p>
        <a href="<%=basePath%>/artist/ArtistServlet?op=editArtistSong&a_n=${sessionScope.artist.artist_name}"
           style="color: black; font-weight: bold; text-decoration: none"
           target="main"><img src="<%=basePath%>/img/icon-9.png"
                              style="width: 24px" />&nbsp;&nbsp;单曲维护</a>
    </p>
<%--    <p>--%>
<%--    <a--%>
<%--            href="${pageContext.request.contextPath}/admin/ManagerServlet?op=addSongUI"--%>
<%--            style="text-decoration: none;" target="main">--%>

<%--        <img src="<%=basePath%>/img/icon-4.png" style="width: 20px" />--%>
<%--        发布单曲</a>--%>
<%--    </p>--%>
<%--    <p>--%>
<%--    <a href="<%=basePath%>/artist/ArtistServlet?op=editArtistSong&a_n=${sessionScope.artist.artist_name}"--%>
<%--       style="text-decoration: none;" target="main">--%>

<%--        <img src="<%=basePath%>/img/icon-9.png" style="width: 20px" />--%>
<%--        单曲维护</a>--%>
<%--    </p>--%>
</div>

</body>
</html>