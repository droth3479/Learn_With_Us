package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for an individual class.
 * Based on the user navigation, will display the appropriate class's content.
 */
@Route(value = ":courseName/:className", layout = MainView.class)
public class ClassView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle {
    private String className;
    private String title = "";
    private String courseName;
    private Class thisClass;
    private ContentService service;

    public ClassView(@Autowired ContentService service) {
        // Access the db to set the Class object
        setClass(service);

        add(new H1(courseName));

        // Add the class content
        Component classContent = thisClass.getContent().display();
        add(classContent);
    }

    private void setClass(ContentService service) {
        this.service = service;
        thisClass = service.getClass(className, courseName);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        courseName = event.getRouteParameters().get("courseName").toString();
        className = event.getRouteParameters().get("className").toString();
        title = courseName + " : " + className;
    }

    @Override
    public String getPageTitle() {
        return title;
    }
}
