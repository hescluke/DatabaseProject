package cn.xh.web.formbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import cn.xh.domain.Song;

//���ﳵ
public class Cart implements Serializable {
	// CartItem�ǹ�����
	private final Map<String, CartItem> itmes = new HashMap<String, CartItem>();
	private int totalQuantity;// ������
	private float totalMoney;// �ܽ��

	public int getTotalQuantity() {
		totalQuantity = 0;
		for (Map.Entry<String, CartItem> me : itmes.entrySet()) {
			totalQuantity += me.getValue().getQuantity();
		}
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalMoney() {
		totalMoney = 0;
		for (Map.Entry<String, CartItem> me : itmes.entrySet()) {
			totalMoney += me.getValue().getMoney();
		}
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Map<String, CartItem> getItmes() {
		return itmes;
	}

	// ���ﳵ�����һ����
	public void addSong(Song song) {
		if (itmes.containsKey(song.getSong_id())) {
			// �ж�Ӧ�Ĺ�����
			CartItem item = itmes.get(song.getSong_id());
			item.setQuantity(item.getQuantity() + 1);
		} else {
			CartItem item = new CartItem(song);
			item.setQuantity(1);
			itmes.put(song.getSong_id(), item);
		}
	}

}
