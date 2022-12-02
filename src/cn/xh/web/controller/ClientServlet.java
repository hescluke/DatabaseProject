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
import cn.xh.domain.User;
import cn.xh.service.ClientService;
import cn.xh.service.ManagerService;
import cn.xh.service.impl.ClientServiceImpl;
import cn.xh.service.impl.ManagerServiceImpl;
import cn.xh.web.formbean.Cart;
import cn.xh.web.formbean.CartItem;

@WebServlet("/client/ClientServlet")
public class ClientServlet extends HttpServlet {
	private final ClientService service = new ClientServiceImpl();
	private final ManagerService managerService = new ManagerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String op = req.getParameter("op");// �õ�������������
		if (op != null && !op.equals("")) {
			if(op.equals("findUserByID")){
				findUserByID(req,resp);
			}
			// ��¼
			if (op.equals("login")) {
				login(req, resp);
			}
			// ע��
			if (op.equals("layout")) {
				layout(req, resp);
			}
			// ע��
			if (op.equals("register")) {
				register(req, resp);
			}
			if(op.equals("becomeVip")){
				becomeVip(req, resp);
			}
			// ��ѧ����������б�
			if (op.equals("category")) {
				getCategorySong(req, resp);
			}

			// ������Ϣ�޸�
			if (op.equals("personInformation")) {
				personInformation(req, resp);
			}
			// �޸�����
			if (op.equals("personPassword")) {
				personPassword(req, resp);
			}
			// ������
			if (op.equals("search")) {
				search(req, resp);
			}
			// ����ҳ��
			if (op.equals("particulars")) {
				particulars(req, resp);
			}
			// ��ӹ��ﳵ
			if (op.equals("addCart")) {
				addCart(req, resp);
			}
			// ɾ�����ﳵ�еĹ�����
			if (op.equals("delItem")) {
				delItem(req, resp);
			}
			// �޸Ĺ���������
			if (op.equals("changeNum")) {
				changeNum(req, resp);
			}
			// ����ղؼ�
			if (op.equals("addfavorite")) {
				addfavorite(req, resp);
			}
			// ��ʾ�ղؼ�
			if (op.equals("showfavorite")) {
				showfavorite(req, resp);
			}
			// ɾ���ղؼ�
			if (op.equals("delFavorite")) {
				delFavorite(req, resp);
			}
			// ɾ���ղؼ�
			if (op.equals("buyNow")) {
				buNow(req, resp);
			}

		}
	}

	private void delFavorite(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String song_id = req.getParameter("song_id");
		String user_id = req.getParameter("user_id");
		User user = (User) session.getAttribute("user");
		String user_id2 = user.getId();
		service.delFavorite(song_id, user_id);

		List<Favorite> lists = (List<Favorite>) session.getAttribute("favorite");
		Iterator<Favorite> iterator = lists.iterator();
		while (iterator.hasNext()) {
			Favorite favorite = iterator.next();
			if (song_id.equals(favorite.getSong().getSong_id())) {
				iterator.remove();// ʹ�õ�������ɾ������ɾ��
			}
		}
		resp.sendRedirect(req.getContextPath() + "/favorite.jsp");
//		resp.sendRedirect(req.getContextPath() + "/play.jsp");
	}

	private void showfavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		List<Favorite> favorites = service.findFavoriteByUserId(user);
		session.setAttribute("favorite", favorites);
		req.getRequestDispatcher("/favorite.jsp").forward(req, resp);
//		req.getRequestDispatcher("/play.jsp").forward(req, resp);
	}





	private void addfavorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String user_id = user.getId();
		String song_id = req.getParameter("song_id");
		boolean isExit = service.findFavorite(user_id, song_id);
		if (isExit == false) {
			service.addfavorite(user_id, song_id);
			List<Favorite> favorites = service.findFavoriteByUserId(user);
			session.setAttribute("favorite", favorites);
			req.getRequestDispatcher("/play.jsp").forward(req, resp);
		}
	}

	private void changeNum(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String num = req.getParameter("num");
		String song_id = req.getParameter("song_id");
		// ȡ�����ﳵ
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		CartItem item = cart.getItmes().get(song_id);
		item.setQuantity(Integer.parseInt(num));
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");

	}


	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		User user = service.login(username, password);
		if (user.getUsername() != null && user.getUsername() != "") {
			req.setAttribute("message", "��¼�ɹ�");
			session.setAttribute("user", user);
			List<Favorite> favorites = service.findFavoriteByUserId(user);
			session.setAttribute("favorite", favorites);
//			req.getRequestDispatcher("/play.jsp").forward(req, resp);
			req.getRequestDispatcher("/admin/top.jsp").forward(req, resp);
		} else {
			req.setAttribute("message", "�û�����������������µ�¼");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}

	private void layout(HttpServletRequest req, HttpServletResponse resp) {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("user");// ��ȡsession���󣬴�session���Ƴ���¼��Ϣ
			resp.sendRedirect("/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void findUserByID(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String user_id = req.getParameter("user_id");
		User user = service.findUserByID(user_id);
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		req.getRequestDispatcher("/admin/top.jsp").forward(req, resp);
	}

	public void becomeVip(HttpServletRequest req, HttpServletResponse resp){
		try{
			String u_id = req.getParameter("u_id");
			User user = service.findUserByID(u_id);
			boolean suc = service.becomeVip(u_id);
			HttpSession session = req.getSession();
			session.setAttribute("user", user);

			if(suc){
				resp.getWriter().write("�ɹ�!");
				resp.getWriter().write("1s��������������");
//				req.getRequestDispatcher(req.getContextPath() + "/personalCenter.jsp").forward(req, resp);
				resp.setHeader("Refresh", "1;URL=" + req.getContextPath() + "/personalCenter.jsp");
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void register(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			String tel = req.getParameter("tel");
			String address = req.getParameter("address");

			boolean isExist = false;// �ж��Ƿ���ڸ��û�

			if (!username.equals("") && !password.equals("")) {
				isExist = service.register(username, password, name, sex, tel, address);
				if (isExist == true) {
					resp.getWriter().write("���û��Ѿ�ע�ᣬ��ֱ��");
					resp.getWriter().write("<a href='" + req.getContextPath() + "/admin/userLogin.jsp'>��¼</a>");
					resp.getWriter().write(",��");
					resp.getWriter().write("<a href='" + req.getContextPath() + "/admin/userRegister.jsp'>����ע��</a>");

				} else {
					resp.getWriter().write("ע��ɹ�!");
					resp.getWriter().write("1s��������¼ҳ");
					resp.setHeader("Refresh", "1;URL=" + req.getContextPath() + "/admin/top.jsp");
				}
			}
			else {
				resp.getWriter().write("��Ч��ע�ᣬ������ע�ᣡ");
				resp.setHeader("Refresh", "1;URL=" + req.getContextPath() + "/admin/userRegister.jsp");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCategorySong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Song> songs = service.getCategorySong(req.getParameter("cid"));// ��ѧ���������
		req.setAttribute("songs", songs);
		List<Category> categoryList= managerService.findAllCategory();
		req.setAttribute("categoryList", categoryList);
		req.getRequestDispatcher("/showSong.jsp").forward(req, resp);
	}

	private void personInformation(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String tel = req.getParameter("tel");
		String address = req.getParameter("address");

		service.personInformation(username, name, sex, tel, address);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/gougou2.1.gif'/>/div>");
	}

	private void personPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

		service.personPassword(password, username);
		resp.getWriter().write("<div style='text-align: center;margin-top: 260px'><img src='" + req.getContextPath()
				+ "/img/gougou2.1.gif'/></div>");
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("search");
		List<Song> searchmessage = service.search(search);
		req.setAttribute("songs", searchmessage);
		req.getRequestDispatcher("/showSong.jsp").forward(req, resp);
	}

	private void particulars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String song_id = req.getParameter("song_id");
		Song song = findSongById(song_id);
		req.setAttribute("song", song);
		req.getRequestDispatcher("/particulars.jsp").forward(req, resp);
	}

	// ͨ������id�ҵ�������Ϣ
	private Song findSongById(String song_id) {
		Song song = service.findSongById(song_id);
		return song;
	}

	private void addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String song_id = req.getParameter("song_id");
		Song song = findSongById(song_id);

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addSong(song);
	}

	private void delItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String song_id = req.getParameter("song_id");
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		cart.getItmes().remove(song_id);
		resp.sendRedirect(req.getContextPath() + "/showCart.jsp");
	}

	private void buNow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String song_id = req.getParameter("song_id");
		Song song = findSongById(song_id);
		HttpSession session = req.getSession();
		Cart cart = new Cart();
		session.setAttribute("buyNowSong", cart);
		cart.addSong(song);
		resp.sendRedirect(req.getContextPath() + "/buyNow.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
