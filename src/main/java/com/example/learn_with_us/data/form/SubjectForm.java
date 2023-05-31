package com.example.learn_with_us.data.form;

import com.example.learn_with_us.data.entity.Subject;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class SubjectForm extends FormLayout {
    TextField name = new TextField("Subject Name");

    Button save = new Button("Save");
    Button cancel = new Button("Cancel");

    Binder<Subject> binder = new BeanValidationBinder<>(Subject.class);

    public SubjectForm() {
        binder.bindInstanceFields(this);

        add(name,
            createButtonsLayout());
    }

    public void setSubject(Subject subject) {
        binder.setBean(subject);
    }

    public static abstract class SubjectFormEvent extends ComponentEvent<SubjectForm> {
        private Subject subject;

        protected SubjectFormEvent(SubjectForm source, Subject subject) {
            super(source, false);
            this.subject = subject;
        }

        public Subject getSubject() {
            return subject;
        }
    }

    public static class SaveEvent extends SubjectForm.SubjectFormEvent {
        SaveEvent(SubjectForm source, Subject subject) {
            super(source, subject);
        }
    }

    public static class ClearEvent extends SubjectForm.SubjectFormEvent {
        ClearEvent(SubjectForm source) {
            super(source, null);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SubjectForm.SaveEvent> listener) {
        return addListener(SubjectForm.SaveEvent.class, listener);
    }

    public Registration addClearListener(ComponentEventListener<SubjectForm.ClearEvent> listener) {
        return addListener(SubjectForm.ClearEvent.class, listener);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        cancel.addClickListener(event -> fireEvent(new SubjectForm.ClearEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, cancel);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SubjectForm.SaveEvent(this, binder.getBean()));
        }
    }
}
