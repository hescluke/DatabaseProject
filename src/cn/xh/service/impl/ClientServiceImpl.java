package cn.xh.service.impl;

import java.util.List;
import java.util.UUID;

import cn.xh.dao.ClientDao;
import cn.xh.dao.impl.ClientDaoImpl;
import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.service.ClientService;

public class ClientServiceImpl implements ClientService {

	private final ClientDao dao = new ClientDaoImpl();

	public User findUserByID(String user_id) { return  dao.findUserByID(user_id);}

	@Override
	public User login(String username, String password) {
		return dao.login(username, password);
	}

	@Override
	public boolean register(String username, String password, String name, String sex, String tel, String address) {
		User user = new User(UUID.randomUUID().toString(), username, password, name, sex, tel, address, "0");
		return dao.register(user);
	}

	public boolean becomeVip(String u_id){ return dao.becomeVip(u_id);}

	@Override
	public List<Song> getCategorySong(String cid) {
		return dao.getCategorySong(cid);
	}

	@Override
	public List<Song> rwsk() {
		return dao.rwsk();
	}

	@Override
	public List<Song> sets() {
		return dao.sets();
	}

	@Override
	public List<Song> jjjr() {
		return dao.jjjr();
	}

	@Override
	public List<Song> kxjs() {
		return dao.kxjs();
	}

	@Override
	public List<Song> jyks() {
		return dao.jyks();
	}

	@Override
	public void personInformation(String username, String name, String sex, String tel, String address) {
		User user = new User(null, username, null, name, sex, tel, address, "0");
		dao.personInformation(user);
	}

	@Override
	public void personPassword(String password, String username) {
		User user = new User(null, username, password, null, null, null, null, "0");
		dao.personPassword(user);
	}

	@Override
	public List<Song> search(String search) {
		return dao.search(search);
	}

	@Override
	public Song findSongById(String song_id) {
		return dao.findSongById(song_id);
	}

	@Override
	public void addfavorite(String user_id, String song_id) {
		dao.addfavorite(UUID.randomUUID().toString(), user_id, song_id);
	}

	@Override
	public List<Favorite> findFavoriteByUserId(User user) {
		return dao.findFavoriteByUserId(user);
	}

	@Override
	public boolean findFavorite(String user_id, String song_id) {
		return dao.findFavorite(user_id, song_id);
	}

	@Override
	public void delFavorite(String song_id, String user_id) {
		dao.delFavorite(song_id, user_id);

	}

}
