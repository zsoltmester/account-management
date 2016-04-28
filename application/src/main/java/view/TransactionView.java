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
public class TransactionView extends EntityView {

    private Transaction transaction;

    private OnTransactionClickedListener listener;

    private JTextField idField;
    private JTextField sourceField;
    private JTextField targetField;
    private JTextField amountField;
    private JTextField creationField;
    private JButton cancelButton;

    /**
     * Creates a new info transaction view.
     */
    public TransactionView() {
        this(null, null);
    }

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

        idField = new JTextField(transaction == null ? Strings.TRANSACTION_ID : Long.toString(transaction.getId()));
        processField(idField);

        sourceField = new JTextField(transaction == null ? Strings.TRANSACTION_SOURCE : transaction.getSourceAccount());
        processField(sourceField);

        targetField = new JTextField(transaction == null ? Strings.TRANSACTION_TARGET : transaction.getTargetAccount());
        processField(targetField);

        amountField = new JTextField(transaction == null ? Strings.TRANSACTION_AMOUNT : transaction.getAmount().toString());
        processField(amountField);

        creationField = new JTextField(transaction == null ? Strings.TRANSACTION_CREATION_DATE
                : Configs.TRANSACTION_CREATION_DATE_FORMAT.format(transaction.getCreationDate()));
        processField(creationField);

        if (transaction != null) {
            cancelButton = new JButton(Strings.TRANSACTION_HISTORY_CANCEL_BUTTON_TITLE);
            cancelButton.addActionListener(event -> listener.onCancelClicked(transaction));
            processButton(cancelButton);
        }

        add(Box.createVerticalStrut(Dimensions.GAP.height));
    }

    @Override
    protected void processField(JTextField field) {
        super.processField(field);
        field.setBackground(transaction == null ? Color.BLUE : Color.GREEN);
        add(field);
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
