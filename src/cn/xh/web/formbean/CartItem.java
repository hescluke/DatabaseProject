package cn.xh.web.formbean;

import java.io.Serializable;

import cn.xh.domain.Song;

//������
public class CartItem implements Serializable {
	private final Song song;
	private int quantity;// ��������
	private float money;// ����С��

	public CartItem(Song song) {
		this.song = song;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getMoney() {
		return song.getSong_price() * quantity;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public Song getSong() {
		return song;
	}

}
