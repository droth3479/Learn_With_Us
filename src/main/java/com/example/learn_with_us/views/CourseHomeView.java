package com.example.learn_with_us.views;

import com.example.learn_with_us.beans.UserBean;
import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.form.ClassForm;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for the homepage of a specific course
 * It displays the classes associated with that course.
 */
@Route(value = "course", layout = MainView.class)
public class CourseHomeView extends VerticalLayout
        implements HasUrlParameter<String>, HasDynamicTitle {

    private Course course;
    private String title = "";
    private final ContentService service;
    private UserBean userBean;
    private User user;

    private final Grid<com.example.learn_with_us.data.entity.Class> grid = new Grid<>(Class.class);
    private ClassForm form;
    private Button addClassButton;

    /**
     * Some typical constructor functions can only be run after setParameter and validateLogin.
     * As such, they are included in configureView.
     * @param service The facade for db access methods.
     */
    public CourseHomeView(@Autowired ContentService service, @Autowired UserBean userBean){
        this.userBean = userBean;
        this.user = userBean.getUser();
        this.service = service;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        course = service.getCourse(parameter);
        title = parameter;
        validateLogin();
    }

    /**
     * Only finish view configuration if valid user is logged in.
     */
    private void validateLogin(){
        if(user != null){
            configureView();
        }
    }

    /**
     * Configures the view after the correct course is fetched from the db, and valid user is sent.
     * Takes the place of the constructor.
     */
    public void configureView() {
        configureGrid();
        configureForm();
        configureButton();

        add(getHeader(), getContent());
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("class-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                navigateToClass(event.getValue()));
    }

    private void configureForm() {
        form = new ClassForm(service, course);
        form.setWidth("25em");
        form.setVisible(false);
        form.addSaveListener(this::saveClass);
        form.addCloseListener(this::closeForm);
    }

    private void configureButton() {
        addClassButton = new Button("Add Class");
        addClassButton.addClickListener(click -> addClass());
    }

    private void addClass() {
        form.setClass(new Class());
        form.setUser(user);
        form.setVisible(true);
    }

    private VerticalLayout getHeader() {
        VerticalLayout header = new VerticalLayout();

        header.add(new H1(course.getName()));
        header.add(new H3(course.getSubject().toString()));
        header.add(new H2("Founded by: " + course.getFounder()));
        header.add(addClassButton);

        return header;
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void saveClass(ClassForm.SaveEvent event) {
        updateList();
        form.setVisible(false);
    }

    private void closeForm(ClassForm.CloseEvent event){
        updateList();
        form.setVisible(false);
    }

    private void navigateToClass(Class c) {
        RouteParam courseParam = new RouteParam("course", course.getName());
        RouteParam classParam = new RouteParam("class", c.getName());
        UI.getCurrent().navigate("/course/" + course.getName() + "/class/" + c.getName());
    }

    private void updateList() {
        grid.setItems(service.getClassesInCourse(course));
    }

    @Override
    public String getPageTitle() {
        return title;
    }
}
