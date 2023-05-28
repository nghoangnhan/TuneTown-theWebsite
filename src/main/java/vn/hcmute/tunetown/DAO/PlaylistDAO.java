package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.GlobalUser;
import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.Playlist;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    public void addPlaylist(Playlist playlist) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();

        try {
            em.persist(playlist);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public Playlist getPlaylistById (Integer playlistId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Playlist p WHERE p.playlistId = :playlistId";
            TypedQuery<Playlist> query = em.createQuery(jpql, Playlist.class);
            query.setParameter("playlistId", playlistId);

            Playlist playlist = query.getSingleResult();
            return playlist;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Playlist> getAllPlaylistByUserId (Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Playlist p WHERE p.userId = :userId AND p.playlistType = :type";
            TypedQuery<Playlist> query = em.createQuery(jpql, Playlist.class);
            query.setParameter("userId", userId);
            query.setParameter("type", "Personal");

            List<Playlist> listPLaylist = query.getResultList();
            return listPLaylist;
        } catch (NoResultException e){
            return null;
        } finally {
            em.close();
        }
    }

    public void modifyPlaylist (Playlist playlist) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(playlist);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public void deletePLaylist (Playlist playlist) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(playlist));
            trans.commit();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public Playlist findSuggestedPlaylist(Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT p FROM Playlist p WHERE p.userId = :userId AND p.playlistType = :type";
            TypedQuery<Playlist> query = em.createQuery(jpql, Playlist.class);
            query.setParameter("userId", userId);
            query.setParameter("type", "Suggest");

            return query.getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
            Playlist suggestedPlaylist = new Playlist();
            suggestedPlaylist.setPlaylistType("Suggest");
            suggestedPlaylist.setPlaylistName("Suggested Playlist");
            suggestedPlaylist.setUserId(GlobalUser.globalUserId);
            List<Song> songList = new ArrayList<>();
            suggestedPlaylist.setPlaylistSongs(songList);


            addPlaylist(suggestedPlaylist);
            return suggestedPlaylist;
        } finally {
            em.close();
        }
    }
}
