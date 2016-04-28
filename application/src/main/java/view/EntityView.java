package view;

import resource.Dimensions;

import javax.swing.*;
import java.awt.*;

/**
 * An abstract view for an entity.
 */
public class EntityView extends JPanel {

    /**
     * Configure a text field.
     *
     * @param field The text field to configure.
     */
    protected void processField(JTextField field) {
        field.setEnabled(false);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setMaximumSize(Dimensions.ENTITY_COMPONENT_SIZE);
        field.setDisabledTextColor(Color.BLACK);
    }

    /**
     * Configure and add a button.
     *
     * @param button The button.
     */
    protected void processButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(Dimensions.ENTITY_COMPONENT_SIZE);
        add(button);
    }
}
