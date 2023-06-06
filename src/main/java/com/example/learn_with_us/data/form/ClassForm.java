package com.example.learn_with_us.data.form;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.Course;
import com.example.learn_with_us.data.entity.VideoContent;
import com.example.learn_with_us.data.service.ContentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassForm extends FormLayout {
    Class thisClass;
    ContentService service;
    Course course;

    Label courseLabel = new Label();
    TextField name = new TextField("Class Name");
    TextField link = new TextField("Provide a link to a Youtube video");

    Button save = new Button("Save");
    Button close = new Button("Close");

    public ClassForm(ContentService service, Course course) {
        this.service = service;
        this.course = course;

        configureCourseLabel();

        add(courseLabel,
            name,
            link,
            createButtonsLayout());
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        return new HorizontalLayout(save, close);
    }

    public void setClass(Class thisClass){
        this.thisClass = thisClass;
    }

    public void configureCourseLabel(){
        courseLabel.setText(course.getName());
    }

    public static abstract class ClassFormEvent extends ComponentEvent<ClassForm> {
        private Class thisClass;

        protected ClassFormEvent(ClassForm source, Class thisClass) {
            super(source, false);
            this.thisClass = thisClass;
        }

        public Class getThisClass() {
            return thisClass;
        }
    }

    public static class SaveEvent extends ClassForm.ClassFormEvent {
        SaveEvent(ClassForm source, Class thisClass) {
            super(source, thisClass);
        }
    }

    public static class CloseEvent extends ClassForm.ClassFormEvent {
        CloseEvent(ClassForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<ClassForm.SaveEvent> listener) {
        return addListener(ClassForm.SaveEvent.class, listener);
    }

    public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
        return addListener(CloseEvent.class, listener);
    }

    private void validateAndSave() {
        if(!name.getValue().equals("") && !link.getValue().equals("")){
            VideoContent video = new VideoContent();
            video.setSource(getYouTubeId(link.getValue()));

            service.addClassContent(video);

            thisClass.setCourse(course);
            thisClass.setName(name.getValue());
            thisClass.setContent(video);

            service.addClass(thisClass);
        }

        fireEvent(new ClassForm.SaveEvent(this, thisClass));
    }

    private String getYouTubeId (String youTubeUrl) {
        String template = "https://www.youtube.com/embed/";
        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed/)[^#&?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(youTubeUrl);
        if(matcher.find()){
            return template + matcher.group();
        } else {
            return "error";
        }
    }
}
