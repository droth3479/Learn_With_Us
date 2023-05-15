package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

/**
 * The Course entity represents a course offered by the web server.
 * A Course will share a one to many relationship with the Class object.
 * Each course has a name, a founder, and a subject.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String founder;
    @Enumerated(value = EnumType.STRING)
    private Subject subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", founder='" + founder + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
