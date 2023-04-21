package com.example.learn_with_us;

public class Class {
    private String name;
    private int id;
    private Content content;

    public Class(String name, int id, Content content) {
        this.name = name;
        this.id = id;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
