package cn.xh.dao;

import java.util.List;

import cn.xh.domain.Administrator;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.User;

public interface ManagerDao {

	Administrator login(String username, String password);

	void managerInformation(Administrator admin);

	void managerPassword(Administrator admin);

	List<Category> findAllCategory();

	Category findCategoryById(String categoryid);

	void addSong(Song song);

	void addCategory(Category category);

	List<Song> getCategorySong(String cid);


	Song findSongById(String song_id);

	void delSong(String song_id);

	void editSong(String song_id, String song_name, String song_artist, String song_press, String song_desc,
			double song_price, String song_kunumber);

	void editCategory(Category category);

	void delCategory(String category_id);

	List<User> findUsers();

	void addAdmin(Administrator admin);

	List<Song> sales();

}
