package controller;

import model.Session;
import model.entity.Customer;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The create transaction window of the application. Read more in the documentation.
 */
public class CreateTransactionWindow extends Window {

    private Customer customer;

    private List<JCheckBox> sources;
    private JTextField balanceField;
    private JTextField targetField;
    private JButton sendButton;

    /**
     * Displays a create transaction window for the given customer.
     *
     * @param session The session.
     */
    public CreateTransactionWindow(Session session, Customer customer) {
        super(Strings.CREATE_TRANSACTION_WINDOW_TITLE, session);
        this.customer = customer;

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createVerticalStrut(Dimensions.GAP.height));
        JTextArea sourceAccountsArea = new JTextArea(Strings.CREATE_TRANSACTION_WINDOW_SOURCE_ACCOUNTS);
        sourceAccountsArea.setEnabled(false);
        sourceAccountsArea.setDisabledTextColor(Color.BLACK);
        sourceAccountsArea.setBackground(null);
        addComponentToBoxLayout(sourceAccountsArea, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SMALLER_SIZE);
        sources = new ArrayList<>(customer.getAccounts().size());
        customer.getAccounts().forEach(account -> {
            JCheckBox source = new JCheckBox(account.getId());
            sources.add(source);
            addComponentToBoxLayout(source, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SMALLER_SIZE);
        });
        targetField = new JTextField(Strings.CREATE_TRANSACTION_WINDOW_TARGET_ACCOUNT_DEFAULT);
        addComponentToBoxLayout(targetField, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);
        balanceField = new JTextField(Strings.CREATE_TRANSACTION_WINDOW_BALANCE_DEFAULT);
        addComponentToBoxLayout(balanceField, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);
        sendButton = new JButton(Strings.CREATE_TRANSACTION_WINDOW_SEND_BUTTON_TITLE);
        sendButton.addActionListener(event -> {
            // TODO It will be implemented after the database integration.
            JOptionPane.showMessageDialog(container, Strings.UNAVAILABLE_DIALOG_MESSAGE,
                    Strings.UNAVAILABLE_DIALOG_TITLE, JOptionPane.INFORMATION_MESSAGE);
        });
        addComponentToBoxLayout(sendButton, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);
        container.add(Box.createVerticalStrut(Dimensions.GAP.height));

        display(Dimensions.CREATE_TRANSACTION_WINDOW_SIZE.width, Dimensions.CREATE_TRANSACTION_WINDOW_SIZE.height);
    }
}
