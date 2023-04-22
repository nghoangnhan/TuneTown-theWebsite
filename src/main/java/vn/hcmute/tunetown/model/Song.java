package vn.hcmute.tunetown.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;
    private String songName;
    private ArrayList<User> artistList;
    private String songPoster;
    private String songData;
    private Integer amountOfLikes;
    private Integer amountOfListens;


    public Song() {
    }
    public Song(int songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }

    public ArrayList<User> getArtistList() {
        return artistList;
    }

    public void setArtistList(ArrayList<User> artistList) {
        this.artistList = artistList;
    }

    public Song(String songName, String songPoster, String songData, Integer amountOfLikes, Integer amountOfListens) {
        this.songName = songName;
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
}
