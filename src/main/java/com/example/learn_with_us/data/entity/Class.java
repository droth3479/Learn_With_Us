package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * The Class entity represents a class within the web server's course offering.
 * Each class is associated with a specific course and contains class content.
 */
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;
    @OneToOne(targetEntity = BaseContent.class)
    @JoinColumn(name = "content_id")
    @NotNull
    private BaseContent content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseContent getContent() {
        return content;
    }

    public void setContent(BaseContent content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return course.getName() + " : " + name;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", content=" + content +
                ", user=" + user +
                '}';
    }
}
