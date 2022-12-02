package cn.xh.domain;

public class Artist {

    private String artist_name;

    private String artist_password;

    public Artist(){super();}

    public Artist(String artist_name, String artist_password){
        super();
        this.artist_name=artist_name;
        this.artist_password=artist_password;
    }
    public void setArtist_name(String artist_name){this.artist_name=artist_name;}
    public void setArtist_password(String artist_password){this.artist_password=artist_password;}
    public String getArtist_name() {
        return artist_name;
    }
    public String getArtist_password(){
        return artist_password;
    }

    @Override
    public String toString(){
        return "Artist [artist_name=" + artist_name + ", artist_password="
                + artist_password + "]";
    }

}
