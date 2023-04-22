package vn.hcmute.tunetown.model;

//import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userName;

    private String email;

    private String userPassword;

    private Date birthDate;

    private int sex;

    private int country;

    private String userBio;

    private int roles;

    public User(){
        userName = "";
        email = "";
        userPassword = "";
        userBio = "";
    }

    public User(int userID, String userName, String email, String userPassword, Date birthDate, int sex, int country, String userBio, int roles){
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.userBio = userBio;
        this.roles = roles;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
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
}
