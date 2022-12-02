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
        String op = req.getParameter("op");// �õ�������������
        // ����Ա��¼
        if (op.equals("login")) {
            login(req, resp);
        }
        // �޸Ĺ���Ա����
        if (op.equals("managerInformation")) {
            managerInformation(req, resp);
        }
        // �޸Ĺ���Ա��¼����
        if (op.equals("managerPassword")) {
            managerPassword(req, resp);
        }
        // ע��
        if (op.equals("layout")) {
            layout(req, resp);
        }
        // ��Ӹ���ǰ�Ȼ�ȡ���з���
        if (op.equals("addSongUI")) {
            addSongUI(req, resp);
        }
        // ��Ӹ���
        if (op.equals("addSong")) {
            try {
                addSong(req, resp);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // ��Ӹ�������
        if (op.equals("addCategory")) {
            addCategory(req, resp);
        }
        // ��ѧ����������б�
        if (op.equals("category")) {
            getCategorySong(req, resp);
        }
        // �༭������Ϣǰ��ȡ��������Ϣ����
        if (op.equals("editSongUI")) {
            editSongUI(req, resp);
        }
        // �༭����
        if (op.equals("editSong")) {
            try {
                editSong(req, resp);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // ɾ������
        if (op.equals("delSong")) {
            delSong(req, resp);
        }
        // ��ȡ���������б�
        if (op.equals("categoryList")) {
            categoryList(req, resp);
        }
        // ��÷�����Ϣ
        if (op.equals("editCategoryUI")) {
            editCategoryUI(req, resp);
        }
        // �޸ĸ���������Ϣ
        if (op.equals("editCategory")) {
            editCategory(req, resp);
        }
        // ɾ����������
        if (op.equals("delCategory")) {
            delCategory(req, resp);
        }
        // �û���Ϣ����
        if (op.equals("findUsers")) {
            findUsers(req, resp);
        }
        // ��ӹ�����Ա
        if (op.equals("addAdmin")) {
            addAdmin(req, resp);
        }
        // �����������
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
            req.setAttribute("message", "��¼�ɹ�");
            session.setAttribute("admin", admin);
            req.getRequestDispatcher("/admin/message.jsp").forward(req, resp);
        } else {
            resp.getWriter()
                    .write("�޴��û�������ϵ����Ա����      <a href='/index.jsp'>������ҳ</a>");
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
//                + "/img/gougou2.1.gif'/>�޸ĳɹ���</div>");
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
            resp.getWriter().write("<font style='color:red;font-size:15px'>����У����������ԣ�</font> <a href=\"managerPassword.jsp\">����</a>");
            //resp.getWriter().write("<strong>����У����������ԣ�</strong> <a href=\"managerPassword.jsp\">����</a>");
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
        // �жϱ��ǲ���multipart/form-data����
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            throw new RuntimeException("���ϴ��������󣡣�");
        }
        // ������������ ������������
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �������������
        ServletFileUpload sfu = new ServletFileUpload(factory);
        List<FileItem> items = new ArrayList<FileItem>();
        items = sfu.parseRequest(req);
        Song song = new Song();
        for (FileItem item : items) {
            if (item.isFormField()) {
                // ��ͨ�ֶΣ������ݷ�װ��song������
                processFormField(item, req, song);
            } else {
                // �ϴ��ֶΣ��ϴ�
                processUplodFiled(item, req, song);
            }
        }
        // �Ѹ�����Ϣ���浽���ݿ���
        service.addSong(song);
        resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                + "/img/gougou2.1.gif'/></div>");
    }

    // �����ļ��ϴ�
    private void processUplodFiled(FileItem item, HttpServletRequest req, Song song) {
        try {
            // ���·������Ҫ����web-inf��
            // 01.��ȡ����ļ�����ʵĿ¼
            String dirImages = getServletContext().getRealPath("/images");
//            String dirImages = getServletContext().getRealPath("/img");
            // 02. ͨ��io���ļ�
            // 03. �����ļ��� ���û��ϴ�ͼƬ�� ͼƬ�������ظ���
            String FieldName = item.getFieldName();// ������nameֵ
            String name = item.getName();
            String fileType = name.substring(name.lastIndexOf(".") + 1);
            String fileName = UUID.randomUUID().toString();// �����ò��ظ����ļ���
            // �����ļ�����
            Date time = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strTime = simpleDateFormat.format(time);
            // path����filename
            String path =  "books";// ��ŵ�song�����е�·��
//            String path = "songs";// ��ŵ�song������
            String filename = name;
            // fileDir��ͼƬ���մ�����fileDir
            File fileDir = new File(dirImages, path + File.separator + filename);
            // InputStream inputStream = item.getInputStream(); ������ ������ ͨ�����ķ�ʽ
            // �� �ϴ����ļ����ص� �ڴ��� ���������
            File parentDir = new File(dirImages, path);// ��Ŀ¼
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

    // ��Fielditem�е����ݷ�װ��song��
    private void processFormField(FileItem item, HttpServletRequest req, Song song) {
        try {
            // itemÿһ��item�������һ�������
            // 01. ��ȡinput������ name ��ֵ ���ݹ淶 ����� ��name��ȡֵ�� �� javabean �е� ������һ��
            String inputName = item.getFieldName();
            String inputValue = item.getString("UTF-8");
            if (inputName.equals("categoryid")) {// ���൥������
                // ��ȡ��ѡ��ķ����id���������idȡ���������Ϣ
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

    // ��Ӹ�������
    private void addCategory(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Category category = new Category();
            BeanUtils.populate(category, req.getParameterMap());
            service.addCategory(category);// ������ӷ���
            resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
                    + "/img/gougou2.1.gif'/></div>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCategorySong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Song> songs = service.getCategorySong(req.getParameter("cid"));// ��ѧ���������
        List<Category> categoryList = service.findAllCategory();// ����
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

    // ͨ������id�ҵ�������Ϣ
    private Song findSongById(String song_id) {
        return service.findSongById(song_id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
