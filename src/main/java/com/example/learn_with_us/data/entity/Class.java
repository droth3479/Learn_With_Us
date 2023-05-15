package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToOne(targetEntity = BaseContent.class)
    @JoinColumn(name = "content_id")
    private ClassContent content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassContent getContent() {
        return content;
    }

    public void setContent(ClassContent content) {
        this.content = content;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", content=" + content +
                '}';
    }
}
