package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type")
@Table(name = "Content")
public abstract class BaseContent implements Content{
    @Id
    @GeneratedValue
    private Long id;
    protected String source;

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }
}
