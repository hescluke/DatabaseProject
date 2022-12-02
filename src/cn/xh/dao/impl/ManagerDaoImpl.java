package cn.xh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion.EPR;

import cn.xh.dao.ManagerDao;
import cn.xh.domain.Administrator;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.User;
import cn.xh.util.JDBCUtil;

public class ManagerDaoImpl implements ManagerDao {

	// 管理员登录
	@Override
	public Administrator login(String username, String password) {
		Administrator admin = new Administrator();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from administrator where admin_username=? and admin_password=?");
			prepareStatement.setString(1, username);
			prepareStatement.setString(2, password);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {
				admin.setUsername(rs.getString("admin_username"));
				admin.setPassword(rs.getString("admin_password"));
				admin.setName(rs.getString("admin_name"));
				admin.setSex(rs.getString("admin_sex"));
				admin.setTel(rs.getString("admin_tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	// 管理员信息修改
	@Override
	public void managerInformation(Administrator admin) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"update administrator set admin_name=?, admin_sex=?, admin_tel=? where admin_username=? ");
			prepareStatement.setString(1, admin.getName());
			prepareStatement.setString(2, admin.getSex());
			prepareStatement.setString(3, admin.getTel());
			prepareStatement.setString(4, admin.getUsername());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 管理员登录密码修改
	@Override
	public void managerPassword(Administrator admin) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("update administrator set admin_password=? where admin_username=?");
			prepareStatement.setString(1, admin.getPassword());
			prepareStatement.setString(2, admin.getUsername());
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获得所有歌曲分类信息
	@Override
	public List<Category> findAllCategory() {
		List<Category> list = new ArrayList<Category>();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategory_id(rs.getString("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_desc(rs.getString("category_desc"));
				list.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 通过分类id找到分类信息
	@Override
	public Category findCategoryById(String categoryid) {
		Category category = new Category();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from category where category_id = ?");
			prepareStatement.setString(1, categoryid);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {

				category.setCategory_id(rs.getString("category_id"));
				category.setCategory_name(rs.getString("category_name"));
				category.setCategory_desc(rs.getString("category_desc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	// 添加图书
	@Override
	public void addSong(Song song) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into songdb values(?,?,?,?,?,?,?,?,?,?,?)");
			prepareStatement.setString(1, song.getSong_id());
			prepareStatement.setString(2, song.getSong_name());
			prepareStatement.setString(3, song.getSong_artist());
			prepareStatement.setString(4, song.getSong_press());
			prepareStatement.setString(5, song.getCategory().getCategory_id());
			prepareStatement.setString(6, song.getFilename());
			prepareStatement.setString(7, song.getPath());
			prepareStatement.setString(8, song.getSong_desc());
			prepareStatement.setDouble(9, song.getSong_price());
			prepareStatement.setInt(10, song.getSong_kunumber());
			prepareStatement.setInt(11, 0);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加歌曲分类
	@Override
	public void addCategory(Category category) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("insert into category values(?,?,?)");
			prepareStatement.setString(1, category.getCategory_id());
			prepareStatement.setString(2, category.getCategory_name());
			prepareStatement.setString(3, category.getCategory_desc());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//分类歌曲
	@Override
	public List<Song> getCategorySong(String cid) {
		ClientDaoImpl clientDaoImpl = new ClientDaoImpl();
		return clientDaoImpl.getCategorySong(cid);
	}


	// 根据歌曲id找到歌曲信息
	@Override
	public Song findSongById(String song_id) {
		Song song = new Song();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select * from songdb where song_id = ?");
			prepareStatement.setString(1, song_id);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {
				song.setSong_id(song_id);
				song.setSong_name(rs.getString("song_name"));
				song.setSong_artist(rs.getString("song_artist"));
				song.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				song.setCategory(category);
				song.setFilename(rs.getString("filename"));
				song.setPath(rs.getString("path"));
				song.setSong_desc(rs.getString("song_desc"));
				song.setSong_price(rs.getDouble("song_price"));
				song.setSong_kunumber(rs.getInt("song_kunumber"));
				song.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return song;
	}

	// 删除歌曲
	@Override
	public void delSong(String song_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(" delete from songdb where song_id=?");
			prepareStatement.setString(1, song_id);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editSong(String song_id, String song_name, String song_artist, String song_press, String song_desc,
			double song_price, String song_kunumber) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"update songdb set song_name=?,song_artist=?,song_press=?,song_desc=?,song_price=?,song_kunumber=? where song_id=?");
			prepareStatement.setString(1, song_name);
			prepareStatement.setString(2, song_artist);
			prepareStatement.setString(3, song_press);
			prepareStatement.setString(4, song_desc);
			prepareStatement.setDouble(5, song_price);
			prepareStatement.setString(6, song_kunumber);
			prepareStatement.setString(7, song_id);
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void editCategory(Category category) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("update category set category_name=?,category_desc=? where category_id=?");
			prepareStatement.setString(1, category.getCategory_name());
			prepareStatement.setString(2, category.getCategory_desc());
			prepareStatement.setString(3, category.getCategory_id());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delCategory(String category_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("delete from category where category_id=?");
			prepareStatement.setString(1, category_id);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<User> findUsers() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select * from user");
			ResultSet rs = prepareStatement.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setAddress(rs.getString("user_address"));
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setSex(rs.getString("user_sex"));
				user.setTel(rs.getString("user_tel"));
				user.setUsername(rs.getString("user_username"));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

	// 添加管理人员
	@Override
	public void addAdmin(Administrator admin) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into administrator values(?,?,?,?,?)");
			prepareStatement.setString(1, admin.getUsername());
			prepareStatement.setString(2, admin.getPassword());
			prepareStatement.setString(3, admin.getName());
			prepareStatement.setString(4, admin.getSex());
			prepareStatement.setString(5, admin.getTel());

			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 歌曲销售情况
	@Override
	public List<Song> sales() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from songdb where song_xiaonumber>0 order by song_xiaonumber desc");
			ResultSet rs = prepareStatement.executeQuery();
			List<Song> songs = new ArrayList<Song>();
			while (rs.next()) {
				Song song = new Song();
				song.setSong_id(rs.getString("song_id"));
				song.setSong_name(rs.getString("song_name"));
				song.setSong_artist(rs.getString("song_artist"));
				song.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				song.setCategory(category);
				song.setFilename(rs.getString("filename"));
				song.setPath(rs.getString("path"));
				song.setSong_desc(rs.getString("song_desc"));
				song.setSong_price(rs.getDouble("song_price"));
				song.setSong_kunumber(rs.getInt("song_kunumber"));
				song.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.add(song);
			}
			return songs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

}
