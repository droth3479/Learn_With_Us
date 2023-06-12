package com.example.learn_with_us.views;

import com.example.learn_with_us.beans.UserBean;
import com.example.learn_with_us.data.entity.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Represents the main application view.
 * It contains the navigation menu and the content area.
 * Does not display any content, but serves as a container for the other views.
 */
public class MainView extends AppLayout {
    UserBean userBean;
    User user;
    private final Tabs menu;
    private H1 viewTitle;

    public MainView(@Autowired UserBean userBean) {
        this.userBean = userBean;
        this.user = userBean.getUser();

        // Use the drawer for the menu
        setPrimarySection(Section.DRAWER);

        // Make the nav bar a header
        addToNavbar(true, createHeaderContent());

        // Put the menu in the drawer
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();

        // Configure styling for the drawer
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        // Have a drawer header with an application logo
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("logo.png", "Learn With Us logo"));
        logoLayout.add(new H1("Learn With Us"));

        // Display the logo and the menu in the drawer
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Tab[] createMenuItems() {
        ArrayList<Tab> tabs = new ArrayList<Tab>();
        tabs.add(createTab("Home", HomeView.class));
        tabs.add(createTab("Courses", CourseListView.class));
        tabs.add(createTab("Course Constructor", CourseConstructorView.class));
        if(user.isAdmin()){
            tabs.add(createAdminTab());
        }

        return tabs.toArray(new Tab[0]);
    }

    private static Tab createTab(String text, java.lang.Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, java.lang.Class.class, navigationTarget);
        return tab;
    }

    private Tab createAdminTab() {
        final Tab tab = new Tab();
        tab.add(new Button("Account Overview", event -> {
            UI.getCurrent().navigate(AccountOverviewView.class)
                    .ifPresent(view -> view.validateLogin(user));
        }));
        ComponentUtil.setData(tab, java.lang.Class.class, AccountOverviewView.class);
        return tab;
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();

        // Configure styling for the header
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Have the drawer toggle button on the left
        layout.add(new DrawerToggle());

        // Placeholder for the title of the current view.
        // The title will be set after navigation.
        viewTitle = new H1();
        layout.add(viewTitle);

        //Logout button, to be displayed on top right
        Button logoutButton = new Button("Log out " + user.getUsername(), e -> {
            userBean.logout();
            UI.getCurrent().navigate(HomeView.class);
        });
        layout.add(logoutButton);

        layout.expand(viewTitle);

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();

        // Select the tab corresponding to currently shown view
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);

        // Set the view title in the header
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    /**
     * Gets page title of current component.
     * If the component uses @PageTitle annotation, gets annotation.
     * If component implements HasDynamicTitle, uses the interface's getter method.
     * If neither system is used, sets a blank title.
     * @return string representation of the current page title.
     */
    private String getCurrentPageTitle() {
        String title;
        try{
            title = getContent().getClass().getAnnotation(PageTitle.class).value();
        } catch (NullPointerException npe) {
            if(getContent() instanceof HasDynamicTitle){
                title = ((HasDynamicTitle) getContent()).getPageTitle();
            }
            else
                title = "";
        }
        return title;
    }
}
