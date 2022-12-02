package cn.xh.service;

import java.util.List;

import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;

public interface ClientService {

	User findUserByID(String user_id);
	User login(String username, String password);

	boolean register(String username, String password, String name, String sex, String tel, String address);

	boolean becomeVip(String u_id);

	List<Song> getCategorySong(String cid);

	List<Song> rwsk();

	List<Song> sets();

	List<Song> jjjr();

	List<Song> kxjs();

	List<Song> jyks();

	void personInformation(String username, String name, String sex, String tel, String address);

	void personPassword(String password, String username);

	List<Song> search(String search);

	Song findSongById(String song_id);

	void addfavorite(String user_id, String song_id);

	List<Favorite> findFavoriteByUserId(User user);

	boolean findFavorite(String user_id, String song_id);

	void delFavorite(String song_id, String user_id);


}
