package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    public Song getSong(Integer songId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try{
            String jpql = "SELECT s FROM Song s WHERE s.songId =: songId";
            TypedQuery<Song> songTypedQuery = em.createQuery(jpql, Song.class);
            songTypedQuery.setParameter("songId", songId);
            Song song = songTypedQuery.getSingleResult();
            System.out.println(song);
            return song;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

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
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();;
        try {
            em.persist(song);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public void updateAmountOfListens (Song song) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();;
        try {
            em.merge(song);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public List<Song> getTop10SongByGenreId (Genre genre) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT s FROM Song s WHERE s.genre =: genre ORDER BY s.amountOfListens";
            TypedQuery<Song> query = em.createQuery(jpql, Song.class);
            query.setParameter("genre", genre);
            List<Song> top10Songs = query.getResultList();
            return top10Songs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
