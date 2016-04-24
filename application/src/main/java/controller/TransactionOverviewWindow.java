package controller;

import model.Session;
import model.entity.Transaction;
import resource.Dimensions;
import resource.Strings;
import view.TransactionView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The transaction overview window of the application. Read more in the documentation.
 */
public class TransactionOverviewWindow extends Window implements TransactionView.OnTransactionClickedListener {

    private List<Transaction> transactions;

    /**
     * Displays a new transaction overview window based on the given params.
     *
     * @param session      The current session.
     * @param transactions The transaction to display.
     */
    public TransactionOverviewWindow(Session session, List<Transaction> transactions) {
        super(Strings.TRANSACTION_HISTORY_WINDOW_TITLE, session);
        this.transactions = transactions;

        // TODO make it scrollable
        // TODO add an info transaction panel

        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        transactions.forEach(transaction -> {
            container.add(new TransactionView(transaction, this), BorderLayout.CENTER);
        });
        display(Dimensions.TRANSACTION_HISTORY_WINDOW_SIZE.width, Dimensions.TRANSACTION_HISTORY_WINDOW_SIZE.height);
    }

    @Override
    public void onCancelClicked(Transaction transaction) {
        // TODO It will be implemented after the database integration.
        JOptionPane.showMessageDialog(container, Strings.UNAVAILABLE_DIALOG_MESSAGE,
                Strings.UNAVAILABLE_DIALOG_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}
