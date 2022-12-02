<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.*" %>
<%@ page import="java.lang.String" %>

<%--<%@page import="com.quickcd.common.entity.Project" %>--%>
<%--<%@page import="com.quickcd.common.entity.AgentPool" %>--%>
<%--<%@page import="com.quickcd.common.service.AgentPoolService" %>--%>
<%--<%@page import="com.quickcd.web.common.QCWebConstants" %>--%>
<%@ page import="cn.xh.domain.Favorite" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link rel="stylesheet" href="<%=basePath%>/css/style.css"/>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Music Player | CodingNepal</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<%--    <script type="text/javascript">--%>
<%--        var values = {};--%>
<%--        <c:forEach items="${favorite}" var="f">--%>
<%--        &lt;%&ndash;values['${item.uuid}'] = '${item.name}';&ndash;%&gt;--%>
<%--        var nn = new s();--%>
<%--        nn.name = '${f.song.song_name}';--%>
<%--        nn.artist = '${f.song.song_artist}';--%>
<%--        nn.img = '${f.song.song_name}';--%>
<%--        nn.src= '${f.song.song_name}';--%>
<%--        allMusic.append(nn);--%>

<%--        window.write('${f.song.song_name}');--%>
<%--        </c:forEach>--%>
<%--        // alert("aaa");--%>
<%--    </script>--%>
</head>
<body>
<script>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/texiao.js"></script>

<div class="wrapper" >
    <div class="top-bar">
<%--        <i class="material-icons">expand_more</i>--%>
        <span style="text-align: center">Listen Now</span>
<%--        <i class="material-icons">more_horiz</i>--%>
    </div>
    <div class="img-area">
        <img src="" alt="">
    </div>
    <div class="song-details">
        <p class="name"></p>
        <p class="artist"></p>
    </div>
    <div class="progress-area">
        <div class="progress-bar">
            <audio id="main-audio" src=""></audio>
        </div>
        <div class="song-timer">
            <span class="current-time">0:00</span>
            <span class="max-duration">0:00</span>
        </div>
    </div>
    <div class="controls">
        <i id="repeat-plist" class="material-icons" title="Playlist looped">repeat</i>
        <i id="prev" class="material-icons">skip_previous</i>
        <div class="play-pause">
            <i class="material-icons play">play_arrow</i>
        </div>
        <i id="next" class="material-icons">skip_next</i>
        <i id="more-music" class="material-icons">queue_music</i>
    </div>
    <div class="music-list">
        <div class="header">
            <div class="row">
                <i class= "list material-icons">queue_music</i>
                <span>Music List</span>
            </div>
            <i id="close" class="material-icons">close</i>
        </div>
        <ul>
            <!-- here li list are coming from js -->
        </ul>
    </div>
</div>
<div style="margin-top: 700px; width: 450px; height: 150px; position: fixed; " align="center">
<table class="table table-bordered" style="background-color: rgba(248,241,225,0.5);box-shadow: 0px 6px 15px var(--lightbshadow);">
<%--       style="margin-top: 800px; width: 450px; overflow: scroll; height: 200px">--%>
    <thead style="display: block">
    <tr style="display: block">
        <td style="text-align: center; width: 200px">歌名</td>
        <td style="text-align: center; width: 150px">歌手</td>
        <td style="text-align: center; width: 100px">歌单</td>
    </tr>
    </thead>
<tbody style=" display: block; overflow: auto;">
<c:forEach items="${favorite}" var="f" varStatus="vs">
    <tr style="display: block">
        <td style="text-align: center;width: 200px">${f.song.song_name}</td>
        <td style="text-align: center;width: 150px">${f.song.song_artist}</td>
        <td style="text-align: center;width: 100px"><a  style="text-align: center" href="javascript:addd('${f.song.song_name}','${f.song.song_artist}')">添加</a><td>
    </tr>
</c:forEach>
</tbody>
</table>
</div>
<%--<script src="js/music-list.js"></script>--%>
<script type="text/javascript">
    <%--let allMusic = "${favorite}";--%>
    function addd(s_n, s_a){
       var nn = new s();
        // alert(s_n);
        nn.name = s_n;
        nn.artist = s_a;
        nn.img = s_n;
        nn.src= s_n;
        allMusic.push(nn);
        // alert(allMusic.length);
        // alert(allMusic[allMusic.length-1].name);
    }
    class s{
        constructor(name, artist, img, src) {
            this.name = name;
            this.artist = artist;
            this.img = img;
            this.src = src;
        }
    }
    // window.write();
    // let allMusic = new Array(s);

    let allMusic = [
        // {
        //     name: "Love Story",
        //     artist: "Taylor Swift",
        //     img: "Love Story",
        //     src: "Love Story"
        // },
        // {
        //     name: "Take Me Home, Country Roads",
        //     artist: "John Denver",
        //     img: "Take Me Home, Country Roads",
        //     src: "Take Me Home, Country Roads"
        // },
        // {
        //     name: "Harley Bird - Home",
        //     artist: "Jordan Schor",
        //     img: "music-1",
        //     src: "music-1"
        // },
        // {
        //     name: "Ikson Anywhere – Ikson",
        //     artist: "Audio Library",
        //     img: "music-2",
        //     src: "music-2"
        // },
        // {
        //     name: "Beauz & Jvna - Crazy",
        //     artist: "Beauz & Jvna",
        //     img: "music-3",
        //     src: "music-3"
        // },
        // {
        //     name: "Hardwind - Want Me",
        //     artist: "Mike Archangelo",
        //     img: "music-4",
        //     src: "music-4"
        // },
        // {
        //     name: "Jim - Sun Goes Down",
        //     artist: "Jim Yosef x Roy",
        //     img: "music-5",
        //     src: "music-5"
        // },
        // {
        //     name: "Lost Sky - Vision NCS",
        //     artist: "NCS Release",
        //     img: "music-6",
        //     src: "music-6"
        // },

    ];
</script>
<script src="js/script.js">

</script>
<%--<script src="build/classes/cn/xh/domain/sss.js"></script>--%>
<%--music_library\build\classes\cn\xh\domain--%>
</body>
</html>