package controller;

import model.Session;
import model.entity.Account;
import model.entity.Customer;
import resource.Dimensions;
import resource.Strings;
import view.AccountView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer manager window of the application. Read more in the documentation.
 */
public class CustomerManagerWindow extends Window implements AccountView.OnAccountClickListener {

    private Customer customer;

    private JPanel infoPanel;
    private JTextArea customerIdArea;
    private JTextArea customerNameArea;
    private JTextArea customerAddressArea;
    private JTextArea customerPhoneArea;
    private JButton createTransactionButton;

    private JPanel accountPanel;
    private List<AccountView> accountViews;

    /**
     * Creates the customer manager window.
     *
     * @param session  The current session.
     * @param customer The customer to display.
     */
    public CustomerManagerWindow(Session session, Customer customer) {
        super(Strings.CUSTOMER_MANAGER_WINDOW_TITLE, session);
        this.customer = customer;

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        customerIdArea = new JTextArea(
                String.format(Strings.CUSTOMER_MANAGER_WINDOW_CUSTOMER_ID, customer.getId()));
        processInfoComponent(customerIdArea);
        customerNameArea = new JTextArea(
                String.format(Strings.CUSTOMER_MANAGER_WINDOW_CUSTOMER_NAME, customer.getName()));
        processInfoComponent(customerNameArea);
        customerAddressArea = new JTextArea(
                String.format(Strings.CUSTOMER_MANAGER_WINDOW_CUSTOMER_ADDRESS, customer.getAddress()));
        processInfoComponent(customerAddressArea);
        customerPhoneArea = new JTextArea(
                String.format(Strings.CUSTOMER_MANAGER_WINDOW_CUSTOMER_PHONE, customer.getPhone()));
        processInfoComponent(customerPhoneArea);
        createTransactionButton = new JButton(Strings.CUSTOMER_MANAGER_CREATE_TRANSACTION_BUTTON_TITLE);
        createTransactionButton.setMaximumSize(Dimensions.CUSTOMER_MANAGER_INFO_COMPONENT_SIZE);
        createTransactionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createTransactionButton.addActionListener(event -> {
            new CreateTransactionWindow(session, customer);
        });
        infoPanel.add(createTransactionButton);
        container.add(infoPanel);

        // TODO add an info account panel

        accountPanel = new JPanel();
        accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
        accountViews = new ArrayList<>(customer.getAccounts().size());
        customer.getAccounts().forEach(account -> {
            AccountView accountView = new AccountView(account, CustomerManagerWindow.this);
            accountViews.add(accountView);
            accountPanel.add(accountView);
        });
        container.add(accountPanel);

        display(Dimensions.CUSTOMER_MANAGER_WINDOW_SIZE.width, Dimensions.CUSTOMER_MANAGER_WINDOW_SIZE.height);
    }

    private void processInfoComponent(JTextArea textArea) {
        textArea.setEnabled(false);
        textArea.setMaximumSize(Dimensions.CUSTOMER_MANAGER_INFO_COMPONENT_SIZE);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setBorder(new EmptyBorder(Dimensions.CUSTOMER_MANAGER_INFO_COMPONENT_BORDER));
        infoPanel.add(textArea);
    }

    @Override
    public void onChangeStatusClicked(Account account) {
        // TODO It will be implemented after the database integration.
        JOptionPane.showMessageDialog(container, Strings.UNAVAILABLE_DIALOG_MESSAGE,
                Strings.UNAVAILABLE_DIALOG_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onTransactionHistoryClicked(Account account) {
        new TransactionOverviewWindow(session, account.getTransactions());
    }
}
