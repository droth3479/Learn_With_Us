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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for the homepage of a specific course
 * It displays the classes associated with that course.
 */
@Route(value = "/:courseName", layout = MainView.class)
public class CourseHomeView extends VerticalLayout
        implements HasUrlParameter<String> {

    private Course course;
    private final ContentService service;
    private final Grid<com.example.learn_with_us.data.entity.Class> grid = new Grid<>(Class.class);

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        course = service.getCourse(event.getRouteParameters().get("courseName").toString());
    }

    public CourseHomeView(@Autowired ContentService service){
        this.service = service;

        setSizeFull();

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
}
