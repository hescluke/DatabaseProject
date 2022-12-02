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
<title>编辑歌曲信息</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
</head>
<body><script type="text/javascript" src="js/texiao.js"></script>
	<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<div>
		<p style="font-size: 25px; margin-top: 20px; margin-left: 42px">编辑歌曲信息</p>
	</div>
	<hr
		style="height: 10px; border: none; border-top: 10px groove #8ddcf8;" />

	<div style="margin-top: 30px; margin-left: 430px">
		<form action="<%=basePath%>/admin/ManagerServlet?op=editSong"
			method="post">
			<div class="form-group">
				<input type="hidden" class="form-control" id="song_id" name="song_id"
					value="${map.song.song_id}" style="width: 700px">
			</div>
			<div class="form-group">
				<label>歌曲名称：</label> <input type="text" class="form-control"
					id="song_name" name="song_name" value="${map.song.song_name}"
					style="width: 700px">
			</div>
			<div class="form-group">
				<label>演唱者：</label> <input type="text" class="form-control"
					id="song_artist" name="song_artist" value="${map.song.song_artist}"
					style="width: 700px">
			</div>
			<div class="form-group">
				<label>歌曲专辑：</label> <input type="text" class="form-control"
					id="song_press" name="song_press" value="${map.song.song_press}"
					style="width: 700px">
			</div>
			<div class="form-group">
				<label>歌曲分类：</label> <select class="form-control" name="categoryid"
					title="歌曲分类" style="width: 700px">
					<option selected="selected">${map.song.category.category_name}</option>
					<c:forEach items="${map.category}" var="c">
						<option value="${c.category_id}">${c.category_name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleInputFile">图片上传：</label> <img
					style="width: 80px; height: 100px"
					src="${pageContext.request.contextPath}/img/books/${map.song.song_name}.png" /><input
					type="file" id="file" name="file">
			</div>
			<div class="form-group">
				<label>歌曲简介：</label>
				<textarea class="form-control" rows="3" id="song_desc"
					name="song_desc" style="width: 700px">${map.song.song_desc}</textarea>
			</div>
			<div class="form-group">
				<label>歌曲单价：</label> <input type="text" class="form-control"
					id="song_price" name="song_price" value="${map.song.song_price}"
					style="width: 700px">
			</div>
			<div class="form-group">
				<label>歌曲库存：</label> <input type="text" class="form-control"
					id="song_kunumber" name="song_kunumber"
					value="${map.song.song_kunumber}" style="width: 700px">
			</div>
			<br>
			<button type="submit" class="btn btn-default"
				onclick="window.location='manager/ManagerServlet?op=editSong'">提交</button>
			<button type="button" class="btn btn-default" data-dismiss="modal"
				style="margin-left: 100px" onclick="history.go(-1);">关闭</button>
		</form>
	</div>
</body>
</html>