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
<title>COOL MUSIC</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/css/header.css" />
</head>
<script type="text/javascript" src="js/texiao.js"></script>
<body>
	<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	<!-- 导航条 -->
	<div id="daohang">
		<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<c:if test="${!empty user}">
					<a href="<%=basePath%>/admin/top.jsp" target="_top"><img style="background-size:contain;height: 50px;width: 50px"
																			 src="<%=basePath%>/img/tou.png" />
						<font style="top:20px; color: #080808; font-size: 30px;font-family: 华文行楷">酷音乐库</font>
					</a>

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome dear <span
						style="color: red; font-weight: bold">${user.name}</span>&nbsp;!&nbsp;&nbsp;&nbsp;Please enjoy yourself in COOL MUSIC!

				</c:if>
				<c:if test="${!empty artist}">
					<a href="<%=basePath%>/admin/top.jsp" target="_top"><img style="background-size:contain;height: 50px;width: 50px"
																			 src="<%=basePath%>/img/tou.png" />
						<font style="top:20px; color: #080808; font-size: 30px;font-family: 华文行楷">酷音乐库</font>
					</a>

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome dear <span
						style="color: red; font-weight: bold">${artist.artist_name}</span>&nbsp;!&nbsp;&nbsp;&nbsp;Please enjoy yourself in COOL MUSIC!

				</c:if>
				<c:if test="${empty user && empty artist}">
					<a href="<%=basePath%>/index.jsp" target="_top"><img style="background-size:contain;height: 50px;width: 50px"
																		 src="<%=basePath%>/img/tou.png" />
						<font style="top:20px; color: #080808; font-size: 30px;font-family: 华文行楷">酷音乐库</font>
					</a>Please login first!
				</c:if>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				 id="bs-example-navbar-collapse-1">
				<c:if test="${empty user && empty artist}">
					<div style="float: right; margin-right: 7px; margin-top: 10px" target="_top">
						<!-- 登录 -->
						<a role="button" class="btn btn-default btn-sm"
						   data-toggle="modal" data-target="#myModal"
						   target="_top"
							<%--                                href="<%=basePath%>/admin/userLogin.jsp">登录--%>
						   onclick=window.parent.location.href="${pageContext.request.contextPath}/admin/userLogin.jsp">用户登录
						</a>

						<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal1"
								onclick=window.parent.location.href="${pageContext.request.contextPath}/admin/userRegister.jsp">用户注册
						</button>

						<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal1"
								onclick=window.parent.location.href="${pageContext.request.contextPath}/artists/artistLogin.jsp">创作者登录
						</button>

						<button type="button" class="btn btn-default btn-sm"
								data-toggle="modal" data-target="#myModal1"
								onclick=window.parent.location.href="${pageContext.request.contextPath}/artists/artistRegister.jsp">创作者注册
						</button>
					</div>
				</c:if>
				<c:if test="${!empty user}">
					<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px" >--%>
					<%--                        <a class="btn btn-default btn-sm"--%>
					<%--                           href="<%=basePath%>/admin/top.jsp"--%>
					<%--                           target="_top"--%>
					<%--                           role="button">返回主页</a>--%>
					<%--                    </div>--%>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" target="_parent"
						   href="${pageContext.request.contextPath}/client/ClientServlet?op=layout"
						   role="button">退出登录</a>
					</div>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" target="_parent"
						   href="<%=basePath%>/showCart.jsp" role="button">购物车</a>
					</div>
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" target="_parent"
						   href="<%=basePath%>/client/ClientServlet?op=showfavorite"
						   role="button">我的歌单</a>
					</div>

					<c:if test="${user.vip=='0'}">
						<div style="float: right; margin-right: 7px; margin-top: 10px">
							<a class="btn btn-default btn-sm" target="_parent"
							   href="<%=basePath%>/personalCenter.jsp"
							   role="button">个人中心</a>
						</div>
					</c:if>
					<c:if test="${user.vip=='1'}">
						<div style="float: right; margin-right: 7px; margin-top: 10px">
							<a class="btn btn-default btn-sm" target="_parent"
							   href="<%=basePath%>/personalCenter.jsp"
							   role="button">会员中心</a>
						</div>
					</c:if>
				</c:if>
				<c:if test="${!empty artist}">
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm" target="_parent"
						   href="${pageContext.request.contextPath}/artist/ArtistServlet?op=layout"
						   role="button">退出登录</a>
					</div>
				</c:if>
				<c:if test="${empty user && empty artist}">
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm"
						   href="${pageContext.request.contextPath}/admin/managerLogin.jsp"
						   role="button">管理员登录</a>
					</div>
				</c:if>
				<c:if test="${!empty user}">
					<div style="float: right; margin-right: 7px; margin-top: 10px">
						<a class="btn btn-default btn-sm"
						   href="${pageContext.request.contextPath}/admin/top.jsp"
						   role="button">返回主页</a>
					</div>
				</c:if>
			</div>
		</div>
		</nav>
	</div>
</body>
</html>