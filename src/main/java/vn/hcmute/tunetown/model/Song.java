package vn.hcmute.tunetown.model;


import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.io.InputStream;
import java.util.ArrayList;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;
    private String songName;

    @ManyToOne
    private User artists;

    private String songPoster;
    private String songData;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;
    private Integer amountOfListens;

    @ManyToMany(mappedBy = "playlistSongs")
    private List<Playlist> playlists;

    private Integer songStatus;

    public Song() {
    }

    public Song(Integer songId, String songName, User artists, String songPoster, String songData, Genre genre, Integer amountOfListens, List<Playlist> playlists) {
        this.songId = songId;
        this.songName = songName;
        this.artists = artists;
        this.songPoster = songPoster;
        this.songData = songData;
        this.genre = genre;
        this.amountOfListens = amountOfListens;
        this.playlists = playlists;
    }

    public Song(Integer songId, String songName, User artists, String songPoster, String songData, Integer amountOfListens, List<Playlist> playlists) {
        this.songId = songId;
        this.songName = songName;
        this.artists = artists;
        this.songPoster = songPoster;
        this.songData = songData;
        this.amountOfListens = amountOfListens;
        this.playlists = playlists;
    }

    public Song(String songName, User artists, String downloadUrlImage, String downloadUrlData, Integer amountOfListens, Genre genre, Integer songStatus) {
        this.songName = songName;
        this.artists = artists;
        this.songPoster = downloadUrlImage;
        this.songData = downloadUrlData;
        this.amountOfListens = amountOfListens;
        this.genre = genre;
        this.songStatus = songStatus;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public User getArtists() {
        return artists;
    }

    public void setArtists(User artists) {
        this.artists = artists;
    }

    public String getSongPoster() {
        return songPoster;
    }

    public void setSongPoster(String songPoster) {
        this.songPoster = songPoster;
    }

    public String getSongData() {
        return songData;
    }

    public void setSongData(String songData) {
        this.songData = songData;
    }

    public Integer getAmountOfListens() {
        return amountOfListens;
    }

    public void setAmountOfListens(Integer amountOfListens) {
        this.amountOfListens = amountOfListens;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getSongStatus() {
        return songStatus;
    }

    public void setSongStatus(Integer songStatus) {
        this.songStatus = songStatus;
    }
}
