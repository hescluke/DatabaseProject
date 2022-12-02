package cn.xh.service.impl;

import cn.xh.dao.ManagerDao;
import cn.xh.dao.impl.ManagerDaoImpl;
import cn.xh.domain.Administrator;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.User;
import cn.xh.service.ManagerService;

import java.util.List;
import java.util.UUID;

public class ManagerServiceImpl implements ManagerService {
	private final ManagerDao dao = new ManagerDaoImpl();

	@Override
	public Administrator login(String username, String password) {
		return dao.login(username, password);
	}

	@Override
	public void managerInformation(String username, String name, String sex, String tel) {
		Administrator admin = new Administrator(username, null, name, sex, tel);
		dao.managerInformation(admin);
	}

	@Override
	public void managerPassword(String username, String password) {
		Administrator admin = new Administrator(username, password, null, null, null);
		dao.managerPassword(admin);
	}

	@Override
	public List<Category> findAllCategory() {
		return dao.findAllCategory();
	}

	@Override
	public Category findCategoryById(String categoryid) {
		return dao.findCategoryById(categoryid);
	}

	@Override
	public void addSong(Song song) {
		song.setSong_id(UUID.randomUUID().toString());
		dao.addSong(song);

	}

	@Override
	public void addCategory(Category category) {
		category.setCategory_id(UUID.randomUUID().toString());
		dao.addCategory(category);
	}

	@Override
	public List<Song> getCategorySong(String cid) {
		return dao.getCategorySong(cid);
	}



	@Override
	public Song findSongById(String song_id) {
		return dao.findSongById(song_id);
	}

	@Override
	public void delSong(String song_id) {
		dao.delSong(song_id);
	}

	@Override
	public void editSong(String song_id, String song_name, String song_artist, String song_press, String song_desc,
			double song_price, String song_kunumber) {
		dao.editSong(song_id, song_name, song_artist, song_press, song_desc, song_price, song_kunumber);
	}

	@Override
	public void editCategory(Category category) {
		dao.editCategory(category);
	}

	@Override
	public void delCategory(String category_id) {
		dao.delCategory(category_id);
	}

	@Override
	public List<User> findUsers() {
		return dao.findUsers();
	}

	@Override
	public void addAdmin(Administrator admin) {
		dao.addAdmin(admin);
	}

	@Override
	public List<Song> sales() {
		return dao.sales();
	}

}
