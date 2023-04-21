package com.example.learn_with_us;

public class Course {
    private String name;
    private int id;
    private String founder;
    private Subject subject;

    private Class[] classes;

    public Course(String name, String founder, Subject subject, Class[] classes){
        this.name = name;
        this.founder = founder;
        this.subject = subject;
        this.classes = classes;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Class[] getClasses() {
        return classes;
    }

    public void setClasses(Class[] classes) {
        this.classes = classes;
    }
}
