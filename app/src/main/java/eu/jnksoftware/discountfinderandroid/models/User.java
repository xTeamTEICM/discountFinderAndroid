package eu.jnksoftware.discountfinderandroid.models;

import java.io.Serializable;
import java.util.Date;

import eu.jnksoftware.discountfinderandroid.services.BinaryIO;

/**
 * Created by makis on 26/10/2017.
 */

public class User implements Serializable {
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordSalt;
    private Role role;
    private Date creationDate;
    private Date lastLogin;
    private String token;

    public User(int ID, String firstName, String lastName, String email, String password, String passwordSalt, Role role, Date creationDate, Date lastLogin, String token) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.role = role;
        this.creationDate = creationDate;
        this.lastLogin = lastLogin;
        this.token = token;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreationDate() {return creationDate;}

    public void setCreationDate(Date creationDate) {

        this.creationDate = creationDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
