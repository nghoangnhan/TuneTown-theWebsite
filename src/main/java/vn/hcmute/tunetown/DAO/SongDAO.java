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
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try{
            String jpql = "SELECT s FROM Song s WHERE s.songId =: songId";
            TypedQuery<Song> songTypedQuery = em.createQuery(jpql, Song.class);
            songTypedQuery.setParameter("songId", songId);
            Song song = songTypedQuery.getSingleResult();
            return song;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }


//    EntityManager em = DBConnection.getEmFactory().createEntityManager();
//        try {
//        String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.userPassword = :password";
//        TypedQuery<User> query = em.createQuery(jpql, User.class);
//        query.setParameter("email", email);
//        query.setParameter("password", password);
//        User user = query.getSingleResult();
//        return user;
//    } catch (NoResultException e) {
//        return null;
//    } finally {
//        em.close();
//
//    }

    public List<Song> getAllSongs(){
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT s FROM Song s";
            TypedQuery<Song> query = em.createQuery(jpql, Song.class);
            List<Song> listSong = query.getResultList();
            return listSong;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
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
