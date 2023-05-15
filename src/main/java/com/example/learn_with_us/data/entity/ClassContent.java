package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;

public interface ClassContent {
    void setSource(String source);
    String getSource();
    Component display();
}
