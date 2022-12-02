package cn.xh.domain;

import java.io.Serializable;

/*
 * ����ʵ����
 */
public class Song implements Serializable {
	private String song_id;// ����id
	private String song_name;// ��������
	private String song_artist;// �ݳ���
	private String song_press;// ������
	private Category category;// ��������
	private String filename; // ͼƬ������
	private String path; // ·��
	private String song_desc;// ���
	private double song_price;// �����۸�
	private int song_kunumber;// ���
	private int song_xiaonumber;// ����

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(String song_id, String song_name, String song_artist, String song_press, Category category,
			String filename, String path, String song_desc, double song_price, int song_kunumber, int song_xiaonumber) {
		super();
		this.song_id = song_id;
		this.song_name = song_name;
		this.song_artist = song_artist;
		this.song_press = song_press;
		this.category = category;
		this.filename = filename;
		this.path = path;
		this.song_desc = song_desc;
		this.song_price = song_price;
		this.song_kunumber = song_kunumber;
		this.song_xiaonumber = song_xiaonumber;
	}

	public String getSong_id() {
		return song_id;
	}

	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getSong_artist() {
		return song_artist;
	}

	public void setSong_artist(String song_artist) {
		this.song_artist = song_artist;
	}

	public String getSong_press() {
		return song_press;
	}

	public void setSong_press(String song_press) {
		this.song_press = song_press;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSong_desc() {
		return song_desc;
	}

	public void setSong_desc(String song_desc) {
		this.song_desc = song_desc;
	}

	public double getSong_price() {
		return song_price;
	}

	public void setSong_price(double song_price) {
		this.song_price = song_price;
	}

	public int getSong_kunumber() {
		return song_kunumber;
	}

	public void setSong_kunumber(int song_kunumber) {
		this.song_kunumber = song_kunumber;
	}

	public int getSong_xiaonumber() {
		return song_xiaonumber;
	}

	public void setSong_xiaonumber(int song_xiaonumber) {
		this.song_xiaonumber = song_xiaonumber;
	}

	@Override
	public String toString() {
		return "Song [song_id=" + song_id + ", song_name=" + song_name + ", song_artist=" + song_artist
				+ ", song_press=" + song_press + ", category=" + category + ", filename=" + filename + ", path=" + path
				+ ", song_desc=" + song_desc + ", song_price=" + song_price + ", song_kunumber=" + song_kunumber
				+ ", song_xiaonumber=" + song_xiaonumber + "]";
	}

}
