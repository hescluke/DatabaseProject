package cn.xh.dao.impl;

import cn.xh.dao.ClientDao;
import cn.xh.dao.ArtistDao;
import cn.xh.domain.Song;
import cn.xh.domain.Category;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.util.JDBCUtil;
import cn.xh.domain.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDaoImpl implements ArtistDao{
    @Override
    public void artistPassword(Artist artist) {
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement prepareStatement = connection
                    .prepareStatement("update artist set artist_password=? where artist_name=?");
            prepareStatement.setString(1, artist.getArtist_password());
            prepareStatement.setString(2, artist.getArtist_name());
            prepareStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public Artist login(String artistname, String password) {
        Artist artist = new Artist();
        try {
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from artist where artist_name=? and artist_password=?");
            preparedStatement.setString(1, artistname);
            preparedStatement.setString(2, password);
            ResultSet executeQuery = preparedStatement.executeQuery();
            if (executeQuery.next()) {
                artist.setArtist_name(executeQuery.getString("artist_name"));
                artist.setArtist_password(executeQuery.getString("artist_password"));

            } else {
            }
        } catch (Exception e) {
        }
        return artist;
    }
//    @Override
    public boolean register(Artist artist) {
        Connection connection = JDBCUtil.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("select * from user where artist_name='" + artist.getArtist_name() + "'");
            if (resultSet.next() == true) {
                return true;
            } else {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "insert into artist (artist_name,artist_password) values(?,?)");
                    preparedStatement.setString(1, artist.getArtist_name());
                    preparedStatement.setString(2, artist.getArtist_password());


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

//    @Override
    public List<Song> findArtistSong(String a_n){
        try{
            Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement =  connection.prepareStatement(
                    "select * from songdb where song_artist = ?");
            preparedStatement.setString(1, a_n);
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

        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("??????");
    }


}
