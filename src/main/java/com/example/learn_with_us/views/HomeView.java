package com.example.learn_with_us.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * The landing page view - what the user will see when they first come to the site.
 */
@Route(value = "", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends VerticalLayout {
    public HomeView() {
        setAlignItems(Alignment.CENTER);
        add(new H1("Come Learn With Us"));
        add(getNavButton());
    }

    private Button getNavButton() {
        Button button = new Button("Get Started!");
        button.addClickListener(e -> UI.getCurrent().navigate(CourseListView.class));
        return button;
    }
}
