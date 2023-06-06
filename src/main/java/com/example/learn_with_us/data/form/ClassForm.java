package com.example.learn_with_us.data.form;

import com.example.learn_with_us.data.entity.Class;
import com.example.learn_with_us.data.entity.ClassContent;
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
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.shared.Registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassForm extends FormLayout {
    Label courseLabel = new Label();
    TextField name = new TextField("Class Name");
    TextField link = new TextField("Provide a link to a Youtube video");

    Button save = new Button("Save");
    Button close = new Button("Close");

    Binder<Class> binder = new BeanValidationBinder<>(Class.class);
    ContentService service;
    Course course;

    public ClassForm(ContentService service, Course course) {
        this.service = service;
        this.course = course;

        binder.bindInstanceFields(this);
        configureLinkBindings();

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

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, close);
    }

    public void setClass(Class thisClass){
        binder.setBean(thisClass);
    }

    public void configureCourseLabel(){
        courseLabel.setText(course.getName());
    }

    private void configureLinkBindings() {
        binder.forField(link)
                .withConverter(
                        new stringToContentConverter()
                )
                .bind(Class::getContent, Class::setContent);
    }

    private class stringToContentConverter implements Converter<String, ClassContent> {
        @Override
        public Result<ClassContent> convertToModel(String value, ValueContext context) {
            VideoContent vc = new VideoContent();
            vc.setSource(getYouTubeId(value));
            service.addClassContent(vc);

            return Result.ok(vc);
        }

        @Override
        public String convertToPresentation(ClassContent value, ValueContext context) {
            if(value == null)
                return "";
            return value.getSource();
        }
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
        if(binder.isValid()) {
            fireEvent(new ClassForm.SaveEvent(this, binder.getBean()));
        }
    }

}
