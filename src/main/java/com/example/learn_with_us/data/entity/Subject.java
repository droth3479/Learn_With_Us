package com.example.learn_with_us.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Subject enum represents the subjects associated with courses in the web server.
 * It includes predefined values for science, math, history, and other subjects.
 */
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }
}
