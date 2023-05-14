package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("video")
public class VideoContent extends BaseContent{
    @Override
    public Component display() {
        return null;
    }
}
