package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.AccountService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Displays meta-info about all user accounts.
 * This view is only visible to admin users.
 */
@Route(layout = MainView.class)
@PageTitle("Account Overview")
public class AccountOverviewView extends VerticalLayout {
    private User user;
    private final AccountService service;
    private Grid<User> grid;

    public AccountOverviewView(@Autowired AccountService service) {
        this.service = service;
        setSizeFull();
    }

    public void setUserAndValidate(User user) {
        this.user = user;
        if(user != null && user.isAdmin()){
            configureView();
        }
        else{
            errorDialog();
        }
    }

    private void errorDialog() {
        Dialog dialog = new Dialog();

        dialog.setHeaderTitle("Error");
        dialog.add("You must be an admin to visit this page.");

        Button backHome = new Button("Back Home", e -> {
            UI.getCurrent().navigate(HomeView.class);
        });
        dialog.getFooter().add(backHome);

        dialog.open();
    }

    private void configureView() {
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid = new Grid<>(User.class);
        grid.setSizeFull();
        grid.setColumns("username", "password");
        grid.addColumn(User::getCreationString).setHeader("Creation Time");
        grid.addColumn(User::getAdminString).setHeader("Account Status");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setVisible(true);
    }

    private void updateList() {
        grid.setItems(service.findAllUsers());
    }
}
