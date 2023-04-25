package vn.hcmute.tunetown.DAO;

import vn.hcmute.tunetown.connection.DBConnection;
import vn.hcmute.tunetown.model.User;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public static User getUserByEmail(String email, String password) {
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


    public void setUSer(User user) {
        String sql = "INSERT INTO user (userName, email ,userPassword, birthDate, sex, country, userBio, roles) VALUES " +
                            "(? ,? ,? ,? ,?, ?, ?, ?)";
        try {
            connection = new DBConnection().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUserPassword());
            ps.setDate(4, (Date) user.getBirthDate());
            ps.setInt(5, user.getSex());
            ps.setInt(6, user.getCountry());
            ps.setString(7, user.getUserBio());
            ps.setInt(8, user.getRoles());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
}
