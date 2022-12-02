package cn.xh.service.impl;

import java.util.List;
import java.util.UUID;

import cn.xh.dao.ArtistDao;

import cn.xh.dao.impl.ArtistDaoImpl;

import cn.xh.domain.Song;
import cn.xh.domain.Favorite;
import cn.xh.domain.Artist;
import cn.xh.domain.User;
import cn.xh.service.ArtistService;
public class ArtistServiceImpl implements ArtistService{
    private final ArtistDao dao = new ArtistDaoImpl();
//    @Override
    public List<Song> findArtistSong(String a_n){return dao.findArtistSong(a_n);}
    public Artist login(String artistname, String password){return dao.login(artistname, password);}
    public boolean register(String artistname, String password){
        Artist artist = new Artist(artistname, password);
        return dao.register(artist);
    }
    public void artistPassword(String password, String artistname) {
        Artist artist = new Artist(artistname, password);
        dao.artistPassword(artist);
    }

}
