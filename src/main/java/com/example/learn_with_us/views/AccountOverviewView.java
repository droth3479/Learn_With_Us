package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.Role;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.AccountService;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
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
    private final ContentService contentService;
    private Grid<User> grid;
    private Editor<User> editor;

    public AccountOverviewView(@Autowired AccountService service, @Autowired ContentService contentService) {
        this.service = service;
        this.contentService = contentService;
        setSizeFull();
    }

    public void validateLogin(User user) {
        this.user = user;
        if(user != null && user.isAdmin()){
            if(grid == null){
                configureView();
            }
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
        grid = new Grid<>(User.class, false);
        editor = grid.getEditor();
        Binder<User> binder = new Binder<>(User.class);
        editor.setBinder(binder);
        editor.setBuffered(true);

        grid.setSizeFull();

        Grid.Column<User> usernameColumn = grid.addColumn(User::getUsername).setHeader("Username");
        Grid.Column<User> passwordColumn = grid.addColumn(User::getPassword).setHeader("Password");
        Grid.Column<User> timeColumn = grid.addColumn(User::getCreationString).setHeader("Creation Time");
        Grid.Column<User> roleColumn = grid.addColumn(User::getRole).setHeader("Account Status");
        Grid.Column<User> coursesCreatedColumn = grid.addComponentColumn(user -> {
            Select<Course> coursesCreated = new Select<>();
            coursesCreated.setItems(contentService.findAllCourseByUserId(user));
            coursesCreated.setItemLabelGenerator(Course::getName);
            return coursesCreated;
        }).setWidth("200px").setHeader("Courses Created");
        Grid.Column<User> classesCreatedColumn = grid.addComponentColumn(user -> {
            Select<Class> classesCreated = new Select<>();
            classesCreated.setItems(contentService.findAllClassesByUser(user));
            classesCreated.setItemLabelGenerator(Class::getFullName);
            return classesCreated;
        }).setWidth("200px").setHeader("Classes Created");
        Grid.Column<User> editColumn = grid.addComponentColumn(user -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                if (editor.isOpen())
                    editor.cancel();
                binder.setBean(user);
                grid.getEditor().editItem(user);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setVisible(true);

        TextField usernameField = new TextField();
        usernameField.setWidthFull();
        binder.forField(usernameField)
                .bind(User::getUsername, User::setUsername);
        usernameColumn.setEditorComponent(usernameField);

        TextField passwordField = new TextField();
        passwordField.setWidthFull();
        binder.forField(passwordField)
                .bind(User::getPassword, User::setPassword);
        passwordColumn.setEditorComponent(passwordField);

        RadioButtonGroup<Role> roleCheckbox = new RadioButtonGroup<>("Role", service.findAllRoles());
        binder.forField(roleCheckbox)
                .bind(User::getRole, User::setRole);
        roleColumn.setEditorComponent(roleCheckbox);

        Button saveButton = new Button("Save", e -> {
            service.updateUser(editor.getItem());
            editor.save();
        });
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ICON,
                ButtonVariant.LUMO_ERROR);
        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);
        actions.setPadding(false);
        editColumn.setEditorComponent(actions);
    }

    private void updateList() {
        grid.setItems(service.findAllUsers());
    }
}
