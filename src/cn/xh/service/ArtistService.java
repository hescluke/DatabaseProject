package cn.xh.service;
import java.util.List;

import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.Artist;
public interface ArtistService {
    List<Song> findArtistSong(String a_n);
    boolean register(String artistname, String password);
    Artist login(String artistname, String password);

    void artistPassword(String password, String artistname);
}
