package cn.xh.web.controller;

import cn.xh.domain.Administrator;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.User;
import cn.xh.service.ManagerService;
import cn.xh.service.impl.ManagerServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/admin/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    private final ManagerService service = new ManagerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String op = req.getParameter("op");// 得到传过来的请求
        // 管理员登录
        if (op.equals("login")) {
            login(req, resp);
        }
        // 修改管理员资料
        if (op.equals("managerInformation")) {
            managerInformation(req, resp);
        }
        // 修改管理员登录密码
        if (op.equals("managerPassword")) {
            managerPassword(req, resp);
        }
        // 注销
        if (op.equals("layout")) {
            layout(req, resp);
        }
        // 添加歌曲前先获取所有分类
        if (op.equals("addSongUI")) {
            addSongUI(req, resp);
        }
        // 添加歌曲
        if (op.equals("addSong")) {
            try {
                addSong(req, resp);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 添加歌曲分类
        if (op.equals("addCategory")) {
            addCategory(req, resp);
        }
        // 文学艺术类歌曲列表
        if (op.equals("category")) {
            getCategorySong(req, resp);
        }
        // 编辑歌曲信息前获取歌曲的信息回显
        if (op.equals("editSongUI")) {
            editSongUI(req, resp);
        }
        // 编辑歌曲
        if (op.equals("editSong")) {
            try {
                editSong(req, resp);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 删除歌曲
        if (op.equals("delSong")) {
            delSong(req, resp);
        }
        // 获取歌曲分类列表
        if (op.equals("categoryList")) {
            categoryList(req, resp);
        }
        // 获得分类信息
        if (op.equals("editCategoryUI")) {
            editCategoryUI(req, resp);
        }
        // 修改歌曲分类信息
        if (op.equals("editCategory")) {
            editCategory(req, resp);
        }
        // 删除歌曲分类
        if (op.equals("delCategory")) {
            delCategory(req, resp);
        }
        // 用户信息管理
        if (op.equals("findUsers")) {
            findUsers(req, resp);
        }
        // 添加工作人员
        if (op.equals("addAdmin")) {
            addAdmin(req, resp);
        }
        // 歌曲销售情况
        if (op.equals("sales")) {
            sales(req, resp);
        }
    }

    private void sales(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> sales = service.sales();
        req.setAttribute("sales", sales);
        req.getRequestDispatcher("/admin/sales.jsp").forward(req, resp);
    }

    private void addAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");
        Administrator admin = new Administrator(username, password, name, sex, tel);
        service.addAdmin(admin);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    private void findUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> list = service.findUsers();
        HttpSession session = req.getSession();
        session.setAttribute("users", list);
        req.getRequestDispatcher("/admin/managerUsers.jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Administrator admin = service.login(username, password);
        if (admin.getUsername() != null && admin.getUsername() != "") {
            req.setAttribute("message", "登录成功");
            session.setAttribute("admin", admin);
            req.getRequestDispatcher("/admin/message.jsp").forward(req, resp);
        } else {
            resp.getWriter()
                    .write("无此用户，请联系管理员！！      <a href='/index.jsp'>返回首页</a>");
        }

    }

    private void managerInformation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String tel = req.getParameter("tel");
        service.managerInformation(username, name, sex, tel);
        HttpSession session = req.getSession();
        Administrator admin = (Administrator) session.getAttribute("admin");
        admin.setName(name);
        admin.setSex(sex);
        admin.setTel(tel);
        session.setAttribute("admin", admin);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");

    }

//    private void managerPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String repassword = req.getParameter("repassword");

//        service.managerPassword(username, password);
//        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
//                + "/img/gougou2.1.gif'/>修改成功！</div>");
//    }


    private void managerPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        if(Objects.equals(password, repassword) && !password.equals("")){
            service.managerPassword(username, password);
            resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                    + "/img/gougou2.1.gif'/></div>");
            resp.setHeader("Refresh","3; URL = managerPassword.jsp");
        }
        else {
            resp.getWriter().write("<font style='color:red;font-size:15px'>密码校验错误，请重试！</font> <a href=\"managerPassword.jsp\">返回</a>");
            //resp.getWriter().write("<strong>密码校验错误，请重试！</strong> <a href=\"managerPassword.jsp\">返回</a>");
        }

    }









    private void layout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("admin");
            resp.sendRedirect("/client/ClientServlet?op=category");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addSongUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categorys = service.findAllCategory();
        req.setAttribute("cs", categorys);
        req.getRequestDispatcher("/admin/addSong.jsp").forward(req, resp);

    }

    private void addSong(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        // 判断表单是不是multipart/form-data类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            throw new RuntimeException("表单上传类型有误！！");
        }
        // 创建工厂用来 解析请求内容
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 构建核心类对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<FileItem>();
        items = sfu.parseRequest(req);
        Song song = new Song();
        for (FileItem item : items) {
            if (item.isFormField()) {
                // 普通字段：把数据分装到song对象中
                processFormField(item, req, song);
            } else {
                // 上传字段：上传
                processUplodFiled(item, req, song);
            }
        }
        // 把歌曲信息保存到数据库中
        service.addSong(song);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    // 处理文件上传
    private void processUplodFiled(FileItem item, HttpServletRequest req, Song song) {
        try {
            // 存放路径：不要放在web-inf中
            // 01.获取存放文件的真实目录
            String dirImages = getServletContext().getRealPath("/images");
//            String dirImages = getServletContext().getRealPath("/img");
            // 02. 通过io存文件
            // 03. 生成文件名 （用户上传图片， 图片名可能重复）
            String FieldName = item.getFieldName();// 输入框的name值
            String name = item.getName();
            String fileType = name.substring(name.lastIndexOf(".") + 1);
            String fileName = UUID.randomUUID().toString();// 生成用不重复的文件名
            // 生成文件夹名
            Date time = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strTime = simpleDateFormat.format(time);
            // path属性filename
            String path =  "books";// 存放到song对象中的路径
//            String path = "songs";// 存放到song对象中
            String filename = name;
            // fileDir：图片最终存在于fileDir
            File fileDir = new File(dirImages, path + File.separator + filename);
            // InputStream inputStream = item.getInputStream(); 从请求 对象中 通过流的方式
            // 把 上传的文件加载到 内存中 构建输出流
            File parentDir = new File(dirImages, path);// 父目录
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }
            song.setFilename(filename);
            song.setPath(path);

            InputStream inputStream = item.getInputStream();
            FileOutputStream fos = new FileOutputStream(fileDir);
            int len = 0;
            while ((len = inputStream.read()) != -1) {
                fos.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 把Fielditem中的数据封装到song中
    private void processFormField(FileItem item, HttpServletRequest req, Song song) {
        try {
            // item每一个item对象代表一个输入框
            // 01. 获取input输入框的 name 的值 根据规范 输入框 的name的取值， 与 javabean 中的 属性名一致
            String inputName = item.getFieldName();
            String inputValue = item.getString("UTF-8");
            if (inputName.equals("categoryid")) {// 分类单独处理
                // 获取到选择的分类的id，根据这个id取到分类的信息
                String categoryid = item.getString();
                System.out.println("categoryid=" + categoryid);
                Category category = service.findCategoryById(categoryid);
                System.out.println(category);
                song.setCategory(category);
            } else {
                BeanUtils.setProperty(song, inputName, inputValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加歌曲分类
    private void addCategory(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());
            service.addCategory(category);// 调用添加方法
            resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                    + "/img/gougou2.1.gif'/></div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCategorySong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> songs = service.getCategorySong(req.getParameter("cid"));// 文学艺术类歌曲
        List<Category> categoryList = service.findAllCategory();// 分类
        req.setAttribute("songs", songs);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/admin/songsList.jsp").forward(req, resp);
    }

    private void editSongUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String song_id = req.getParameter("song_id");
        Song song = findSongById(song_id);
        List<Category> category = service.findAllCategory();
        HashMap map = new HashMap();
        map.put("song", song);
        map.put("category", category);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/admin/editSong.jsp").forward(req, resp);
    }

    private void editSong(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        String song_id = req.getParameter("song_id");
        String song_name = req.getParameter("song_name");
        String song_artist = req.getParameter("song_artist");
        String song_press = req.getParameter("song_press");
        String song_desc = req.getParameter("song_desc");
        double song_price = Double.parseDouble(req.getParameter("song_price"));
        String song_kunumber = req.getParameter("song_kunumber");
        service.editSong(song_id, song_name, song_artist, song_press, song_desc, song_price, song_kunumber);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    private void delSong(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String song_id = req.getParameter("song_id");
        service.delSong(song_id);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    private void categoryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = service.findAllCategory();
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/admin/categorysList.jsp").forward(req, resp);
    }

    private void editCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = service.findCategoryById(req.getParameter("category_id"));
        req.setAttribute("category", category);
        req.getRequestDispatcher("/admin/editCategory.jsp").forward(req, resp);
    }

    private void editCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Category category = new Category(req.getParameter("category_id"), req.getParameter("category_name"),
                req.getParameter("category_desc"));
        service.editCategory(category);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    private void delCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String category_id = req.getParameter("category_id");
        service.delCategory(category_id);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    // 通过歌曲id找到歌曲信息
    private Song findSongById(String song_id) {
        return service.findSongById(song_id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
