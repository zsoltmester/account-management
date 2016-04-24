package controller;

import model.Session;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

/**
 * The login window of the application. Read more in the documentation.
 */
public class LoginWindow extends Window {

    private JTextField userField;
    private JTextField passwordField;
    private JButton loginButton;

    /**
     * Displays the login window.
     */
    public LoginWindow() {
        super(Strings.LOGIN_WINDOW_TITLE, null);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        userField = new JTextField(Strings.LOGIN_WINDOW_USER_FIELD_DEFAULT);
        addComponent(userField);
        passwordField = new JTextField(Strings.LOGIN_WINDOW_PASSWORD_FIELD_DEFAULT);
        addComponent(passwordField);
        loginButton = new JButton(Strings.LOGIN_WINDOW_LOGIN_BUTTON_TITLE);
        loginButton.addActionListener(new OnLoginButtonClickListener());
        addComponent(loginButton);
        display(Dimensions.LOGIN_WINDOW_SIZE.width, Dimensions.LOGIN_WINDOW_SIZE.height);
    }

    private void addComponent(JComponent component) {
        component.setMaximumSize(Dimensions.LOGIN_WINDOW_COMPONENT_SIZE);
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(component);
    }

    private class OnLoginButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            String user = userField.getText();
            byte[] password = Base64.getEncoder().encode(passwordField.getText().getBytes());
            Session session = Session.login(user, password);
            if (session == null) {
                JOptionPane.showMessageDialog(container, Strings.LOGIN_WINDOW_INVALID_CREDENTIALS,
                        Strings.LOGIN_WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
            } else {
                // TODO navigate with the created session
            }
        }
    }
}
