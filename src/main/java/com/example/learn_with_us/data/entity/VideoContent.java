package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * The VideoContent class represents video-based content for a class.
 * It extends the BaseContent class and provides a method to display the content as a Vaadin Flow component.
 */
@Entity
@DiscriminatorValue("video")
public class VideoContent extends BaseContent{
    /**
     * Displays the video content as a Vaadin Flow component.
     *
     * @return The Vaadin Flow component representing the video content.
     */
    @Override
    public Component display() {
        return null;
    }
}
