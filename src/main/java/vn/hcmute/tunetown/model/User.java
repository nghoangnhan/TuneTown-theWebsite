package vn.hcmute.tunetown.model;

//import jakarta.persistence.*;

import net.bytebuddy.utility.nullability.MaybeNull;

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

    private int sex;

    private int country;

    private String userBio;

    private int roles;
    private String userAvatar;

    @ManyToMany
    private List<Song> songs;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Genre> favoriteGenre;

    public User(){
        userName = "";
        email = "";
        userPassword = "";
        userBio = "";
    }

    public User(int userID, String userName, String email, String userPassword, String birthDate, int sex, int country, String userBio, int roles, List<Song> songs, List<Genre> favoriteGenre, String userAvatar) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.userBio = userBio;
        this.roles = roles;
        this.songs = songs;
        this.favoriteGenre = favoriteGenre;
        this.userAvatar = userAvatar;
    }
    public User(int userID, String userName, String birthDate, String email, String userPassword, int sex, int roles,String userBio, String userAvatar) {
        this.userID = userID;
        this.userName = userName;
        this.birthDate = birthDate;
        this.email = email;
        this.userPassword = userPassword;
        this.sex = sex;
        this.roles = roles;
        this.userBio = userBio;
        this.userAvatar = userAvatar;
    }
    public User(String userName, String birthDate, String email, String userPassword, int sex, int roles,String userBio, String userAvatar) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.email = email;
        this.userPassword = userPassword;
        this.sex = sex;
        this.roles = roles;
        this.userBio = userBio;
        this.userAvatar = userAvatar;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(java.lang.String userPassword) {
        this.userPassword = userPassword;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public java.lang.String getUserBio() {
        return userBio;
    }

    public void setUserBio(java.lang.String userBio) {
        this.userBio = userBio;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
