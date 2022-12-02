package cn.xh.web.controller;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.Favorite;
import cn.xh.domain.Artist;
import cn.xh.service.ArtistService;

import cn.xh.service.ManagerService;
import cn.xh.service.impl.ArtistServiceImpl;

import cn.xh.service.impl.ManagerServiceImpl;
import cn.xh.web.formbean.Cart;
import cn.xh.web.formbean.CartItem;

@WebServlet("/artist/ArtistServlet")

public class ArtistServlet extends HttpServlet {
    private final ManagerService service = new ManagerServiceImpl();
    private final ArtistService artistService = new ArtistServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String op = req.getParameter("op");// 得到传过来的请求
        if (op != null && !op.equals("")) {
//            if(op.equals("findArtistSong")){
//                findArtistSong(req,resp);
//            }
            // 登录
            if (op.equals("findArtistSong")){
                findArtistSong(req,resp);
            }
            if (op.equals("login")) {
                login(req, resp);
            }
            if(op.equals("artistPassword")){
                artistPassword(req,resp);
            }
            if (op.equals("layout")) {
                layout(req, resp);
            }
            if (op.equals("editArtistSong")) {
                editArtistSong(req, resp);
            }
        }
    }
    private void editArtistSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> songs = artistService.findArtistSong(req.getParameter("a_n"));// 文学艺术类歌曲
        req.setAttribute("artistsongs", songs);
        List<Category> categoryList = service.findAllCategory();// 分类
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/artists/artistSongList.jsp").forward(req, resp);
    }
    private void layout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("artist");// 获取session对象，从session中移除登录信息
            resp.sendRedirect("/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void findArtistSong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> songs = artistService.findArtistSong(req.getParameter("a_n"));// 文学艺术类歌曲
        req.setAttribute("artistsongs", songs);

        List<Category> categoryList = service.findAllCategory();// 分类
        req.setAttribute("categoryList", categoryList);

        req.getRequestDispatcher("/artistShowSong.jsp").forward(req, resp);
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String artistname = req.getParameter("artistname");
            String password = req.getParameter("password");
            HttpSession session = req.getSession();
            Artist artist = artistService.login(artistname, password);
            if (artist.getArtist_name() != null && artist.getArtist_name() != "") {
                req.setAttribute("message", "登录成功");
                session.setAttribute("artist", artist);
                List<Song> song = artistService.findArtistSong(artistname);
                session.setAttribute("artistSong", song);
                req.getRequestDispatcher("/artists/artistTop.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "用户名或密码错误，请重新登录");
                req.getRequestDispatcher("/message.jsp").forward(req, resp);
            }
    }
    private void artistPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String artistname = req.getParameter("artist_name");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        artistService.artistPassword(password, artistname);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }
    private void register(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String artistname = req.getParameter("artistname");
            String password = req.getParameter("password");


            boolean isExist = false;// 判断是否存在该用户

            if (!artistname.equals("") && !password.equals("")) {
                isExist = artistService.register(artistname, password);
                if (isExist == true) {
                    resp.getWriter().write("该用户已经注册，请直接");
                    resp.getWriter().write("<a href='" + req.getContextPath() + "/admin/userLogin.jsp'>登录</a>");
                    resp.getWriter().write(",或");
                    resp.getWriter().write("<a href='" + req.getContextPath() + "/admin/userRegister.jsp'>重新注册</a>");

                } else {
                    resp.getWriter().write("注册成功!");
                    resp.getWriter().write("1s后跳往登录页");
                    resp.setHeader("Refresh", "1;URL=" + req.getContextPath() + "/admin/top.jsp");
                }
            }
            else {
                resp.getWriter().write("无效的注册，请重新注册！");
                resp.setHeader("Refresh", "1;URL=" + req.getContextPath() + "/admin/userRegister.jsp");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
