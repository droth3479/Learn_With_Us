package com.example.learn_with_us;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

public class CourseListView extends VerticalLayout {
    Grid<Course> grid = new Grid<>(Course.class);
    TextField filterText = new TextField();

    public CourseListView() {
        addClassName("course-list-view");
        setSizeFull();
        configureGrid();

        add(getSearchbar(), grid);
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
}
