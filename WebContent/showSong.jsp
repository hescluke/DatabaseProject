<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>COOL MUSIC</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			 ;
%>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
</head>
<body>

<script type="text/javascript" src="<%=basePath%>/js/texiao.js"></script>
<script type="text/javascript"
		src="<%=basePath%>/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/util.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script type="text/javascript">
		window.onload = function () {
			document.getElementById("bt1").onclick = function () {
				// var song_id = s_id;
				var song_id = "${s.song_id}";
				//01.获取ajax引擎,获取ajax的XMLHttpRequest核心对象
				var xhr = getXhr();
				//02.注册事件,监测readystate值的变化
				xhr.onreadystatechange = function () {
					//什么时候执行,服务器响应完成之后
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {

						}
					}
				}
				//03.发送请求
				xhr.open("get",
						"/client/ClientServlet?op=addCart&song_id="
						+ song_id);
				//04.发送请求正文
				xhr.send(null);
				alert("添加成功!");
			};

			document.getElementById("a1").onclick = function () {
				alert("添加成功");
				// var song_id = s_id;
				var song_id = "${s.song_id}";
				//01.获取ajax引擎,获取ajax的XMLHttpRequest核心对象
				var xhr = getXhr();
				//02.注册事件,监测readystate值的变化
				xhr.onreadystatechange = function () {
					//什么时候执行,服务器响应完成之后
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {

						}
					}
				}
				//03.发送请求
				xhr.open("get",
						"/client/ClientServlet?op=addfavorite&song_id="
						+ song_id);
				//04.发送请求正文
				xhr.send(null);
			}

		}
	</script>

	<c:if test="${empty user}">
		<jsp:include page="/header.jsp"></jsp:include>
	</c:if>
	<c:if test="${!empty user}">
		<jsp:include page="/wutouheader.jsp"></jsp:include>
	</c:if>

	<!-- 歌曲导航条 -->
	<div class="navbar navbar-default" style="; width:1141px; text-align:center; margin:0 auto" >

			<div class="collapse navbar-collapse"
				 id="bs-example-navbar-collapse-1" style="text-align: center">
				<ul class="nav navbar-nav">
					<c:forEach items="${sessionScope.categoryList}" var="c">
						<li><a href="<%=basePath%>/client/ClientServlet?op=category&cid=${c.category_id}">${c.category_name}</a></li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</ul>
				<!-- 搜索框 -->
				<div style="float: right; margin-right: 7px">
					<form class="navbar-form navbar-left"
						  action="<%=basePath%>/client/ClientServlet?op=search" method="post">
						<div class="form-group">
							<input type="text" name="search" class="form-control"
								   placeholder="搜索歌曲">
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>
				</div>
			</div>
<%--		</div>--%>
	</div>


<%--	<table class="table table-bordered table-hover"--%>
<%--		style="margin-left: 381px; width: 1141px">--%>
<%--		一定要在页面大小为100%的时候--%>
	<table class="table table-bordered table-hover"
		   align="center" style="width:1141px">
		<tr>
			<th style="text-align: center; width: 5%">编号</th>
			<th style="text-align: center; width: 10%">图片</th>
			<th style="text-align: center; width: 15%">名称</th>
			<th style="text-align: center; width: 10%">类型</th>
			<th style="text-align: center; width: 10%">作者</th>
			<th style="text-align: center; width: 10%">专辑</th>
			<th style="text-align: center; width: 5%">价格</th>
			<th style="text-align: center; width: 10%">销量</th>
			<th style="text-align: center; width: 25%">操作</th>
		</tr>
		<c:forEach items="${songs}" var="s" varStatus="vs">
			<tr class="active">
				<td style="text-align: center;">${vs.index+1}</td>
				<td style="text-align: center;"><img style="width: 80px; height: 80px"
					src="${pageContext.request.contextPath}/img/books/${s.song_name}.png" />
				</td>
				<td style="text-align: center;">${s.song_name}</td>
				<td style="text-align: center;">${s.category.category_name}</td>
				<td style="text-align: center;">${s.song_artist}</td>
				<td style="text-align: center;">${s.song_press}</td>
				<td style="text-align: center;"><span style="color: rgb(198, 46, 45); font-weight: bold">￥${s.song_price}</span></td>
				<td style="text-align: center;">${s.song_xiaonumber}</td>
				<td style="text-align: center;">

					<c:if test="${!empty user}">
						<c:if test="${user.vip=='1' || s.song_price=='0'}">
							<div style="margin-top: 20px; margin-left: 25px">
								<a id="a1" href="javascript:addf('${s.song_id}')" onclick="favorite();return false;"
								   style="text-decoration: none; color: black">添加歌单</a>
							</div>
						</c:if>
						<c:otherwise>
							<div style="margin-top: 20px; margin-left: 25px">
								<a id="a2" href="javascript:add2()"
								   style="text-decoration: none; color: black">添加歌单</a>
							</div>
						</c:otherwise>">

					</c:if>
					<c:if test="${empty user}">
						<div style="margin-top: 20px; margin-left: 25px">
							<a href="javascript:add()"
							   style="text-decoration: none; color: black">添加歌单</a>
						</div>
					</c:if>

				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/footer.jsp"></jsp:include>

	<script type="text/javascript">
		function cart() {
			alert("请先登录！${s.song_id}");
		}
		function addf(s_id){
			alert("添加成功");
			var song_id = s_id;
			// var song_id = "${s.song_id}";
			//01.获取ajax引擎,获取ajax的XMLHttpRequest核心对象s
			var xhr = getXhr();
			//02.注册事件,监测readystate值的变化
			xhr.onreadystatechange = function () {
				//什么时候执行,服务器响应完成之后
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {

					}
				}
			}
			//03.发送请求
			xhr.open("get",
					"/client/ClientServlet?op=addfavorite&song_id="
					+ song_id);
			//04.发送请求正文
			xhr.send(null);
			<%--alert(${s.song_id});--%>
		}

		function add() {
			alert("请先登录！");
		}

		function buyNow() {
			//立即购买
			var song_id = "${s.song_id}";
			//01.获取ajax引擎,获取ajax的XMLHttpRequest核心对象
			var xhr = getXhr();
			//02.注册事件,监测readystate值的变化
			xhr.onreadystatechange = function () {
				//什么时候执行,服务器响应完成之后
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {

					}
				}
			}
			//03.发送请求
			xhr.open("get",
					"/client/ClientServlet?op=buyNow&song_id="
					+ song_id);
			//04.发送请求正文
			xhr.send(null);
		}
	</script>
</body>
</html>