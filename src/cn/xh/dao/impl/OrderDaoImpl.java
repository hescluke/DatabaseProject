package cn.xh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Or;

import cn.xh.dao.OrdetrDao;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.Order;
import cn.xh.domain.Orderitem;
import cn.xh.domain.User;
import cn.xh.util.JDBCUtil;
import sun.net.www.content.text.plain;

public class OrderDaoImpl implements OrdetrDao {

	Connection connection = JDBCUtil.getConnection();

	@Override
	public void save(Order o) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(
					"insert into orders (ordernum,quantity,money,time,status,userId) values (?,?,?,?,?,?)");
			prepareStatement.setString(1, o.getOrdernum());
			prepareStatement.setInt(2, o.getQuantity());
			prepareStatement.setDouble(3, o.getMoney());
			prepareStatement.setString(4, o.getTime());
			prepareStatement.setInt(5, o.getStatus());
			prepareStatement.setString(6, o.getUser().getId());
			prepareStatement.executeUpdate();

			// 保存订单项信息
			List<Orderitem> items = o.getItems();
			for (Orderitem item : items) {
				PreparedStatement preparedStatement2 = connection.prepareStatement(
						"insert into orderitems (id,quantity,price,song_id,ordernum) values(?,?,?,?,?)");
				preparedStatement2.setString(1, item.getId());
				preparedStatement2.setInt(2, item.getQuantity());
				preparedStatement2.setDouble(3, item.getPrice());
				preparedStatement2.setString(4, item.getSong().getSong_id());
				preparedStatement2.setString(5, item.getOrdernum());
				preparedStatement2.executeUpdate();
				addXiaonumber(item.getSong().getSong_id());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addXiaonumber(String song_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"update songdb set song_xiaonumber = song_xiaonumber+1,song_kunumber=song_kunumber-1 where song_id=?");
			prepareStatement.setString(1, song_id);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Order findOrderByNum(String ordernum) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from orders where ordernum=?");
			prepareStatement.setString(1, ordernum);
			ResultSet rs = prepareStatement.executeQuery();
			Order order = new Order();
			if (rs.next()) {
				order.setOrdernum(rs.getString("ordernum"));
				order.setQuantity(rs.getInt("quantity"));
				order.setMoney(rs.getDouble("money"));
				order.setTime(rs.getString("time"));
				order.setStatus(rs.getInt("status"));
				User user = findUserByid(rs.getString("userId"));
				order.setUser(user);
				List<Orderitem> items = finOrdersItemsByNum(rs.getString("ordernum"));
				order.setItems(items);
			}
			return order;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	// 根据订单id找到订单项
	private List<Orderitem> finOrdersItemsByNum(String ordernum) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from orderitems where ordernum=?");
			prepareStatement.setString(1, ordernum);
			ResultSet rs = prepareStatement.executeQuery();
			List<Orderitem> list = new ArrayList<Orderitem>();
			while (rs.next()) {
				Orderitem item = new Orderitem();
				item.setOrdernum(ordernum);
				Song song = findSongById(rs.getString("song_id"));
				item.setSong(song);
				item.setId(rs.getString("id"));
				item.setPrice(rs.getDouble("price"));
				item.setQuantity(rs.getInt("quantity"));
				list.add(item);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	// 根据歌曲id找到歌曲信息
	private Song findSongById(String song_id) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from songdb where song_id=?");
			prepareStatement.setString(1, song_id);
			ResultSet rs = prepareStatement.executeQuery();
			Song song = new Song();
			if (rs.next()) {
				song.setSong_id(rs.getString("song_id"));
				song.setSong_name(rs.getString("song_name"));
				song.setSong_artist(rs.getString("song_artist"));
				song.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				song.setCategory(category);
				song.setFilename(rs.getString("filename"));
				song.setPath(rs.getString("path"));
				song.setSong_desc(rs.getString("song_desc"));
				song.setSong_kunumber(rs.getInt("song_kunumber"));
				song.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				song.setSong_price(rs.getDouble("song_price"));
				return song;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	// 根据歌曲分类的id找到歌曲分类信息
	private Category findCategoryById(String category_id) {
		Category category = new Category();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from category where category_id=?");
			preparedStatement.setString(1, category_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				category.setCategory_id(resultSet.getString("category_id"));
				category.setCategory_name(resultSet.getString("category_name"));
				category.setCategory_desc(resultSet.getString("category_desc"));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	// 根据id找到用户信息
	private User findUserByid(String user_id) {
		try {
			User user = new User();
			PreparedStatement prepareStatement = connection.prepareStatement("select * from user where user_id=?");
			prepareStatement.setString(1, user_id);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {
				user.setId(rs.getString("user_id"));
				user.setUsername(rs.getString("user_username"));
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	@Override
	public List<Order> findOrdersByUser(String userId) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from orders where userId = ? order by ordernum desc");
			prepareStatement.setString(1, userId);
			ResultSet rs = prepareStatement.executeQuery();
			List<Order> list = new ArrayList<Order>();
			while (rs.next()) {
				Order order = new Order();
				List<Orderitem> items = finOrdersItemsByNum(rs.getString("ordernum"));
				order.setItems(items);
				order.setMoney(rs.getDouble("money"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getInt("status"));
				order.setTime(rs.getString("time"));
				order.setUser(findUserByid(userId));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	@Override
	public List<Order> findOrders() {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from orders order by ordernum desc");
			ResultSet rs = prepareStatement.executeQuery();
			List<Order> list = new ArrayList<Order>();
			while (rs.next()) {
				Order order = new Order();
				List<Orderitem> items = finOrdersItemsByNum(rs.getString("ordernum"));
				order.setItems(items);
				order.setMoney(rs.getDouble("money"));
				order.setOrdernum(rs.getString("ordernum"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getInt("status"));
				order.setTime(rs.getString("time"));
				order.setUser(findUserByid(rs.getString("userId")));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("");
	}

	@Override
	public void faHuo(String ordernum) {
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("update orders set status = 1 where ordernum=?");
			prepareStatement.setString(1, ordernum);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
