package view;

import model.entity.Account;
import resource.Configs;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * A view can display an account.
 */
public class AccountView extends EntityView {

    private Account account;

    private OnAccountClickListener listener;

    private JTextField idField;
    private JTextField numberField;
    private JTextField balanceField;
    private JTextField creationField;
    private JButton changeStatusButton;
    private JButton transactionHistoryButton;

    /**
     * Creates a new info account view.
     */
    public AccountView() {
        this(null, null);
    }

    /**
     * Creates a new account view based on the given account.
     *
     * @param account  The account to display.
     * @param listener The listener which handles the click events.
     */
    public AccountView(Account account, OnAccountClickListener listener) {
        this.account = account;
        this.listener = listener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(Dimensions.GAP.height));

        idField = new JTextField(account == null ? Strings.ACCOUNT_ID : Long.toString(account.getId()));
        processField(idField);

        numberField = new JTextField(account == null ? Strings.ACCOUNT_NUMBER : account.getNumber());
        processField(numberField);

        balanceField = new JTextField(account == null ? Strings.ACCOUNT_BALANCE : account.getBalance().toString());
        processField(balanceField);

        creationField = new JTextField(account == null ? Strings.ACCOUNT_CREATION_DATE
                : Configs.ACCOUNT_CREATION_DATE_FORMAT.format(account.getCreationDate()));
        processField(creationField);

        if (account != null) {
            changeStatusButton = new JButton(account.isActive() ? Strings.ACCOUNT_DEACTIVATE : Strings.ACCOUNT_ACTIVATE);
            changeStatusButton.addActionListener(event -> {
                listener.onChangeStatusClicked(account);
            });
            processButton(changeStatusButton);

            transactionHistoryButton = new JButton(Strings.ACCOUNT_TRANSACTION_HISTORY_BUTTON);
            transactionHistoryButton.addActionListener(event -> {
                listener.onTransactionHistoryClicked(account);
            });
            processButton(transactionHistoryButton);
        }

        add(Box.createVerticalStrut(Dimensions.GAP.height));
    }

    @Override
    protected void processField(JTextField field) {
        super.processField(field);
        field.setBackground(account == null ? Color.BLUE : account.isActive() ? Color.GREEN : Color.RED);
        add(field);
    }

    /**
     * Interface which handles the click events on the associated account.
     */
    public interface OnAccountClickListener {

        /**
         * Called when the user click on the change status button.
         *
         * @param account The associated account.
         */
        void onChangeStatusClicked(Account account);

        /**
         * Called when the user click on the transaction history button.
         *
         * @param account The associated account.
         */
        void onTransactionHistoryClicked(Account account);
    }
}
