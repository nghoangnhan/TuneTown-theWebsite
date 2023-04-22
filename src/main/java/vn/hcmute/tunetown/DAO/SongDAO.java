package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SongDAO {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    public Song getSong(Integer songId) {
        Song song = new Song();
        try{
            sql = "SELECT songId, songName, songPoster FROM songs WHERE songId = ?";

            connection = new DBConnection().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, songId);

            rs = ps.executeQuery();

            while(rs.next())
            {
                song.setSongId(Integer.parseInt(rs.getString("songId")));
                song.setSongName(rs.getString("songName"));

                //song.setArtistList(getSongArtist(songId));

                byte[] imageByte = rs.getBytes("songPoster");
                String poster = Base64.getEncoder().encodeToString(imageByte);
                song.setSongPoster(poster);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return song;
    }

    public ArrayList<User> getSongArtist(Integer songId){

        try{
            connection = new DBConnection().getConnection();
            sql = "SELECT users.userId, users.userName, songId FROM song_artists INNER JOIN users " +
                    "WHERE users.userId = song_artists.userId AND songId = ? GROUP BY users.userId, users.userName";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, songId);
            rs = ps.executeQuery();

            ArrayList<User> list  = new ArrayList<User>();
            while(rs.next()) {
                User user = new User();
                user.setUserID(Integer.parseInt(rs.getString("userId")));
                user.setUserName(rs.getString("userName"));
                list.add(user);
            }
            return list;


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Song> castImageBase64(List<Song> songs){
        for (Song song : songs) {
            byte[] imageByte = song.getSongPoster().getBytes();
            String poster = Base64.getEncoder().encodeToString(imageByte);
            song.setSongPoster(poster);
        }
        return songs;
    }

    public ArrayList<Song> getAllSongs(){
//        ArrayList<Song> listSong = new ArrayList<>();
//        EntityManager em = DBConnection.getEmFactory().createEntityManager();
//        try {
//            String jpql = "SELECT s FROM Song s";
//            TypedQuery<Song> query = em.createQuery(jpql, Song.class);
//            List<Song> songs = query.getResultList();
//
//
////            String jpsl_poster = "SELECT s.songPoster FROM Song s";
////            TypedQuery<byte[]> posterQuery = em.createQuery(jpsl_poster, );
//            songs = castImageBase64(songs);
//            listSong.addAll(songs);
//
//            return listSong;
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            em.close();
//        }

        ArrayList<Song> listSong = new ArrayList<>();
        try {
            connection = new DBConnection().getConnection();
            sql = "SELECT * FROM song";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Song song = new Song();

                song.setSongId(Integer.parseInt(rs.getString("songId")));
                song.setSongName(rs.getString("songName"));

                byte[] imageByte = rs.getBytes("songPoster");
                String poster = Base64.getEncoder().encodeToString(imageByte);
                song.setSongPoster(poster);

                song.setSongData(rs.getString("songData"));

                listSong.add(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSong;
    }

    public List<Song> findSongBySongName (String searchText) {
        ArrayList<Song> listSong = new ArrayList<>();

        String sql = "SELECT * FROM song WHERE CONCAT(songName) = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, searchText);
            rs = ps.executeQuery();

            while(rs.next()) {
                Song song = new Song();

                song.setSongId(Integer.parseInt(rs.getString("songId")));
                song.setSongName(rs.getString("songName"));

                byte[] imageByte = rs.getBytes("songPoster");
                String poster = Base64.getEncoder().encodeToString(imageByte);
                song.setSongPoster(poster);

                song.setSongData(rs.getString("songData"));

                listSong.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listSong;
    }

    public void uploadSong(Song song) {

        try{
            connection = new DBConnection().getConnection();
            sql = "INSERT INTO song (songName, songPoster, songData, amountOfLikes, amountOfListens)" +
                    "values (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, song.getSongName());

            //image processing
            byte[] imageByte = Base64.getDecoder().decode(song.getSongPoster());
            InputStream songPoster = new ByteArrayInputStream(imageByte);
            ps.setBlob(2, songPoster);

            ps.setString(3, song.getSongData());
            ps.setInt(4, song.getAmountOfLikes());
            ps.setInt(5, song.getAmountOfListens());

            ps.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
