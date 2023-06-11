package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.User;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for displaying a list of courses.
 * The user will be able to select a course to visit its homepage.
 */
@Route(layout = MainView.class)
@PageTitle("Courses")
public class CourseListView extends VerticalLayout {
    private final ContentService service;
    Grid<Course> grid;
    TextField filterText = new TextField();
    private User user;

    public CourseListView(@Autowired ContentService service) {
        this.service = service;
        this.grid = new Grid<>(Course.class);
        addClassName("course-list-view");
        setSizeFull();
    }

    /**
     * Only finish view configuration if valid user is passed along.
     * @param user The currently logged in user.
     */
    public void validateLogin(User user) {
        this.user = user;
        configureView();
    }

    /**
     * The rest of the constructor, only to be completed upon confirmation of valid login.
     */
    private void configureView() {
        configureGrid();
        add(getSearchbar(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("course-grid");
        grid.setSizeFull();
        grid.setColumns("name", "founder");
        grid.addColumn(course -> (course.getSubject().toString())).setHeader("Subject").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event ->
                navigateToCourse(event.getValue()));
    }

    private HorizontalLayout getSearchbar() {
        filterText.setPlaceholder("Search by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(service.findAllCourses(filterText.getValue()));
    }

    private void navigateToCourse(Course course) {
        UI.getCurrent().navigate("/course/" + course.getName());
    }
}
