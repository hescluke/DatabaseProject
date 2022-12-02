package cn.xh.dao;

import java.util.List;

import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;

public interface ClientDao {

	User findUserByID(String user_id);

	User login(String username, String password);

	boolean register(User user);

	boolean becomeVip(String u_id);

	List<Song> getCategorySong(String cid);

	List<Song> rwsk();

	List<Song> sets();

	List<Song> jjjr();

	List<Song> kxjs();

	List<Song> jyks();

	void personInformation(User user);

	void personPassword(User user);

	List<Song> search(String search);

	Song findSongById(String song_id);

	void addfavorite(String string, String user_id, String song_id);

	List<Favorite> findFavoriteByUserId(User user);

	boolean findFavorite(String user_id, String song_id);

	void delFavorite(String song_id, String user_id);

}
