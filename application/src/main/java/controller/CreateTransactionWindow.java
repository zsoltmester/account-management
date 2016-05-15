package controller;

import model.Session;
import model.entity.Account;
import model.entity.Customer;
import model.manager.AccountManager;
import model.manager.TransactionManager;
import resource.Dimensions;
import resource.Strings;
import util.DialogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The create transaction window of the application. Read more in the documentation.
 */
public class CreateTransactionWindow extends Window {

    private static final String ACCOUNT_PROPERTY_KEY =
            CreateTransactionWindow.class.getName() + ":ACCOUNT_PROPERTY_KEY";

    private Customer customer;

    private List<JCheckBox> sources;
    private List<JTextField> amountFields;
    private JTextField targetField;
    private JButton sendButton;

    /**
     * Displays a create transaction window for the given customer.
     *
     * @param session The session.
     */
    public CreateTransactionWindow(Session session, Customer customer) {
        super(Strings.CREATE_TRANSACTION_WINDOW_TITLE, session, false);
        this.customer = customer;

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(Dimensions.GAP.height));

        JTextArea sourceAccountsArea = new JTextArea(Strings.CREATE_TRANSACTION_WINDOW_SOURCE_ACCOUNTS);
        sourceAccountsArea.setEnabled(false);
        sourceAccountsArea.setDisabledTextColor(Color.BLACK);
        sourceAccountsArea.setBackground(null);

        addComponentToBoxLayout(sourceAccountsArea, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SMALLER_SIZE);
        List<Account> accounts = new ArrayList<>(customer.getAccounts());
        sources = new ArrayList<>(accounts.size());
        accounts.forEach(account -> {
            JCheckBox source = new JCheckBox(account.getNumber());
            source.putClientProperty(ACCOUNT_PROPERTY_KEY, account);
            sources.add(source);
            addComponentToBoxLayout(source, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SMALLER_SIZE);
        });

        targetField = new JTextField(Strings.CREATE_TRANSACTION_WINDOW_TARGET_ACCOUNT_DEFAULT);
        addComponentToBoxLayout(targetField, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);

        amountFields = new ArrayList<>(sources.size());
        for (int i = 0; i < sources.size(); ++i) {
            JTextField amountField = new JTextField(
                    String.format(Strings.CREATE_TRANSACTION_WINDOW_BALANCE_DEFAULT, accounts.get(i).getNumber()));
            addComponentToBoxLayout(amountField, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);
            amountField.putClientProperty(ACCOUNT_PROPERTY_KEY, accounts.get(i));
            amountFields.add(amountField);
        }

        sendButton = new JButton(Strings.CREATE_TRANSACTION_WINDOW_SEND_BUTTON_TITLE);
        sendButton.addActionListener(new OnSendButtonClickListener());
        addComponentToBoxLayout(sendButton, Dimensions.CREATE_TRANSACTION_WINDOW_COMPONENT_SIZE);

        container.add(Box.createVerticalStrut(Dimensions.GAP.height));

        display(Dimensions.CREATE_TRANSACTION_WINDOW_SIZE.width, Dimensions.CREATE_TRANSACTION_WINDOW_SIZE.height);
    }

    @Override
    protected void refresh() {
        close();
        Application.refreshWindow(CustomerManagerWindow.class);
    }

    private class OnSendButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            new Thread(() -> {
                List<Long> selectedAccounts = new ArrayList<>();
                List<BigDecimal> inputAmounts = new ArrayList<>();
                for (int i = 0; i < sources.size(); ++i) {
                    if (sources.get(i).isSelected()) {
                        selectedAccounts.add(((Account) sources.get(i).getClientProperty(ACCOUNT_PROPERTY_KEY)).getId());
                        try {
                            inputAmounts.add(new BigDecimal(amountFields.get(i).getText()));
                        } catch (NumberFormatException e) {
                            DialogUtil.showErrorDialog(container);
                            return;
                        }
                    }
                }

                if (!DialogUtil.showConfirmDialog(container)) {
                    return;
                }

                try {
                    if (TransactionManager.performTransaction(selectedAccounts,
                            AccountManager.getIdForNumber(targetField.getText()), inputAmounts)) {
                        refresh();
                    } else {
                        DialogUtil.showErrorDialog(container);
                    }
                } catch (NumberFormatException e) {
                    DialogUtil.showErrorDialog(container);
                }
            }).start();
        }
    }
}
