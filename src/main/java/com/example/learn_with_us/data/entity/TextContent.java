package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class TextContent extends BaseContent{
    @Override
    public Component display() {
        return null;
    }
}
