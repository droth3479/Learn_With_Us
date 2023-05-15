package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;


/**
 * The ClassContent interface represents the content of a class, which can be either video or text.
 * It provides methods for setting the content source, retrieving the content source, and displaying the content as a
 * Vaadin Flow component.
 */
public interface ClassContent {
    void setSource(String source);
    String getSource();
    Component display();
}
