package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route(value = "coursehome", layout = MainView.class)
public class CourseHomeView extends VerticalLayout
        implements HasUrlParameter<String> {
    private Course course;
    Grid<com.example.learn_with_us.data.entity.Class> grid = new Grid<>(Class.class);

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        //TODO set course from DB
    }

    public CourseHomeView(){
        //getHeader();
        //configureGrid();
        add(grid);
    }

}
