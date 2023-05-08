package com.example.learn_with_us.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String founder;
    private String subject;

    public Course(String name, String founder, String subject){
        this.name = name;
        this.founder = founder;
        this.subject = subject;
    }

    public Course() {

    }

    public Long getId(){
        return id;
    }
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
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
