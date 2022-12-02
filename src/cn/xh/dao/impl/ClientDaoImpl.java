package cn.xh.dao.impl;


import cn.xh.dao.ClientDao;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

	public User findUserByID(String user_id){
		User user = new User();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user where user_id=?");
			preparedStatement.setString(1, user_id);
			ResultSet executeQuery = preparedStatement.executeQuery();
			if (executeQuery.next()) {
				user.setUsername(executeQuery.getString("user_username"));
				user.setPassword(executeQuery.getString("user_password"));
				user.setName(executeQuery.getString("user_name"));
				user.setSex(executeQuery.getString("user_sex"));
				user.setTel(executeQuery.getString("user_tel"));
				user.setAddress(executeQuery.getString("user_address"));
				user.setId(executeQuery.getString("user_id"));
				user.setVip(executeQuery.getString("user_vip"));
			} else {
			}
		} catch (Exception e) {
		}
		return user;
	}
	// ???
	@Override
	public User login(String username, String password) {
		User user = new User();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user where user_username=? and user_password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet executeQuery = preparedStatement.executeQuery();
			if (executeQuery.next()) {
				user.setUsername(executeQuery.getString("user_username"));
				user.setPassword(executeQuery.getString("user_password"));
				user.setName(executeQuery.getString("user_name"));
				user.setSex(executeQuery.getString("user_sex"));
				user.setTel(executeQuery.getString("user_tel"));
				user.setAddress(executeQuery.getString("user_address"));
				user.setId(executeQuery.getString("user_id"));
				user.setVip(executeQuery.getString("user_vip"));
			} else {
			}
		} catch (Exception e) {
		}
		return user;
	}

	// ???
	@Override
	public boolean register(User user) {
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from user where user_username='" + user.getUsername() + "'");
			if (resultSet.next() == true) {
				return true;
			} else {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"insert into user (user_id,user_username,user_password,user_name,user_sex,user_tel,user_address) values(?,?,?,?,?,?,?)");
					preparedStatement.setString(1, user.getId());
					preparedStatement.setString(2, user.getUsername());
					preparedStatement.setString(3, user.getPassword());
					preparedStatement.setString(4, user.getName());
					preparedStatement.setString(5, user.getSex());
					preparedStatement.setString(6, user.getTel());
					preparedStatement.setString(7, user.getAddress());

					// ??
					preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean becomeVip(String u_id){
		Connection connection = JDBCUtil.getConnection();
		try{
//			Statement statement = connection.createStatement();
			PreparedStatement preparedStatement  =  connection.prepareStatement("update user set user_vip = '1' where user_id = ?");
			preparedStatement.setString(1, u_id);
			preparedStatement.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Song> getCategorySong(String cid) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = null;

			if (cid==null){
				preparedStatement=  connection.prepareStatement(
                        "select * from songdb");
            }else {
				preparedStatement =  connection.prepareStatement(
                        "select * from songdb where song_category = (select category_id from category where category_id = '"+cid+"')");
            }



            ResultSet rs = preparedStatement.executeQuery();


			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ????鼮????id????鼮??????????
	private Category findCategoryById(String song_category) {
		Category category = new Category();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from category where category_id=?");
			preparedStatement.setString(1, song_category);
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

	// ??????????鼮?б?
	@Override
	public List<Song> rwsk() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from songdb where song_category = (select category_id from category where category_name = '???????')");
			ResultSet rs = preparedStatement.executeQuery();

			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ?????????鼮?б?
	@Override
	public List<Song> sets() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from songdb where song_category = (select category_id from category where category_name = '??????')");
			ResultSet rs = preparedStatement.executeQuery();

			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ??????????鼮?б?
	@Override
	public List<Song> jjjr() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from songdb where song_category = (select category_id from category where category_name = '???????')");
			ResultSet rs = preparedStatement.executeQuery();

			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ??????????鼮?б?
	@Override
	public List<Song> kxjs() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from songdb where song_category = (select category_id from category where category_name = '???????')");
			ResultSet rs = preparedStatement.executeQuery();

			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ???????????鼮?б?
	@Override
	public List<Song> jyks() {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from songdb where song_category = (select category_id from category where category_name = '????????')");
			ResultSet rs = preparedStatement.executeQuery();

			List<Song> list = new ArrayList<Song>();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				list.add(songs);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}

	// ??????????
	@Override
	public void personInformation(User user) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(
					"update user set user_name=?,user_sex=?,user_tel=?,user_address=? where user_username=?");
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getSex());
			prepareStatement.setString(3, user.getTel());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getUsername());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ???????
	@Override
	public void personPassword(User user) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("update user set user_password=? where user_username=?");
			prepareStatement.setString(1, user.getPassword());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ??????
	@Override
	public List<Song> search(String search) {
		List<Song> lists = new ArrayList<Song>();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from songdb where song_name like ?");
			preparedStatement.setString(1, "%" + search + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Song songs = new Song();
				songs.setSong_id(rs.getString("song_id"));
				songs.setSong_name(rs.getString("song_name"));
				songs.setSong_artist(rs.getString("song_artist"));
				songs.setSong_press(rs.getString("song_press"));
				Category category = findCategoryById(rs.getString("song_category"));
				songs.setCategory(category);
				songs.setFilename(rs.getString("filename"));
				songs.setPath(rs.getString("path"));
				songs.setSong_desc(rs.getString("song_desc"));
				songs.setSong_kunumber(rs.getInt("song_kunumber"));
				songs.setSong_xiaonumber(rs.getInt("song_xiaonumber"));
				songs.setSong_price(rs.getDouble("song_price"));
				lists.add(songs);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ????鼮id????鼮???
	@Override
	public Song findSongById(String song_id) {
		try {
			Song song = new Song();
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("select * from songdb where song_id = ?");
			prepareStatement.setString(1, song_id);
			ResultSet rs = prepareStatement.executeQuery();
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
			}
			return song;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ???????
	@Override
	public void addfavorite(String favorite_id, String user_id, String song_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into favorite (favorite_id,user_id,song_id) values (?,?,?)");
			prepareStatement.setString(1, favorite_id);
			prepareStatement.setString(2, user_id);
			prepareStatement.setString(3, song_id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ???????
	@Override
	public List<Favorite> findFavoriteByUserId(User user) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from favorite where user_id = ?");
			prepareStatement.setString(1, user.getId());
			ResultSet rs = prepareStatement.executeQuery();
			List<Favorite> list = new ArrayList<Favorite>();
			while (rs.next()) {
				Favorite favorite = new Favorite();
				favorite.setUser(user);
				favorite.setFavorite_id(rs.getString("favorite_id"));
				Song song = findSongById(rs.getString("song_id"));
				favorite.setSong(song);
				list.add(favorite);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean findFavorite(String user_id, String song_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from favorite where user_id=? and song_id=?");
			prepareStatement.setString(1, user_id);
			prepareStatement.setString(2, song_id);
			ResultSet rs = prepareStatement.executeQuery();
            return rs.next();
        } catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

	@Override
	public void delFavorite(String song_id, String user_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("delete from favorite where song_id=? and user_id=?");
			prepareStatement.setString(1, song_id);
			prepareStatement.setString(2, user_id);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
