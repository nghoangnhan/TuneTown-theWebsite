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

//    public User getUser (String email) {
//        String sql = "SELECT * FROM user WHERE email = ?";
//        try {
//            connection = new DBConnection().getConnection();
//            ps = connection.prepareStatement(sql);
//            ps.setString(1, email);
//            rs = ps.executeQuery();
//
//            while(rs.next()){
//
//                User user = new User();
//                user.setUserID(rs.getInt("userId"));
//                user.setUserName(rs.getString("userName"));
//                user.setEmail(rs.getString("email"));
//                user.setUserPassword(rs.getString("userPassword"));
//                user.setBirthDate(rs.getDate("birthDate"));
//                user.setUserBio(rs.getString("userBio"));
//                user.setCountry(rs.getInt("country"));
//                user.setSex(rs.getInt("sex"));
//                user.setRoles(rs.getInt("roles"));
//
//                return user;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static User getUserByEmail(String email) {
        EntityManager em = DBConnection.getEmFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
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
