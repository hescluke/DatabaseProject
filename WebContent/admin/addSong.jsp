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
<title>添加歌曲信息</title>
<link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath%>/css/header.css" />
</head>
<body><script type="text/javascript" src="js/texiao.js"></script>
	<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<div>
		<p style="font-size: 25px; margin-top: 20px; margin-left: 42px">添加歌曲信息</p>
	</div>
	<hr
		style="height: 10px; border: none; border-top: 10px groove #8ddcf8;" />
	<form style="margin-top: 30px; margin-left: 450px"
		action="<%=basePath%>/admin/ManagerServlet?op=addSong" method="post"
		enctype="multipart/form-data">
		<div class="form-group">
			<label>歌曲名称：</label> <input type="text" class="form-control"
				id="song_name" name="song_name" placeholder="歌曲名称"
				style="width: 700px">
		</div>
		<div class="form-group">
			<label>演唱者：</label> <input type="text" class="form-control"
				id="song_artist" name="song_artist" placeholder="演唱者"
				style="width: 700px">
		</div>
		<div class="form-group">
			<label>歌曲专辑：</label> <input type="text" class="form-control"
				id="song_press" name="song_press" placeholder="歌曲专辑"
				style="width: 700px">
		</div>
		<div class="form-group">
			<label>歌曲分类：</label> <select class="form-control" name="categoryid"
				title="歌曲分类" style="width: 700px">
				<option selected="selected">---所属分类---</option>
				<c:forEach items="${cs}" var="c">
					<option value="${c.category_id}">${c.category_name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="exampleInputFile">图片上传：</label> <input type="file"
				id="file" name="file">
		</div>
		<div class="form-group">
			<label>歌曲简介：</label>
			<textarea class="form-control" rows="3" id="song_desc"
				name="song_desc" placeholder="歌曲简介" style="width: 700px"></textarea>
		</div>
		<div class="form-group">
			<label>歌曲单价：</label> <input type="text" class="form-control"
				id="song_price" name="song_price" placeholder="歌曲单价/￥"
				style="width: 700px">
		</div>
		<div class="form-group">
			<label>歌曲库存：</label> <input type="text" class="form-control"
				id="song_kunumber" name="song_kunumber" placeholder="歌曲库存"
				style="width: 700px">
		</div>
		<br>
		<button type="submit" class="btn btn-default"
			onclick="window.location='manager/ManagerServlet?op=addSong'">提交</button>
		<button type="button" class="btn btn-default" data-dismiss="modal"
			style="margin-left: 100px"
			onclick="window.location='managerIndex.jsp'">关闭</button>
	</form>
</body>
</html>