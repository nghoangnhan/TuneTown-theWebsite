package vn.hcmute.tunetown.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;

    private String playlistName;

    private Integer userId;
    private String playlistType;


    @ManyToMany (fetch = FetchType.EAGER)
    private List<Song> playlistSongs;

    public Playlist() {
    }

    public Playlist(Integer playlistId, String playlistName, Integer userId, String playlistType, List<Song> playlistSongs) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.userId = userId;
        this.playlistType = playlistType;
        this.playlistSongs = playlistSongs;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Integer playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPlaylistType() {
        return playlistType;
    }

    public void setPlaylistType(String playlistType) {
        this.playlistType = playlistType;
    }

    public List<Song> getPlaylistSongs() {
        return playlistSongs;
    }

    public void setPlaylistSongs(List<Song> playlistSongs) {
        this.playlistSongs = playlistSongs;
    }
}
