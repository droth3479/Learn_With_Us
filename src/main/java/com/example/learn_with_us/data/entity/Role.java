package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

/**
 * Role represents the roles that a user may assume, such as Admin or Standard.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String role;

    public Role(){}

    public Role(String role){
        this.role = role;
    }

    @Override
    public String toString(){return role;}
}
