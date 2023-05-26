package vn.hcmute.tunetown.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    private String artists;

    private String songPoster;
    private String songData;
    private Integer amountOfLikes;
    private Integer amountOfListens;

    @ManyToMany(mappedBy = "playlistSongs")
    @JsonBackReference
    private List<Playlist> playlists;


    public Song() {
    }
    public Song(int songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public Song(Integer songId, String songName, String artists, String songPoster, String songData, Integer amountOfLikes, Integer amountOfListens, List<Playlist> playlists) {
        this.songId = songId;
        this.songName = songName;
        this.artists = artists;
        this.songPoster = songPoster;
        this.songData = songData;
        this.amountOfLikes = amountOfLikes;
        this.amountOfListens = amountOfListens;
        this.playlists = playlists;
    }
    public Song(String songName, String artists, String songPoster, String songData, Integer amountOfLikes, Integer amountOfListens) {
        this.songName = songName;
        this.artists = artists;
        this.songPoster = songPoster;
        this.songData = songData;
        this.amountOfLikes = amountOfLikes;
        this.amountOfListens = amountOfListens;
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

    public Integer getAmountOfLikes() {
        return amountOfLikes;
    }

    public void setAmountOfLikes(Integer amountOfLikes) {
        this.amountOfLikes = amountOfLikes;
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
}
