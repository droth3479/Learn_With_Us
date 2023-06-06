package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * The User entity represents a user account.
 * A user consists of account info and meta info.
 * A user may have different access level, as indicated by the isAdmin boolean.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @CreationTimestamp
    private Timestamp creationTimestamp;
    private boolean isAdmin;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public String getCreationString(){
        return creationTimestamp.toString();
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getAdminString(){
        return isAdmin ? "Admin" : "Standard User";
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
