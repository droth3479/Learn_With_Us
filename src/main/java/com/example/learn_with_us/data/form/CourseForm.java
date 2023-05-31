package com.example.learn_with_us.data.form;

import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.Subject;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseForm extends FormLayout {
    TextField name = new TextField("Course Name");
    TextField founder = new TextField("Your Name");
    ComboBox<Subject> subject = new ComboBox<>("Subject");
    Button save = new Button("Save");
    Button clear = new Button("Clear");
    
    Binder<Course> binder = new BeanValidationBinder<>(Course.class);
    
    public CourseForm(List<Subject> subjects) {
        binder.bindInstanceFields(this);
        
        add(name,
            founder,
            subject,
            createButtonsLayout());
    }
    
    public void setCourse(Course course){
        binder.setBean(course);
    }

    public static abstract class CourseFormEvent extends ComponentEvent<CourseForm> {
        private Course course;

        protected CourseFormEvent(CourseForm source, Course course) {
            super(source, false);
            this.course = course;
        }

        public Course getCourse() {
            return course;
        }
    }

    public static class SaveEvent extends CourseForm.CourseFormEvent {
        SaveEvent(CourseForm source, Course course) {
            super(source, course);
        }
    }

    public static class ClearEvent extends CourseForm.CourseFormEvent {
        ClearEvent(CourseForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<CourseForm.SaveEvent> listener) {
        return addListener(CourseForm.SaveEvent.class, listener);
    }

    public Registration addClearListener(ComponentEventListener<CourseForm.ClearEvent> listener) {
        return addListener(CourseForm.ClearEvent.class, listener);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        clear.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        clear.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        clear.addClickListener(event -> fireEvent(new CourseForm.ClearEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, clear);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new CourseForm.SaveEvent(this, binder.getBean()));
        }
    }
}
