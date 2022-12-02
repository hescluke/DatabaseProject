package cn.xh.domain;

import java.io.Serializable;

//�ղؼ�
public class Favorite implements Serializable {
	private String favorite_id;// �ղ���id
	private User user;// �û���Ϣ
	private Song song;// ������Ϣ

	public Favorite() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Favorite(String favorite_id, User user, Song song) {
		super();
		this.favorite_id = favorite_id;
		this.user = user;
		this.song = song;
	}

	public String getFavorite_id() {
		return favorite_id;
	}

	public void setFavorite_id(String favorite_id) {
		this.favorite_id = favorite_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	@Override
	public String toString() {
		return "Favorite [favorite_id=" + favorite_id + ", user=" + user + ", song=" + song + "]";
	}

}
