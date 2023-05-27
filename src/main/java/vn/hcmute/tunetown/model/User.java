package vn.hcmute.tunetown.model;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userName;

    private String email;

    private String userPassword;

    private String birthDate;

    private int gender;

    private String userBio;

    private Integer role;
    private String userAvatar;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Genre> favoriteGenre;

    @OneToMany
    private List<Song> history;

    public User() {
    }

    public User(int userID, String userName, String email, String userPassword, String birthDate, int gender, String userBio, Integer role, String userAvatar, List<Genre> favoriteGenre, List<Song> history) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.birthDate = birthDate;
        this.gender = gender;
        this.userBio = userBio;
        this.role = role;
        this.userAvatar = userAvatar;
        this.favoriteGenre = favoriteGenre;
        this.history = history;
    }

    public User(String username, Object o, String email, String password, int i, int i1, Object o1, String s) {
    }

    public User(int userID, String username, String birthdate, String email, String password, int gender, Integer role, String userBio, String downloadUrlAvatar) {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<Genre> getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(List<Genre> favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public List<Song> getHistory() {
        return history;
    }

    public void setHistory(List<Song> history) {
        this.history = history;
    }
}
