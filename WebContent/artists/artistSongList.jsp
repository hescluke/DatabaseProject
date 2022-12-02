<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                ;
    %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>歌曲信息维护</title>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
</head>
<body><script type="text/javascript" src="js/texiao.js"></script>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- 歌曲导航条 -->
<%--<nav class="navbar navbar-default" style="margin-bottom: 0px;">--%>
<%--    <div class="container-fluid">--%>
<%--&lt;%&ndash;        <div class="navbar-header">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <button type="button" class="navbar-toggle collapsed"&ndash;%&gt;--%>
<%--&lt;%&ndash;                    data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"&ndash;%&gt;--%>
<%--&lt;%&ndash;                    aria-expanded="false">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <span class="sr-only">Toggle navigation</span> <span&ndash;%&gt;--%>
<%--&lt;%&ndash;                    class="icon-bar"></span> <span class="icon-bar"></span> <span&ndash;%&gt;--%>
<%--&lt;%&ndash;                    class="icon-bar"></span>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <a class="navbar-brand" href="<%=basePath%>/admin/ManagerServlet?op=category">歌曲分类</a>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;        <div class="collapse navbar-collapse"&ndash;%&gt;--%>
<%--&lt;%&ndash;             id="bs-example-navbar-collapse-1">&ndash;%&gt;--%>
<%--&lt;%&ndash;            <ul class="nav navbar-nav">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <c:forEach items="${categoryList}" var="c">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <li><a href="<%=basePath%>/admin/ManagerServlet?op=category&cid=${c.category_id}">${c.category_name}</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </ul>&ndash;%&gt;--%>
<%--            <!-- 搜索框 -->--%>
<%--&lt;%&ndash;            <div style="float: right; margin-right: 7px">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <form class="navbar-form navbar-left"&ndash;%&gt;--%>
<%--&lt;%&ndash;                      action="<%=basePath%>/admin/ManagerServlet?op=search"&ndash;%&gt;--%>
<%--&lt;%&ndash;                      method="post">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <div class="form-group">&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <input type="text" name="search" class="form-control"&ndash;%&gt;--%>
<%--&lt;%&ndash;                               placeholder="搜索歌曲">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <button type="submit" class="btn btn-default">搜索</button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </form>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--    </div>--%>
<%--</nav>--%>
<%--<c:if test="${!empty songs}">--%>
    <table class="table table-bordered table-hover" style="width: 100%">
        <tr>
            <th style="text-align: center; width: 100px">歌曲编号</th>
            <th style="text-align: center; width: 100px">歌曲图片</th>
            <th style="text-align: center; width: 200px">歌曲名称</th>
            <th style="text-align: center; width: 200px">歌曲类型</th>
            <th style="text-align: center; width: 200px">演唱者</th>
            <th style="text-align: center; width: 300px">歌曲专辑</th>
            <th style="text-align: center; width: 200px">歌曲价格</th>
            <th style="text-align: center; width: 200px">销量</th>
            <th style="text-align: center; width: 200px">操作</th>
        </tr>
        <c:forEach items="${sessionScope.artistSong}" var="s" varStatus="vs">
            <tr class="active">
                <td>${vs.index+1}</td>
                <td><img style="width: 80px; height: 100px"
                         src="${pageContext.request.contextPath}/img/books/${s.song_name}.png" />
                </td>
                <td>${s.song_name}</td>
                <td>${s.category.category_name}</td>
                <td>${s.song_artist}</td>
                <td>${s.song_press}</td>
                <td><span style="color: rgb(198, 46, 45); font-weight: bold">￥${s.song_price}</span></td>
                <td>${s.song_xiaonumber}</td>
                <td><a
                        href="<%=basePath%>/admin/ManagerServlet?op=editSongUI&song_id=${s.song_id}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a
                            href="<%=basePath%>/admin/ManagerServlet?op=delSong&song_id=${s.song_id}"
                            onclick="if(confirm('确定删除'+'${s.song_name}'+'?')===false) return false;">删除</a>
            </tr>
        </c:forEach>
    </table>
<jsp:include page="/footer.jsp"></jsp:include>

<%--</c:if>--%>
</body>
</html>