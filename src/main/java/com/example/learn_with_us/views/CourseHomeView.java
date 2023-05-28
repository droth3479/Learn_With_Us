package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

/**
 * Represents the view for the homepage of a specific course
 * It displays the classes associated with that course.
 */
@Route(value = "course", layout = MainView.class)
public class CourseHomeView extends VerticalLayout
        implements HasUrlParameter<String>, HasDynamicTitle, AfterNavigationObserver {

    private Course course;
    private String title = "";
    private final ContentService service;
    private final Grid<com.example.learn_with_us.data.entity.Class> grid = new Grid<>(Class.class);

    public CourseHomeView(@Autowired ContentService service){
        this.service = service;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        course = service.getCourse(parameter);
        title = parameter;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        add(getHeader());

        configureGrid();
        add(grid);
    }

    private VerticalLayout getHeader() {
        VerticalLayout header = new VerticalLayout();

        header.add(new H1(course.getName()));
        header.add(new H3(course.getSubject().toString()));
        header.add(new H2("Founded by: " + course.getFounder()));

        return header;
    }

    private void configureGrid() {
        grid.addClassNames("class-grid");
        grid.setSizeFull();
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    @Override
    public String getPageTitle() {
        return title;
    }
}
