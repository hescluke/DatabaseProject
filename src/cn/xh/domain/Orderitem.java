package cn.xh.domain;

import java.io.Serializable;

//������
public class Orderitem implements Serializable {
	private String id;// ������id
	private int quantity;// �����������
	private double price;// ������С��
	private Song song;// ������Ϣ
	private String ordernum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public Orderitem(String id, int quantity, double price, Song song, String ordernum) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.song = song;
		this.ordernum = ordernum;
	}

	@Override
	public String toString() {
		return "Orderitem [id=" + id + ", quantity=" + quantity + ", price=" + price + ", song=" + song + ", ordernum="
				+ ordernum + "]";
	}

	public Orderitem() {
		super();
		// TODO Auto-generated constructor stub
	}

}
