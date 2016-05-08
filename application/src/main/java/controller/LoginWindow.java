package controller;

import model.Session;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
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
        addComponentToBoxLayout(userField, Dimensions.LOGIN_WINDOW_COMPONENT_SIZE);

        passwordField = new JTextField(Strings.LOGIN_WINDOW_PASSWORD_FIELD_DEFAULT);
        addComponentToBoxLayout(passwordField, Dimensions.LOGIN_WINDOW_COMPONENT_SIZE);

        loginButton = new JButton(Strings.LOGIN_WINDOW_LOGIN_BUTTON_TITLE);
        loginButton.addActionListener(new OnLoginButtonClickListener());
        addComponentToBoxLayout(loginButton, Dimensions.LOGIN_WINDOW_COMPONENT_SIZE);

        display(Dimensions.LOGIN_WINDOW_SIZE.width, Dimensions.LOGIN_WINDOW_SIZE.height);
    }

    private class OnLoginButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            new Thread(() -> {
                String user = userField.getText();
                byte[] password = Base64.getEncoder().encode(passwordField.getText().getBytes());
                Session session = Session.login(user, password);
                if (session == null) {
                    JOptionPane.showMessageDialog(container, Strings.LOGIN_WINDOW_INVALID_CREDENTIALS,
                            Strings.LOGIN_WINDOW_TITLE, JOptionPane.ERROR_MESSAGE);
                } else {
                    close();
                    new CustomerSearchWindow(session);
                }
            }).start();
        }
    }
}
