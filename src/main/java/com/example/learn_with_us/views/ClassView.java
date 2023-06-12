package com.example.learn_with_us.views;

import com.example.learn_with_us.beans.UserBean;
import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for an individual class.
 * Displays the classes content, either a video or text lesson.
 */
@Route(value = "course/:courseName/class/:className", layout = MainView.class)
public class ClassView extends VerticalLayout implements BeforeEnterObserver, HasDynamicTitle {
    private String className;
    private String title = "";
    private String courseName;
    private Class thisClass;
    private final ContentService service;
    private UserBean userBean;
    private User user;

    public ClassView(@Autowired ContentService service, @Autowired UserBean userBean) {
        this.userBean = userBean;
        this.user = userBean.getUser();
        this.service = service;
        setSizeFull();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        courseName = event.getRouteParameters().get("courseName").orElseThrow();
        className = event.getRouteParameters().get("className").orElseThrow();
        title = courseName + ": " + className;
        thisClass = service.getClass(className, courseName);
        validateLogin();
    }

    /**
     * Only finish view configuration if valid user logged in.
     */
    private void validateLogin(){
        if(user != null)
            configureContent();
    }

    private void configureContent() {
        add(new H1(courseName));

        // Add the class content
        Component classContent = thisClass.getContent().display();
        add(classContent);
    }

    @Override
    public String getPageTitle() {
        return title;
    }
}
