package com.example.learn_with_us.beans;

import com.example.learn_with_us.data.entity.User;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

/**
 * The user bean represents the user currently logged in.
 * It is set on login, and referenced by each page on navigation.
 * The scope is the vaadin session, ie a browser window.
 */
@SpringComponent
@VaadinSessionScope
public class UserBean {
    private User user;

    public void login(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void logout() {
        user = null;
    }
}
