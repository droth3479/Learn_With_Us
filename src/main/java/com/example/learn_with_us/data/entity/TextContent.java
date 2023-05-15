package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * The TextContent class represents text-based content for a class.
 * It extends the BaseContent class and provides a method to display the content as a Vaadin Flow component.
 */
@Entity
@DiscriminatorValue("text")
public class TextContent extends BaseContent{
    /**
     * Displays the text content as a Vaadin Flow component.
     *
     * @return The Vaadin Flow component representing the text content.
     */
    @Override
    public Component display() {
        return null;
    }
}
