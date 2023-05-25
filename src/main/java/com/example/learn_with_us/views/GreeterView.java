package com.example.learn_with_us.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * The landing page view - what the user will see when they first come to the site.
 */
@Route(layout = MainView.class)
@PageTitle("Home")
public class GreeterView  extends VerticalLayout {
}
