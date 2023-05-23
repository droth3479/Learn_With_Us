package com.example.learn_with_us.data.entity;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.TextArea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The TextContent class represents text-based content for a class.
 * It extends the BaseContent class and provides a method to display the content as a Vaadin Flow component.
 */
@Entity
@DiscriminatorValue("text")
public class TextContent extends BaseContent{
    /**
     * Displays the text content as a Vaadin Flow component.
     *
     * @return The Vaadin Flow component representing the text content.
     */
    @Override
    public Component display() {
        TextArea readonlyArea = new TextArea();
        readonlyArea.setReadOnly(true);
        readonlyArea.setValue(getText());

        return readonlyArea;
    }

    private String getText() {
        String fileAsString = "";

        try {
            final File file = new File(this.getSource());
            fileAsString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileAsString;
    }
}
