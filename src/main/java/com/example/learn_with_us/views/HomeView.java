package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.AccountService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The landing page view - what the user will see when they first come to the site.
 */
@Route(value = "")
@PageTitle("Home")
public class HomeView extends VerticalLayout {
    User user;
    AccountService accountService;
    Label errorMessage = new Label();
    Button actionButton;

    public HomeView(@Autowired AccountService accountService) {
        this.accountService = accountService;

        setAlignItems(Alignment.CENTER);

        add(new H1("Come Learn With Us"));

        configureButton();
    }

    private void configureButton() {
        remove(actionButton);
        if(user != null)
            actionButton = getNavButton();
        else
            actionButton = getLoginButton();
        add(actionButton);
    }

    private Button getLoginButton() {
        Button button = new Button("Log in to get started!");
        button.addClickListener(e -> loginDialog());
        return button;
    }

    private Button getNavButton() {
        Button button = new Button("Click to get started!");
        button.addClickListener(e -> UI.getCurrent().navigate(CourseListView.class)
                .ifPresent(page -> page.validateLogin(user)));
        return button;
    }

    public void validateLogin(User user){
        this.user = user;
        configureButton();
    }

    private void loginDialog() {
        Dialog dialog = new Dialog();
        TextField username = new TextField("Username");
        TextField password = new TextField("Password");

        dialog.setHeaderTitle("Login");
        dialog.add(username, password);

        Button login = new Button("Login");
        login.addClickListener(e -> validateLogin(username.getValue(), password.getValue()));
        dialog.getFooter().add(login, errorMessage);

        dialog.open();
    }

    private void validateLogin(String username, String password) {
        if((user = accountService.validateUser(username, password)) != null){
            UI.getCurrent().navigate(CourseListView.class)
                    .ifPresent(view -> view.validateLogin(user));
        }
        else{
            errorMessage.setText("Invalid login attempt. Please try again.");
        }
    }
}
