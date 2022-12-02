package cn.xh.dao;
import java.util.List;

import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.Artist;
public interface ArtistDao {
    List<Song> findArtistSong(String a_n);

    Artist login(String artistname, String password);

    boolean register(Artist artist);

    void artistPassword(Artist artist);

}
