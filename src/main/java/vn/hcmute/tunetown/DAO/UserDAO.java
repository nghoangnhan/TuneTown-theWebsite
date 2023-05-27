package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.Song;
import vn.hcmute.tunetown.model.User;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDAO {

    public static User getUserByEmailAndPassword(String email, String password) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.userPassword = :password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }
    }

    public static User getUserByEmail(String email) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email ";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }
    }


    public static String checkUserByEmail(String email) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email ";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);
            User user = query.getSingleResult();
            return user.getEmail();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }
    }

    public static String checkUserByUsername(String userName) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.userName = :userName ";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("userName", userName);
            User user = query.getSingleResult();
            return user.getUserName();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();

        }
    }

    public User getUserById(Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.userID = :userId";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("userId", userId);
            User user = query.getSingleResult();
            return user;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static void insert(User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static void update(User user) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public List<Song> getUserListeningHistory (Integer userId) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();

        User parent = em.find(User.class, userId);
        return parent.getHistory(); // Access the lazy-initialized collection
    }
}
