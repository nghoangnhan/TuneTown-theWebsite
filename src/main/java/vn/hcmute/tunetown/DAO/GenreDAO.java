package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.Genre;
import vn.hcmute.tunetown.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    public List<Genre> getFavoriteGenresByUserId (Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserById(userId);

            List<Genre> genreList = user.getFavoriteGenre();
            return genreList;
        } catch (NoResultException e){
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Genre findGenreById (Integer genreId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        try {
            String jpql = "SELECT g FROM Genre g WHERE g.genreId =: genreId";
            TypedQuery<Genre> query = em.createQuery(jpql, Genre.class);
            query.setParameter("genreId", genreId);

            Genre genre = query.getSingleResult();
            return genre;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}
