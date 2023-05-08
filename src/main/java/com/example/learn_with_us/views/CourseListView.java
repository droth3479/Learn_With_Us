package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.repository.CourseRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

//Todo Try to make a service https://vaadin.com/docs/latest/tutorial/database-access
@Route(value = "courselist")
public class CourseListView extends VerticalLayout {

    private final CourseRepository repo;
    Grid<Course> grid;
    TextField filterText = new TextField();

    public CourseListView(@Autowired CourseRepository repo) {
        this.repo = repo;
        this.grid = new Grid<>(Course.class);
        addClassName("course-list-view");
        setSizeFull();
        configureGrid();

        add(getSearchbar(), grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("course-grid");
        grid.setSizeFull();
        grid.setColumns("name", "founder", "subject");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getSearchbar() {
        filterText.setPlaceholder("Search by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(repo.findAll());
    }

}
