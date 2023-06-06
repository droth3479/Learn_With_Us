package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

/**
 * The Subject enum represents the subjects associated with courses in the web server.
 * It includes predefined values for science, math, history, and other subjects.
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
