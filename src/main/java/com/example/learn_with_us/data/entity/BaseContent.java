package com.example.learn_with_us.data.entity;

import jakarta.persistence.*;

/**
 * The BaseContent class is an abstract entity representing the content of a class.
 * It serves as the superclass for specific types of class content, such as video or text.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type")
public abstract class BaseContent implements ClassContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String source;

    /**
     * Sets the source of the content, which is a url.
     * @param source The source to set for the content.
     */
    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String getSource() {
        return source;
    }

    public Long getId() {
        return id;
    }
}
