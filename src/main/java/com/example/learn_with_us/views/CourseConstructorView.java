package com.example.learn_with_us.views;

import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.Subject;
import com.example.learn_with_us.data.form.CourseForm;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Represents the view for the Course Constructor page.
 * Enables the construction of a new course.
 */
@Route(layout = MainView.class)
@PageTitle("Course Constructor")
public class CourseConstructorView extends VerticalLayout {
    CourseForm form;
    Button addSubject;
    ContentService service;

    CourseConstructorView(@Autowired ContentService service) {
        this.service = service;

        configureHeader();
        configureForm();
        configureButton();

        add(form);
        add(addSubject);
    }

    private void configureHeader() {
        add(new H1("This is where great courses are created."));
        add(new H2("Just fill in the blanks and hit save."));
    }

    private void configureForm() {
        System.out.println("Form configured");
        for (Subject s :
                service.findAllSubjects()) {
            System.out.println(s.toString());
        }
        form = new CourseForm(service.findAllSubjects());
        form.setCourse(new Course());
        form.setVisible(true);
        form.addSaveListener(this::saveCourse);
        form.addClearListener(e -> clearForm());
    }

    private void configureButton() {
        addSubject = new Button("Add a subject");
        addSubject.addClickListener(event -> subjectDialog());
    }

    private void saveCourse(CourseForm.SaveEvent saveEvent) {
        service.addCourse(saveEvent.getCourse());
        clearForm();

        confirmDialog(saveEvent.getCourse());
    }

    private void clearForm() {
        form.setCourse(new Course());
    }

    private void confirmDialog(Course course) {
        ConfirmDialog dialog = new ConfirmDialog();
        String message = String.format("Thanks for adding a brand new class, \n%s: founded by %s \nto the %s department.",
                course.getName(), course.getFounder(), course.getSubject());

        dialog.setHeader("Success!");
        dialog.setText(message);

        dialog.setConfirmText("Go");
        dialog.addConfirmListener(event -> UI.getCurrent().navigate("/course/" + course.getName()));

        dialog.open();
    }

    private void subjectDialog() {
        Dialog dialog = new Dialog();
        TextField subject = new TextField("New Subject");

        dialog.setHeaderTitle("New Subject");
        dialog.add(subject);

        Button saveButton = new Button("Save", e -> {
            saveSubject(subject.getValue());
            dialog.close();
        });
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);

        dialog.open();
    }

    private void saveSubject(String name) {
        service.addSubject(new Subject(name));
        form.configureSubjects(service.findAllSubjects());
    }
}
