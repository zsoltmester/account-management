package view;

import model.entity.Transaction;
import resource.Configs;
import resource.Dimensions;
import resource.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * A view can display an account.
 */
public class TransactionView extends JPanel {

    private Transaction transaction;

    private OnTransactionClickedListener listener;

    /**
     * Creates a new transaction view based on the given transaction.
     *
     * @param transaction The transaction to display.
     * @param listener    The transaction click listener.
     */
    public TransactionView(Transaction transaction, OnTransactionClickedListener listener) {
        this.transaction = transaction;
        this.listener = listener;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(Dimensions.GAP.height));
        JTextField idField = new JTextField(Long.toString(transaction.getId()));
        processField(idField);
        JTextField sourceField = new JTextField(transaction.getSourceAccount());
        processField(sourceField);
        JTextField targetField = new JTextField(transaction.getTargetAccount());
        processField(targetField);
        JTextField amountField = new JTextField(transaction.getAmount().toString());
        processField(amountField);
        JTextField creationField = new JTextField(Configs.TRANSACTION_CREATION_DATE_FORMAT.format(transaction.getCreationDate()));
        processField(creationField);
        JButton cancelButton = new JButton(Strings.TRANSACTION_HISTORY_CANCEL_BUTTON_TITLE);
        cancelButton.addActionListener(event -> {
            listener.onCancelClicked(transaction);
        });
        processButton(cancelButton);
        add(Box.createVerticalStrut(Dimensions.GAP.height));
    }

    // TODO code duplication with the AccountView#processField(...)
    private void processField(JTextField field) {
        field.setEnabled(false);
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setMaximumSize(Dimensions.TRANSACTION_COMPONENT_SIZE);
        field.setDisabledTextColor(Color.BLACK);
        add(field);
    }

    // TODO code duplication with the AccountView#processButton(...)
    private void processButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(Dimensions.TRANSACTION_COMPONENT_SIZE);
        add(button);
    }

    /**
     * The listener which handles the transaction click events.
     */
    public interface OnTransactionClickedListener {

        /**
         * Called when the user clicked on the cancel transaction button.
         *
         * @param transaction The associated transaction.
         */
        void onCancelClicked(Transaction transaction);
    }
}
