<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=basePath%>/css/header.css"/>
</head>
<body>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/texiao.js"></script>
<!-- 导航条 -->
<%--<div id="daohang">--%>
<%--    <nav class="navbar navbar-default">--%>
<%--        <div class="container-fluid">--%>
<%--            <!-- Brand and toggle get grouped for better mobile display -->--%>
<%--            --%>
<%--            --%>
<%--&lt;%&ndash;            <div class="navbar-header">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <button type="button" class="navbar-toggle collapsed"&ndash;%&gt;--%>
<%--&lt;%&ndash;                        data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"&ndash;%&gt;--%>
<%--&lt;%&ndash;                        aria-expanded="false">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <span class="sr-only">Toggle navigation</span> <span&ndash;%&gt;--%>
<%--&lt;%&ndash;                        class="icon-bar"></span> <span class="icon-bar"></span> <span&ndash;%&gt;--%>
<%--&lt;%&ndash;                        class="icon-bar"></span>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <a href="<%=basePath%>/client/ClientServlet?op=category"><img style="background-size:contain;height: 50px;width: 50px"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                                              src="<%=basePath%>/img/tou.png" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <font style="top:20px; color: #080808; font-size: 30px;font-family: 华文行楷">酷音乐库</font>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <c:if test="${!empty user}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome Dear <span&ndash;%&gt;--%>
<%--&lt;%&ndash;                        style="color: red; font-weight: bold">${sessionScope.user.name}</span>&nbsp;!&nbsp;&nbsp;&nbsp;Please enjoy yourself in COOL MUSIC!&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &lt;%&ndash;                    <img src="<%=basePath%>/img/gerenzx.png"&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &lt;%&ndash;                         style="margin-left: 1000px"/>&nbsp;&nbsp;&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                    &lt;%&ndash;                    <a href="<%=basePath%>/personalCenter.jsp" style="color: black">个人中心</a>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                </c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--            --%>
<%--            --%>
<%--            --%>
<%--            <!-- Collect the nav links, forms, and other content for toggling -->--%>
<%--            <div class="collapse navbar-collapse"--%>
<%--                 id="bs-example-navbar-collapse-1">--%>
<%--                <c:if test="${empty user}">--%>
<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <!-- 登录 -->--%>
<%--                        <button type="button" class="btn btn-default btn-sm"--%>
<%--                                data-toggle="modal" data-target="#myModal"--%>
<%--                                onclick=window.location.href="${pageContext.request.contextPath}/admin/userLogin.jsp">登录--%>
<%--                        </button>--%>
<%--                            &lt;%&ndash;                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                             aria-labelledby="myModalLabel">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                            <div class="modal-dialog" role="document">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                <div class="modal-content">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    <div class="modal-header">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <button type="button" class="close" data-dismiss="modal"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                aria-label="Close">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <span aria-hidden="true">&times;</span>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <h4 class="modal-title" id="myModalLabel">登录</h4>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    <form action="<%=basePath%>/client/ClientServlet?op=login"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                          method="post">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <div class="modal-body">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputName2">用户名： </label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="text" name="username" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputName2" placeholder="Username">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <br>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputPassword1">密码：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="password" name="password" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputPassword1" placeholder="Password">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <br>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <div class="modal-footer">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <button type="button" class="btn btn-default"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    data-dismiss="modal">关闭&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <button type="submit" class="btn btn-primary">登录</button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    </form>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                        </div>&ndash;%&gt;--%>
<%--                        <!-- 注册 -->--%>
<%--                        <button type="button" class="btn btn-default btn-sm"--%>
<%--                                data-toggle="modal" data-target="#myModal1"--%>
<%--                                onclick=window.location.href="${pageContext.request.contextPath}/admin/userRegister.jsp">注册--%>
<%--                        </button>--%>

<%--                            &lt;%&ndash;                        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 111&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                             aria-labelledby="myModalLabel">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                            <div class="modal-dialog" role="document">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                <div class="modal-content">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    <div class="modal-header">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <button type="button" class="close" data-dismiss="modal"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                aria-label="Close">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <span aria-hidden="true">&times;</span>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <h4 class="modal-title" id="myModalLabel">注册</h4>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    <form&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            action="/client/ClientServlet?op=register"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            method="post">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <div class="modal-body">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputName2">用户名：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="text" id="username" name="username"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    class="form-control" id="exampleInputName2"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    placeholder="Username">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <span id="s1"></span> <br>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputPassword1">密码：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="password" name="password" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputPassword1" placeholder="Password">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputName2">姓名：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="text" name="name" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputName2" placeholder="Name">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <label for="Ssex">性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <input type="radio" name="sex" value="男">男&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                type="radio" name="sex" value="女">女 <br>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputName2">电话：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="text" name="tel" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputName2" placeholder="Telephone number">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <div class="form-group">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                <label for="exampleInputName2">收货地址：</label> <input&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    type="text" name="address" class="form-control"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    id="exampleInputName2" placeholder="Address">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        <div class="modal-footer">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <button type="button" class="btn btn-default"&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                                    data-dismiss="modal">关闭&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            </button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                            <button type="submit" class="btn btn-primary">注册</button>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                    </form>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                                </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;                        </div>11&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <c:if test="${!empty user}">--%>
<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <a class="btn btn-default btn-sm" target="_parent"--%>
<%--                           href="${pageContext.request.contextPath}/client/ClientServlet?op=layout"--%>
<%--                           role="button">退出</a>--%>
<%--                    </div>--%>
<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <a class="btn btn-default btn-sm" target="_parent"--%>
<%--                           href="<%=basePath%>/showCart.jsp" role="button">购物车</a>--%>
<%--                    </div>--%>
<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <a class="btn btn-default btn-sm" target="_parent"--%>
<%--                           href="<%=basePath%>/client/ClientServlet?op=showfavorite"--%>
<%--                           role="button">收藏夹</a>--%>
<%--                    </div>--%>

<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <a class="btn btn-default btn-sm" target="_parent"--%>
<%--                           href="<%=basePath%>/personalCenter.jsp"--%>
<%--                           role="button">个人中心</a>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--                <c:if test="${empty user}">--%>
<%--                    <div style="float: right; margin-right: 7px; margin-top: 10px">--%>
<%--                        <a class="btn btn-default btn-sm"--%>
<%--                           href="${pageContext.request.contextPath}/admin/managerLogin.jsp"--%>
<%--                           role="button">管理员入口</a>--%>
<%--                    </div>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </nav>--%>
<%--</div>--%>


<div>
    <!-- 轮播图 -->
    <div class="container">
        <div id="carousel-example-generic" class="carousel slide"
             data-ride="carousel">
            <!-- 轮播图的中的小点 -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                <li data-target="#carousel-example-generic" data-slide-to="5"></li>
                <li data-target="#carousel-example-generic" data-slide-to="6"></li>
                <li data-target="#carousel-example-generic" data-slide-to="7"></li>
                <li data-target="#carousel-example-generic" data-slide-to="8"></li>
            </ol>
            <!-- 轮播图的轮播图片 -->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="<%=basePath%>/img/background/tl.jpg">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                        <%--                        微信关注公众号 HelloCoder ，领取 Java学习资料--%>
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/ed.jpg" style = "width:100%;">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/hh.jpg"style = "width:100%;">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/justin.jpg"style = "width:100%;">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/lp.jpg"style = "width:100%">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/m5.jpg"style = "width:100%">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/pts.jpg"style = "width:100%">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/sia.jpg" style = "width:100%">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
                <div class="item">
                    <img src="<%=basePath%>/img/background/avril.jpg" style = "width:100%">
                    <div class="carousel-caption">
                        <!-- 轮播图上的文字 -->
                    </div>
                </div>
            </div>

            <!-- 上一张 下一张按钮 -->
            <a class="left carousel-control" href="#carousel-example-generic"
               role="button" data-slide="prev"> <span
                    class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a> <a class="right carousel-control" href="#carousel-example-generic"
                    role="button" data-slide="next"> <span
                class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
        </div>
    </div>
</div>


</body>
</html>