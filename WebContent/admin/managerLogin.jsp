<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理后台登录</title>
<link rel="stylesheet" href="css/normalize.css">
	<script type="text/javascript" src="js/texiao.js"></script>
<style type="text/css">
.btn {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	padding: 4px 10px 4px;
	margin-bottom: 0;
	font-size: 13px;
	line-height: 18px;
	color: #333333;
	text-align: center;
	text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
	vertical-align: middle;
	background-color: #f5f5f5;
	background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);/*#e6e6e6*/
	background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6);
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff),
		to(#e6e6e6));
	background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
	background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
	background-image: linear-gradient(top, #ffffff, #e6e6e6);
	background-repeat: repeat-x;
	filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff,
		endColorstr=#e6e6e6, GradientType=0);
	border-color: #e6e6e6 #e6e6e6 #e6e6e6;
	border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
	border: 1px solid #e6e6e6;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.05);
	-moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.05);
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.05);
	cursor: pointer;
	*margin-left: .3em;
}

.btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
	background-color: #e6e6e6;
}

.btn-large {
	padding: 9px 14px;
	font-size: 15px;
	line-height: normal;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

.btn:hover {
	color: #333333;
	text-decoration: none;
	background-color: #e6e6e6;
	background-position: 0 -15px;
	-webkit-transition: background-position 0.1s linear;
	-moz-transition: background-position 0.1s linear;
	-ms-transition: background-position 0.1s linear;
	-o-transition: background-position 0.1s linear;
	transition: background-position 0.1s linear;
}

.btn-primary, .btn-primary:hover {
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
	color: #ffffff;
}

.btn-primary.active {
	color: rgba(255, 255, 255, 0.75);
}

.btn-primary {
	background-color: rgba(171, 252, 94, 0.5);
	background-size: 100px;
background-image: -moz-linear-gradient(top, rgba(238, 253, 112, 0.5), rgba(171, 252, 94, 0.5));
	background-image: -ms-linear-gradient(top, rgba(238, 253, 112, 0.5), rgba(171, 252, 94, 0.5));
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(rgba(238, 253, 112, 0.5)),
		to(rgba(171, 252, 94, 0.5)));
	background-image: -webkit-linear-gradient(top, rgba(238, 253, 112, 0.5), rgba(171, 252, 94, 0.5));
	background-image: -o-linear-gradient(top, rgba(238, 253, 112, 0.5), rgba(171, 252, 94, 0.5));
	background-image: linear-gradient(top, rgba(238, 253, 112, 0.5), rgba(171, 252, 94, 0.5));
	background-repeat: repeat-x;
	/*filter: alpha(opacity:50);*/
	filter: progid:dximagetransform.microsoft.gradient(startColorstr=rgba(211, 246, 178, 0.5),
	endColorstr=rgba(152, 203, 104, 0.5), GradientType=0);
	border: 1px solid rgba(171, 252, 94, 0.5);
	text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.5);
}

.btn-primary:hover, .btn-primary:active, .btn-primary.active,
	.btn-primary.disabled, .btn-primary[disabled] {
	filter: none;
	background-color: rgba(209, 246, 164, 0.5);
}

.btn-block {
	width: 100%;
	display: block;
}

* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	-o-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	width: 100%;
	height: 100%;
	overflow: hidden;
}

body {
	width: 100%;
	height: 100%;
	font-family: 'Open Sans', sans-serif;
	background-image: url("/img/bk1.jpg");
	background-size: 100%, 100%;
	/*background: #092756;*/
	/*background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)*/
	/*	10%, rgba(138, 114, 76, 0) 40%),*/
	/*	-moz-linear-gradient(top, rgba(57, 173, 219, .25) 0%,*/
	/*	rgba(42, 60, 87, .4) 100%),*/
	/*	-moz-linear-gradient(-45deg, #670d10 0%, #092756 100%);*/
	/*background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)*/
	/*	10%, rgba(138, 114, 76, 0) 40%),*/
	/*	-webkit-linear-gradient(top, rgba(57, 173, 219, .25) 0%,*/
	/*	rgba(42, 60, 87, .4) 100%),*/
	/*	-webkit-linear-gradient(-45deg, #670d10 0%, #092756 100%);*/
	/*background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)*/
	/*	10%, rgba(138, 114, 76, 0) 40%),*/
	/*	-o-linear-gradient(top, rgba(57, 173, 219, .25) 0%,*/
	/*	rgba(42, 60, 87, .4) 100%),*/
	/*	-o-linear-gradient(-45deg, #670d10 0%, #092756 100%);*/
	/*background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)*/
	/*	10%, rgba(138, 114, 76, 0) 40%),*/
	/*	-ms-linear-gradient(top, rgba(57, 173, 219, .25) 0%,*/
	/*	rgba(42, 60, 87, .4) 100%),*/
	/*	-ms-linear-gradient(-45deg, #670d10 0%, #092756 100%);*/
	/*background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104, 128, 138, .4)*/
	/*	10%, rgba(138, 114, 76, 0) 40%),*/
	/*	linear-gradient(to bottom, rgba(57, 173, 219, .25) 0%,*/
	/*	rgba(42, 60, 87, .4) 100%),*/
	/*	linear-gradient(135deg, #670d10 0%, #092756 100%);*/
	/*filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D',*/
	/*	endColorstr='#092756', GradientType=1);*/
}
.loginB1 {
	position: absolute;
	top: 53%;
	right:50.2%;
	/*margin-left: 60px;*/
	width:140px;
	height:60px;
}
.loginB2 {
	position: absolute;
	top: 53%;
	left:50.2%;
	margin-right: 60px;
	width:140px;
	height:60px;
}
.loginB {
	position: absolute;
	top: 73.5%;
	left: 50%;
	margin: -150px 0 0 -150px;/*150 150*/
	width: 300px;
	height: 300px;
}
.login {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -150px 0 0 -150px;/*150 150*/
	width: 300px;
	height: 300px;
}

.login h1 {
	color: #fff;
	text-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	letter-spacing: 1px;
	text-align: center;
}

input {
	width: 100%;
	margin-bottom: 10px;
	background: rgba(0, 0, 0, 0.25);
	/*border: none;*/
	outline: none;
	padding: 10px;
	font-size: 13px;
	color: #fff;
	text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.4);
	border: 1px solid rgba(0, 0, 0, 0.4);
	border-radius: 4px;
	box-shadow: inset 0 -5px 45px rgba(100, 100, 100, 0.4), 0 1px 1px
		rgba(255, 255, 255, 0.4);
	-webkit-transition: box-shadow .5s ease;
	-moz-transition: box-shadow .5s ease;
	-o-transition: box-shadow .5s ease;
	-ms-transition: box-shadow .5s ease;
	transition: box-shadow .5s ease;
}

input:focus {
	box-shadow: inset 0 -5px 45px rgba(100, 100, 100, 0.4), 0 1px 1px
		rgba(255, 255, 255, 0.2);
}
input::placeholder{
		 color: rgba(255,255,255,0.8);
	 }
</style>

</head>
<body>
	<div class = login>
		<h1>Welcome Admin!</h1>
		<form
			action="${pageContext.request.contextPath}/admin/ManagerServlet?op=login"
			method="post">
			<input type="text" name="username" placeholder="Username"
				required="required" /><input type="password" name="password"
				placeholder="Password" required="required" />
		<button  type="submit" class="btn btn-primary " style="width:145px">Login</button>
			<button  type="submit" class="btn btn-primary "
<%--					 onclick=window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=category"--%>
					 onClick="javascript:history.go(-1);"
					 style="width:145px"
			>Return</button>
		</form>
	</div>
<%--	<div class = loginB>--%>
<%--		<form--%>
<%--				action="${pageContext.request.contextPath}/client/ClientServlet?op=category"--%>
<%--				method="post">--%>
<%--			<button  type="submit" class="btn btn-primary btn-block btn-large">Return</button>--%>
<%--		</form>--%>
<%--	</div>--%>

<%--		<form>--%>
<%--			<input type="text" name="username" placeholder="Username" required="required" />--%>
<%--			<input type="text" name="password" placeholder="Password" required="required" />--%>
<%--		</form>--%>
<%--	</div>--%>
<%--	<div class = loginB1>--%>
<%--		<form action="${pageContext.request.contextPath}/admin/ManagerServlet?op=login" method="post">--%>
<%--			<button type = "submit" class="btn btn-primary btn-block btn-large">Login</button>--%>
<%--		</form>--%>
<%--	</div>--%>
<%--	<div class = loginB2>--%>
<%--		<form action="${pageContext.request.contextPath}/client/ClientServlet?op=category" method="post">--%>
<%--			<button type="submit" class="btn btn-primary btn-block btn-large" >Return</button>--%>
<%--		</form>--%>
<%--	</div>--%>

</body>
</html>